package com.example.phong.myapplication.activity.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.phong.myapplication.R;
import com.example.phong.myapplication.activity.admin.chitiet_sanpham;
import com.example.phong.myapplication.activity.admin.giohang_admin;
import com.example.phong.myapplication.adapter.AdidasAdapter;
import com.example.phong.myapplication.adapter.UserAdidasAdapter;
import com.example.phong.myapplication.connect;
import com.example.phong.myapplication.model.sanpham;
import com.example.phong.myapplication.until.checkconnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class user_sanpham_adidas extends AppCompatActivity {
    Toolbar toolbarAdidasuser ;
    UserAdidasAdapter adidasAdapteruser ;
    ListView listViewAdidasuser ;
    ArrayList<sanpham> mangadidasuser ;
    int idadidas = 0 ;
    int page = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sanpham_adidas);
        anhxa();
        if(checkconnection.haveNetworkConnection(getApplicationContext()))
        {
            actiontoolbar();
            getData(page);
            getIDHangsx();
            loadmoredata();
        }else
        {
            checkconnection.showToast_short(getApplicationContext(),"kiem tra lai ket noi");

        }

    }

    private void loadmoredata() {
        listViewAdidasuser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),chitiet_sanpham.class);
                intent.putExtra("idhangsx1",mangadidasuser.get(position));
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang :
                Intent intent = new Intent(getApplicationContext(),giohang_admin.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void getIDHangsx() {
        idadidas = getIntent().getIntExtra("idhangsx",-1 );
    }

    private void getData(int Page) {
        Context context;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = connect.sanphamadidas + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int idsanpham = 0 ;
                String tensanpham = "";
                String hinhanh = "";
                Integer giasanpham = 0;
                int id = 0 ;
                if (response != null ){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0 ; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("IDsanpham");
                            tensanpham = jsonObject.getString("TenSP");
                            hinhanh = jsonObject.getString("hinhanh");
                            giasanpham = jsonObject.getInt("Gia");
                            id = jsonObject.getInt("ID");
                            mangadidasuser.add(new sanpham(idsanpham,tensanpham,hinhanh,giasanpham,id));
                            adidasAdapteruser.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("ID",String.valueOf(idadidas));

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void actiontoolbar() {
        setSupportActionBar(toolbarAdidasuser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAdidasuser.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbarAdidasuser = findViewById(R.id.toolbarspAdidasuser);
        listViewAdidasuser = findViewById(R.id.lstspAdidasuser);
        mangadidasuser = new ArrayList<>();
        adidasAdapteruser = new UserAdidasAdapter(getApplicationContext(),mangadidasuser);
        listViewAdidasuser.setAdapter(adidasAdapteruser);
    }
}

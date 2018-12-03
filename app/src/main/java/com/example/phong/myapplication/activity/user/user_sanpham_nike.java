package com.example.phong.myapplication.activity.user;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.example.phong.myapplication.activity.admin.sanphamNike;
import com.example.phong.myapplication.activity.admin.them_sanpham_nike;
import com.example.phong.myapplication.adapter.UsernikeAdapter;
import com.example.phong.myapplication.adapter.nikeAdapter;
import com.example.phong.myapplication.connect;
import com.example.phong.myapplication.model.sanpham;
import com.example.phong.myapplication.until.checkconnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class user_sanpham_nike extends AppCompatActivity {
    Toolbar toolbarsanphamnikeuser;
    ListView listViewsanphamnikeuser;
    UsernikeAdapter sanphamnikeAdapteruser;
    ArrayList<sanpham> mangsanpham;
    int idnike = 0;
    int page = 1;
    View footerview;
    boolean isloading = false;
    boolean limitdata = false;
    mHanler mHanler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sanpham_nike);
        anhxa();
        if (checkconnection.haveNetworkConnection(getApplicationContext()))
        {
            getidhangsx();
            actiontoolbar();
            getdata(page);
            Loadmoredata();
        } else
            {
            checkconnection.showToast_short(getApplicationContext(), "ban hay kiem tra lai internet");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), giohang_admin.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void Loadmoredata() {
        listViewsanphamnikeuser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), chitiet_sanpham.class);
                intent.putExtra("idhangsx1", mangsanpham.get(position));
                startActivity(intent);

            }
        });
        listViewsanphamnikeuser.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && isloading == false && limitdata == false) {
                    isloading = true;
                    Threaddata threaddata = new Threaddata();
                    threaddata.start();
                }
            }
        });
    }

    private void getdata(int Page) {
        Context context;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = connect.sanphamnike + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int idsanpham = 0;
                String tensp = "";
                String hinhanh = "";
                int gia = 0;
                int id = 0;
                if (response != null && response.length() != 2) {
                    listViewsanphamnikeuser.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            idsanpham = jsonObject.getInt("IDsanpham");
                            tensp = jsonObject.getString("TenSP");
                            hinhanh = jsonObject.getString("hinhanh");
                            gia = jsonObject.getInt("Gia");
                            id = jsonObject.getInt("ID");
                            mangsanpham.add(new sanpham(idsanpham, tensp, hinhanh, gia, id));
                            sanphamnikeAdapteruser.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    limitdata = true;
                    listViewsanphamnikeuser.removeFooterView(footerview);
                    checkconnection.showToast_short(getApplicationContext(), "da het du lieu");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("ID", String.valueOf(idnike));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void actiontoolbar() {
        setSupportActionBar(toolbarsanphamnikeuser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarsanphamnikeuser.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getidhangsx() {
        idnike = getIntent().getIntExtra("idhangsx", -1);
    }

    private void anhxa() {
        toolbarsanphamnikeuser = findViewById(R.id.toolbarspnikeuser);
        listViewsanphamnikeuser = findViewById(R.id.lstspnikeuser);
        mangsanpham = new ArrayList<>();
        sanphamnikeAdapteruser = new UsernikeAdapter(getApplicationContext(), mangsanpham);
        listViewsanphamnikeuser.setAdapter(sanphamnikeAdapteruser);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar, null);
        mHanler = new mHanler();
    }

    public class mHanler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    listViewsanphamnikeuser.addFooterView(footerview);
                    break;
                case 1:
                    getdata(++page);
                    isloading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class Threaddata extends Thread {
        @Override
        public void run() {
            mHanler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHanler.obtainMessage(1);
            mHanler.sendMessage(message);
            super.run();
        }
    }
}


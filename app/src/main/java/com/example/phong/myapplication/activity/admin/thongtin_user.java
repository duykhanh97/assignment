package com.example.phong.myapplication.activity.admin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.phong.myapplication.R;
import com.example.phong.myapplication.connect;
import com.example.phong.myapplication.until.checkconnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class thongtin_user extends AppCompatActivity {

    EditText txttenkh , txtemail , txtsdt ;
    Button btntrove , btnxacnhan ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_user);
        anhxa();
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (checkconnection.haveNetworkConnection(getApplicationContext())){
            eventbutton();
        }
        else {
            checkconnection.showToast_short(getApplicationContext(),"hay kiem tra lai ket noi ");
        }

    }

    private void eventbutton() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = txttenkh.getText().toString().trim();
                final String sdt = txtsdt.getText().toString().trim();
                final String email = txtemail.getText().toString().trim();
                if(ten.length()>0 && sdt.length()>0 && email.length()>0){
                    final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    final StringRequest stringRequest = new StringRequest(Request.Method.POST, connect.thongtinkhachhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String IDkh) {
                            checkconnection.showToast_short(getApplicationContext(),"them thanh cong");
                            if (Integer.parseInt(IDkh)> 0){
                                Context context;
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, connect.chitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1")){
                                            admin_trangchu.manggiohang.clear();
                                            checkconnection.showToast_short(getApplicationContext(),"ban da them du lieu gio hang thanh cong ");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0 ; i < admin_trangchu.manggiohang.size();i++){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madh",IDkh);
                                                jsonObject.put("masp",admin_trangchu.manggiohang.get(i).getIdsp());
                                                jsonObject.put("tensp",admin_trangchu.manggiohang.get(i).getTensp());
                                                jsonObject.put("gia",admin_trangchu.manggiohang.get(i).getGia());
                                                jsonObject.put("soluong",admin_trangchu.manggiohang.get(i).getSoluong());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap = new HashMap<>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("tenkh",ten);
                            hashMap.put("sdt",sdt);
                            hashMap.put("email",email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else
                {
                    checkconnection.showToast_short(getApplicationContext(),"hay kiem tra lai du lieu ");
                }
            }
        });
    }

    private void anhxa() {
        txttenkh = findViewById(R.id.edittexttenkhachhang);
        txtemail = findViewById(R.id.edittextEmail);
        txtsdt = findViewById(R.id.edittextsdtkhachhang);
        btntrove = findViewById(R.id.btntrove);
        btnxacnhan=findViewById(R.id.btnxacnhan);
    }
}

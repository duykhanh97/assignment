package com.example.phong.myapplication;

//lam ASYNTASK
//lam DangKY
//lam CUSTOM VIEW , menu hien san pham


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.phong.myapplication.activity.admin.admin_trangchu;
import com.example.phong.myapplication.activity.user.user_trangchu;
import com.example.phong.myapplication.until.checkconnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class dangnhap extends AppCompatActivity {

    Button btnDN , btnDKy , btnRegister ;
    EditText txtDN , txtMK ;
    String user , pass ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);
        anhxa();
        if (checkconnection.haveNetworkConnection(getApplicationContext()))
        {
            controlbutton();
        }
        else {
            checkconnection.showToast_short(getApplicationContext(),"kiem tra lai internet");
        }
    }

    private void controlbutton() {

        //loi huy de tro ve admin trang chu
        btnDKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dangnhap.this ,dangky.class);
                startActivity(intent);
            }
        });

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             user = txtDN.getText().toString().trim();
             pass = txtMK.getText().toString().trim();

                if (user.length()==0 && pass.length()==0)
                {
                    Toast.makeText(dangnhap.this,"khong co user va pass " , Toast.LENGTH_LONG).show();
                }
                else
                {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, connect.dangnhap, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Toast.makeText(dangnhap.this, response.toString(), Toast.LENGTH_LONG).show();
                            try {

                                JSONObject jsonObject = new JSONObject(response);
                                String tendn = jsonObject.getString("user");
                                String passdn = jsonObject.getString("pass");
                                String quyen = jsonObject.getString("quyen");

                                if (tendn.toString() == "null" && passdn.toString() == "null") {
                                    Toast.makeText(dangnhap.this, "sai user va pass ", Toast.LENGTH_LONG).show();
                                } else {
                                    if(quyen.equals("1")){
                                    Toast.makeText(dangnhap.this, "xin chao " + tendn, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(dangnhap.this,admin_trangchu.class);
                                    startActivity(intent);}
                                    else
                                    {
                                        Toast.makeText(dangnhap.this, "xin chao " + tendn, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(dangnhap.this,user_trangchu.class);
                                        startActivity(intent);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(dangnhap.this,"khong ket noi duoc ", Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            params.put("user" , user);
                            params.put("pass",pass);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);

                }
            }
        });
    }

    private void anhxa() {

        txtDN = findViewById(R.id.txtdangnhap);
        txtMK = findViewById(R.id.txtmatkhau);
        btnDN= findViewById(R.id.btnDN);
        btnDKy = findViewById(R.id.btnDKy);

    }

}

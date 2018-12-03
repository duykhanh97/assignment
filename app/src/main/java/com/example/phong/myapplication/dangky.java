package com.example.phong.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

import java.util.HashMap;
import java.util.Map;

public class dangky extends AppCompatActivity {
    EditText txtTen , txtTenDN , txtMK , txtSDT ;
    Button btndky ,btnthoat ;
    String ten ,tendn , mk ,sdt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        anhxa();
        controllbutton();
    }

    private void anhxa ()
    {
        txtTen = findViewById(R.id.txtTen);
        txtTenDN = findViewById(R.id.txtTenDN);
        txtMK = findViewById(R.id.txtMK);
        txtSDT = findViewById(R.id.txtSDT);

        btndky = findViewById(R.id.btnDK);
        btnthoat = findViewById(R.id.btnthoat);

    }

    private void controllbutton ()
    {
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(dangky.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("bạn có muốn thoát !");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });

                builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        btndky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tendn = txtTenDN.getText().toString().trim();
                mk = txtMK.getText().toString().trim();
                ten = txtTen.getText().toString().trim();
                sdt = txtSDT.getText().toString().trim();

                if(ten.length()==0 && tendn.length()==0 && mk.length()==0 && sdt.length()==0)
                {
                    Toast.makeText(dangky.this, "kiem tra lai thong tin " , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, connect.dangky, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Toast.makeText(dangky.this,response.toString(),Toast.LENGTH_SHORT).show();
                            if (response.trim().equals("thanhcong")) {

                                Toast.makeText(dangky.this, "Dang ky thanh cong", Toast.LENGTH_LONG).show();
                                String action;
                                Intent intent = new Intent(dangky.this,dangnhap.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(dangky.this, "Loi them!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(dangky.this,"Loi",Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            params.put("Ten",ten);
                            params.put("tenDN",tendn);
                            params.put("matkhau",mk);
                            params.put("SDT",sdt);
                            return params;
                        }

                    };
                    requestQueue.add(stringRequest);
                }



            }
        });
    }
}

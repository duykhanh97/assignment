package com.example.phong.myapplication.activity.admin;

import android.content.Context;
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
import com.example.phong.myapplication.R;
import com.example.phong.myapplication.connect;

import java.util.HashMap;
import java.util.Map;

public class them_sanpham_nike extends AppCompatActivity {
    EditText editTexttensanpham , editTexthinhanh , editTextgia;
    Button btnthem , btnhuy ;
    String tensanphamnike , image , gia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sanpham_nike);
        anhxa();
        controlbutton();
    }

    private void controlbutton() {
    btnhuy.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),sanphamNike.class);
            startActivity(intent);
        }
    });
    btnthem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tensanphamnike = editTexttensanpham.getText().toString().trim();
            image = editTexthinhanh.getText().toString().trim();
            gia = editTextgia.getText().toString().trim();
            if (tensanphamnike.length() == 0 && image.length() == 0 && gia.length() == 0) {
                Toast.makeText(them_sanpham_nike.this, "chưa thêm thông tin vào", Toast.LENGTH_SHORT).show();

            } else {
                Context context;
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, connect.themsanphamnike, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("thanhcong")) {
                            Toast.makeText(them_sanpham_nike.this, "thêm thành công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(them_sanpham_nike.this, "khong thêm được", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(them_sanpham_nike.this, "Loi them!", Toast.LENGTH_LONG).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("TenSP",tensanphamnike);
                        params.put("hinhanh",image);
                        params.put("Gia",gia);
                        return params;
                    }
                };requestQueue.add(stringRequest);

            }

        }
    });

    }

    private void anhxa() {
        editTexttensanpham = findViewById(R.id.txtthemsanpham_nike);
        editTexthinhanh = findViewById(R.id.txtthemhinhanh_sanpham_nike);
        editTextgia = findViewById(R.id.txtgia_sanpham_nike);
        btnthem = findViewById(R.id.btndongythem_sanpham_nike);
        btnhuy = findViewById(R.id.btnhuythem_sanpham_nike);
    }
}

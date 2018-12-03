package com.example.phong.myapplication.activity.admin;

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
import com.example.phong.myapplication.model.sanpham;

import java.util.HashMap;
import java.util.Map;

public class update_sanpham_nike extends AppCompatActivity {
    EditText editupdateten , editupdatehinhanh ,editupdategia ;
    Button btnthem , btnhuy ;
    String tenhangsanxuat , image , gia;
    int id=0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sanpham_nike);
        anhxa();
        Intent intent = getIntent();
        sanpham sanpham1 = (sanpham) intent.getSerializableExtra("idhangsx");
        id = sanpham1.getIdsp();
        editupdateten.setText(sanpham1.getTensp());
        editupdatehinhanh.setText(sanpham1.getHinhanh());
        editupdategia.setText(sanpham1.getGia().toString()  );
        controlbutton();
    }

    private void controlbutton() {
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(update_sanpham_nike.this,sanphamNike.class);
                startActivity(intent);
            }
        });

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenhangsanxuat = editupdateten.getText().toString().trim();
                image = editupdatehinhanh.getText().toString().trim();
                gia = editupdategia.getText().toString().trim();
                if (tenhangsanxuat.length() == 0 && image.length() == 0) {
                    Toast.makeText(update_sanpham_nike.this, "chưa thêm thông tin vào", Toast.LENGTH_SHORT).show();

                } else {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, connect.updatesanphamnike, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("thanhcong")) {
                                Toast.makeText(update_sanpham_nike.this, "update thành công", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(update_sanpham_nike.this, "khong thêm được", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(update_sanpham_nike.this, "Loi them!", Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            params.put("IDsanpham",String.valueOf(id));
                            params.put("TenSP",tenhangsanxuat);
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
        editupdateten = findViewById(R.id.txtupdate_ten_sanpham_nike);
        editupdatehinhanh = findViewById(R.id.txtupdate_hinhanh_sanpham_nike);
        editupdategia = findViewById(R.id.txtupdate_gia_sanpham_nike);
        btnthem = findViewById(R.id.btn_dongy_update_sanpham_nike);
        btnhuy = findViewById(R.id.btn_huy_update_sanpham_nike);
    }
}

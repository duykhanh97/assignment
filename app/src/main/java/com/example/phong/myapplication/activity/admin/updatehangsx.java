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
import com.example.phong.myapplication.model.hangsanxuatt;

import java.util.HashMap;
import java.util.Map;

public class updatehangsx extends AppCompatActivity {
    EditText editTexthangsx , editTexthinhanh ;
    Button btnthem , btnhuy ;
    String tenhangsanxuat , image;
    int id=0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatehangsx);

        Intent intent = getIntent();
        hangsanxuatt hangsxtt = (hangsanxuatt) intent.getSerializableExtra("idhangsx");

        editTexthangsx = findViewById(R.id.updatehangsx);
        editTexthinhanh = findViewById(R.id.updateimage);
        btnthem = findViewById(R.id.btnupdate);
        btnhuy = findViewById(R.id.btnhuyupdate);

        id = hangsxtt.getID();
        editTexthangsx.setText(hangsxtt.getTenhangsanxuat());
        editTexthinhanh.setText(hangsxtt.getHinhanh());

        controlbutton();
    }

    private void controlbutton() {
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updatehangsx.this,admin_trangchu.class);
                startActivity(intent);
            }
        });

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenhangsanxuat = editTexthangsx.getText().toString().trim();
                image = editTexthinhanh.getText().toString().trim();
                if (tenhangsanxuat.length() == 0 && image.length() == 0) {
                    Toast.makeText(updatehangsx.this, "chưa thêm thông tin vào", Toast.LENGTH_SHORT).show();

                } else {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, connect.updatehangsanxuat, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("thanhcong")) {
                                Toast.makeText(updatehangsx.this, "update thành công", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(updatehangsx.this, "khong thêm được", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(updatehangsx.this, "Loi them!", Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            params.put("ID",String.valueOf(id));
                            params.put("tenhangsanxuat",tenhangsanxuat);
                            params.put("image",image);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
    }
}

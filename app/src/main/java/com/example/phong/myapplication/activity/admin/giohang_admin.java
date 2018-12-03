package com.example.phong.myapplication.activity.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phong.myapplication.R;
import com.example.phong.myapplication.adapter.giohangAdapter;
import com.example.phong.myapplication.model.giohang;
import com.example.phong.myapplication.until.checkconnection;

import java.text.DecimalFormat;

public class giohang_admin extends AppCompatActivity {
    ListView listViewgiohang ;
    TextView txtthongbao;
    static TextView txttongtien ;
    Button btnthanhtoan , btntieptucmua ;
    Toolbar toolbargiohang ;
    giohangAdapter giohangAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang);
        anhxa();
        actiontoolbar();
        checkdata();
        EventUltil();
        chonxoaitemlistview();
        eventbutton();

    }

    private void eventbutton() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),admin_trangchu.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (admin_trangchu.manggiohang.size()>0){
                    Intent intent = new Intent(getApplicationContext(),thongtin_user.class);
                    startActivity(intent);
                }
                else {
                    checkconnection.showToast_short(getApplicationContext(),"giỏ hàng của bạn chưa có sản phẩm nào");
                }
            }
        });
    }

    private void chonxoaitemlistview() {
        listViewgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(giohang_admin.this);
                builder.setTitle("xác nhận xóa sản phảm");
                builder.setMessage("bạn có muốn chắc xóa sản phẩm này");
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (admin_trangchu.manggiohang.size()<=0)
                        {
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else {
                            admin_trangchu.manggiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            EventUltil();
                            if (admin_trangchu.manggiohang.size()<=0){
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else {
                                txtthongbao.setVisibility(View.INVISIBLE);
                                admin_trangchu.manggiohang.remove(position);
                                giohangAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
                builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        EventUltil();
                    }
                });
                builder.show();
                return false;
            }
        });
    }

    public static void EventUltil() {
        long tongtien = 0 ;
        for (int i =0 ; i<admin_trangchu.manggiohang.size();i++){
            tongtien += admin_trangchu.manggiohang.get(i).getGia();

        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien)+"Đ");
    }

    private void checkdata() {
        if (admin_trangchu.manggiohang.size()<=0)
        {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            listViewgiohang.setVisibility(View.INVISIBLE);
        }else {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            listViewgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void actiontoolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        listViewgiohang = findViewById(R.id.lstgiohang);
        txtthongbao = findViewById(R.id.textViewthongbao);
        txttongtien = findViewById(R.id.textViewtongtien);
        btnthanhtoan = findViewById(R.id.btnthanhtoangiohang);
        btntieptucmua = findViewById(R.id.btntieptucmuahang);
        toolbargiohang = findViewById(R.id.toolbargiohang);
        giohangAdapter = new giohangAdapter(giohang_admin.this,admin_trangchu.manggiohang);
        listViewgiohang.setAdapter(giohangAdapter);

    }
}

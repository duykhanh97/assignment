package com.example.phong.myapplication.activity.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.phong.myapplication.R;
import com.example.phong.myapplication.model.giohang;
import com.example.phong.myapplication.model.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class chitiet_sanpham extends AppCompatActivity {
    int idsanpham =0 ;
    String tenchitiet = "" ;
    int giachitiet = 0 ;
    String hinhanhchitiet = "" ;
    int id = 0 ;

        Toolbar toolbarchitiet ;
        ImageView imageViewchitiet ;
        TextView txtten , txtgia ;
        Spinner  spinnersize ;
        Button btndatmua ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_sanpham);
        anhxa();
        acctionbar();
        getthongtin();
        CatheventSpinnersize();
        Eventbutton();



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

    private void Eventbutton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(admin_trangchu.manggiohang.size()>0){
                    int sl = Integer.parseInt(spinnersize.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i = 0 ; i< admin_trangchu.manggiohang.get(i).getIdsp();i++){
                        admin_trangchu.manggiohang.get(i).setSoluong(admin_trangchu.manggiohang.get(i).getSoluong()+sl);
                        admin_trangchu.manggiohang.get(i).setGia(giachitiet * admin_trangchu.manggiohang.get(i).getSoluong());
                        exists = true;
                    }
                    if (exists == false){
                        int soluong =Integer.parseInt( spinnersize .getSelectedItem().toString());
                        long giamoi = soluong * giachitiet ;
                        admin_trangchu.manggiohang.add(new giohang(idsanpham,tenchitiet,giamoi,hinhanhchitiet,soluong));
                    }
                }else {
                    int soluong =Integer.parseInt( spinnersize .getSelectedItem().toString());
                    long giamoi = soluong * giachitiet ;
                    admin_trangchu.manggiohang.add(new giohang(idsanpham,tenchitiet,giamoi,hinhanhchitiet,soluong));
                }
                Intent intent = new Intent(getApplicationContext(), giohang_admin.class);
                startActivity(intent);
            }
        });
    }

    private void CatheventSpinnersize() {
        Integer[] soluong = new Integer[]{1,2,3,4,5};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinnersize.setAdapter(arrayAdapter);
    }

    private void getthongtin() {

        sanpham sanpham = (com.example.phong.myapplication.model.sanpham) getIntent().getSerializableExtra("idhangsx1");
        idsanpham = sanpham.getIdsp();
        tenchitiet = sanpham.getTensp();
        giachitiet = sanpham.getGia();
        hinhanhchitiet = sanpham.getHinhanh();
        id = sanpham.getIdhangsx();

        txtten.setText(tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá : " + decimalFormat.format(giachitiet)+"Đ");
        Picasso.with(getApplicationContext()).load(hinhanhchitiet)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imageViewchitiet);
    }

    private void acctionbar() {
    setSupportActionBar(toolbarchitiet);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

    }

    private void anhxa() {
        toolbarchitiet = findViewById(R.id.toolbarchitietsanpham);
        imageViewchitiet = findViewById(R.id.imgchitietsanpham);
        txtten = findViewById(R.id.textviewTenchitietsanpham);
        txtgia = findViewById(R.id.textviewgiachitietsanpham);
        spinnersize = findViewById(R.id.spinerSize);
        btndatmua = findViewById(R.id.buttondatmua);
    }
}

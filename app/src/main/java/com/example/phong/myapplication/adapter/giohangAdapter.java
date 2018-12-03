package com.example.phong.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phong.myapplication.R;
import com.example.phong.myapplication.activity.admin.admin_trangchu;
import com.example.phong.myapplication.activity.admin.giohang_admin;
import com.example.phong.myapplication.model.giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class giohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<giohang> arraygiohang;

    public giohangAdapter(Context context, ArrayList<giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arraygiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txttengiohang , txtgiagiohang ;
        public ImageView imggiohang ;
        public Button btntru , btnhienthi , btncong ;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder1 = null ;
         if (convertView == null){
             viewHolder1 = new ViewHolder() ;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_giohang,null);
            viewHolder1.txttengiohang = convertView.findViewById(R.id.textViewtengiohang);
            viewHolder1.txtgiagiohang = convertView.findViewById(R.id.textViewgiamonhang);
            viewHolder1.imggiohang = convertView.findViewById(R.id.imagegiohang);
            viewHolder1.btntru = convertView.findViewById(R.id.btntru);
             viewHolder1.btncong = convertView.findViewById(R.id.btncong);
             viewHolder1.btnhienthi = convertView.findViewById(R.id.btnhienthi);
             convertView.setTag(viewHolder1);
         }else {
             viewHolder1 = (ViewHolder) convertView.getTag();
         }
         giohang giohang = (com.example.phong.myapplication.model.giohang) getItem(position);
         viewHolder1.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder1.txtgiagiohang.setText(decimalFormat.format(giohang.getGia())+"Đ");
        Picasso.with(context).load(giohang.getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder1.imggiohang);
        viewHolder1.btnhienthi.setText(giohang.getSoluong()+ "");
        int sl = Integer.parseInt(viewHolder1.btnhienthi.getText().toString());
        if (sl >=10){
            viewHolder1.btncong.setVisibility(View.INVISIBLE);
            viewHolder1.btntru.setVisibility(View.VISIBLE);
        }else if (sl<=1)
        {
            viewHolder1.btntru.setVisibility(View.INVISIBLE);

        }else if (sl >=1){
            viewHolder1.btntru.setVisibility(View.VISIBLE);
            viewHolder1.btncong.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder1;
        viewHolder1.btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btnhienthi.getText().toString())+1;
                int slht = admin_trangchu.manggiohang.get(position).getSoluong();
                long giaht = admin_trangchu.manggiohang.get(position).getGia();
                admin_trangchu.manggiohang.get(position).setSoluong(slmoinhat);
                long giamoinhat = (giaht * slmoinhat)/slht;
                admin_trangchu.manggiohang.get(position).setGia(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat)+"Đ");
                giohang_admin.EventUltil();
                if (slmoinhat > 9){
                    finalViewHolder.btncong.setVisibility(View.INVISIBLE);
                    finalViewHolder.btntru.setVisibility(View.VISIBLE);
                    finalViewHolder.btnhienthi.setText(String.valueOf(slmoinhat));
                }else {
                    finalViewHolder.btntru.setVisibility(View.VISIBLE);
                    finalViewHolder.btncong.setVisibility(View.VISIBLE);
                    finalViewHolder.btnhienthi.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder1.btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btnhienthi.getText().toString())-1;
                int slht = admin_trangchu.manggiohang.get(position).getSoluong();
                long giaht = admin_trangchu.manggiohang.get(position).getGia();
                admin_trangchu.manggiohang.get(position).setSoluong(slmoinhat);
                long giamoinhat = (giaht * slmoinhat)/slht;
                admin_trangchu.manggiohang.get(position).setGia(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat)+"Đ");
                giohang_admin.EventUltil();
                if (slmoinhat < 2 ){
                    finalViewHolder.btntru.setVisibility(View.INVISIBLE);
                    finalViewHolder.btncong.setVisibility(View.VISIBLE);
                    finalViewHolder.btnhienthi.setText(String.valueOf(slmoinhat));
                }else {
                    finalViewHolder.btncong.setVisibility(View.VISIBLE);
                    finalViewHolder.btntru.setVisibility(View.VISIBLE);
                    finalViewHolder.btnhienthi.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return convertView;
    }
}

package com.example.phong.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phong.myapplication.R;
import com.example.phong.myapplication.model.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdidasAdapter extends BaseAdapter {
    Context context ;
    ArrayList<sanpham> arrayAdidas ;

    public AdidasAdapter (Context context, ArrayList<sanpham> arrayAdidas) {
        this.context = context;
        this.arrayAdidas = arrayAdidas;
    }

    @Override
    public int getCount() {
        return arrayAdidas.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayAdidas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Viewholder{
        public TextView textViewtenAdidas , textViewgiaAdidas ;
        public ImageView imgAdidas ;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdidasAdapter.Viewholder viewholder = null;
        if (convertView == null)
        {
            viewholder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_sanpham_adidas,null);
            viewholder.textViewtenAdidas = convertView.findViewById(R.id.txttenspAdidas);
            viewholder.textViewgiaAdidas = convertView.findViewById(R.id.txtgiaspAdidas);
            viewholder.imgAdidas = convertView.findViewById(R.id.imgsanphamAdidas);
            convertView.setTag(viewholder);
        }else {
            viewholder = (Viewholder) convertView.getTag();
        }
        sanpham sanpham = (sanpham) getItem(position);
        viewholder.textViewtenAdidas.setText(sanpham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.textViewgiaAdidas.setText("Giá :" + decimalFormat.format(sanpham.getGia())+"Đ");
        Picasso.with(context).load(sanpham.getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewholder.imgAdidas);
        return convertView;
    }
}

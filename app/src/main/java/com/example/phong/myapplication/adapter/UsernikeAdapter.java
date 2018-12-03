package com.example.phong.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phong.myapplication.R;
import com.example.phong.myapplication.activity.admin.update_sanpham_nike;
import com.example.phong.myapplication.model.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UsernikeAdapter extends BaseAdapter {
    Context context;
    ArrayList<sanpham> arraynikeAdapter;

    public UsernikeAdapter(Context context, ArrayList<sanpham> arraynikeAdapter) {
        this.context = context;
        this.arraynikeAdapter = arraynikeAdapter;
    }
    @Override
    public int getCount() {
        return arraynikeAdapter.size();
    }

    @Override
    public Object getItem(int position) {
        return arraynikeAdapter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Viewholder{
        public TextView textViewtennike , textViewgianike ;
        public ImageView imgnike;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        UsernikeAdapter.Viewholder viewholder = null;
        if (convertView == null)
        {
            viewholder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_sanphamnikeuser,null);
            viewholder.textViewtennike = convertView.findViewById(R.id.txttenspnikeuser);
            viewholder.textViewgianike = convertView.findViewById(R.id.txtgiaspnikeuser);
            viewholder.imgnike = convertView.findViewById(R.id.imgsanphamnikeuser);
            convertView.setTag(viewholder);
        }else {
            viewholder = (UsernikeAdapter.Viewholder) convertView.getTag();
        }
        sanpham sanpham = (sanpham) getItem(position);
        viewholder.textViewtennike.setText(sanpham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.textViewgianike.setText("Giá :" + decimalFormat.format(sanpham.getGia())+"Đ");
        Picasso.with(context).load(sanpham.getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewholder.imgnike);
        return convertView;
    }
}

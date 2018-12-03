package com.example.phong.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.phong.myapplication.R;
import com.example.phong.myapplication.activity.admin.update_sanpham_nike;
import com.example.phong.myapplication.activity.admin.updatehangsx;
import com.example.phong.myapplication.connect;
import com.example.phong.myapplication.model.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class nikeAdapter extends BaseAdapter {
    Context context ;
    ArrayList<sanpham> arraynike ;

    public nikeAdapter(Context context, ArrayList<sanpham> arraynike) {
        this.context = context;
        this.arraynike = arraynike;
    }

    @Override
    public int getCount() {
        return arraynike.size();
    }

    @Override
    public Object getItem(int position) {
        return arraynike.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Viewholder{
        public TextView textViewtennike , textViewgianike ;
        public ImageView imgnike , imgupdate , imgdelete;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder viewholder = null;
        if (convertView == null)
        {
            viewholder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_sanpham_nike,null);
            viewholder.textViewtennike = convertView.findViewById(R.id.txttenspnike);
            viewholder.textViewgianike = convertView.findViewById(R.id.txtgiaspnike);
            viewholder.imgnike = convertView.findViewById(R.id.imgsanphamnike);
            viewholder.imgupdate = convertView.findViewById(R.id.updatesanphamnike);
            viewholder.imgdelete = convertView.findViewById(R.id.deletesanphamnike);
            convertView.setTag(viewholder);
        }else {
            viewholder = (Viewholder) convertView.getTag();
        }
        sanpham sanpham = (sanpham) getItem(position);
        viewholder.textViewtennike.setText(sanpham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.textViewgianike.setText("Giá :" + decimalFormat.format(sanpham.getGia())+"Đ");
        Picasso.with(context).load(sanpham.getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewholder.imgnike);

        viewholder.imgupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,update_sanpham_nike.class);
                intent.putExtra("idhangsx",arraynike.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        viewholder.imgdelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });


        return convertView;


    }
}

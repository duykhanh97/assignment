package com.example.phong.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phong.myapplication.R;
import com.example.phong.myapplication.activity.admin.chitiet_sanpham;
import com.example.phong.myapplication.model.sanpham;
import com.example.phong.myapplication.until.checkconnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class sanphamAdapter extends RecyclerView.Adapter<sanphamAdapter.ItemHolder> {
    Context context ;
    ArrayList<sanpham> arraysanpham ;

    public sanphamAdapter(Context context, ArrayList<sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_sanphan_moinhat,null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder( ItemHolder itemHolder, int i) {
        sanpham sanpham = arraysanpham.get(i);
        itemHolder.txttensanpham.setText(sanpham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        itemHolder.txtgiasanpham.setText("Giá :" + decimalFormat.format(sanpham.getGia())+"Đ");
        Picasso.with(context).load(sanpham.getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(itemHolder.imghinhsanpham);
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham ;
        public TextView txttensanpham , txtgiasanpham;

        public ItemHolder( View itemView) {
            super(itemView);
            imghinhsanpham = itemView.findViewById(R.id.imgsanphammoinhat);
            txttensanpham = itemView.findViewById(R.id.textviewtensanpham);
            txtgiasanpham = itemView.findViewById(R.id.textviewgiasanpham);

            imghinhsanpham.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, chitiet_sanpham.class);
                    intent.putExtra("idhangsx1", arraysanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    checkconnection.showToast_short(context, arraysanpham.get(getPosition()).getTensp());
                    context.startActivity(intent);
                }
            });

        }


    }
}

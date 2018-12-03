package com.example.phong.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.phong.myapplication.R;
import com.example.phong.myapplication.activity.admin.updatehangsx;
import com.example.phong.myapplication.connect;
import com.example.phong.myapplication.model.hangsanxuatt;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserhangsxAdapter extends BaseAdapter {
    ArrayList<hangsanxuatt> arrayhangsxAdapter;
    Context context;
    int id= 0;

    public UserhangsxAdapter(ArrayList<hangsanxuatt> arrayhangsxAdapter, Context context) {
        this.arrayhangsxAdapter = arrayhangsxAdapter;
        this.context = context;

    }
    @Override
    public int getCount() {
        return arrayhangsxAdapter.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayhangsxAdapter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class viewhold
    {
        public TextView txthangsx ;
        public ImageView imagehangsx ;


    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        UserhangsxAdapter.viewhold viewhold = null;
        if (view == null) {
            viewhold = new viewhold();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_hangsx_user, null);
            viewhold.txthangsx = view.findViewById(R.id.txthangsxuser);
            viewhold.imagehangsx = view.findViewById(R.id.imagehangsxuser);
            view.setTag(viewhold);
        } else {
            viewhold = (UserhangsxAdapter.viewhold) view.getTag();
        }
        hangsanxuatt hangsanxuatt = (com.example.phong.myapplication.model.hangsanxuatt) getItem(i);
        viewhold.txthangsx.setText(hangsanxuatt.getTenhangsanxuat());
        Picasso.with(context).load(hangsanxuatt.getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewhold.imagehangsx);

        return view;
    }

}


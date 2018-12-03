package com.example.phong.myapplication.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.phong.myapplication.dangky;
import com.example.phong.myapplication.model.hangsanxuatt;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class hangsxAdapter extends BaseAdapter {
    ArrayList<hangsanxuatt> arrayListhangsx ;
    Context context;
    int id =0 ;




    public hangsxAdapter(ArrayList<hangsanxuatt> arrayListhangsx, Context context) {
        this.arrayListhangsx = arrayListhangsx;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListhangsx.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListhangsx.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class viewhold
    {

        public TextView txthangsx ;
        public ImageView imagehangsx , imageViewupdatehangsx , imageViewdeletehangsx  ;

    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        hangsxAdapter.viewhold viewhold = null ;
        if(view == null )
        {
            viewhold = new viewhold();

            view = LayoutInflater.from(context).inflate(R.layout.dong_listview_hangsx, viewGroup, false);
            viewhold.txthangsx = view.findViewById(R.id.txthangsx);
            viewhold.imagehangsx=view.findViewById(R.id.imagehangsx);
            viewhold.imageViewupdatehangsx = view.findViewById(R.id.updatehangsx);
            //viewhold.imageViewdeletehangsx = view.findViewById(R.id.deletehangsx);
            view.setTag(viewhold);
        }else
        {
            viewhold = (hangsxAdapter.viewhold) view.getTag();
        }
        hangsanxuatt hangsanxuatt = (com.example.phong.myapplication.model.hangsanxuatt) getItem(i);
        viewhold.txthangsx.setText(hangsanxuatt.getTenhangsanxuat());
        Picasso.with(context).load(hangsanxuatt.getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewhold.imagehangsx);

        viewhold.imageViewupdatehangsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,updatehangsx.class);
                intent.putExtra("idhangsx",arrayListhangsx.get(i));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        //code sai khong xoa dc
        viewhold.imageViewdeletehangsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, connect.xoahangsanxuat, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("thanhcong"))
                        {
                            Toast.makeText(context,"xoa thanh cong",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(context,"khong xoa duoc",Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"loi",Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("ID" , String.valueOf(id));
                        return params;
                    }
                };
            }
        });

        return view;
    }


}

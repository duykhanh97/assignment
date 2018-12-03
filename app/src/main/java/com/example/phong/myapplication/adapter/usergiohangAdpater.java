package com.example.phong.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.phong.myapplication.model.giohang;

import java.util.ArrayList;

public class usergiohangAdpater extends BaseAdapter {
    Context context;
    ArrayList<giohang> arraygiohang;

    public usergiohangAdpater(Context context, ArrayList<giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<giohang> getArraygiohang() {
        return arraygiohang;
    }

    public void setArraygiohang(ArrayList<giohang> arraygiohang) {
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

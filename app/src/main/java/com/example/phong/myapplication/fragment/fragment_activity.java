package com.example.phong.myapplication.fragment;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.phong.myapplication.R;


public class fragment_activity extends AppCompatActivity {
    ViewPager viewPager ;
    TabLayout tabLayout ;
    Toolbar toolbar ;
    DrawerLayout drawerLayout ;
    NavigationView navigationView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        viewPager = findViewById(R.id.viewpager_fragment);
        viewPager.setAdapter(new fragment_adapter(getSupportFragmentManager()));
        tabLayout = findViewById(R.id.tablayout_fragment);
        tabLayout.setupWithViewPager(viewPager);
        toolbar = findViewById(R.id.toolbarfagment);
        drawerLayout = findViewById(R.id.drawerlayout1);
        navigationView = findViewById(R.id.navigation);

    }




}
package com.example.phong.myapplication.activity.user;

        import android.content.Intent;
        import android.support.design.widget.NavigationView;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.AdapterView;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.ViewFlipper;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.Volley;
        import com.example.phong.myapplication.R;
        import com.example.phong.myapplication.activity.admin.sanphamAdidas;
        import com.example.phong.myapplication.activity.admin.sanphamNike;
        import com.example.phong.myapplication.activity.admin.themhangsx;
        import com.example.phong.myapplication.adapter.UserhangsxAdapter;
        import com.example.phong.myapplication.adapter.hangsxAdapter;
        import com.example.phong.myapplication.adapter.sanphamAdapter;
        import com.example.phong.myapplication.connect;
        import com.example.phong.myapplication.fragment.fragment_activity;
        import com.example.phong.myapplication.model.giohang;
        import com.example.phong.myapplication.model.hangsanxuatt;
        import com.example.phong.myapplication.model.sanpham;
        import com.example.phong.myapplication.until.checkconnection;
        import com.squareup.picasso.Picasso;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;

        import static com.example.phong.myapplication.connect.sanphammoinhat;

public class user_trangchu extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbaruser ;
    RecyclerView recyclerViewuser;
    ViewFlipper viewFlipperuser;
    ListView listViewtrangchuuser;
    NavigationView navigationView;
    DrawerLayout drawerLayoutuser;
    ArrayList<hangsanxuatt> manghangsxuser ;
    UserhangsxAdapter userhangsxAdapter;
    int ID = 0 ;
    String tenhangsanxuat = "";
    String image = "";
    ArrayList<sanpham> mangsanpham ;
    public  static  ArrayList<giohang> manggiohang ;
    sanphamAdapter sanphamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_trangchu);
        anhxa();
        actionbar();
        actionviewfipper();
        gethangsx();
        chonitemlistview();
        sanphammoinhat();
    }

    private void gethangsx() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(connect.hangsanxuat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null)
                {
                    for (int i = 0 ; i< response.length();i++ )
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("ID");
                            tenhangsanxuat = jsonObject.getString("tenhangsanxuat");
                            image = jsonObject.getString("image");
                            manghangsxuser.add(new hangsanxuatt(ID,tenhangsanxuat,image));
                            userhangsxAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    manghangsxuser.add(4,new hangsanxuatt(1,"thoát","https://banner2.kisspng.com/20180319/sze/kisspng-computer-icons-scalable-vector-graphics-desktop-wa-exit-icon-png-close-5ab071b7971ab3.7386694715215128876189.jpg"));
                    //manghangsx.add(3,new hangsanxuatt(1,"nike","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVyp1vb8FErsR21tZaW_x33mrwmta70MGF_Xdk5I10mp4PnXYe"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                checkconnection.showToast_short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void sanphammoinhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(connect.sanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null)
                {
                    int idsanpham = 0 ;
                    String tensp = "";
                    String hinhanh = "";
                    Integer Gia = 0 ;
                    int idhangsx =0 ;
                    for (int i = 0 ; i < response.length();i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            idsanpham = jsonObject.getInt("IDsanpham");
                            tensp = jsonObject.getString("TenSP");
                            hinhanh = jsonObject.getString("hinhanh");
                            Gia = jsonObject.getInt("Gia");
                            idhangsx = jsonObject.getInt("ID");
                            mangsanpham.add(new sanpham(idsanpham , tensp , hinhanh , Gia , idhangsx));
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void chonitemlistview() {
        listViewtrangchuuser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        if (checkconnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(user_trangchu.this,fragment_activity.class);
                            startActivity(intent);
                        }
                        else{
                            checkconnection.showToast_short(getApplicationContext(),"hãy kiểm tra lại kết nối ");
                        }
                        break;
                    case 1:
                        if (checkconnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(user_trangchu.this,user_sanpham_nike.class);
                            intent.putExtra("idhangsx",manghangsxuser.get(position).getID());
                            startActivity(intent);
                        }
                        else{
                            checkconnection.showToast_short(getApplicationContext(),"hãy kiểm tra lại kết nối ");
                        }
                        break;
                    case 2 :
                        if (checkconnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(user_trangchu.this,user_sanpham_adidas.class);
                            intent.putExtra("idhangsx",manghangsxuser.get(position).getID());
                            startActivity(intent);
                        }
                        else{
                            checkconnection.showToast_short(getApplicationContext(),"hãy kiểm tra lại kết nối ");
                        }
                        drawerLayoutuser.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void actionviewfipper() {
        ArrayList<String> quangcao = new ArrayList<>();
        quangcao.add("https://www.designhill.com/design-blog/wp-content/uploads/2017/12/Blog-feature-min-1.jpg");
        quangcao.add("https://pre00.deviantart.net/2d82/th/pre/i/2009/125/4/2/a_d_i_d_a_s__by_11_95.png");
        quangcao.add("https://static.vecteezy.com/system/resources/previews/000/074/287/non_2x/vans-logos-vector.jpg");

        for (int i=0 ; i<quangcao.size();i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperuser.addView(imageView);
        }
        viewFlipperuser.setFlipInterval(5000);
        viewFlipperuser.setAutoStart(true);

        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipperuser.setInAnimation(animation_slide_in);
        viewFlipperuser.setOutAnimation(animation_slide_out);

    }

    private void actionbar() {
        setSupportActionBar(toolbaruser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbaruser.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbaruser.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayoutuser.openDrawer(GravityCompat.START);
            }
        });

    }

    private void anhxa() {
        toolbaruser = findViewById(R.id.toolbartrangchuuser);
        recyclerViewuser = findViewById(R.id.recyclerviewuser);
        viewFlipperuser = findViewById(R.id.viewtrangchuuser);
        listViewtrangchuuser = findViewById(R.id.lvtrangchuuser);
        navigationView = findViewById(R.id.navviewtrangchuuser);
        drawerLayoutuser = findViewById(R.id.drawerlayoutuser);
        manghangsxuser = new ArrayList<>();
        manghangsxuser.add(0,new hangsanxuatt(0,"trang chính","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVyp1vb8FErsR21tZaW_x33mrwmta70MGF_Xdk5I10mp4PnXYe"));
        userhangsxAdapter = new UserhangsxAdapter(manghangsxuser , getApplicationContext() );
        listViewtrangchuuser.setAdapter(userhangsxAdapter);
        mangsanpham = new ArrayList<>();
        sanphamAdapter = new sanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerViewuser.setHasFixedSize(true);
        recyclerViewuser.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewuser.setAdapter(sanphamAdapter);
    }
}

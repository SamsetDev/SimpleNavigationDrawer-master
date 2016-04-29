package com.samset.simplenavigationdrawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.samset.simplenavigationdrawer.adapters.NavigationDrawerAdapter;
import com.samset.simplenavigationdrawer.fragments.HomeFragment;
import com.samset.simplenavigationdrawer.listeners.OnItemListener;
import com.samset.simplenavigationdrawer.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemListener{
    // Add icons
    private  int[] icons = {
            R.drawable.ic_home, R.drawable.ic_android, R.drawable.ic_facebook,
            R.drawable.ic_twitter, R.drawable.ic_dropbox, R.drawable.ic_camera, R.drawable.ic_audiotrack,
            R.drawable.ic_video, R.drawable.ic_wifi,R.drawable.ic_call,R.drawable.ic_gallery,
            R.drawable.ic_cloudy,R.drawable.ic_power};
    //https://github.com/SamsetDev/MaterialNavigationDrawer/tree/master

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RelativeLayout drawer_pane;
    private DrawerLayout drawerLayout;
    private RecyclerView.LayoutManager layoutManager;
    private ActionBarDrawerToggle mDrawerToggle;
    NavigationDrawerAdapter nav_adapter;

    private String[] titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        setUpDrawar();
        beginTransction(new HomeFragment());
    }

    private void Init() {

        titles = getResources().getStringArray(R.array.nav_drawer_labels);
        drawer_pane = (RelativeLayout) findViewById(R.id.drawer_switch);
        toolbar = (Toolbar) findViewById(R.id.maintoolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) findViewById(R.id.drawer);

    }

    public void setUpDrawar() {



        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        }

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        nav_adapter = new NavigationDrawerAdapter(this, getData(), icons);
        recyclerView.setAdapter(nav_adapter);
        nav_adapter.setListner(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }


        };
        drawerLayout.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State


    }

    public  List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();
        // preparing navigation drawer items
        for (int i = 0; i < titles.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(titles[i]);
            data.add(navItem);
        }
        return data;
    }

    @Override
    public void OnItemClick(View view, final int position) {
        drawerLayout.closeDrawer(drawer_pane);
        drawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // You call fragment as u want
                Toast.makeText(MainActivity.this, "You click " + titles[position], Toast.LENGTH_SHORT).show();
            }
        }, 300);

    }

    private void beginTransction(Fragment fragment) {

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.container_body,fragment).commit();

    }
}

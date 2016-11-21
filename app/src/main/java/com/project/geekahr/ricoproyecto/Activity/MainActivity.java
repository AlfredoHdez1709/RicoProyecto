package com.project.geekahr.ricoproyecto.Activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.geekahr.ricoproyecto.Fragmentos.HistoryFragment;
import com.project.geekahr.ricoproyecto.Fragmentos.HomeFragment;
import com.project.geekahr.ricoproyecto.Fragmentos.NotifyFragment;
import com.project.geekahr.ricoproyecto.R;
import com.squareup.picasso.Picasso;


import java.io.Serializable;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Serializable{


    FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView txtname = (TextView) headerView.findViewById(R.id.name);
        TextView txtemail = (TextView) headerView.findViewById(R.id.email);







        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            String userid = user.getUid();


            txtname.setText(name);
            txtemail.setText(email);



        }else{
            goLoginScreen();
        }

        FragmentManager fm = getFragmentManager();

        fm.beginTransaction().replace(R.id.content_main, new HomeFragment()).commit();

    }

    //bo funciona
    //Login
    public void  goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.notify) {
            fm.beginTransaction().replace(R.id.content_main, new NotifyFragment()).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean FragmentTransation = false;



        if (id == R.id.nav_home) {
            fm.beginTransaction().replace(R.id.content_main, new HomeFragment()).commit();
            setTitle("Home");
        } else if (id == R.id.nav_notify) {
            fm.beginTransaction().replace(R.id.content_main, new NotifyFragment()).commit();
            setTitle("Notificaciones");
        } else if (id == R.id.nav_history) {
            fm.beginTransaction().replace(R.id.content_main, new HistoryFragment()).commit();
            setTitle("Historial de busqueda");
        }  else if (id == R.id.nav_config) {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));

            return true;
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutUsActivity.class));

            return true;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

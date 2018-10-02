package com.fongwama.edupalu_v3;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.fongwama.edupalu_v3.controller.LaunchManager;
import com.fongwama.edupalu_v3.fragments.ComprendreFragment;
import com.fongwama.edupalu_v3.fragments.DiagnosticFragment;
import com.fongwama.edupalu_v3.fragments.MainFragment;
import com.fongwama.edupalu_v3.fragments.ProtegerFragment;
import com.fongwama.edupalu_v3.fragments.QuestionReponseFragment;
import com.fongwama.edupalu_v3.fragments.SoignerFragment;
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    CoordinatorLayout cordinatorLayoutActivityA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        ft.replace(R.id.main_frame,mainFragment);
        ft.commit();


        //DrawerLayout initialization
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
            return true;
        }else if(id == R.id.action_search){

//            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            SearchFragment searchFragment = new SearchFragment();
//            ft.replace(R.id.main_frame, searchFragment);
//            ft.commit();

//            SearchView searchView =(SearchView) item.getActionView();


        }
//        else if(id == R.id.action_guide_app){
//            LaunchManager LaunchManager = new LaunchManager(getApplicationContext());
//            // make first time launch TRUE
//            LaunchManager.setFirstTimeLaunch(true);
//            startActivity(new Intent(MainActivity.this, SliderActivity.class));
//            finish();
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            MainFragment mainFragment = new MainFragment();
            ft.replace(R.id.main_frame, mainFragment);
            ft.commit();
        }else if (id == R.id.nav_comprendre) {
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ComprendreFragment comprendreFragment = new ComprendreFragment();
            ft.replace(R.id.main_frame,comprendreFragment);
            ft.commit();
        }else if (id == R.id.nav_proteger){
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ProtegerFragment protegerFragment = new ProtegerFragment();
            ft.replace(R.id.main_frame,protegerFragment);
            ft.commit();
        } else if (id == R.id.nav_diagnostic) {
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            DiagnosticFragment diagnosticFragment = new DiagnosticFragment();
            ft.replace(R.id.main_frame,diagnosticFragment);
            ft.commit();
        } else if (id == R.id.nav_soigner) {
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            SoignerFragment soignerFragment = new SoignerFragment();
            ft.replace(R.id.main_frame,soignerFragment);
            ft.commit();
        } else if (id == R.id.nav_questions) {
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            QuestionReponseFragment questionReponseFragment = new QuestionReponseFragment();
            ft.replace(R.id.main_frame,questionReponseFragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

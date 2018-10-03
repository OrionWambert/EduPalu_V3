package com.fongwama.edupalu_v3;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.fongwama.edupalu_v3.adapters.ListAdapter;
import com.fongwama.edupalu_v3.model.PlaceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    SearchView searchViewQuery;
    ImageButton imageViewSearchMenu;

    RecyclerView recyclerView;
    ArrayList<PlaceModel>listPlaces;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recheerche");

        //searchViewQuery = (SearchView)findViewById(R.id.searchViewQuery);
        imageViewSearchMenu = (ImageButton) findViewById(R.id.imageViewSearchMenu);
        recyclerView =(RecyclerView) findViewById(R.id.rv);

        // white background notification bar
        whiteNotificationBar(recyclerView);



    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }


    private void loadJson() throws IOException {
        InputStream inputStream = null;
        listPlaces = new ArrayList<PlaceModel>();


        JSONArray jsonArray = null;
        try {

            inputStream = getApplicationContext().getAssets().open("places_db.json");

            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer);

            jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PlaceModel pl = new PlaceModel();
                pl.setAddress(jsonObject.getString("address"));
                pl.setCity(jsonObject.getString("city"));
                pl.setId(jsonObject.getInt("id"));
                pl.setLat(jsonObject.getInt("lat"));
                pl.setLon(jsonObject.getInt("lon"));
                pl.setName(jsonObject.getString("name"));
                pl.setTel1(jsonObject.getString("tel1"));
                pl.setTel2(jsonObject.getString("tel2"));
                listPlaces.add(pl);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search_action).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
                // filter recycler view when query submitted
               // mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
                // filter recycler view when text is changed
                //mAdapter.getFilter().filter(query);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent i = new Intent(SearchActivity.this, AboutActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

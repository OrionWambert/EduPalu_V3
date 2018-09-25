package com.fongwama.edupalu_v3;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchViewQuery = (SearchView)findViewById(R.id.searchViewQuery);
        imageViewSearchMenu = (ImageButton) findViewById(R.id.imageViewSearchMenu);
        recyclerView =(RecyclerView) findViewById(R.id.rv);


        try {
            loadJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //SearchBar
        EditText searchEditText = (EditText) searchViewQuery.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(ResourcesCompat.getColor(getResources(),R.color.colortitle,null));
        searchEditText.setHintTextColor(ResourcesCompat.getColor(getResources(),R.color.txt_hint_text,null));

        ImageView searchImage = (ImageView) searchViewQuery.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        searchImage.setVisibility(View.GONE);
        popUpShowNearToMe();

    }


    private void popUpShowNearToMe(){
        imageViewSearchMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SearchActivity.this, "Looking for the near drugStore", Toast.LENGTH_SHORT).show();
            }
        });
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

            ListAdapter listAdapter = new ListAdapter(this,listPlaces);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(listAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_search, menu);
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

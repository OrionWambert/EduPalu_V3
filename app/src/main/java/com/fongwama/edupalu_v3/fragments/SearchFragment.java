package com.fongwama.edupalu_v3.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fongwama.edupalu_v3.R;
import com.fongwama.edupalu_v3.adapters.ListAdapter;
import com.fongwama.edupalu_v3.data.PlacePharmaDao;
import com.fongwama.edupalu_v3.model.PlaceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, ListAdapter.PlaceAdapterListener {
    SearchView searchViewQuery;
    ImageButton imageViewSearchMenu;


    RecyclerView recyclerView;
    ArrayList<PlaceModel>listPlaces;
    ListAdapter listAdapter;
    private PlacePharmaDao placePharmaDao;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        searchViewQuery = (SearchView)v.findViewById(R.id.searchViewQuery);
        imageViewSearchMenu = (ImageButton) v.findViewById(R.id.imageViewSearchMenu);
        recyclerView =(RecyclerView) v.findViewById(R.id.rv);

        searchViewQuery.setOnQueryTextListener(this);

//        try {
//            loadJson();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        loadList();

        ImageView searchImage = (ImageView) searchViewQuery.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        searchImage.setVisibility(View.GONE);
        popUpShowNearToMe();

        return v;
    }

//    private void loadJson() throws IOException {
//        InputStream inputStream = null;
//        listPlaces = new ArrayList<PlaceModel>();
//
//        JSONArray jsonArray = null;
//        try {
//            inputStream = getActivity().getAssets().open("places_db.json");
//            byte[] buffer = new byte[inputStream.available()];
//            inputStream.read(buffer);
//            inputStream.close();
//            String json = new String(buffer);
//            jsonArray = new JSONArray(json);
//            for (int i = 0; i < jsonArray.length(); i++){
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                PlaceModel pl = new PlaceModel();
//                pl.setAddress(jsonObject.getString("address"));
//                pl.setCity(jsonObject.getString("city"));
//                pl.setId(jsonObject.getInt("id"));
//                pl.setLat(jsonObject.getInt("lat"));
//                pl.setLon(jsonObject.getInt("lon"));
//                pl.setName(jsonObject.getString("name"));
//                pl.setTel1(jsonObject.getString("tel1"));
//                pl.setTel2(jsonObject.getString("tel2"));
//                listPlaces.add(pl);
//            }
//            listAdapter = new ListAdapter(getContext(),listPlaces,this);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setAdapter(listAdapter);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public void loadList(){
        listPlaces = new ArrayList<PlaceModel>();
        placePharmaDao = new PlacePharmaDao(getContext());

        listPlaces = placePharmaDao.getPlaceModels();
        listAdapter = new ListAdapter(getContext(),listPlaces,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);
    }

//    private void searchData(String str){
//        ArrayList<PlaceModel> ps = new ArrayList<>();
//        if (!str.trim().isEmpty()){
//            for (PlaceModel p : listPlaces){
//                if (p.getName().toLowerCase().contains(str)){
//                    ps.add(p);
//                }
//            }
//        }
//        if (ps.size() <= 0){
//            listAdapter.initData(listPlaces);
//        }
//        listAdapter.initData(ps);
//    }

    private void popUpShowNearToMe(){
        imageViewSearchMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Looking for the near drugStore....", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        //Toast.makeText(getActivity(), "we listen if text submit", Toast.LENGTH_SHORT).show();
       // placePharmaDao.filterPlaceModel(query);

//        listPlaces = placePharmaDao.filterPlaceModel(query);
//
//        listAdapter = new ListAdapter(getContext(),listPlaces,this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(listAdapter);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       // Toast.makeText(getActivity(), "we listen if text has changed", Toast.LENGTH_SHORT).show();
        placePharmaDao.filterPlaceModel(newText);
        listPlaces = placePharmaDao.filterPlaceModel(newText);
        listAdapter = new ListAdapter(getContext(),listPlaces,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);

        return false;
    }

    @Override
    public void onContactSelected(PlaceModel placeModel) {
        Toast.makeText(getActivity(), "numero : "+placeModel.getName(), Toast.LENGTH_SHORT).show();
    }
}

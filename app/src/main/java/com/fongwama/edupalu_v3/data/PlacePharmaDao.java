package com.fongwama.edupalu_v3.data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.fongwama.edupalu_v3.contracts.PlacePharmaContract;
import com.fongwama.edupalu_v3.model.PlaceModel;

import java.util.ArrayList;

public class PlacePharmaDao extends DbHelper {

    public PlacePharmaDao(Context context) {
        super(context);
    }

    public Long savePlaceModel(PlaceModel placeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       // values.put(PlacePharmaContract.PlacePharamEntry._ID,placeModel.getId());
        values.put(PlacePharmaContract.PlacePharamEntry.PLACE_NAME,placeModel.getName());
        values.put(PlacePharmaContract.PlacePharamEntry.PLACE_ADDRESS,placeModel.getAddress());
        values.put(PlacePharmaContract.PlacePharamEntry.PLACE_CITY,placeModel.getCity());
        values.put(PlacePharmaContract.PlacePharamEntry.PLACE_LAT,placeModel.getLat());
        values.put(PlacePharmaContract.PlacePharamEntry.PLACE_LON,placeModel.getLon());
        values.put(PlacePharmaContract.PlacePharamEntry.PLACE_TEL_1,placeModel.getTel1());
        values.put(PlacePharmaContract.PlacePharamEntry.PLACE_TEl_2,placeModel.getTel2());
        Long ip = db.insert(PlacePharmaContract.PlacePharamEntry.TABLE_NAME,null,values);
        return ip;
    }

    public ArrayList<PlaceModel> getFilteredPlace(String filter){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<PlaceModel>placeModelsFiltered = new ArrayList<PlaceModel>();

        Cursor cursor = db.query(true, PlacePharmaContract.PlacePharamEntry.TABLE_NAME,
                new String[]{PlacePharmaContract.PlacePharamEntry.PLACE_NAME, PlacePharmaContract.PlacePharamEntry.PLACE_ADDRESS},
                PlacePharmaContract.PlacePharamEntry.PLACE_NAME +" LIKE ?",new String[]{filter},null,null,null,null);
        if(cursor.moveToFirst())
            do{
                placeModelsFiltered.add(
                        new PlaceModel(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getLong(4),
                                cursor.getLong(5),
                                cursor.getString(6),
                                cursor.getString(7)
                        ));
            }while (cursor.moveToNext());
        return  placeModelsFiltered;
    }

    public ArrayList<PlaceModel>filterPlaceModel(String query){
        SQLiteDatabase db = this.getReadableDatabase();



        String SELECT_PLACE_MODEL = "SELECT * FROM "+PlacePharmaContract.PlacePharamEntry.TABLE_NAME+
                " WHERE "+ PlacePharmaContract.PlacePharamEntry.PLACE_NAME+" LIKE %"+query+"%";


        ArrayList<PlaceModel>placeModelsList = new ArrayList<PlaceModel>();
        Cursor cursor = db.rawQuery(SELECT_PLACE_MODEL,null);

        if(cursor.getColumnCount() == 0){
            String result ="Aucune donnée trouvée";
        }else{
            while (cursor.moveToNext()){
                placeModelsList.add(
                        new PlaceModel(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getLong(4),
                                cursor.getLong(5),
                                cursor.getString(6),
                                cursor.getString(7)
                        ));
            }
            cursor.close();
        }
        return placeModelsList;
    }

    public ArrayList<PlaceModel>getPlaceModels(){
        SQLiteDatabase db = this.getReadableDatabase();

        String SELECT_PLACE_MODEL = "SELECT * FROM "+ PlacePharmaContract.PlacePharamEntry.TABLE_NAME;


        ArrayList<PlaceModel>placeModelsList = new ArrayList<PlaceModel>();
        Cursor cursor =db.rawQuery(SELECT_PLACE_MODEL,null);

        if(cursor.moveToFirst())
            do{
                placeModelsList.add(
                        new PlaceModel(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getLong(4),
                                cursor.getLong(5),
                                cursor.getString(6),
                                cursor.getString(7)
                        ));
            }while (cursor.moveToNext());

        return placeModelsList;
    }

}

package com.fongwama.edupalu_v3.controller;

import com.fongwama.edupalu_v3.model.PlaceModel;

import java.util.ArrayList;

public class Response {
    private ArrayList<PlaceModel> places = null;

    public ArrayList<PlaceModel> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<PlaceModel> places) {
        this.places = places;
    }
}

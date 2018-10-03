package com.fongwama.edupalu_v3.contracts;

import android.provider.BaseColumns;

public class PlacePharmaContract {

    public static  final class PlacePharamEntry implements BaseColumns{
        public static final String TABLE_NAME = "tab_place";
        public static final String PLACE_NAME = "name";
        public static final String PLACE_CITY = "city";
        public static final String PLACE_ADDRESS = "address";
        public static final String PLACE_TEL_1 = "tel1";
        public static final String PLACE_TEl_2 = "tel2";
        public static final String PLACE_LAT = "lat";
        public static final String PLACE_LON = "lon";
    }

}

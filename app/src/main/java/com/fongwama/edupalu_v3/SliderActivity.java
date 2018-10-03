package com.fongwama.edupalu_v3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fongwama.edupalu_v3.controller.LaunchManager;
import com.fongwama.edupalu_v3.data.PlacePharmaDao;
import com.fongwama.edupalu_v3.model.PlaceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SliderActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button Skip, Next;
    private LaunchManager launchManager;

    public static List<PlaceModel> placeModelList;
    private PlacePharmaDao placePharmaDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Checking for first time launch - before calling setContentView()
        launchManager = new LaunchManager(this);
        if (!LaunchManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        initPlacesList();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_slider);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        Skip = (Button) findViewById(R.id.btn_skip);
        Next = (Button) findViewById(R.id.btn_next);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.slide1,
                R.layout.slide2,
                R.layout.slide3};

        // adding bottom dots
        addBottomDots(0);
        // making notification bar transparent
        changeStatusBarColor();
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });
    }

        private void addBottomDots(int currentPage) {
            dots = new TextView[layouts.length];
            int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
            int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
            dotsLayout.removeAllViews();
            for (int i = 0; i < dots.length; i++) {
                dots[i] = new TextView(this);
                dots[i].setText(Html.fromHtml("&#8226;"));
                dots[i].setTextSize(35);
                dots[i].setTextColor(colorsInactive[currentPage]);
                dotsLayout.addView(dots[i]);
            }
            if (dots.length > 0)
                dots[currentPage].setTextColor(colorsActive[currentPage]);
        }

        private int getItem(int i) {
            return viewPager.getCurrentItem() + i;
        }
        private void launchHomeScreen(){
            LaunchManager.setFirstTimeLaunch(false);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        //  viewpager change listener
        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == layouts.length-1) {
                    // last page. make button text to GOT IT
                    Next.setText(getString(R.string.start));
                    Skip.setVisibility(View.GONE);
                } else {
                    // still pages are left
                    Next.setText(getString(R.string.next));
                    Skip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private MyViewPagerAdapter() {
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }


    }


    private void initPlacesList() {
        placeModelList = new ArrayList<>();
        /*get file data*/
        InputStream inputStream = null;
        try{
            inputStream = getApplicationContext().getAssets().open("places_db.json");

            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer);

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++){
               final JSONObject jsonObject = jsonArray.getJSONObject(i);
                placePharmaDao = new PlacePharmaDao(this);

                //PlaceModel pl = new PlaceModel();
//                Long id = placePharmaDao.savePlaceModel(new PlaceModel(
//                        jsonObject.getInt("id"),
//                        jsonObject.getLong("lat"),
//                        jsonObject.getLong("lon"),
//                        jsonObject.getString("city"),
//                        jsonObject.getString("name"),
//                        jsonObject.getString("address"),
//                        jsonObject.getString("tel1"),
//                        jsonObject.getString("tel2"))
//                );


                final int finalI = i;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try{

                            String str_addr = jsonObject.getString("address");
                            String str_city = jsonObject.getString("city");
                            String str_name = jsonObject.getString("name");
                            String str_tel1 = jsonObject.getString("tel1");
                            String str_tel2 = jsonObject.getString("tel2");
                            Long lat = jsonObject.getLong("lat");
                            Long lon = jsonObject.getLong("lon");
                            int id = jsonObject.getInt("id");

                            Log.d("RESULTS", str_name+"/"+str_city+"/"+String.valueOf(lat)+" / "+String.valueOf(lon));

                            placePharmaDao.savePlaceModel(new PlaceModel(id,str_name,str_addr,str_city,lat,lon,str_tel1,str_tel2));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }



                    }
                }, jsonArray.length());


            }

        }catch (IOException | JSONException e){
            e.printStackTrace();
        }
    }
}

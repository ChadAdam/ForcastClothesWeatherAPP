package com.demo.weather;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.demo.weather.utils.PreferenceUtils;

public class ClothesPrefActivity extends AppCompatActivity {
    ImageView mDefaultImage_Fall;
    ImageView mDefaultImage_Winter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clothes_pref_activity);
        ActionBar actionBar = this.getSupportActionBar();


        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        String extra = getIntent().getStringExtra("pref_date");
       // if(extra!=null) {

          // String season = PreferenceUtils.setSeason(getApplicationContext() , extra);


//        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id ==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

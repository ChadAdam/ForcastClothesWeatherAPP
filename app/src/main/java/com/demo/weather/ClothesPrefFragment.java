package com.demo.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.demo.weather.utils.PreferenceUtils;
import com.demo.weather.utils.WeatherContract;

import java.util.ArrayList;

public class ClothesPrefFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    ImageViewPreference imageViewPreference;
    ImageViewPreference imageViewPreference_Fall;
    ImageViewPreference imageViewPreference_Winter;
    private void setPreferenceSum(Preference p , Object v){
        String value = v.toString();
        String key = p.getKey();
        if(p instanceof ListPreference){
            ListPreference listPreference = (ListPreference)p;
            int index = listPreference.findIndexOfValue(value);
            if(index >=0){
                p.setSummary(listPreference.getEntries()[index]);
            }
        }
        else{p.setSummary(value);}
    }
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        //addPreferencesFromResource(R.xml.pref_clothes);
        setPreferencesFromResource(R.xml.pref_clothes, s);
        imageViewPreference = (ImageViewPreference) findPreference("image_preference");

        //imageViewPreference_Winter = (ImageViewPreference) findPreference("image_preference_Winter");
        if (imageViewPreference != null)

            imageViewPreference.setImageClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent i = new Intent(getContext() , test_image_preference_activity.class);
                    //startActivity(i);
                    //do whatever you want on image click here
                   // ArrayList<Integer> pref_default =PreferenceUtils.getDefClothes(getContext());
                    //Toast.makeText(getContext(), pref_default.toString(), Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getContext() , test_image_preference_activity.class);
                    startActivity(i);
                }
            });



        //Toast toast = Toast.makeText(getContext(), "Click Image to Chose Items \nWhen Text Disappears", Toast.LENGTH_LONG);
        //toast.setGravity(Gravity.CENTER , 0 ,0);
        //toast.show();
        /*
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount();
        for(int i =0;i<count;i++){
            Preference p = preferenceScreen.getPreference(i);
            // Only Edit text and List Pref
            if(!(p instanceof CheckBoxPreference)){
                String v = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSum(p , v); }
        } */

    }


    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

    }
    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Activity activity = getActivity();

        Preference p = findPreference(s);
        if(p !=null){
            if(!(p instanceof CheckBoxPreference)){
                setPreferenceSum(p , sharedPreferences.getString(p.getKey(), ""));
            }}
    }

}

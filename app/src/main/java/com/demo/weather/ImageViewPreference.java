package com.demo.weather;

import android.content.Context;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class ImageViewPreference extends Preference {

    private View getImageView_Winter;
    View.OnClickListener imageClickListener;
    View.OnTouchListener imageTouchListener;
    public ImageViewPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //onBindViewHolder() will be called after we call setImageClickListener() from SettingsFragment
    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        ImageView imageView_Fall = (ImageView) holder.findViewById(R.id.image_preference);
        imageView_Fall.setOnClickListener(imageClickListener);


    }

    public void setImageClickListener(View.OnClickListener onClickListener)
    {
        imageClickListener = onClickListener;
    }
    public void setTouchListener(View.OnTouchListener onTouchListener){
        imageTouchListener = onTouchListener;
    }
}
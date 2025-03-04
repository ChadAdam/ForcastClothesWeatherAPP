package com.demo.weather;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.weather.utils.DateUtils;
import com.demo.weather.utils.FormatUtils;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherAdapterViewHolder> {
 //  ArrayList<String> mWeatherData;

   private final Context mContext;
   private Cursor mCursor;
   final WeatherAdapterOnClickHandler mClickHandler;
   private final int VIEW_TYPE_TODAY = 5;
   private final int VIEW_TYPE_FUTURE = 2;
   private final boolean mUseTodayLayout;

    public interface WeatherAdapterOnClickHandler{
       void onClick(long weatherLine);
    }


    public WeatherAdapter(@NonNull Context c,WeatherAdapterOnClickHandler clickHandler){
        //later
        mContext = c;
        mClickHandler = clickHandler;
        mUseTodayLayout=mContext.getResources().getBoolean(R.bool.today_layout);
    }

    @NonNull
    @Override
    public WeatherAdapterViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        // Things to happen on ViewHolder is created
        // Executed when RecyclerView is laid out to full screen
        int viewTypeId;
        Context c = viewGroup.getContext();


        switch(i){
            case VIEW_TYPE_TODAY:{
                viewTypeId=R.layout.list_item_weather_today;
                break;}

            case VIEW_TYPE_FUTURE:{
                viewTypeId=R.layout.weather_list_item;
                break;}
            default:
                throw new IllegalArgumentException("Invalid viewtype , val :" + i);
        }


        //int layout = R.layout.weather_list_item;
        LayoutInflater inflate = LayoutInflater.from(c);
        View v = inflate.inflate(viewTypeId,viewGroup, false);
        v.setFocusable(true);
        return new WeatherAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder( WeatherAdapterViewHolder weatherAdapterViewHolder, int i) {
        //Assign WeatherAdapterViewHolder with data at position i
        // Recyclerview calls this to display data at position i
        //String weatherDataLine = mWeatherData.get(i);
        //weatherAdapterViewHolder.mWeatherTextView.setText(weatherDataLine);
        mCursor.moveToPosition(i);
        int weatherImageId;
        int viewType = getItemViewType(i);

        long dateInMillis = mCursor.getLong(MainActivity.INDEX_WEATHER_DATE);
        /* Get human readable string using our utility method */
        String dateString = DateUtils.getFriendlyDateString(mContext, dateInMillis, false);
        /* Use the weatherId to obtain the proper description */
        int weatherId = mCursor.getInt(MainActivity.INDEX_WEATHER_CONDITION_ID);
        //String description =DateUtils.getStringForWeatherCondition(mContext, weatherId);
        /* Read high temperature from the cursor (in degrees celsius) */
        double highInCelsius = mCursor.getDouble(MainActivity.INDEX_WEATHER_MAX_TEMP);
        /* Read low temperature from the cursor (in degrees celsius) */
        double lowInCelsius = mCursor.getDouble(MainActivity.INDEX_WEATHER_MIN_TEMP);
        double humudity =  mCursor.getDouble(MainActivity.INDEX_WEATHER_HUM);

        //int weatherImageId = FormatUtils.getSmallArtResourceIdForWeatherCondition(weatherId);

        String humidString = FormatUtils.formatHumidity(mContext, humudity);
        String highString = FormatUtils.formatTemp(mContext, highInCelsius);
        String lowString = FormatUtils.formatTemp(mContext, lowInCelsius);
        //String highAndLowTemperature = FormatUtils.formatHighLows(mContext, highInCelsius, lowInCelsius);

        //String weatherSummary = dateString + " - " + highAndLowTemperature+" "+FormatUtils.formatHumidity(mContext, humudity);
        //weatherAdapterViewHolder.iconView.setImageResource(weatherImageId);
        weatherAdapterViewHolder.dateView.setText(dateString);
        weatherAdapterViewHolder.humidity.setText(humidString);
        weatherAdapterViewHolder.highTempView.setText(highString);
        weatherAdapterViewHolder.lowTempView.setText(lowString);

        switch (viewType){
            case VIEW_TYPE_TODAY:{
                weatherImageId=FormatUtils.getLargeArtResourceIdForWeatherCondition(weatherId);
                break;}
            case VIEW_TYPE_FUTURE:{
                weatherImageId=FormatUtils.getSmallArtResourceIdForWeatherCondition(weatherId);
                break;}
            default:
                throw new IllegalArgumentException("Invalid viewtype , val :" + i);


        }
        weatherAdapterViewHolder.iconView.setImageResource(weatherImageId);
    }
    // Gets number of items to be used by RecylerView


    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        if(mUseTodayLayout && position==0){
            return VIEW_TYPE_TODAY;
        }
        else{
            return  VIEW_TYPE_FUTURE;
        }

    }

    @Override
    public int getItemCount() {
        if(mCursor==null){
            return 0;
        }
        return mCursor.getCount();
    }

    public class WeatherAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Deals with item
        //public final TextView mWeatherTextView;
        public ImageView iconView;
        public TextView dateView;
        public TextView highTempView;
        public TextView lowTempView;
        public TextView humidity;

        public WeatherAdapterViewHolder( View itemView) {
            super(itemView);
            //mWeatherTextView = (TextView) itemView.findViewById(R.id.tv_weather_data_item);
            iconView = (ImageView) itemView.findViewById(R.id.weather_icon);
            highTempView = (TextView) itemView.findViewById(R.id.high_temperature);
            lowTempView = (TextView) itemView.findViewById(R.id.low_temperature);
            humidity = (TextView) itemView.findViewById(R.id.humidity);
            dateView = (TextView) itemView.findViewById(R.id.date);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int ad_pos = getAdapterPosition();
            //String weatherLine = mWeatherData.get(ad_pos);
            //String weatherLine = mWeatherTextView.getText().toString();
            mCursor.moveToPosition(ad_pos);
            int col_Index = MainActivity.INDEX_WEATHER_DATE;
            long weatherLine = mCursor.getLong(col_Index);
            mClickHandler.onClick(weatherLine);

        }
    }

   /* public void setWeatherData(ArrayList<String> wd){
        mWeatherData= wd;
        notifyDataSetChanged();
    } */

   public void swapCursor(Cursor cursor){
       mCursor = cursor;
       notifyDataSetChanged();
   }
}

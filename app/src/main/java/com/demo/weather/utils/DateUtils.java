package com.demo.weather.utils;

import android.content.Context;

import com.demo.weather.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public final class DateUtils {

        public static final long SECOND_IN_MILLIS = 1000;
        public static final long MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60;
        public static final long HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
        public static final long DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;



        public static long getDayNumber(long date) {
            TimeZone tz = TimeZone.getDefault();
            long gmtOffset = tz.getOffset(date);
            return (date + gmtOffset) / DAY_IN_MILLIS;
        }

        public static int getMonthNumber(long date){
            TimeZone tz = TimeZone.getDefault();
            long gmtOffset = tz.getOffset(date);
            Time timeFormat = new Time(date+gmtOffset);
            int month = timeFormat.getMonth();
            return month;

        }

        public static double getSeason(Context c , int date ){
            int monthNum = date;
            double season=0;
            if(monthNum==12 || monthNum >=1 && monthNum<=2){
                season=2;
            }
            else if ( monthNum>=3){
                season = 2.5;
            }
            else if (monthNum>=4 && monthNum<=5){
                season = 3;
            }
            else if(monthNum ==6){
                season= 3.5;
            }
            else if(monthNum>=7 && monthNum<=8){
                season = 4;
            }
            else if(monthNum==9){
                season = 1;
            }
            else if(monthNum>=10 && monthNum <=11){
                season = 1.5;
            }
            return season;
        }



        public static long dateNormalize(long date) {

            long retValNew = date / DAY_IN_MILLIS * DAY_IN_MILLIS;
            return retValNew;
        }





        public static boolean isDateNormalized(long millisFromEpoch) {
            boolean isDateNormalized = false;
            if (millisFromEpoch % DAY_IN_MILLIS == 0) {
                isDateNormalized = true;
            }

            return isDateNormalized;
        }


        public static long getLocalDateFromUTC(long utcDate) {
            TimeZone tz = TimeZone.getDefault();
            long gmtOffset = tz.getOffset(utcDate);
            return utcDate - gmtOffset;
        }

        /*
        public static long getUTCDateFromLocal(long localDate) {
            TimeZone tz = TimeZone.getDefault();
            long gmtOffset = tz.getOffset(localDate);
            return localDate + gmtOffset;
        } */


        public static String getFriendlyDateString(Context context, long dateInMillis, boolean showFullDate) {

            long localDate = getLocalDateFromUTC(dateInMillis);
            long dayNumber = getDayNumber(localDate);
            long currentDayNumber = getDayNumber(System.currentTimeMillis());

            if (dayNumber == currentDayNumber || showFullDate) {

                String dayName = getDayName(context, localDate);
                String readableDate = getReadableDateString(context, localDate);
                if (dayNumber - currentDayNumber < 2) {

                    String localizedDayName = new SimpleDateFormat("EEEE").format(localDate);
                    return readableDate.replace(localizedDayName, dayName);
                } else {
                    return readableDate;
                }
            } else if (dayNumber < currentDayNumber + 7) {

                return getDayName(context, localDate);
            } else {
                int flags = android.text.format.DateUtils.FORMAT_SHOW_DATE
                        | android.text.format.DateUtils.FORMAT_NO_YEAR
                        | android.text.format.DateUtils.FORMAT_ABBREV_ALL
                        | android.text.format.DateUtils.FORMAT_SHOW_WEEKDAY;

                return android.text.format.DateUtils.formatDateTime(context, localDate, flags);
            }
        }


        private static String getReadableDateString(Context context, long timeInMillis) {
            int flags = android.text.format.DateUtils.FORMAT_SHOW_DATE
                    | android.text.format.DateUtils.FORMAT_NO_YEAR
                    | android.text.format.DateUtils.FORMAT_SHOW_WEEKDAY;

            return android.text.format.DateUtils.formatDateTime(context, timeInMillis, flags);
        }


        private static String getDayName(Context context, long dateInMillis) {

            long dayNumber = getDayNumber(dateInMillis);
            long currentDayNumber = getDayNumber(System.currentTimeMillis());
            if (dayNumber == currentDayNumber) {
                return context.getString(R.string.today);
            } else if (dayNumber == currentDayNumber + 1) {

                return context.getString(R.string.tomorrow);
            } else {

                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                return dayFormat.format(dateInMillis);
            }
        }

    public static long getNormalizedUtcDateForToday() {


        long utcNowMillis = System.currentTimeMillis();


        TimeZone currentTimeZone = TimeZone.getDefault();


        long gmtOffsetMillis = currentTimeZone.getOffset(utcNowMillis);


        long timeSinceEpochLocalTimeMillis = utcNowMillis + gmtOffsetMillis;


        long daysSinceEpochLocal = TimeUnit.MILLISECONDS.toDays(timeSinceEpochLocalTimeMillis);


        long normalizedUtcMidnightMillis = TimeUnit.DAYS.toMillis(daysSinceEpochLocal);

        return normalizedUtcMidnightMillis;
    }
    }


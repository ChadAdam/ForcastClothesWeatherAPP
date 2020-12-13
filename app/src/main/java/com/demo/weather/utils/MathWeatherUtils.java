package com.demo.weather.utils;

import android.content.Context;
import android.content.Intent;

import com.demo.weather.ClothesPrefActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MathWeatherUtils {
    private static final int NO_CLOTHES_PICKED = 0;
    private static final int ERROR_FINDING_PREF = -1;
    private static final int LOW = 1;
    private static final int MEDIUM = 2;
    private static final int HIGH = 3;

    private static final int HEAD_INDEX = 0;
    private static final int BODY_INDEX = 1;
    private static final int ARMS_INDEX = 2;
    private static final int LEGS_INDEX = 3;

    private static ArrayList<Integer> prefArray = new ArrayList<>();

    private static ArrayList<Integer> getPrefArray(Context context){

        ArrayList<Integer> arr = PreferenceUtils.getDefClothes(context);
        return arr;
    }
    public static ArrayList<Integer> getWeatherIntArr(Context context , double temp){
        prefArray= getPrefArray(context);
        ArrayList<Integer> prefArrayR = new ArrayList<>();
        int head = prefArray.get(HEAD_INDEX);
        int body = prefArray.get(BODY_INDEX);
        int arms = prefArray.get(ARMS_INDEX);
        int legs = prefArray.get(LEGS_INDEX);
        final double lower_point = 40.0;
        final double mid_point = 60.0;
        final double high_point = 80.0;
        final int more_clothes = 1;
        final int less_clothes= -1;
        final int perfect_clothes =0;
        if(temp<=lower_point){
            switch (head){
                case 1:
                    prefArrayR.add(HEAD_INDEX, more_clothes);
                    break;
                case 2:
                    prefArrayR.add(HEAD_INDEX, more_clothes);
                    break;
                case 3:
                    prefArrayR.add(HEAD_INDEX,perfect_clothes );
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }
            switch (body){
                case 1:
                    prefArrayR.add(BODY_INDEX, more_clothes);
                    break;
                case 2:
                    prefArrayR.add(BODY_INDEX, more_clothes);
                    break;
                case 3:
                    prefArrayR.add(BODY_INDEX,perfect_clothes );
                    break;
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }

            switch (arms){
                case 1:
                    prefArrayR.add(ARMS_INDEX, more_clothes);
                    break;
                case 2:
                    prefArrayR.add(ARMS_INDEX, more_clothes);
                    break;
                case 3:
                    prefArrayR.add(ARMS_INDEX,perfect_clothes );
                    break;
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }

            switch (legs){
                case 1:
                    prefArrayR.add(LEGS_INDEX, more_clothes);
                    break;
                case 2:
                    prefArrayR.add(LEGS_INDEX, more_clothes);
                    break;
                case 3:
                    prefArrayR.add(LEGS_INDEX,perfect_clothes );
                    break;
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }
        }
        else if(temp>=high_point){
            switch (head){
                case 1:
                    prefArrayR.add(HEAD_INDEX, perfect_clothes);
                    break;
                case 2:
                    prefArrayR.add(HEAD_INDEX, less_clothes);
                    break;
                case 3:
                    prefArrayR.add(HEAD_INDEX,less_clothes);
                    break;
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }
            switch (body){
                case 1:
                    prefArrayR.add(BODY_INDEX, perfect_clothes);
                    break;
                case 2:
                    prefArrayR.add(BODY_INDEX, less_clothes);
                    break;
                case 3:
                    prefArrayR.add(BODY_INDEX,less_clothes);
                    break;
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }
            switch (arms){
                case 1:
                    prefArrayR.add(ARMS_INDEX, perfect_clothes);
                    break;
                case 2:
                    prefArrayR.add(ARMS_INDEX, less_clothes);
                    break;
                case 3:
                    prefArrayR.add(ARMS_INDEX,less_clothes);
                    break;
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }
            switch (legs){
                case 1:
                    prefArrayR.add(LEGS_INDEX, perfect_clothes);
                    break;
                case 2:
                    prefArrayR.add(LEGS_INDEX, less_clothes);
                    break;
                case 3:
                    prefArrayR.add(LEGS_INDEX,less_clothes);
                    break;
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }
        }
        else if(temp>lower_point && temp < high_point){
            switch (head){
                case 1:
                    prefArrayR.add(HEAD_INDEX, more_clothes);
                    break;
                case 2:
                    prefArrayR.add(HEAD_INDEX, perfect_clothes);
                    break;
                case 3:
                    prefArrayR.add(HEAD_INDEX,less_clothes );
                    break;
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }
            switch (body){
                case 1:
                    prefArrayR.add(BODY_INDEX, more_clothes);
                    break;
                case 2:
                    prefArrayR.add(BODY_INDEX, perfect_clothes);
                    break;
                case 3:
                    prefArrayR.add(BODY_INDEX,less_clothes );
                    break;
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }
            switch (arms){
                case 1:
                    prefArrayR.add(ARMS_INDEX, more_clothes);
                    break;
                case 2:
                    prefArrayR.add(ARMS_INDEX, perfect_clothes);
                    break;
                case 3:
                    prefArrayR.add(ARMS_INDEX,less_clothes );
                    break;
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }
            switch (legs){
                case 1:
                    prefArrayR.add(LEGS_INDEX, more_clothes);
                    break;
                case 2:
                    prefArrayR.add(LEGS_INDEX, perfect_clothes);
                    break;
                case 3:
                    prefArrayR.add(LEGS_INDEX,less_clothes );
                    break;
                default:
                    // Reset pref ???
                    PreferenceUtils.resetDefClothes(context);
                    return null;
            }
        }
        else{throw new IllegalArgumentException();}
            return prefArrayR;
    }

    public static double applyWindChill( double temp , double windMPH){
        if(temp>50.0 || windMPH < 3.0){
            return -1000;}
        double windChill;
        windChill = (35.74 +  (0.6215 * temp) - (35.75 *(Math.pow(windMPH,0.16))) +  (0.4275*temp*(Math.pow(windMPH,0.16))));
        return windChill;
    }

    public static double applyHumid(double temp , double humid){
        if(temp < 80 || humid < .40){
            return -1000;
        }
       double humidPercent = humid * 100;

      //  -42.379 + 2.04901523(Tf) + 10.14333127(RH) – 0.22475541(Tf)(RH) – 6.83783×10**(-3)*(Tf**(2)) – 5.481717×10**(-2)*(RH**(2))
        //+ 1.22874×10**(-3)*(Tf**(2))*(RH) + 8.5282×10**(-4)*(Tf)*(RH**(2)) – 1.99×10**(-6)*(Tf**(2))*(RH**(2))

       double result= (-42.379 + 2.04901523*(temp) + 10.14333127*(humidPercent) - 0.22475541*(temp)*(humidPercent) - 6.83783
    * Math.pow(10,-3)*(Math.pow(temp,2)) - 5.481717
    *Math.pow(10,-2)*(Math.pow(humidPercent,2))+ 1.22874
    *Math.pow(10,-3)*(Math.pow(temp,2))
        *(humidPercent) + 8.5282*
        Math.pow(10,-4)*(temp)*(Math.pow(humidPercent,2)) - 1.99*
        Math.pow(10,-6)*(Math.pow(temp,2))*(Math.pow(humidPercent,2)));
       return result;
    }


}

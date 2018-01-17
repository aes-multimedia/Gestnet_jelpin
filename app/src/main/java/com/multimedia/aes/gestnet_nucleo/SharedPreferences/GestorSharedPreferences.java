package com.multimedia.aes.gestnet_nucleo.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class GestorSharedPreferences {
    //<---------------------------------------------------------------------------------->
    public static SharedPreferences getSharedPreferencesTecnico(Context context) {
        return context.getSharedPreferences("spTecnico", context.MODE_PRIVATE);
    }

    public static void setJsonTecnico(SharedPreferences sharedPreferences, JSONObject jsonObject) {
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.putString("tecnico", jsonObject.toString());
        spe.commit();
    }

    public static JSONObject getJsonTecnico(SharedPreferences sharedPreferences) throws JSONException {
        String s = sharedPreferences.getString("tecnico", "{}");
        return new JSONObject(s);
    }

    public static void clearSharedPreferencesTecnico(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferencesTecnico(context);
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.clear();
        spe.commit();
    }
    //<---------------------------------------------------------------------------------->
    public static SharedPreferences getSharedPreferencesMantenimiento(Context context) {
        return context.getSharedPreferences("spParte", context.MODE_PRIVATE);
    }

    public static void setJsonParte(SharedPreferences sharedPreferences, JSONObject jsonObject) {
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.putString("parte", jsonObject.toString());
        spe.commit();
    }

    public static JSONObject getJsonParte(SharedPreferences sharedPreferences) throws JSONException {
        String s = sharedPreferences.getString("parte", "{}");
        return new JSONObject(s);
    }

    public static void clearSharedPreferencesParte(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferencesMantenimiento(context);
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.clear();
        spe.commit();
    }
    //<---------------------------------------------------------------------------------->

}
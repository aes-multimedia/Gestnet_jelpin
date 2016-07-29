package com.multimedia.aes.gestnet_sgsv2.SharedPreferences;

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
        return context.getSharedPreferences("spMantenimiento", context.MODE_PRIVATE);
    }

    public static void setJsonMantenimiento(SharedPreferences sharedPreferences, JSONObject jsonObject) {
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.putString("mantenimiento", jsonObject.toString());
        spe.commit();
    }

    public static JSONObject getJsonMantenimiento(SharedPreferences sharedPreferences) throws JSONException {
        String s = sharedPreferences.getString("mantenimiento", "{}");
        return new JSONObject(s);
    }

    public static void clearSharedPreferencesMantenimiento(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferencesMantenimiento(context);
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.clear();
        spe.commit();
    }
    //<---------------------------------------------------------------------------------->
    public static SharedPreferences getSharedPreferencesPartes(Context context) {
        return context.getSharedPreferences("spPartes", context.MODE_PRIVATE);
    }

    public static void setJsonPartes(SharedPreferences sharedPreferences, JSONObject jsonObject) {
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.putString("partes", jsonObject.toString());
        spe.commit();
    }

    public static JSONObject getJsonPartes(SharedPreferences sharedPreferences) throws JSONException {
        String s = sharedPreferences.getString("partes", "{}");
        return new JSONObject(s);
    }

    public static void clearSharedPreferencesPartes(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferencesPartes(context);
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.clear();
        spe.commit();
    }
}
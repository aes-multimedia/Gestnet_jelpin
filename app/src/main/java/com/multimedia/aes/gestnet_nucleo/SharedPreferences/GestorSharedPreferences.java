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
    public static SharedPreferences getSharedPreferencesParte(Context context) {
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
        SharedPreferences sharedPreferences = getSharedPreferencesParte(context);
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.clear();
        spe.commit();
    }
    //<---------------------------------------------------------------------------------->
    public static SharedPreferences getSharedPreferencesDia(Context context) {
        return context.getSharedPreferences("spDia", context.MODE_PRIVATE);
    }

    public static void setJsonDia(SharedPreferences sharedPreferences, JSONObject jsonObject) {
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.putString("dia", jsonObject.toString());
        spe.commit();
    }

    public static JSONObject getJsonDia(SharedPreferences sharedPreferences) throws JSONException {
        String s = sharedPreferences.getString("dia", "{}");
        return new JSONObject(s);
    }

    public static void clearSharedPreferencesDia(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferencesDia(context);
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.clear();
        spe.commit();
    }//<---------------------------------------------------------------------------------->
    public static SharedPreferences getSharedPreferencesKilometros(Context context) {
        return context.getSharedPreferences("spKilometros", context.MODE_PRIVATE);
    }

    public static void setJsonKilometros(SharedPreferences sharedPreferences, JSONObject jsonObject) {
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.putString("kilometros", jsonObject.toString());
        spe.commit();
    }

    public static JSONObject getJsonKilometros(SharedPreferences sharedPreferences) throws JSONException {
        String s = sharedPreferences.getString("kilometros", "{}");
        return new JSONObject(s);
    }

    public static void clearSharedPreferencesKilometros(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferencesDia(context);
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.clear();
        spe.commit();
    }
    public static SharedPreferences getSharedPreferencesHoraCie(Context context) {
        return context.getSharedPreferences("HoCie", context.MODE_PRIVATE);
    }

    public static void setJsonHoraCie(SharedPreferences sharedPreferences, JSONObject jsonObject) {
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.putString("hora_cierre", jsonObject.toString());
        spe.commit();
    }

    public static JSONObject getJsonHoraCie(SharedPreferences sharedPreferences) throws JSONException {
        String s = sharedPreferences.getString("hora_cierre", "{}");
        return new JSONObject(s);
    }

    public static void clearSharedPreferencesHoraCie(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferencesDia(context);
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.clear();
        spe.commit();
    }
    //<---------------------------------------------------------------------------------->
    public static SharedPreferences getSharedPreferencesMinCie(Context context) {
        return context.getSharedPreferences("MiCie", context.MODE_PRIVATE);
    }

    public static void setJsonMinCie(SharedPreferences sharedPreferences, JSONObject jsonObject) {
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.putString("min_cierre", jsonObject.toString());
        spe.commit();
    }

    public static JSONObject getJsonMinCie(SharedPreferences sharedPreferences) throws JSONException {
        String s = sharedPreferences.getString("min_cierre", "{}");
        return new JSONObject(s);
    }

    public static void clearSharedPreferencesMinCie(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferencesDia(context);
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.clear();
        spe.commit();
    }
}
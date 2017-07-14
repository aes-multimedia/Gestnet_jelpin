package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.entidades.Parte;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by acp on 12/07/2017.
 */

public class GuardarParte {

    private static String Json;
    private static Context context;
    private static boolean bien=false;
    private static List<Parte> partes;

    public GuardarParte(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonParte();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonParte() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);


        Log.d(Json,"erererererererer");







    }
}

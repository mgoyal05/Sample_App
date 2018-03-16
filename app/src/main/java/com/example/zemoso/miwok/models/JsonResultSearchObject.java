package com.example.zemoso.miwok.models;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by zemoso on 15/3/18.
 */

public class JsonResultSearchObject extends RealmObject {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    public JsonResultSearchObject(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public JsonResultSearchObject() {
    }

    public static List<JsonResultSearchObject> convertJsonResultSearchObject(String jsonString) throws JSONException {

        Log.v("JSON RESULT", jsonString);
        return new GsonBuilder().create().fromJson(jsonString, new TypeToken<List<JsonResultSearchObject>>() {
        }.getType());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

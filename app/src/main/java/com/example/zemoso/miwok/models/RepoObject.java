package com.example.zemoso.miwok.models;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by zemoso on 5/3/18.
 */

public class RepoObject extends RealmObject {

    private String jsonString;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("id")
    private String htmlUrl;

    public RepoObject() {
    }

    public RepoObject(String jsonString) {
        this.jsonString = jsonString;
    }

    public static List<RepoObject> convertRepoObject(String jsonString) throws JSONException {

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray results = jsonObject.optJSONArray("items");

        return new GsonBuilder().create().fromJson(results.toString(), new TypeToken<List<RepoObject>>() {
        }.getType());

 /*       JSONArray results = jsonObject.getJSONArray("items");
        for
        repoObject.setFullName(results.getJSONObject(0).getString("full_name"));
        repoObject.setHtmlUrl(results.getJSONObject(0).getString("html_url"));


        return repoObject;*/
    }

    public String getFullName() {
            return fullName;
        }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}

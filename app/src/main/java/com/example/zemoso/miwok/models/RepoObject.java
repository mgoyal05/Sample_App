package com.example.zemoso.miwok.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;

/**
 * Created by zemoso on 5/3/18.
 */

public class RepoObject extends RealmObject {

    private String jsonString;

    private String fullName;

    private String htmlUrl;

    public RepoObject() {
    }

    public RepoObject(String jsonString) {
        this.jsonString = jsonString;
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

    public RepoObject convertRepoObject() throws JSONException {

        RepoObject repoObject = new RepoObject();

        JSONObject jsonObject = new JSONObject(jsonString);

        JSONArray results = jsonObject.getJSONArray("items");

        repoObject.setFullName(results.getJSONObject(0).getString("full_name"));
        repoObject.setHtmlUrl(results.getJSONObject(0).getString("html_url"));

        return repoObject;
    }
}

package com.example.zemoso.miwok.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.example.zemoso.miwok.models.RepoObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by zemoso on 5/3/18.
 */

public class RepoObjectRequest extends JsonRequest<RepoObject> {

    public RepoObjectRequest(int method, String url, String requestBody, Response.Listener<RepoObject> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    @Override
    protected Response<RepoObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            return Response.success(new RepoObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }
}
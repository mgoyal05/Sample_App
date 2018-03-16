package com.example.zemoso.miwok.utils;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.zemoso.miwok.models.JsonResultSearchObject;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by zemoso on 15/3/18.
 */

public class JsonResultSearchObjectRequest extends Request<List<JsonResultSearchObject>> {
    /**
     * Default charset for JSON request.
     */
    protected static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Content type for request.
     */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private final Response.Listener<List<JsonResultSearchObject>> mListener;
    private final String mRequestBody;

    public JsonResultSearchObjectRequest(int method, String url, String requestBody, Response.Listener<List<JsonResultSearchObject>> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        mRequestBody = requestBody;
    }

    @Override
    protected Response<List<JsonResultSearchObject>> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            Log.v("REQUEST", jsonString);
            Log.v("RESPONSE", response.data.toString());
            return Response.success(JsonResultSearchObject.convertJsonResultSearchObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            je.printStackTrace();
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(List<JsonResultSearchObject> response) {
        mListener.onResponse(response);
    }
}
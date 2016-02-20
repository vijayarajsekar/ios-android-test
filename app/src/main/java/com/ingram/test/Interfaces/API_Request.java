package com.ingram.test.Interfaces;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ingram.test.AppController;
import com.ingram.test.Utils.ApiConstants;
import com.ingram.test.Utils.Logger;

import java.util.Map;

/**
 * Created by vijayarajsekar on 19/2/16.
 */

public class API_Request implements ApiConstants {

    private String TAG = API_Request.class.getSimpleName();

    private static API_Request mInstance;

    private API_Response mResponseHandler;

    private Map<String, String> mParams;

    /**
     * Constructor
     */

    public static synchronized API_Request instance() {

        if (mInstance == null) {
            mInstance = new API_Request();
        }

        return mInstance;
    }

    public void GetMatchDetails(final API_Response mHandler) {

        StringRequest mStringRequestGet = new StringRequest(Request.Method.GET,
                DATA_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String mResponse) {

                        Logger.Print(TAG + " URL ", DATA_URL);
                        Logger.Print(TAG + " Response ", mResponse);

                        if (mHandler != null) {
                            mHandler.HandleResponse(REQ_DATA_URL, mResponse.toString());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError mError) {

                if (mHandler != null) {
                    Logger.Print(TAG + " ERROR ", mError.toString());
                    mHandler.HandleResponse(REQ_DATA_URL, "ERR" + mError.toString());
                }
            }
        });

        AppController.getInstance().addToRequestQueue(mStringRequestGet, TAG);
    }

    public void GetHomeTeam(final API_Response mHandler, final String _url) {

        StringRequest mStringRequestGet = new StringRequest(Request.Method.GET,
                _url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String mResponse) {

                        Logger.Print(TAG + " URL ", _url);
                        Logger.Print(TAG + " Response ", mResponse);

                        if (mHandler != null) {
                            mHandler.HandleResponse(REQ_TEAM_DETAILS, mResponse.toString());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError mError) {

                if (mHandler != null) {
                    Logger.Print(TAG + " ERROR ", mError.toString());
                    mHandler.HandleResponse(REQ_TEAM_DETAILS, "ERR" + mError.toString());
                }
            }
        });

        AppController.getInstance().addToRequestQueue(mStringRequestGet, TAG);
    }

    public void GetPlayerDetails(final API_Response mHandler, final String _url) {

        StringRequest mStringRequestGet = new StringRequest(Request.Method.GET,
                _url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String mResponse) {

                        Logger.Print(TAG + " URL ", _url);
                        Logger.Print(TAG + " Response ", mResponse);

                        if (mHandler != null) {
                            mHandler.HandleResponse(REQ_PLAYER_DETAILS, mResponse.toString());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError mError) {

                if (mHandler != null) {
                    Logger.Print(TAG + " ERROR ", mError.toString());
                    mHandler.HandleResponse(REQ_PLAYER_DETAILS, "ERR" + mError.toString());
                }
            }
        });

        AppController.getInstance().addToRequestQueue(mStringRequestGet, TAG);
    }

}
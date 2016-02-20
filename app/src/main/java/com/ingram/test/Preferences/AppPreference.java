package com.ingram.test.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ingram.test.Utils.ApiConstants;


/**
 * Created by VijayarajSekar on 16/3/15.
 */

public class AppPreference implements ApiConstants {

    private static final String TAG = AppPreference.class.getSimpleName();


    private static SharedPreferences mPreferences;


    private Editor mEditor;

    private Context mContext;

    private int PRIVATE_MODE = 0;


    /**
     * Constructor
     */

    private static AppPreference instance;

    public AppPreference(Context ctx) {
        this.mContext = ctx;
        mPreferences = mContext.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        mEditor = mPreferences.edit();
    }

    public boolean getIsReadOnline() {
        return mPreferences.getBoolean(mIsReadOnline, false);
    }

    public void setIsReadOnline(boolean values) {
        mEditor.putBoolean(mIsReadOnline, values);
        mEditor.commit();
    }

    public String getMatchDetail() {
        return mPreferences.getString(mMatchDetails, "");
    }

    public void setMatchDetails(String values) {
        mEditor.putString(mMatchDetails, values);
        mEditor.commit();
    }
}

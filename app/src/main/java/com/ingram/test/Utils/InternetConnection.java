package com.ingram.test.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by vijayarajsekar on 19/2/16.
 */
public class InternetConnection {

    private String TAG = InternetConnection.class.getSimpleName();

    private Context _context;

    public InternetConnection(Context context) {
        this._context = context;
    }

    public boolean isConnectingToInternet() {

        try {

            ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
            }

        } catch (Exception ex) {
            Logger.Print(TAG, ex.getMessage().toString());
        }

        return false;
    }
}

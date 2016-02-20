package com.ingram.test.Utils;

import android.content.Context;
import android.widget.Toast;

import com.ingram.test.AppController;
import com.ingram.test.R;

/**
 * Created by vijayarajsekar on 19/2/16.
 */

public class Logger {


    public static void ShowToast(String msg) {
        Toast.makeText(AppController.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void NoInternet(Context _ctx) {
        Toast.makeText(_ctx, _ctx.getResources().getString(R.string.str_no_internet), Toast.LENGTH_SHORT).show();
    }

    public static void Print(String tag, String msg) {
        System.out.println("~ ~ ~ ~ ~ " + tag + "  < = = = > " + msg);
    }
}

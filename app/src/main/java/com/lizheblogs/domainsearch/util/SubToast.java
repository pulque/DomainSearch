package com.lizheblogs.domainsearch.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by LiZhe on 2017-10-09.
 * com.lizheblogs.domainsearch.util
 */

public class SubToast {

    public static void showNormalToast(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}

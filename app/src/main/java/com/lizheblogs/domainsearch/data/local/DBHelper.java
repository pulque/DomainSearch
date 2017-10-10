package com.lizheblogs.domainsearch.data.local;

import android.content.Context;

import com.lizheblogs.domainsearch.common.SubApplication;

/**
 * Created by LiZhe on 2017-10-10.
 * com.lizheblogs.domainsearch.data.local
 */

public class DBHelper {

    private static final String DOMAIN_DB = "domain_db";
    private static DBHelper dbHelper = null;
    private DaoSession daoSession;

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public void initDBHelper(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DOMAIN_DB, null);
        daoSession = new DaoMaster(helper.getWritableDatabase()).newSession();
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            initDBHelper(SubApplication.getInstance());
        }
        return daoSession;
    }
}

package com.example.iching.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.iching.app.model.DivinationObject;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DivinationDatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "divination.sqlite";

    private static final int DATABASE_VERSION = 1;
    private RuntimeExceptionDao postDAO = null;

    public DivinationDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, DivinationObject.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
    }

    public RuntimeExceptionDao<DivinationObject, Long> getPostDAO() {
        if (null == postDAO) {
            postDAO = getRuntimeExceptionDao(DivinationObject.class);
        }
        return postDAO;
    }
}

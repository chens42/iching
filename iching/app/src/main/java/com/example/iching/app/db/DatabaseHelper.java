package com.example.iching.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.iching.app.model.Hex;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "hexagrams.sqlite";

    private static final int DATABASE_VERSION = 1;
    private RuntimeExceptionDao postDAO = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Hex.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {

    }

    public RuntimeExceptionDao<Hex, Long> getPostDAO() {
        if (null == postDAO) {
            postDAO = getRuntimeExceptionDao(Hex.class);
        }
        return postDAO;
    }
}

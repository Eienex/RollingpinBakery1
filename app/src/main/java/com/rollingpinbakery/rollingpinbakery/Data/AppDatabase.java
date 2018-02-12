package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


/**
 * Created by rudst on 2/12/2018.
 */

@Database(entities = {Customer.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    //Abstract DAO Classes (add as we go for each table/Class we create)
    public abstract CustomerDao customerDao();


    public static AppDatabase getAppDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "rolling-pin-database")
                    .allowMainThreadQueries()//THis is not production ready
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {INSTANCE = null;}
}

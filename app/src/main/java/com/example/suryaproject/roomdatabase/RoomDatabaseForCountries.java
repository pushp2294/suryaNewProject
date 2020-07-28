package com.example.suryaproject.roomdatabase;

import android.app.Application;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.suryaproject.model.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class RoomDatabaseForCountries extends RoomDatabase {

    public abstract DaoInterface getPromptReminderDaoInstance();
    private static RoomDatabaseForCountries roomDatabaseForCountries;

    public static synchronized RoomDatabaseForCountries providesRoomDatabase(Application mApplication) {
        if (roomDatabaseForCountries == null) {
            roomDatabaseForCountries = Room.databaseBuilder(mApplication, RoomDatabaseForCountries.class, "CNGNEW").build();
            return roomDatabaseForCountries;
        }
        else {
            return roomDatabaseForCountries;
        }
    }
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}

package com.example.suryaproject.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.suryaproject.model.UserEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface DaoInterface {
    @Query("SELECT * from UserTable")
    LiveData<List<UserEntity>> getAllList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertanewData(List<UserEntity> userEntities);

    @Query("DELETE FROM UserTable")
    Single<Integer> deleteAll();


}

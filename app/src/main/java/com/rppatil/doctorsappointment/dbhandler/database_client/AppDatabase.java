package com.rppatil.doctorsappointment.dbhandler.database_client;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.rppatil.doctorsappointment.dbhandler.dao.DoctorsDataDao;
import com.rppatil.doctorsappointment.dbhandler.entity.DoctorsData;

@Database(entities = {DoctorsData.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DoctorsDataDao doctorsDataDao();
}

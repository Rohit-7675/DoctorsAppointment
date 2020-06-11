package com.rppatil.doctorsappointment.dbhandler.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.rppatil.doctorsappointment.dbhandler.entity.DoctorsData;

import java.util.List;

@Dao
public interface DoctorsDataDao {
    @Query("SELECT COUNT(*) FROM DoctorsData")
    int getcount();
    @Insert
    void insert(DoctorsData doctorsData);
    @Query("SELECT * FROM DoctorsData")
    List<DoctorsData> getAll();
    @Query("DELETE FROM DoctorsData")
    void deleteAll();
    @Query("SELECT * FROM DoctorsData where userid = :userid")
    List<DoctorsData> getAllDetails(int userid);
}

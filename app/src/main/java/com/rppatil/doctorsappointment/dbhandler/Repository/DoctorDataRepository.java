package com.rppatil.doctorsappointment.dbhandler.Repository;

import android.content.Context;
import android.util.Log;

import com.rppatil.doctorsappointment.dbhandler.database_client.DatabaseClient;
import com.rppatil.doctorsappointment.dbhandler.entity.DoctorsData;

import java.util.List;

public class DoctorDataRepository {
    private Context mCtx;
    private static DoctorDataRepository mInstance;

    private DoctorDataRepository(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized DoctorDataRepository getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DoctorDataRepository(mCtx);
        }
        return mInstance;
    }

    public void insert(DoctorsData doctorsData){
        //adding to database
        DatabaseClient.getInstance(mCtx).getAppDatabase().doctorsDataDao().insert(doctorsData);
        Log.d("alphabet_data","inserted");
    }

    public List<DoctorsData> getAll(){
        return DatabaseClient.getInstance(mCtx).getAppDatabase().doctorsDataDao().getAll();
    }

    public int  getCount(){
        return DatabaseClient.getInstance(mCtx).getAppDatabase().doctorsDataDao().getcount();
    }
    public void deleteAll(){
        DatabaseClient.getInstance(mCtx).getAppDatabase().doctorsDataDao().deleteAll();
    }

    public List<DoctorsData> getAllDetails(int useid){
        return DatabaseClient.getInstance(mCtx).getAppDatabase().doctorsDataDao().getAllDetails(useid);
    }

}

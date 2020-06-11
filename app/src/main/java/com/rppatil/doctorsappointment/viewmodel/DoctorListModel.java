package com.rppatil.doctorsappointment.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rppatil.doctorsappointment.R;
import com.rppatil.doctorsappointment.connectivity.ApiClient;
import com.rppatil.doctorsappointment.connectivity.NetworkClient;
import com.rppatil.doctorsappointment.dbhandler.Repository.DoctorDataRepository;
import com.rppatil.doctorsappointment.dbhandler.entity.DoctorsData;
import com.rppatil.doctorsappointment.model.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorListModel extends ViewModel {
    public int useridd;
    public String email;
    public String fname;
    public String doctor_name;
    public String lname;
    public String profile_image;
    public String doctor_street;
    public String doctor_city;
    public String doctor_state;
    public String doctor_zip;
    public String doctor_speciality;
    public String doctor_online_status;

    public MutableLiveData<List<DoctorListModel>> mutableLiveData = new MutableLiveData<>();
    private List<DoctorListModel> arrayList;
    private List<DoctorsData> doctorLists;
    private MutableLiveData<Boolean> _isViewLoading = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isViewLoading = _isViewLoading;

    public String getImageurl() {
        return profile_image;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadimage(ImageView imageView, String imageUrl) {
        if (imageUrl == null || imageUrl.equals("")) {
            Glide.with(imageView.getContext()).load(R.drawable.empty_user).apply(RequestOptions.circleCropTransform()).into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(imageView);
        }
    }

    @BindingAdapter({"imagePath"})
    public static void loadDoctorStatus(ImageView imageView, String imagePath) {
        if (imagePath != null && imagePath.equals("Y")) {
            Glide.with(imageView.getContext()).load(R.drawable.ic_online).into(imageView);
        }
    }
    public DoctorListModel() {
    }

    public DoctorListModel(DoctorsData doctorList) {
        this.useridd = doctorList.getUserid();
        this.email = doctorList.getEmail();
        this.fname = doctorList.getFname();
        this.doctor_name = doctorList.getDoctor_name();
        this.lname = doctorList.getLname();
        this.profile_image = doctorList.getProfile_image();
        this.doctor_street = doctorList.getDoctor_street();
        this.doctor_city = doctorList.getDoctor_city();
        this.doctor_state = doctorList.getDoctor_state();
        this.doctor_zip = doctorList.getDoctor_zip();
        this.doctor_speciality = doctorList.getDoctor_speciality();
        this.doctor_online_status = doctorList.getDoctor_online_status();
    }


    public MutableLiveData<List<DoctorListModel>> getMutableLiveData(Context context) {

        arrayList = new ArrayList<>();
        if (DoctorDataRepository.getInstance(context).getCount() > 0) {
            doctorLists = new ArrayList<>();
            doctorLists = DoctorDataRepository.getInstance(context).getAll();
            for (int i = 0; i < doctorLists.size(); i++) {
                DoctorsData doctorsData = doctorLists.get(i);
                DoctorListModel doctorListModel = new DoctorListModel(doctorsData);
                arrayList.add(doctorListModel);
                mutableLiveData.setValue(arrayList);
            }
        } else {
            ApiClient api = NetworkClient.getInstance().getApiClient();
            Call<ServerResponse> call = api.getdoctorsdata(96250);
            _isViewLoading.postValue(true);
            call.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    ServerResponse serverResponse = response.body();
                    if (serverResponse.status.equals("1")) {
                        _isViewLoading.postValue(false);
                        doctorLists = new ArrayList<>();
                        doctorLists = serverResponse.getResult();
                        Log.e("ResultData", serverResponse.getResult().toString());
                        for (int i = 0; i < doctorLists.size(); i++) {
                            DoctorsData doctorsData = doctorLists.get(i);
                            DoctorListModel doctorListModel = new DoctorListModel(doctorsData);
                            arrayList.add(doctorListModel);
                            mutableLiveData.setValue(arrayList);
                            AddData(doctorsData, context);
                        }
                    } else {
                        _isViewLoading.postValue(false);
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    _isViewLoading.postValue(false);
                }
            });
        }
        return mutableLiveData;
    }

    public void AddData(DoctorsData doctorList, Context context) {
        DoctorsData doctorsData = new DoctorsData();
        doctorsData.setUserid(doctorList.getUserid());
        doctorsData.setEmail(doctorList.getEmail());
        doctorsData.setFname(doctorList.getFname());
        doctorsData.setDoctor_name(doctorList.getDoctor_name());
        doctorsData.setLname(doctorList.getLname());
        doctorsData.setProfile_image(doctorList.getProfile_image());
        doctorsData.setDoctor_street(doctorList.getDoctor_street());
        doctorsData.setDoctor_city(doctorList.getDoctor_city());
        doctorsData.setDoctor_state(doctorList.getDoctor_state());
        doctorsData.setDoctor_zip(doctorList.getDoctor_zip());
        doctorsData.setDoctor_speciality(doctorList.getDoctor_speciality());
        doctorsData.setDoctor_online_status(doctorList.getDoctor_online_status());
        DoctorDataRepository.getInstance(context).insert(doctorsData);
    }
}

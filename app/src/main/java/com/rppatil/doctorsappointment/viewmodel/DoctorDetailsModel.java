package com.rppatil.doctorsappointment.viewmodel;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.rppatil.doctorsappointment.R;
import com.rppatil.doctorsappointment.dbhandler.Repository.DoctorDataRepository;
import com.rppatil.doctorsappointment.dbhandler.entity.DoctorsData;

import java.util.ArrayList;
import java.util.List;

public class DoctorDetailsModel extends ViewModel {
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

    public MutableLiveData<List<DoctorDetailsModel>> mutableLiveData = new MutableLiveData<>();
    private List<DoctorDetailsModel> arrayList;
    private List<DoctorsData> doctorLists;

    @BindingAdapter({"imageUrl_details"})
    public static void loadimage(ImageView imageView, String imageUrl) {
        if (imageUrl == null || imageUrl.equals("")) {
            Glide.with(imageView.getContext()).load(R.drawable.empty_user).into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
        }
    }

    @BindingAdapter({"imagePath_details"})
    public static void loadDoctorStatus(ImageView imageView, String imagePath) {
        if (imagePath != null && imagePath.equals("Y")) {
            Glide.with(imageView.getContext()).load(R.drawable.ic_online).into(imageView);
        }
    }

    public DoctorDetailsModel() {
    }

    public DoctorDetailsModel(DoctorsData doctorList) {
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


    public MutableLiveData<List<DoctorDetailsModel>> getDetails(Context context, int id) {
        arrayList = new ArrayList<>();
        doctorLists = new ArrayList<>();
        doctorLists = DoctorDataRepository.getInstance(context).getAllDetails(id);
        for (int i = 0; i < doctorLists.size(); i++) {
            DoctorsData doctorsData = doctorLists.get(i);
            DoctorDetailsModel doctorDetailsModel = new DoctorDetailsModel(doctorsData);
            arrayList.add(doctorDetailsModel);
            mutableLiveData.setValue(arrayList);
        }
        return mutableLiveData;
    }

}


package com.rppatil.doctorsappointment.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rppatil.doctorsappointment.R;
import com.rppatil.doctorsappointment.viewmodel.DoctorDetailsModel;

import java.util.List;

public class DoctorDetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private DoctorDetailsModel doctorDetailsModel;
    private DoctorDetailsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        recyclerview = findViewById(R.id.recyclerView);
        doctorDetailsModel = ViewModelProviders.of(this).get(DoctorDetailsModel.class);
        String ID = getIntent().getStringExtra("USER_ID");
        SetObserver(Integer.parseInt(ID));
    }

    public void SetObserver(int id) {
        doctorDetailsModel.getDetails(getApplicationContext(), id).observe(this, new Observer<List<DoctorDetailsModel>>() {
            @Override
            public void onChanged(List<DoctorDetailsModel> doctorDetailsModels) {
                adapter = new DoctorDetailsAdapter(doctorDetailsModels, DoctorDetailsActivity.this);
                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerview.setAdapter(adapter);
            }
        });
    }

}

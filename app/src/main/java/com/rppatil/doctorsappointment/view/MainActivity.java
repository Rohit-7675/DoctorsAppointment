package com.rppatil.doctorsappointment.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rppatil.doctorsappointment.ItemClickListener;
import com.rppatil.doctorsappointment.R;
import com.rppatil.doctorsappointment.dbhandler.Repository.DoctorDataRepository;
import com.rppatil.doctorsappointment.viewmodel.DoctorListModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerview;
    private DoctorListModel myListViewModel;
    private ProgressBar progressBar;
    private DoctorListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.swipeContainer);
        myListViewModel = ViewModelProviders.of(this).get(DoctorListModel.class);
        SetProgressBar();
        SetObserver();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SetProgressBar();
                SetObserver();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void SetProgressBar()
    {
        myListViewModel.isViewLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    public void SetObserver() {
        DoctorDataRepository.getInstance(getApplicationContext()).deleteAll();
        myListViewModel.getMutableLiveData(getApplicationContext()).observe(this, new Observer<List<DoctorListModel>>() {
            @Override
            public void onChanged(List<DoctorListModel> myListViewModels) {
                adapter = new DoctorListAdapter(myListViewModels, MainActivity.this, MainActivity.this::onItemClicked);
                recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerview.setAdapter(adapter);
            }
        });
    }


    @Override
    public void onItemClicked(View vh, int item, int pos) {
        Intent intent = new Intent(getBaseContext(), DoctorDetailsActivity.class);
        intent.putExtra("USER_ID", String.valueOf(item));
        startActivity(intent);
        //Toast.makeText(this, String.valueOf(item), Toast.LENGTH_SHORT).show();
    }
}
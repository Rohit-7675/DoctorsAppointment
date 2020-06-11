package com.rppatil.doctorsappointment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rppatil.doctorsappointment.R;
import com.rppatil.doctorsappointment.databinding.DoctorDetailsBinding;
import com.rppatil.doctorsappointment.viewmodel.DoctorDetailsModel;

import java.util.List;

public class DoctorDetailsAdapter extends RecyclerView.Adapter<DoctorDetailsAdapter.ViewHolder> {
    private List<DoctorDetailsModel> doctorList;
    private Context context;
    private LayoutInflater layoutInflater;

    public DoctorDetailsAdapter(List<DoctorDetailsModel> doctorList, Context context) {
        this.doctorList = doctorList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater==null){
            layoutInflater=LayoutInflater.from(parent.getContext());
        }
        DoctorDetailsBinding myListBinding= DataBindingUtil.inflate(layoutInflater, R.layout.doctordetails,parent,false);
        return new ViewHolder(myListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoctorDetailsModel myListViewModel=doctorList.get(position);
        holder.bind(myListViewModel);
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private DoctorDetailsBinding binding;
        public ViewHolder(@NonNull DoctorDetailsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void bind(DoctorDetailsModel myli){
            this.binding.setDoctordetailsmodel(myli);
            binding.executePendingBindings();
        }
        public DoctorDetailsBinding getBinding(){
            return binding;
        }
    }

}


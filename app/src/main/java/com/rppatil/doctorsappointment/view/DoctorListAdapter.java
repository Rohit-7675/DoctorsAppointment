package com.rppatil.doctorsappointment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.rppatil.doctorsappointment.ItemClickListener;
import com.rppatil.doctorsappointment.R;
import com.rppatil.doctorsappointment.databinding.MyListBinding;
import com.rppatil.doctorsappointment.viewmodel.DoctorListModel;

import java.util.ArrayList;
import java.util.List;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder> {
    private List<DoctorListModel> doctorList;
    private Context context;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    public DoctorListAdapter(List<DoctorListModel> doctorList, Context context, ItemClickListener itemClickListener) {
        this.doctorList = doctorList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater==null){
            layoutInflater=LayoutInflater.from(parent.getContext());
        }
        MyListBinding myListBinding= DataBindingUtil.inflate(layoutInflater, R.layout.doctorlist,parent,false);
        return new ViewHolder(myListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoctorListModel myListViewModel=doctorList.get(position);
        holder.bind(myListViewModel, itemClickListener, position);
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private MyListBinding myListBinding;
        public ViewHolder(@NonNull MyListBinding myListBinding) {
            super(myListBinding.getRoot());
            this.myListBinding=myListBinding;
        }
        public void bind(DoctorListModel myli){
            this.myListBinding.setDoctorlistmodel(myli);
            myListBinding.executePendingBindings();
        }
        public MyListBinding getMyListBinding(){
            return myListBinding;
        }
        public void bind(DoctorListModel myli, ItemClickListener itemClickListener, int position) {
            this.myListBinding.setDoctorlistmodel(myli);
            this.myListBinding.executePendingBindings();
            myListBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClickListener !=null){
                        itemClickListener.onItemClicked(myListBinding.getRoot(), myli.useridd, position);
                    }
                }
            });
        }
    }

}

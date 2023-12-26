package com.itcraftsolution.romanager.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itcraftsolution.romanager.databinding.RvSampleCustomerViewBinding;

public class HomeCustomerRecyclerAdapter extends RecyclerView.Adapter<HomeCustomerRecyclerAdapter.viewHolder> {
    private Context context;


    @NonNull
    @Override
    public HomeCustomerRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCustomerRecyclerAdapter.viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private RvSampleCustomerViewBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvSampleCustomerViewBinding.bind(itemView);

        }
    }
}

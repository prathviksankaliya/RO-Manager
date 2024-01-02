package com.itcraftsolution.romanager.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.itcraftsolution.romanager.Models.CustomerModel;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.RvSampleCustomerViewBinding;

import java.util.List;

public class HomeCustomerRecyclerAdapter extends RecyclerView.Adapter<HomeCustomerRecyclerAdapter.viewHolder> {
    private Context context;
    private List<CustomerModel> list;

    public HomeCustomerRecyclerAdapter(Context context, List<CustomerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeCustomerRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_sample_customer_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCustomerRecyclerAdapter.viewHolder holder, int position) {
        CustomerModel model = list.get(position);
        holder.binding.txCustomerRvNameChar.setText(model.getCust_name().substring(0, 1));
        holder.binding.txCustomerName.setText(model.getCust_name());
//        holder.binding.txCustomerStatus.setText(model.getCust_status());
//        holder.binding.txCustomerMsg.setText(model.getCust_msg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private RvSampleCustomerViewBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvSampleCustomerViewBinding.bind(itemView);
        }
    }
}

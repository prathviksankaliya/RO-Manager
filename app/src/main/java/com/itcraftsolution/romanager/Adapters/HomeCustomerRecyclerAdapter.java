package com.itcraftsolution.romanager.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.itcraftsolution.romanager.Activities.CustomerTransactionActivity;
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
        holder.binding.txCustomerMsg.setText(model.getCust_msg());
        if(model.getTotal_balance() <= 0){
            holder.binding.txCustomerPrice.setTextColor(context.getResources().getColor(R.color.red, null));
        }else{
            holder.binding.txCustomerPrice.setTextColor(context.getResources().getColor(R.color.green, null));
        }
        holder.binding.txCustomerPrice.setText("₹ " + String.valueOf(model.getTotal_balance()));
        holder.binding.txCustomerStatus.setText(model.getMoney_status());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CustomerTransactionActivity.class)
                        .putExtra("custName", model.getCust_name())
                        .putExtra("moneyStatus", model.getMoney_status())
                        .putExtra("totalBalance", model.getTotal_balance())
                        .putExtra("plantId", model.getPlant_id())
                        .putExtra("custId", model.getCust_id()));

            }
        });
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

package com.itcraftsolution.romanager.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itcraftsolution.romanager.Models.CustomerTransactionModel;
import com.itcraftsolution.romanager.R;
import com.itcraftsolution.romanager.databinding.RvCustomerTransactionSampleBinding;

import java.util.List;

public class CustomerTransactionRecyclerAdapter extends RecyclerView.Adapter<CustomerTransactionRecyclerAdapter.viewHolder> {

    private Context context;
    private List<CustomerTransactionModel> list;

    public CustomerTransactionRecyclerAdapter(Context context, List<CustomerTransactionModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CustomerTransactionRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_customer_transaction_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerTransactionRecyclerAdapter.viewHolder holder, int position) {
        CustomerTransactionModel model = list.get(position);
        if(model.getDebit() != 0){
            holder.binding.layoutReceivedTransaction.setVisibility(View.GONE);
            holder.binding.txTransactionReceivedLeftBalance.setVisibility(View.GONE);
            holder.binding.layoutGivenTransaction.setVisibility(View.VISIBLE);
            String parts[] = model.getCust_tra_date().split("-");
            String date = parts[0].trim();
            String time = parts[1].trim();
            holder.binding.txTransactionGivenTime.setText(time);
            holder.binding.txTransactionDate.setText(date);
            holder.binding.txTransactionGivenPrice.setText(String.valueOf("₹ " +model.getDebit()));
            holder.binding.txTransactionGivenLeftBalance.setText(String.valueOf("₹ " +model.getTotal() + " DUE"));
            if(model.getJag() != 0 && model.getBottle() != 0){
                holder.binding.txNumOfJags2.setVisibility(View.VISIBLE);
                holder.binding.txNumOfBottles2.setVisibility(View.VISIBLE);
                holder.binding.txJagGivenCount.setText(String.valueOf(model.getJag()));
                holder.binding.txBottleGivenCount.setText(String.valueOf(model.getBottle()));
            }else if(model.getBottle() != 0){
                holder.binding.txNumOfBottles2.setVisibility(View.VISIBLE);
                holder.binding.txNumOfJags2.setVisibility(View.GONE);
                holder.binding.txJagGivenCount.setVisibility(View.GONE);
                holder.binding.txBottleGivenCount.setText(String.valueOf(model.getBottle()));
            }else{
                holder.binding.txNumOfJags2.setVisibility(View.VISIBLE);
                holder.binding.txNumOfBottles2.setVisibility(View.GONE);
                holder.binding.txBottleGivenCount.setVisibility(View.GONE);
                holder.binding.txJagGivenCount.setText(String.valueOf(model.getJag()));
            }
            if(model.getNote().isEmpty() || model.getNote() == null){
                holder.binding.txTransactionGivenDesc.setVisibility(View.GONE);
            }else{
                holder.binding.txTransactionGivenDesc.setVisibility(View.VISIBLE);
                holder.binding.txTransactionGivenDesc.setText(model.getNote());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        RvCustomerTransactionSampleBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvCustomerTransactionSampleBinding.bind(itemView);
        }
    }
}

package com.cindodcindy.vieroshoesadmin.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.cindodcindy.vieroshoesadmin.R;
import com.cindodcindy.vieroshoesadmin.view.model.ModelForItem;

import java.util.List;

public class AdapterChatCustomer extends RecyclerView.Adapter<AdapterChatCustomer.ChatCustomerChild> {


    public Context context;

    public List<ModelForItem> modelForItems;

    public AdapterChatCustomer(Context context, List<ModelForItem> modelForItems){
        this.context=context;
        this.modelForItems=modelForItems;
    }
    @NonNull

    @Override
    public ChatCustomerChild onCreateViewHolder( ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View views= layoutInflater.inflate(R.layout.chat_customer, parent, false);
        ChatCustomerChild chatCustomerChild =new ChatCustomerChild(views);
        return chatCustomerChild;
    }

    @Override
    public void onBindViewHolder( ChatCustomerChild holder, int position) {
        final ModelForItem modelForItem = modelForItems.get(position);
        holder.textView_chat_customer.setText(modelForItems.get(position).getItem_satu());


    }

    @Override
    public int getItemCount() {
        return modelForItems.size();
    }

    public class ChatCustomerChild extends RecyclerView.ViewHolder{

        public TextView textView_chat_customer;

        public ChatCustomerChild( View itemView) {
            super(itemView);
            textView_chat_customer=itemView.findViewById(R.id.tv_chat_customer);

        }
    }
}

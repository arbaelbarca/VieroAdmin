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

public class AdapterChatAdmin extends RecyclerView.Adapter<AdapterChatAdmin.AdminChild> {

    public Context context;

    public List<ModelForItem> modelForItems;

    public AdapterChatAdmin(Context context, List<ModelForItem> modelForItems){
        this.context=context;
        this.modelForItems=modelForItems;
    }

    @NonNull
    @Override
    public AdminChild onCreateViewHolder( ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View views= layoutInflater.inflate(R.layout.chat_merchant_admin, parent, false);
        AdminChild adminChild=new AdminChild(views);

        return adminChild;
    }

    @Override
    public void onBindViewHolder( AdminChild holder, int position) {
        final ModelForItem modelForItem = modelForItems.get(position);
        holder.textView_chat_admin.setText(modelForItems.get(position).getItem_satu());

    }

    @Override
    public int getItemCount() {
        return  modelForItems.size();
    }

    public class AdminChild extends RecyclerView.ViewHolder{

        public TextView textView_chat_admin;

        public AdminChild( View itemView) {
            super(itemView);

            textView_chat_admin=itemView.findViewById(R.id.tv_chat_admin);
        }
    }
}

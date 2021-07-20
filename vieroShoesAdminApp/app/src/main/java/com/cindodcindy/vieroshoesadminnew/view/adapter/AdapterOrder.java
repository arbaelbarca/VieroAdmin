package com.cindodcindy.vieroshoesadminnew.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.cindodcindy.vieroshoesadminnew.R;
import com.cindodcindy.vieroshoesadminnew.view.model.ModelForItem;

import java.util.List;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.OrderChild> {

    public Context context;

    public List<ModelForItem> modelForItems;

    public AdapterOrder(Context context, List<ModelForItem> modelForItems){
        this.context=context;
        this.modelForItems=modelForItems;
    }

    @Override
    public OrderChild onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View views= layoutInflater.inflate(R.layout.order_list, parent, false);
        OrderChild orderChild=new OrderChild(views);

        return orderChild;
    }

    @Override
    public void onBindViewHolder( OrderChild holder, int position) {
        final ModelForItem modelForItem = modelForItems.get(position);
        holder.textView_nama_barang_order.setText(modelForItems.get(position).getItem_satu());

    }

    @Override
    public int getItemCount() {
        return modelForItems.size();
    }

    public class OrderChild extends RecyclerView.ViewHolder{

        private TextView textView_nama_barang_order;

        public OrderChild( View itemView) {
            super(itemView);
            textView_nama_barang_order=itemView.findViewById(R.id.tv_nama_barang_order);
        }
    }
}

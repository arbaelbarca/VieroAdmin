package com.cindodcindy.vieroshoesadmin.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.cindodcindy.vieroshoesadmin.R;
import com.cindodcindy.vieroshoesadmin.view.model.ModelForItem;
import com.cindodcindy.vieroshoesadmin.view.model.StockData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.HomeChild> {

    public Context context;

    public List<StockData> stockData;

    public AdapterHome(Context context, List<StockData> stockData){
        this.context=context;
        this.stockData=stockData;
    }

    @Override
    public HomeChild onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View views= layoutInflater.inflate(R.layout.sepatu_item, parent, false);
        HomeChild homeChild=new HomeChild(views);


        return homeChild;
    }

    @Override
    public void onBindViewHolder( HomeChild holder, int position) {
        final StockData stockDatas = stockData.get(position);
        holder.textView_nama_aitem.setText(stockData.get(position).getNamaStock());
        Picasso.with(context)
                .load(stockDatas.getmImgUrl())
                .into(holder.imageView_stockData);



    }

    @Override
    public int getItemCount() {
        return stockData.size();
    }

    public class HomeChild extends RecyclerView.ViewHolder{
       private TextView textView_nama_aitem;
       private ImageView imageView_stockData;


        public HomeChild( View itemView) {
            super(itemView);
            textView_nama_aitem=itemView.findViewById(R.id.tv_nama_barang);
            imageView_stockData=itemView.findViewById(R.id.iv_stock_data);

                   }
    }
}

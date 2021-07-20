package com.cindodcindy.vieroshoesadminnew.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.cindodcindy.vieroshoesadminnew.R;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.HomeChild> {

    public Context context;
/*
    public List<StockData> stockData;

    public AdapterHome(Context context, List<StockData> stockData){
        this.context=context;
        this.stockData=stockData;
    }

 */

    ArrayList<String> imageList;

    public  AdapterHome(ArrayList<String> imageList, Context context){
        this.imageList=imageList;
        this.context=context;

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
        /*
        final StockData stockDatas = stockData.get(position);
        holder.textView_nama_aitem.setText(stockData.get(position).getNamaStock());
        holder.textView_harga.setText(stockData.get(position).getHargaStock());
        holder.textView_ukuran.setText(stockData.get(position).getUkuranStock());
        holder.textView_lokasi.setText(stockData.get(position).getLokasiStock());
       /*
        Picasso.(context)
                .load(stockDatas.getmImgUrl())
                .into(holder.imageView_stockData);

        */
/*
        Picasso.get()
                .load(stockDatas.getmImgUrl())
                .resize(50, 50)
                .centerCrop()
                .into(holder.imageView_stockData);



 */
        Glide.with(holder.itemView.getContext()).load(imageList.get(position)).into(holder.imageView_stockData);
    }

    @Override
    public int getItemCount() {
       // return stockData.size();
         return imageList.size();
    }

    public class HomeChild extends RecyclerView.ViewHolder{
       private TextView textView_nama_aitem, textView_harga, textView_ukuran, textView_lokasi;
       private ImageView imageView_stockData;



        public HomeChild( View itemView) {
            super(itemView);
            textView_nama_aitem=itemView.findViewById(R.id.tv_nama_barang);
            textView_harga=itemView.findViewById(R.id.tv_harga);
            textView_ukuran=itemView.findViewById(R.id.tv_ukuran);
            textView_lokasi=itemView.findViewById(R.id.tv_lokasi);
            imageView_stockData=itemView.findViewById(R.id.iv_stock_data);

                   }
    }
}

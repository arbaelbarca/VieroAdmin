package com.cindodcindy.vieroshoesadminnew.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cindodcindy.vieroshoesadminnew.R;
import com.cindodcindy.vieroshoesadminnew.view.model.ModelForItem;

import java.util.List;

public class AdapterPayment extends RecyclerView.Adapter<AdapterPayment.PaymentChild> {

    public Context context;

    public List<ModelForItem> modelForItems;

    public AdapterPayment(Context context, List<ModelForItem> modelForItems) {
        this.context = context;
        this.modelForItems = modelForItems;
    }

    @NonNull

    @Override
    public PaymentChild onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View views = layoutInflater.inflate(R.layout.payment_item, parent, false);
        PaymentChild paymentChild = new PaymentChild(views);


        return paymentChild;
    }

    @Override
    public void onBindViewHolder(PaymentChild holder, int position) {

        final ModelForItem modelForItem = modelForItems.get(position);
        holder.textView_nm_brg_payment.setText(modelForItems.get(position).getItem_satu());


    }

    @Override
    public int getItemCount() {
        return modelForItems.size();
    }

    public class PaymentChild extends RecyclerView.ViewHolder {

        private TextView textView_nm_brg_payment;

        public PaymentChild(View itemView) {
            super(itemView);

            textView_nm_brg_payment = itemView.findViewById(R.id.tv_nama_barang_payment);
        }
    }
}

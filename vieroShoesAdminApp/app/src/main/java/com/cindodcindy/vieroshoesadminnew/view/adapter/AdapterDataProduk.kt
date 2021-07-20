package com.cindodcindy.vieroshoesadminnew.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cindodcindy.vieroshoesadminnew.R
import com.cindodcindy.vieroshoesadminnew.view.model.DataProduk
import com.cindodcindy.vieroshoesadminnew.view.onclick.OnClickItemRv
import com.cindodcindy.vieroshoesadminnew.view.utils.loadImageUrl
import com.cindodcindy.vieroshoesadminnew.view.utils.showView
import kotlinx.android.synthetic.main.layout_item_dataproduk.view.*

class AdapterDataProduk(
    val dataProdukList: MutableList<DataProduk>,
    val onClickItemRv: OnClickItemRv
) :
    RecyclerView.Adapter<AdapterDataProduk.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_dataproduk, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataProduk = dataProdukList[position]
        holder.apply {
            itemView.apply {
                tvNamaProduk.text = "Nama Produk : " + dataProduk.title
                tvHargaProduk.text = "Harga : " + dataProduk.hargaProduk
                tvDescProduk.text = "Deskripsi : " + dataProduk.description

                imgDataProduk.loadImageUrl(dataProduk.image.toString(), context)

                if (dataProduk.namaCustomer?.isEmpty()!!) {
                    tvStatusBayar.setTextColor(ContextCompat.getColor(context, R.color.red))
                    tvStatusBayar.text = "Produk Belum ada yang beli"
                } else {
                    tvStatusBayar.setTextColor(ContextCompat.getColor(context, R.color.green))
                    tvStatusBayar.text = "Produk Sudah di bayar oleh"
                    showView(llDataProduk)
                }

                tvNamaCs.text = dataProduk.namaCustomer
                tvDateCs.text = dataProduk.tanggalBayar
                tvBayarCs.text = dataProduk.inputCustomer
            }
            itemView.setOnClickListener {
                onClickItemRv.clickItem(dataProduk, position)
            }
        }

    }

    override fun getItemCount(): Int {
        return dataProdukList.size
    }
}
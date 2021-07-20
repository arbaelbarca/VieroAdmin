package com.cindodcindy.vieroshoesadminnew.view.activity

import android.os.Bundle
import com.cindodcindy.vieroshoesadminnew.R
import com.cindodcindy.vieroshoesadminnew.view.basedata.BaseActivity
import com.cindodcindy.vieroshoesadminnew.view.model.DataProduk
import com.cindodcindy.vieroshoesadminnew.view.utils.loadImageUrl
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : BaseActivity() {
    var dataProduk: DataProduk? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        initial()
    }

    private fun initial() {
        dataProduk = intent.getSerializableExtra("dataproduk") as DataProduk?

        initData(dataProduk)
        initOnClick()
    }

    private fun initOnClick() {
        imgIncrese.setOnClickListener {
            val value = edValueQty.text.toString().toInt() + 1
            edValueQty.text = value.toString()
            val total = dataProduk?.hargaProduk?.toInt()!! * value
            tvTotalCheckout.text = total.toString()
            tvQtyCheckout.text = value.toString()
        }

        imgDecrese.setOnClickListener {
            val value = edValueQty.text.toString().toInt() - 1
            edValueQty.text = value.toString()
            val total = dataProduk?.hargaProduk?.toInt()!! * value
            tvTotalCheckout.text = total.toString()
            tvQtyCheckout.text = value.toString()
        }
    }

    private fun initData(dataProduk: DataProduk?) {
        imgDataProdukCheckout.loadImageUrl(dataProduk?.image.toString(), this)
        tvNamaProdukCheckout.text = dataProduk?.title
        tvHargaProdukCheckout.text = dataProduk?.hargaProduk
        tvDescProdukCheckout.text = dataProduk?.description

        tvTotalCheckout.text = dataProduk?.hargaProduk
        tvQtyCheckout.text = edValueQty.text.toString()
    }
}
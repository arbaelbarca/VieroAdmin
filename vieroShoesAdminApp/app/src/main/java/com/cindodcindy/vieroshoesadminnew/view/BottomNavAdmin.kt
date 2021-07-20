package com.cindodcindy.vieroshoesadminnew.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cindodcindy.vieroshoesadminnew.R
import com.cindodcindy.vieroshoesadminnew.view.fragment.*
import com.cindodcindy.vieroshoesadminnew.view.utils.dexterPermissionMultiLocation
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav_admin)
        dexterPermissionMultiLocation(this)

        //Menampilkan halaman Fragment yang pertama kali muncul
        getFragmentPage(FrUploadItem())

        /*Inisialisasi BottomNavigationView beserta listenernya untuk
         *menangkap setiap kejadian saat salah satu menu item diklik
         */
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.sell_item -> fragment = FrUploadItem()
                R.id.home_shoes -> fragment = FrListItem()
                R.id.get_order -> fragment = FrGetOrder()
                R.id.get_payment -> fragment = FrGetPayment()
                R.id.chat -> fragment = FrChat()
            }
            getFragmentPage(fragment)
        }
    }

    //Menampilkan halaman Fragment
    private fun getFragmentPage(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.page_container, fragment)
                    .commit()
            return true
        }
        return false
    }
}
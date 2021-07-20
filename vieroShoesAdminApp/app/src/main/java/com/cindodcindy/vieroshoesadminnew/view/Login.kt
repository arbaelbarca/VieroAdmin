package com.cindodcindy.vieroshoesadminnew.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cindodcindy.vieroshoesadminnew.R
import com.cindodcindy.vieroshoesadminnew.view.basedata.BaseActivity
import com.cindodcindy.vieroshoesadminnew.view.utils.validateEdittextEmpty
import kotlinx.android.synthetic.main.activity_login.*

class Login : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initial()

    }

    private fun initial() {
        initOnClick()
    }

    private fun initOnClick() {
        btn_login?.setOnClickListener { v: View? ->
            val edEmail = edEmailAdmin.text.toString()
            val edPass = edPasswordAdmin.text.toString()

            if (edEmail.validateEdittextEmpty() ||
                edPass.validateEdittextEmpty()
            ) {
                showToast("Form tidak boleh kosoong")
            } else if (edEmail.contains("admin") && edPass.contains("admin")) {
                val intent = Intent(this@Login, BottomNavAdmin::class.java)
                startActivity(intent)
                finish()
            } else {
                showToast("Email atau Password salah!")
            }
        }
    }
}
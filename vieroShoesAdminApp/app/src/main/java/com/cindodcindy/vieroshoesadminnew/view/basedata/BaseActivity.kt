package com.cindodcindy.vieroshoesadminnew.view.basedata

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cindodcindy.vieroshoesadminnew.view.dialog.CustomProgressDialog

open class BaseActivity : AppCompatActivity() {
    var customProgressDialog: CustomProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customProgressDialog = CustomProgressDialog(this)
    }

    fun showDialog(boolean: Boolean) {
        if (boolean) {
            customProgressDialog?.show()
        } else {
            customProgressDialog?.dismiss()
        }
    }

    fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
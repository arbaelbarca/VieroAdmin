package com.cindodcindy.vieroshoesadminnew.view.basedata

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cindodcindy.vieroshoesadminnew.view.dialog.CustomProgressDialog

open class BaseFragment : Fragment() {
    var customProgressDialog: CustomProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customProgressDialog = CustomProgressDialog(context as Context)
    }

    fun showDialog(boolean: Boolean) {
        if (boolean) {
            customProgressDialog?.show()
        } else {
            customProgressDialog?.dismiss()
        }
    }

    fun showToast(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}
package com.cindodcindy.vieroshoesadminnew.view.utils

import android.Manifest
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import pl.aprilapps.easyphotopicker.ChooserType
import pl.aprilapps.easyphotopicker.EasyImage

fun ImageView.loadImageUrl(url: String, context: Context) {
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun EasyImageBuilder(context: Context): EasyImage {
    return EasyImage.Builder(context)
        .setChooserTitle("Upload Foto")
        .setCopyImagesToPublicGalleryFolder(false) //                .setChooserType(ChooserType.CAMERA_AND_DOCUMENTS)
        .setChooserType(ChooserType.CAMERA_AND_GALLERY)
        .setFolderName("Picture VierosShoes")
        .allowMultiple(true)
        .build()
}

fun showView(view: View) {
    view.visibility = View.VISIBLE
}

fun dexterPermissionMultiLocation(context: Context) {
    Dexter.withContext(context)
        .withPermissions(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {

            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest>,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        })
        .onSameThread()
        .check()
}

fun dexterPermissionMultiImage(context: Context) {
    Dexter.withContext(context)
        .withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {

            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest>,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        })
        .onSameThread()
        .check()
}

fun String.validateEdittextEmpty(): Boolean {
    var isEmpty = false
    if (this.isEmpty()) {
        isEmpty = true
    }
    return isEmpty
}


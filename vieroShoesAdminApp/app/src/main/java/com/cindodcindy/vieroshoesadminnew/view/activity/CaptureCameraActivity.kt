package com.cindodcindy.vieroshoesadminnew.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.cindodcindy.vieroshoesadminnew.R
import com.cindodcindy.vieroshoesadminnew.view.utils.EasyImageBuilder
import com.orhanobut.hawk.Hawk
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource
import java.io.FileNotFoundException

class CaptureCameraActivity : AppCompatActivity() {
    var easyImage: EasyImage? = null
    var launchSomeActivity: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture_camera)
    }

    private fun initial() {
        easyImage = EasyImageBuilder(this)
        easyImage?.openChooser(this)

        launchSomeActivity =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    easyImage?.handleActivityResult(
                            result.resultCode,
                            result.resultCode,
                            result.data,
                            this,
                            object : DefaultCallback() {
                                override fun onMediaFilesPicked(
                                        imageFiles: Array<MediaFile>,
                                        source: MediaSource
                                ) {
                                    val fileMedia = imageFiles[0]
                                    Log.d("respon EasyImage", "Image file returned: ${fileMedia.file}")

                                    val imageCamera = Uri.fromFile(fileMedia.file)
                                    try {
                                        Hawk.put("file", fileMedia.file)
                                        val intent = Intent()
                                        intent.putExtra("uri", fileMedia.file)
                                        setResult(20, intent)
                                        finish()
                                    } catch (e: FileNotFoundException) {
                                        e.printStackTrace()
                                    }

                                }

                                override fun onImagePickerError(error: Throwable, source: MediaSource) {
                                    //Some error handling
                                    error.printStackTrace()
                                    println("respon Error ${error.message}")
                                }

                                override fun onCanceled(source: MediaSource) {
                                    //Not necessary to remove any files manually anymore
                                }
                            })
                }


    }
}
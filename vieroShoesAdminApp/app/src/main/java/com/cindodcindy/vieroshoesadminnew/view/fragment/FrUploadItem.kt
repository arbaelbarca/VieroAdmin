package com.cindodcindy.vieroshoesadminnew.view.fragment

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.cindodcindy.vieroshoesadminnew.R
import com.cindodcindy.vieroshoesadminnew.view.UploadImageActivity
import com.cindodcindy.vieroshoesadminnew.view.basedata.BaseFragment
import com.cindodcindy.vieroshoesadminnew.view.model.DataProduk
import com.cindodcindy.vieroshoesadminnew.view.utils.dexterPermissionMultiImage
import com.cindodcindy.vieroshoesadminnew.view.utils.validateEdittextEmpty
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.tasks.Continuation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_fr_upload_item.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [FrUploadItem.newInstance] factory method to
 * create an instance of this fragment.
 */
var mParam1: String? = null
var mParam2: String? = null

class FrUploadItem : BaseFragment() {

    private val GALLERY = 1
    private var CAMERA: Int = 2

    var firebaseFirestore: FirebaseFirestore? = null
    var user: FirebaseUser? = null
    var storage: FirebaseStorage? = null
    var storageReference: StorageReference? = null
    var fileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(mParam1)
            mParam2 = requireArguments().getString(mParam1)
        }
    }

    var button_add_stock: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fr_upload_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initial()
    }

    private fun initial() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().currentUser;
        storage = FirebaseStorage.getInstance();
        storageReference = storage!!.getReference("UploadProduk");

        dexterPermissionMultiImage(requireContext())
        initOnClick()
    }

    private fun initOnClick() {
        button_add_stock?.setOnClickListener { v: View? ->
            val intent = Intent(activity, UploadImageActivity::class.java)
            startActivity(intent)
        }

        imgupload.setOnClickListener {
            takePicture()
        }

        btnAddProduk.setOnClickListener {
            val getNameProduk = edNamaProduk.text.toString()
            val getHarga = edHargaProduk.text.toString()
            val getDesc = edDescProduk.text.toString()

            if (getNameProduk.validateEdittextEmpty() ||
                getHarga.validateEdittextEmpty() ||
                getDesc.validateEdittextEmpty()
            ) {
                showToast("Form tidak boleh kosong")
            } else addUploadProduk(getNameProduk, getHarga, getDesc)
        }


    }

    private fun addUploadProduk(getNameProduk: String, getHarga: String, getDesc: String) {
        showDialog(true)
        val uuid = UUID.randomUUID().toString();
        val referenceStore =
            storageReference?.child(System.currentTimeMillis().toString() + ".jpg");
        val uploadTask = referenceStore?.putFile(fileUri!!)

        val urlTask = uploadTask?.continueWithTask(Continuation { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation referenceStore.downloadUrl
        })?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                val dataProduk = DataProduk()
                dataProduk.title = getNameProduk
                dataProduk.hargaProduk = getHarga
                dataProduk.description = getDesc
                dataProduk.image = downloadUri.toString()
                dataProduk.uuid = uuid
                dataProduk.phone = "+6285820205083"
                dataProduk.latitude = -6.1753924
                dataProduk.longitude = 106.8249641

                firebaseFirestore?.collection("data_produk")
                    ?.document(uuid)
                    ?.set(dataProduk, SetOptions.merge())
                    ?.addOnCompleteListener {
                        showToast("Berhasil upload produk")
                        showDialog(false)
                        edNamaProduk.text = null
                        edHargaProduk.text = null
                        edDescProduk.text = null
                    }?.addOnFailureListener {
                        showToast("Gagal upload produk ${it.message}")
                        it.printStackTrace()
                        showDialog(false)
                    }
            } else {
                // Handle failures
                showToast("Gagal upload produk cuy")
                showDialog(false)
            }
        }?.addOnFailureListener {

        }
    }

    private fun takePicture() {
        ImagePicker.with(this)
            .compress(1024)         //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )  //Final image resolution will be less than 1080 x 1080(Optional)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }


    fun getFileExtention(uri: Uri?): String? {
        val resolver: ContentResolver = context?.contentResolver!!
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri!!))
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                fileUri = data?.data!!
                imgupload.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    companion object {
        fun newInstance(param1: String?, param2: String?): FrUploadItem {
            val fragment = FrUploadItem()
            val args = Bundle()
            args.putString(mParam1, param1)
            args.putString(mParam1, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
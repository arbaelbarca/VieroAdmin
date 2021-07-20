package com.cindodcindy.vieroshoesadminnew.view.activity

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.cindodcindy.vieroshoesadminnew.R
import com.cindodcindy.vieroshoesadminnew.view.basedata.BaseActivity
import com.cindodcindy.vieroshoesadminnew.view.model.DataProduk
import com.cindodcindy.vieroshoesadminnew.view.utils.loadImageUrl
import com.cindodcindy.vieroshoesadminnew.view.utils.validateEdittextEmpty
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.tasks.Continuation
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_update_data_produk.*
import kotlinx.android.synthetic.main.fragment_fr_upload_item.*
import java.text.SimpleDateFormat
import java.util.*

class UpdateDataProdukActivity : BaseActivity() {
    var dataProduk: DataProduk? = null
    var firebaseFirestore: FirebaseFirestore? = null
    var storage: FirebaseStorage? = null
    var storageReference: StorageReference? = null
    var fileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data_produk)

        initial()
    }

    private fun initial() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        dataProduk = intent.getSerializableExtra("dataproduk") as DataProduk?

        initData(dataProduk)
        initOnClick()
    }

    private fun initOnClick() {
        btnUpdateProduk.setOnClickListener {
            val getNameProduk = edNamaProdukUpdate.text.toString()
            val getHarga = edHargaProdukUpdate.text.toString()
            val getDesc = edDescProdukUpdate.text.toString()

            if (getNameProduk.validateEdittextEmpty() ||
                getHarga.validateEdittextEmpty() ||
                getDesc.validateEdittextEmpty()
            ) {
                showToast("Form tidak boleh kosong")
            } else updateData(getNameProduk, getHarga, getDesc)
        }

        btnDeleteProduk.setOnClickListener {
            deleteProduk()
        }

        imgUploadUpdate.setOnClickListener {
            takePicture()
        }
    }

    private fun deleteProduk() {
        showDialog(true)
        firebaseFirestore?.collection("data_produk")
            ?.document(dataProduk?.uuid.toString())
            ?.delete()
            ?.addOnCompleteListener {
                showDialog(false)
                showToast("Berhasil menghapus data")
                finish()
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

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                fileUri = data?.data!!
                imgupload.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                showToast(ImagePicker.getError(data))
            }
        }

    private fun updateData(getNameProduk: String, getHarga: String, getDesc: String) {
        showDialog(true)

        if (fileUri != null) {
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
                    val currentDate: String =
                        SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())

                    val dataProduks = DataProduk()
                    dataProduks.title = getNameProduk
                    dataProduks.hargaProduk = getHarga
                    dataProduks.description = getDesc
                    dataProduks.image = downloadUri.toString()
                    dataProduks.namaCustomer = dataProduk?.namaCustomer
                    dataProduks.inputCustomer = dataProduk?.inputCustomer
                    dataProduks.tanggalBayar = currentDate
                    dataProduks.uuid = dataProduk?.uuid
                    dataProduks.phone = dataProduk?.phone
                    dataProduks.latitude = dataProduk?.latitude
                    dataProduks.longitude = dataProduk?.longitude


                    firebaseFirestore?.collection("data_produk")
                        ?.document(dataProduk?.uuid.toString())
                        ?.set(dataProduks)
                        ?.addOnSuccessListener {
                            showDialog(false)
                            showToast("Berhasil memperbarui data")
                            finish()
                        }?.addOnFailureListener {
                            showToast("Order Error ${it.message}")
                            showDialog(false)
                        }

                } else {
                    // Handle failures
                    showToast("Gagal upload produk cuy")
                    showDialog(false)
                }
            }?.addOnFailureListener {

            }
        } else {
            val currentDate: String =
                SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())

            val dataProduks = DataProduk()
            dataProduks.title = getNameProduk
            dataProduks.hargaProduk = getHarga
            dataProduks.description = getDesc
            dataProduks.image = dataProduk?.image.toString()
            dataProduks.namaCustomer = dataProduk?.namaCustomer
            dataProduks.inputCustomer = dataProduk?.inputCustomer
            dataProduks.uuid = dataProduk?.uuid
            dataProduks.phone = dataProduk?.phone
            dataProduks.latitude = dataProduk?.latitude
            dataProduks.longitude = dataProduk?.longitude


            firebaseFirestore?.collection("data_produk")
                ?.document(dataProduk?.uuid.toString())
                ?.set(dataProduks)
                ?.addOnSuccessListener {
                    showDialog(false)
                    showToast("Berhasil memperbarui data")
                    finish()
                }?.addOnFailureListener {
                    showToast("Order Error ${it.message}")
                    showDialog(false)
                }
        }

    }

    private fun initData(dataProduk: DataProduk?) {
        imgUploadUpdate.loadImageUrl(dataProduk?.image.toString(), this)
        edNamaProdukUpdate.setText(dataProduk?.title)
        edHargaProdukUpdate.setText(dataProduk?.hargaProduk)
        edDescProdukUpdate.setText(dataProduk?.description)
    }
}
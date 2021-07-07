package com.cindodcindy.vieroshoesadmin.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cindodcindy.vieroshoesadmin.R;
import com.cindodcindy.vieroshoesadmin.view.model.StockData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;

public class UploadImageActivity extends AppCompatActivity {

   // private static final java.util.UUID UUID = ;
    private ImageView imageView_upload_sepatu;
    private EditText editText_nama, editText_ukuran, editText_harga, editText_lokasi, editText_nama_image;
    private Button button_upload_image;




    //varibale dari difrent exmaple
    private static final int PICK_IMAGE_REQUEST=1;
    private DatabaseReference mdataref;
    private StorageReference mstorageref;
    final int IMAGE_REQUEST_CODE = 999;

    private Uri mimguri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);


        imageView_upload_sepatu=findViewById(R.id.iv_up_sepatu_image);
        editText_nama=findViewById(R.id.et_up_nama_sepatu);
        editText_ukuran=findViewById(R.id.et_up_uk_sepatu);
        editText_harga=findViewById(R.id.et_up_harga_sepatu);
        editText_lokasi=findViewById(R.id.et_up_lokasi_sepatu);
        editText_nama_image=findViewById(R.id.et_up_nama_image);
        button_upload_image=findViewById(R.id.btn_up_btn_sepatu);

        //inisialisasi difrent example

        mstorageref= FirebaseStorage.getInstance().getReference("Uploads");
        mdataref= FirebaseDatabase.getInstance().getReference("Uploads");



        button_upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();

            }
        });

        imageView_upload_sepatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(UploadImageActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},IMAGE_REQUEST_CODE);

            }
        });

    }





    // metod dari difren example

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==IMAGE_REQUEST_CODE){
            if (grantResults.length>0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent=new Intent(new Intent(Intent.ACTION_PICK));
                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent,"select image"),IMAGE_REQUEST_CODE);

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            mimguri = data.getData();
            Picasso.with(this).load(mimguri).into(imageView_upload_sepatu);
        }
    }
    private String getFileExtensoin (Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    private void uploadImage() {
        if (mimguri!=null){
            StorageReference storageReference=mstorageref.child(System.currentTimeMillis()+"."+ getFileExtensoin(mimguri));
            storageReference.putFile(mimguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                }
                            },5000);
                            Toast.makeText(UploadImageActivity.this,"Upload SuccsessFul",Toast.LENGTH_SHORT).show();
                            StockData list_data=new StockData(editText_nama.getText().toString(), editText_ukuran.getText().toString(), editText_harga.getText().toString(),editText_lokasi.getText().toString(),editText_nama_image.getText().toString().trim(),taskSnapshot.getUploadSessionUri().toString());
                            String uploadid=mdataref.push().getKey();
                            mdataref.child(uploadid).setValue(list_data);
                            startActivity(new Intent(UploadImageActivity.this,BottomNavAdmin.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double pr=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                }
            });
        }else {
            Toast.makeText(UploadImageActivity.this,"File Not Selected",Toast.LENGTH_SHORT).show();
        }

    }

}
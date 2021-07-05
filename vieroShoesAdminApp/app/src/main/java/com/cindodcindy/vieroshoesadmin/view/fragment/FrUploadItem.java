package com.cindodcindy.vieroshoesadmin.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cindodcindy.vieroshoesadmin.R;
import com.cindodcindy.vieroshoesadmin.view.UploadImageActivity;
import com.cindodcindy.vieroshoesadmin.view.adapter.AdapterChatAdmin;
import com.cindodcindy.vieroshoesadmin.view.adapter.AdapterChatCustomer;
import com.cindodcindy.vieroshoesadmin.view.model.ModelForItem;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrUploadItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrUploadItem extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FrUploadItem() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrUploadItem.
     */
    // TODO: Rename and change types and number of parameters
    public static FrUploadItem newInstance(String param1, String param2) {
        FrUploadItem fragment = new FrUploadItem();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public Button button_add_stock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_fr_upload_item, container, false);
        button_add_stock=view.findViewById(R.id.btn_add_stock);
        button_add_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UploadImageActivity.class);
                startActivity(intent);
            }
        });

              return view;
    }


}
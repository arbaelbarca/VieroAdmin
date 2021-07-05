package com.cindodcindy.vieroshoesadmin.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cindodcindy.vieroshoesadmin.R;
import com.cindodcindy.vieroshoesadmin.view.adapter.AdapterChatAdmin;
import com.cindodcindy.vieroshoesadmin.view.adapter.AdapterChatCustomer;
import com.cindodcindy.vieroshoesadmin.view.model.Message;
import com.cindodcindy.vieroshoesadmin.view.model.ModelForItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrChat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrChat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FrChat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrChat.
     */
    // TODO: Rename and change types and number of parameters
    public static FrChat newInstance(String param1, String param2) {
        FrChat fragment = new FrChat();
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
    private List<ModelForItem> modelForItems ;
    private RecyclerView recyclerView_satu, recyclerView_dua;
    private AdapterChatAdmin adapterChatAdmin;
    private AdapterChatCustomer adapterChatCustomer;
    private EditText editText_chat_admin;

    //firebasenya


    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    Message message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_fr_chat, container, false);

        recyclerView_satu =view.findViewById(R.id.rv_admin);
        // recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView_satu.setHasFixedSize(true);
        recyclerView_satu.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //

        editText_chat_admin=view.findViewById(R.id.et_chat_admin);
/*
        editText_chat_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

 */

        modelForItems = new ArrayList<>();
        modelForItems.add(new ModelForItem("Admin Message First"));
        modelForItems.add(new ModelForItem("Admin Message Second"));
        modelForItems.add(new ModelForItem("Admin Message Third"));


        adapterChatAdmin= new AdapterChatAdmin(getActivity(),modelForItems);
        recyclerView_satu.setAdapter(adapterChatAdmin);



        recyclerView_dua =view.findViewById(R.id.rv_cst);
        // recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView_dua.setHasFixedSize(true);
        recyclerView_dua.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //


        modelForItems = new ArrayList<>();
        modelForItems.add(new ModelForItem("Customer Message First"));
        modelForItems.add(new ModelForItem("Customer Message Second"));
        modelForItems.add(new ModelForItem("Customer Message Third"));


        adapterChatCustomer= new AdapterChatCustomer(getContext(),modelForItems);
        recyclerView_dua.setAdapter(adapterChatCustomer);

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("EmployeeInfo");

        // initializing our object
        // class variable.
        message = new Message();

       // sendDatabtn = findViewById(R.id.idBtnSendData);

        // adding on click listener for our button.
        editText_chat_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String text = editText_chat_admin.getText().toString();
                //String phone = employeePhoneEdt.getText().toString();
                //String address = employeeAddressEdt.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(text) ) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(getActivity(), "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(text);
                }
            }
        });

        return  view;
    }

    private void addDatatoFirebase(String text) {
        // below 3 lines of code is used to set
        // data in our object class.
        message.setText(text);
        //employeeInfo.setEmployeeContactNumber(phone);
        //employeeInfo.setEmployeeAddress(address);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(message);

                // after adding this data we are showing toast message.
                Toast.makeText(getActivity(), "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(getActivity(), "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
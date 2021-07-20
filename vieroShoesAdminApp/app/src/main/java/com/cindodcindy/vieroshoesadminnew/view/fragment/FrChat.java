package com.cindodcindy.vieroshoesadminnew.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cindodcindy.vieroshoesadminnew.R;
import com.cindodcindy.vieroshoesadminnew.view.adapter.AdapterChatAdmin;
import com.cindodcindy.vieroshoesadminnew.view.adapter.AdapterChatCustomer;
import com.cindodcindy.vieroshoesadminnew.view.model.Message;
import com.cindodcindy.vieroshoesadminnew.view.model.ModelForItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private FirebaseFirestore firestoreDB;
    String id = "";

    private static final String TAG = "AddNoteActivity";




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

        editText_chat_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote(editText_chat_admin.getText().toString());
            }
        });



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

        firestoreDB = FirebaseFirestore.getInstance();



        return  view;
    }


    private void addNote(String text) {
        Map message = new Message(text).toMap();

        firestoreDB.collection("message")
                .add(message)
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }


                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Note document", e);
                        Toast.makeText(getActivity(), "Note could not be added!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
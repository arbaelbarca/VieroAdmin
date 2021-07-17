package com.cindodcindy.vieroshoesadmin.view.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cindodcindy.vieroshoesadmin.MainActivity;
import com.cindodcindy.vieroshoesadmin.R;
import com.cindodcindy.vieroshoesadmin.view.adapter.AdapterHome;
import com.cindodcindy.vieroshoesadmin.view.model.ModelForItem;
import com.cindodcindy.vieroshoesadmin.view.model.StockData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrListItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrListItem extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FrListItem() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrListItem.
     */
    // TODO: Rename and change types and number of parameters
    public static FrListItem newInstance(String param1, String param2) {
        FrListItem fragment = new FrListItem();
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

    private List<StockData> stockData ;
    private RecyclerView recyclerView;
    private AdapterHome adapterHome;
    private DatabaseReference db;

    StorageReference root;
    ArrayList<String> imagelist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_fr_list_item, container, false);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //FirebaseApp.initializeApp(getContext());

        FirebaseApp.initializeApp(getContext());
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());



/*

        // recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        // GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //


        modelForItems = new ArrayList<>();
        modelForItems.add(new ModelForItem("Viero White Grey"));
        modelForItems.add(new ModelForItem("Viero White Grey"));
        modelForItems.add(new ModelForItem("Viero White Grey"));
        modelForItems.add(new ModelForItem("Viero White Grey"));
        modelForItems.add(new ModelForItem("Viero White Grey"));


        adapterHome= new AdapterHome(getActivity(),modelForItems);
        recyclerView.setAdapter(adapterHome);


 */
        /*

        recyclerView =view.findViewById(R.id.rv_home_item);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        stockData=new ArrayList<>();

         */


        imagelist=new ArrayList<>();
         recyclerView = view.findViewById(R.id.rv_home_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

     //   stockData= new ArrayList<>();

      /*
        selectbtn=(TextView)findViewById(R.id.select_btn);
        selectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,UploadImage.class));
                finish();
            }
        });

         */

        /*
        db= FirebaseDatabase.getInstance().getReference("Uploads");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot post : dataSnapshot.getChildren()){
                    StockData stockDatas=post.getValue(StockData.class);
                    stockData.add(stockDatas);
                }
               // adapterHome=new AdapterHome(getActivity(),stockData);
                //recyclerView.setAdapter(adapterHome);

                adapterHome = new AdapterHome( getContext(),stockData);
                recyclerView.setAdapter(adapterHome);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

         */

        StorageReference listRef = FirebaseStorage.getInstance().getReference().child("Uploads");
        listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for(StorageReference file:listResult.getItems()){
                    file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imagelist.add(uri.toString());
                            Log.e("Itemvalue",uri.toString());
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            recyclerView.setAdapter(adapterHome);
                           // progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });

        //firebaseDatabase.setLogLevel(Logger.Level.DEBUG);


        return view;
    }


}
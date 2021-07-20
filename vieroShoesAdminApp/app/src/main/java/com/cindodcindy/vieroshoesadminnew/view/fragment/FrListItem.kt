package com.cindodcindy.vieroshoesadminnew.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cindodcindy.vieroshoesadminnew.R
import com.cindodcindy.vieroshoesadminnew.view.activity.UpdateDataProdukActivity
import com.cindodcindy.vieroshoesadminnew.view.adapter.AdapterDataProduk
import com.cindodcindy.vieroshoesadminnew.view.adapter.AdapterHome
import com.cindodcindy.vieroshoesadminnew.view.basedata.BaseFragment
import com.cindodcindy.vieroshoesadminnew.view.model.DataProduk
import com.cindodcindy.vieroshoesadminnew.view.model.StockData
import com.cindodcindy.vieroshoesadminnew.view.onclick.OnClickItemRv
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_fr_list_item.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [FrListItem.newInstance] factory method to
 * create an instance of this fragment.
 */
class FrListItem : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    var firebaseFirestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    private val stockData: List<StockData>? = null
    private var recyclerView: RecyclerView? = null
    private val adapterHome: AdapterHome? = null
    private val db: DatabaseReference? = null
    var root: StorageReference? = null
    var imagelist: ArrayList<String>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fr_list_item, container, false)
        val firebaseDatabase = FirebaseDatabase.getInstance()
        //FirebaseApp.initializeApp(getContext());
        FirebaseApp.initializeApp(requireContext())
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            SafetyNetAppCheckProviderFactory.getInstance()
        )


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

//         */imagelist = ArrayList()
//        val linearLayoutManager = LinearLayoutManager(activity)
//        recyclerView?.setLayoutManager(linearLayoutManager)

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

//        StorageReference listRef = FirebaseStorage.getInstance().getReference().child("Uploads");
//        listRef.listAll().addOnSuccessListener(listResult -> {
//            for (StorageReference file : listResult.getItems()) {
//                file.getDownloadUrl().addOnSuccessListener(uri -> {
//                    imagelist.add(uri.toString());
//                    Log.e("Itemvalue", uri.toString());
//                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        recyclerView.setAdapter(adapterHome);
//                        // progressBar.setVisibility(View.GONE);
//                    }
//                });
//            }
//        });

        //firebaseDatabase.setLogLevel(Logger.Level.DEBUG);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial()
    }

    private fun initial() {
        firebaseFirestore = FirebaseFirestore.getInstance()

    }

    override fun onResume() {
        super.onResume()
        initGetDataProduk()
    }
    private fun initGetDataProduk() {
        showDialog(true)

        val ref = firebaseFirestore?.collection("data_produk")
        ref?.get()
            ?.addOnCompleteListener {
                val mutableList: MutableList<DataProduk> = mutableListOf()
                showDialog(false)
                for (documentin in it.result!!) {
                    val dataProduk = documentin.toObject(DataProduk::class.java)
                    mutableList.add(dataProduk)
                    initAdapter(mutableList)
                }
            }?.addOnFailureListener {
                showDialog(false)
            }
    }

    private fun initAdapter(dataProduk: MutableList<DataProduk>) {
        val adapterDataProduk = AdapterDataProduk(dataProduk,
            object : OnClickItemRv {
                override fun clickItem(any: Any, pos: Int) {
                    val dataProduks = any as DataProduk
                    val intent = Intent(requireContext(), UpdateDataProdukActivity::class.java)
                        .putExtra("dataproduk", dataProduks)
                    startActivity(intent)
                }
            })

        rvDataProduk.apply {
            adapter = adapterDataProduk
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
        }
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): FrListItem {
            val fragment = FrListItem()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
package com.cindodcindy.vieroshoesadmin.view.model;

public class StockData {

    private String namaStock;
    private String ukuranStock;
    private String hargaStock;
    private String lokasiStock;
    private String mImgUrl;
    private String mName;

    public StockData(String namaStock, String ukuranStock, String hargaStock, String lokasiStock,String imgUrl, String name){
        if (name.trim().equals("")){
            name="No Name";
        }
        this.mName=name;
        this.mImgUrl=imgUrl;
        this.namaStock=namaStock;
        this.ukuranStock=ukuranStock;
        this.hargaStock=hargaStock;
        this.lokasiStock=lokasiStock;
    }

    public String getNamaStock() {
        return namaStock;
    }

    public void setNamaStock(String namaStock) {
        this.namaStock = namaStock;
    }

    public String getUkuranStock() {
        return ukuranStock;
    }

    public void setUkuranStock(String ukuranStock) {
        this.ukuranStock = ukuranStock;
    }

    public String getHargaStock() {
        return hargaStock;
    }

    public void setHargaStock(String hargaStock) {
        this.hargaStock = hargaStock;
    }

    public String getLokasiStock() {
        return lokasiStock;
    }

    public void setLokasiStock(String lokasiStock) {
        this.lokasiStock = lokasiStock;
    }

    public String getmImgUrl() {
        return mImgUrl;
    }

    public void setmImgUrl(String mImgUrl) {
        this.mImgUrl = mImgUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}

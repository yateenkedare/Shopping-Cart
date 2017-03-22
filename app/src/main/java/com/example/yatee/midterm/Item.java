package com.example.yatee.midterm;

public class Item {
    private String title;
    private String sale_price;
    private String msrp_price;
    private String discount;
    private String uid = null;



    private String imageURL;
    private long _id;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSale_price() {
        return "Price: "+sale_price+"$";
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getMsrp_price() {
        return msrp_price;
    }

    public void setMsrp_price(String msrp_price) {
        this.msrp_price = msrp_price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getDiscount() {
        return "Discount: "+String.format("%.2f" , ( 100 * (Float.parseFloat(msrp_price)-Float.parseFloat(sale_price))/Float.parseFloat(msrp_price)))+"%";
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }



    public Item() {

    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", sale_price='" + sale_price + '\'' +
                ", msrp_price='" + msrp_price + '\'' +
                ", discount='" + discount + '\'' +
                ", uid='" + uid + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", _id=" + _id +
                '}';
    }

    public Item(String title, String msrp_price, String sale_price, String discount, String URL, String UID) {
        this.title = title;
        this.msrp_price= msrp_price;
        this.sale_price = sale_price;
        this.discount = discount;
        this.imageURL = URL;
        this.uid = UID;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }



}

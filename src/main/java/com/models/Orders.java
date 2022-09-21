package com.models;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String userEmail;
    private int productID;

    private String imgUrl;
    private int qty;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private String deliveryAddress;
    private long contact;

    public int getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(int bill) {
        this.billAmount = bill;
    }

    private int billAmount;
    private String orderTime;
    @Transient
    private String error;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Orders(int id, String userEmail, int productID, String imgUrl, int qty, String deliveryAddress, long contact, int billAmount, String orderTime, String error) {
        this.id = id;
        this.userEmail = userEmail;
        this.productID = productID;
        this.imgUrl = imgUrl;
        this.qty = qty;
        this.deliveryAddress = deliveryAddress;
        this.contact = contact;
        this.billAmount = billAmount;
        this.orderTime = orderTime;
        this.error = error;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", productID='" + productID + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contact='" + contact + '\''
                 ;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }


    public Orders() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

}

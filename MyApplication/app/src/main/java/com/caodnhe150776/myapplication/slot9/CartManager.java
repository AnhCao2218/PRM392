package com.caodnhe150776.myapplication.slot9;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;// bien toan cuc
    private List<Product91> cartItems;// ds mat hang o trong gio hang

    CartManager(){
        cartItems= new ArrayList<>();

    }
    //phuong thuc xu li bien tinh=> xu li de du lieu ko mat di
    public static synchronized CartManager getInstance(){
        if(instance==null){
            instance= new CartManager();
        }
        return instance;
    }
    //add sp vao gio hang
    public void addProductToCart(Product91 product){
        cartItems.add(product);

    }
    //lay ve 1 item trong gio hang
    public List<Product91> getCartItems(){
        return cartItems;
    }
}

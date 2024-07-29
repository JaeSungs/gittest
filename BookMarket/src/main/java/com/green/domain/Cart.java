package com.green.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart{
	
    private String cartId;  // ��ٱ��� ID
    private Map<String,CartItem> cartItems; // ��ٱ��� �׸�
    private int grandTotal;  // �Ѿ�

    //�⺻������
    public Cart() {  
        cartItems = new HashMap<String, CartItem>();
        grandTotal = 0;
    }  

    //�Ϲݻ�����
    public Cart(String cartId) {  
        this();
        this.cartId = cartId;
    }  

    //getter, setter
    public String getCartId() {  
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getGrandTotal() {
        return grandTotal;
    }  

    //updateGrandTotal() �޼���� ��ٱ��Ͽ� ��ϵǴ� ���������� ������ ����մϴ�.
    public void updateGrandTotal() { 
    	
        grandTotal= 0;
        
        for(CartItem item : cartItems.values()){
            grandTotal = grandTotal + item.getTotalPrice();
        }
    }  
    
    //** ���� ����� ������ ������ ��ٱ��Ͽ� ����ϴ� addCartItem() �޼��� �߰� **
    public void addCartItem(CartItem item) {
        String bookId = item.getBook().getBookId(); //���� ����ϱ� ���� ���� ID ��������

        //����ID�� cartItems ��ü�� ��� �Ǿ��ִ��� Ȯ��
        if(cartItems.containsKey(bookId)) {  
        	//��ϵ� ���� ID�� ���� ���� ��������
            CartItem cartItem = cartItems.get(bookId);  
            //��ϵ� ���� ID�� ���� �߰� ����
            cartItem.setQuantity(cartItem.getQuantity()+item.getQuantity());  
            //��ϵ� ���� ID�� ���� ���� ����(cartItem) ����
            cartItems.put(bookId, cartItem);  
        } else {
        	//����ID�� ���� ���� ���� ����
            cartItems.put(bookId, item);  
        }
        updateGrandTotal(); //�Ѿ� ����ϱ�  
    }
    
    //** ��ϵ� ���� �׸� �����ϴ� removeCartItem() �޼��� �߰� **
    public void removeCartItem(CartItem item) {
        
    	String bookId = item.getBook().getBookId();
        
    	cartItems.remove(bookId); //bookId ���� ����  
        
    	updateGrandTotal(); //�Ѿ� ����  
    }
    
    //cartId �ʵ��� hashCode() �޼���, equals() �ڵ�����
    @Override  
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cart other = (Cart) obj;
        if (cartId == null) {
            if (other.cartId != null)
                return false;
        } else if (!cartId.equals(other.cartId))
            return false;
        return true;
    }  
}

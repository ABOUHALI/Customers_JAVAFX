/***
 * Anandkrishna 200476485
 * **/
package com.example.w22comp1011gctest2student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Customer implements Comparable{

    public Long id;

    public String firstName;

    public String lastName;

    public String streetAddress;

    public String city;

    public String province;

    public String postalCode;

    public String emailAddress;

    public String ccType;

    public String bloodType;

    public String phoneNumber;

    public double pounds;

    public Integer heightInCM;

    public String priceString;


    public List<Product> purchases = new ArrayList<>();


    /**
     * Getter
     * */
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCcType() {
        return ccType;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getPounds() {
        return pounds;
    }

    public Integer getHeightInCM() {
        return heightInCM;
    }

    public List<Product> getPurchases() {
        return purchases;
    }


    public Double getTotalPurchases(){
        List<Double> total = new ArrayList<>();
        for (Product p:
             this.purchases) {
            total.add(p.salePrice);
        }
        Double result = total.stream().collect(Collectors.summingDouble(Double::doubleValue));

        return result;
    }

    public Double getTotalSaved(){

        List<Double> saved = new ArrayList<>();
        for (Product p:
                this.purchases) {
            saved.add(p.regularPrice-p.salePrice);
        }
        Double result = saved.stream().collect(Collectors.summingDouble(Double::doubleValue));
        return result;
    }

    public boolean isSaved(){
        return this.getTotalSaved() >= 5d;
    }

     /*
    price with $
    **/


    public String getPriceString() {
        return this.getTotalPurchases()+" $";
    }

    public double getTotalRegularPrice() {
        List<Double> saved = new ArrayList<>();
        for (Product p:
                this.purchases) {
            saved.add(p.regularPrice);
        }
        Double result = saved.stream().collect(Collectors.summingDouble(Double::doubleValue));
        return result;
    }

    public double getTotalSalePrice(){
        List<Double> saved = new ArrayList<>();
        for (Product p:
                this.purchases) {
            saved.add(p.salePrice);
        }
        Double result = saved.stream().collect(Collectors.summingDouble(Double::doubleValue));
        return  result;
    }

    @Override
    public int compareTo(Object o) {
        Customer c= (Customer) o ;
        if(this.getTotalSalePrice() == c.getTotalSalePrice()){
            return 0;
        }else if (this.getTotalSalePrice() > c.getTotalSalePrice()){
            return 1;
        }else {
            return -1;
        }
    }
}
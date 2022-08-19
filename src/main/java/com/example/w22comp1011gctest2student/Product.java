/***
 *Anandkrishna 200476485
 * **/
package com.example.w22comp1011gctest2student;

public class Product {

  public Long id;

  public String sku;

  public String name;

  public Double salePrice;

  public Double regularPrice;

  public String urlImage;

  public Long getId() {
    return id;
  }

  public String getSku() {
    return sku;
  }

  public String getName() {
    return name;
  }

  public Double getSalePrice() {
    return salePrice;
  }

  public Double getRegularPrice() {
    return regularPrice;
  }

  public String getImage() {
    return urlImage;
  }

  @Override
  public String toString() {
    return name + "-$" + salePrice;
  }
}
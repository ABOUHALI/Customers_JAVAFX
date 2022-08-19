package com.example.w22comp1011gctest2student;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
/***
 *Anandkrishna 200476485
 * **/
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JsonConfig {

    public List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("customers.json"));
            JsonObject object = new Gson().fromJson(reader, new TypeToken<JsonObject>() {}.getType());
            /*Customers*/
            JsonArray listOfElemens = object.get("Customers").getAsJsonArray();
            listOfElemens.forEach((jsonElement -> {
                JsonObject objectCustum = jsonElement.getAsJsonObject();
                Customer customer = new Customer();
                customer.id = objectCustum.get("id").getAsLong();
                customer.firstName = objectCustum.get("firstName").getAsString();
                customer.lastName = objectCustum.get("lastName").getAsString();
                customer.streetAddress = objectCustum.get("streetAddress").getAsString();
                customer.city = objectCustum.get("city").getAsString();
                customer.province = objectCustum.get("province").getAsString();
                customer.postalCode = objectCustum.get("postalCode").getAsString();
                customer.emailAddress = objectCustum.get("emailAddress").getAsString();
                customer.ccType = objectCustum.get("ccType").getAsString();
                customer.bloodType = objectCustum.get("bloodType").getAsString();
                customer.phoneNumber = objectCustum.get("phoneNumber").getAsString();
                customer.heightInCM = objectCustum.get("heightInCM").getAsInt();
                customer.pounds = objectCustum.get("pounds").getAsDouble();
                /*Products*/
                JsonArray listOfProducts = objectCustum.get("purchases").getAsJsonArray();
                listOfProducts.forEach((elementObject) -> {
                    JsonObject objectProduct = elementObject.getAsJsonObject();
                    Product product = new Product();
                    product.id = objectProduct.get("id").getAsLong();
                    product.sku = objectProduct.get("sku").getAsString();
                    product.name = objectProduct.get("name").getAsString();
                    product.salePrice = objectProduct.get("salePrice").getAsDouble();
                    product.regularPrice = objectProduct.get("regularPrice").getAsDouble();
                    product.urlImage = objectProduct.get("image").getAsString();
                    customer.purchases.add(product);
                });
                customers.add(customer);

            }));
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customers;
    }


    public List<Product> loadProduct() throws IOException {
        List<Product> produits = new ArrayList<>();

            Reader reader = Files.newBufferedReader(Paths.get("customers.json"));
            JsonObject object = new Gson().fromJson(reader, new TypeToken<JsonObject>() {
            }.getType());
            /*Customers*/
            JsonArray listOfElemens = object.get("Customers").getAsJsonArray();
            listOfElemens.forEach((jsonElement -> {
                JsonObject objectCustum = jsonElement.getAsJsonObject();
                JsonArray listOfProducts = objectCustum.get("purchases").getAsJsonArray();
                listOfProducts.forEach((elementObject) -> {
                    JsonObject objectProduct = elementObject.getAsJsonObject();
                    Product product = new Product();
                    product.id = objectProduct.get("id").getAsLong();
                    product.sku = objectProduct.get("sku").getAsString();
                    product.name = objectProduct.get("name").getAsString();
                    product.salePrice = objectProduct.get("salePrice").getAsDouble();
                    product.regularPrice = objectProduct.get("regularPrice").getAsDouble();
                    product.urlImage = objectProduct.get("image").getAsString();
                    produits.add(product);
                });
                                        }));
            reader.close();
        return produits;
    }
}


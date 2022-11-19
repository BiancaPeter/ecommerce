package com.spring.ecommerce.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "category",cascade=CascadeType.ALL)
    List<Product>productList;
    public Category(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProductList() {
        if(productList ==null) {
            productList = new ArrayList<>();
        }
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

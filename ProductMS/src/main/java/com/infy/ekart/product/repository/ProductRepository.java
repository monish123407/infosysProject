package com.infy.ekart.product.repository;

import com.infy.ekart.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//extends it with required Interface
public interface ProductRepository extends JpaRepository<Product,Integer> {

}

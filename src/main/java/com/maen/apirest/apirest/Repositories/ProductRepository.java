package com.maen.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maen.apirest.apirest.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}

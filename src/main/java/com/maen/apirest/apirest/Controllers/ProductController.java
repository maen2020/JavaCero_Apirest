package com.maen.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maen.apirest.apirest.Repositories.ProductRepository;

import com.maen.apirest.apirest.Entities.Product;


@RestController
@RequestMapping("/products")
public class ProductController {

    // Inyectar el repositorio.
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getByIdProduct(@PathVariable Long id){
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Registro no econtrado"));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        Product updateProduct = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("El registro que se intenta actualizar no existe."));

        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        return productRepository.save(updateProduct);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Product deleteProduct = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        productRepository.delete(deleteProduct);
        return "El producto con el id: " + id + " se elimino correctamente.";
    }
}
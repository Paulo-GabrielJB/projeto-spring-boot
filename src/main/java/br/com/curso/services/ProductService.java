package br.com.curso.services;

import br.com.curso.models.entities.Product;
import br.com.curso.repositories.ProductRepository;
import br.com.curso.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){

        Optional<Product> obj = productRepository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}

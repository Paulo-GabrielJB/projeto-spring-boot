package br.com.curso.services;

import br.com.curso.models.entities.Product;
import br.com.curso.repositories.ProductRepository;
import br.com.curso.services.exceptions.DatabaseException;
import br.com.curso.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Product insert(Product obj){
        return productRepository.save(obj);
    }

    public void delete(Long id){
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Product update(Long id, Product obj){
        try {
            Product entity = productRepository.getOne(id);
            updateData(entity, obj);
            return productRepository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Product entity, Product obj) {

        entity.setDescription(obj.getDescription());
        entity.setImgUrl(obj.getImgUrl());
        entity.setName(obj.getName());
        entity.setPrice(obj.getPrice());

    }
}

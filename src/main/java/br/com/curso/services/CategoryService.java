package br.com.curso.services;

import br.com.curso.models.entities.Category;
import br.com.curso.repositories.CategoryRepository;
import br.com.curso.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){

        Optional<Category> obj = categoryRepository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}

package br.com.curso.resources;

import br.com.curso.models.entities.Category;
import br.com.curso.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {


    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){

        List<Category> list = service.findAll();

        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Long id){

        Category obj = service.findById(id);

        return ResponseEntity.ok().body(obj);

    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody Category obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") Long id, @RequestBody Category obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}

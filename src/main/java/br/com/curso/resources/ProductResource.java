package br.com.curso.resources;

import br.com.curso.models.entities.Product;
import br.com.curso.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/products")
public class ProductResource {


    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){

        List<Product> list = service.findAll();

        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id){

        Product obj = service.findById(id);

        return ResponseEntity.ok().body(obj);

    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product obj){
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
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Product obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}

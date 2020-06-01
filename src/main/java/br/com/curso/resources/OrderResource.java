package br.com.curso.resources;

import br.com.curso.models.entities.Order;
import br.com.curso.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){

        List<Order> list = service.findAll();

        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") Long id){

        Order obj = service.findById(id);

        return ResponseEntity.ok().body(obj);

    }

    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order obj){
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
    public ResponseEntity<Order> update(@PathVariable("id") Long id, @RequestBody Order obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}

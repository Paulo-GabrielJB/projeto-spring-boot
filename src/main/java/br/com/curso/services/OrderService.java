package br.com.curso.services;

import br.com.curso.models.entities.Order;
import br.com.curso.repositories.OrderRepository;
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
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){

        Optional<Order> obj = orderRepository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Order insert(Order obj){
        return orderRepository.save(obj);
    }

    public void delete(Long id){
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Order update(Long id, Order obj){
        try {
            Order entity = orderRepository.getOne(id);
            updateData(entity, obj);
            return orderRepository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Order entity, Order obj) {

        entity.setOrderStatus(obj.getOrderStatus());

    }
}

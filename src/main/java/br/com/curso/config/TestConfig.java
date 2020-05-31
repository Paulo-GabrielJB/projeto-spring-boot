package br.com.curso.config;

import br.com.curso.models.entities.*;
import br.com.curso.models.entities.enums.OrderStatus;
import br.com.curso.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "11988888888", "123456", null);
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "11977777777", "123456", null);

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.DELIVERED.getCode(), u1, new HashSet<>());
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAID.getCode(), u2, new HashSet<>());
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT.getCode(), u1, new HashSet<>());

        Category c1 = new Category(null, "Eletronics", null);
        Category c2 = new Category(null, "Books", null);
        Category c3 = new Category(null, "Computers", null);

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ispsum dolor sit amet, consectetur.", 90.5, "", new HashSet<>());
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "", new HashSet<>());
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifends maximus tortor, at mollis.", 1250.0, "", new HashSet<>());
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "", new HashSet<>());
        Product p5 = new Product(null, "Rail for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "", new HashSet<>());

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        p1.getCategories().add(c2);
        p2.getCategories().add(c1);
        p2.getCategories().add(c3);
        p3.getCategories().add(c3);
        p4.getCategories().add(c3);
        p5.getCategories().add(c2);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        OrderItem oi1 = new OrderItem(p1, o1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(p3, o1, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(p3, o2, 2, p1.getPrice());
        OrderItem oi4 = new OrderItem(p5, o3, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));


    }
}

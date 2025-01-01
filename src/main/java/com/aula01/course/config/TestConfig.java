package com.aula01.course.config;

import com.aula01.course.entities.*;
import com.aula01.course.entities.enums.OrderStatus;
import com.aula01.course.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

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
    private OrderItemRepository orderItemRepository; // Repositório para OrderItem

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "Maria Brown", "maria@gmail.com", "123456");
        User user2 = new User(null, "Alex Green", "alex@gmail.com", "123456");

        Category c1 = new Category(null, "Electronics");
        Category c2 = new Category(null, "Moveis");

        Product p1 = new Product(null, "Laptop", "High-performance laptop", 3500.0, "https://example.com/images/laptop.jpg");
        Product p2 = new Product(null, "Smartphone", "Smartphone with 108MP camera", 1500.0, "https://example.com/images/smartphone.jpg");
        Product p3 = new Product(null, "Gaming Mouse", "Mouse with 7 programmable buttons", 120.0, "https://example.com/images/gaming_mouse.jpg");
        Product p4 = new Product(null, "Headset", "Noise-canceling headset", 250.0, "https://example.com/images/headset.jpg");

        Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, user1);
        Order order2 = new Order(null, Instant.parse("2024-06-20T19:53:07Z"), OrderStatus.WAITING_PAYMENT, user2);
        Order order3 = new Order(null, Instant.parse("2023-06-20T19:53:07Z"), OrderStatus.WAITING_PAYMENT, user2);

        userRepository.saveAll(Arrays.asList(user1, user2));
        categoryRepository.saveAll(Arrays.asList(c1, c2));

        p1.getCategories().add(c1);
        p1.getCategories().add(c2);
        p2.getCategories().add(c2);
        p4.getCategories().add(c1);
        p4.getCategories().add(c2);

        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

        // Criando instâncias de OrderItem
        OrderItem oi1 = new OrderItem(order1, p1, 1, p1.getPrice());
        OrderItem oi2 = new OrderItem(order1, p3, 2, p3.getPrice());
        OrderItem oi3 = new OrderItem(order2, p2, 1, p2.getPrice());
        OrderItem oi4 = new OrderItem(order3, p4, 3, p4.getPrice());

        // Salvando no banco
        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
    }
}

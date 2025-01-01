package com.aula01.course.config;

import com.aula01.course.entities.Category;
import com.aula01.course.entities.Order;
import com.aula01.course.entities.User;
import com.aula01.course.entities.enums.OrderStatus;
import com.aula01.course.repositories.CategoryRepository;
import com.aula01.course.repositories.OrderRepository;
import com.aula01.course.repositories.UserRepository;
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

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "Maria Brown", "maria@gmail.com", "123456");
        User user2 = new User(null, "Alex Green", "alex@gmail.com", "123456");

        Category c1 = new Category(null, "Electronics");
        Category c2 = new Category(null, "Moveis");

        Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, user1);
        Order order2 = new Order(null, Instant.parse("2024-06-20T19:53:07Z"), OrderStatus.WAITING_PAYMENT, user2);
        Order order3 = new Order(null, Instant.parse("2023-06-20T19:53:07Z"), OrderStatus.WAITING_PAYMENT, user2);

        userRepository.saveAll(Arrays.asList(user1, user2));
        categoryRepository.saveAll(Arrays.asList(c1, c2));
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));


    }
}

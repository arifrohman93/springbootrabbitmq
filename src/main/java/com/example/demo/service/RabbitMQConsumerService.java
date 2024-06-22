package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Service
public class RabbitMQConsumerService {

    @Autowired
    private UserRepository userRepository;

    @RabbitListener(queues = "users")
    public void consume(User user) {
        userRepository.save(user);
    }
}

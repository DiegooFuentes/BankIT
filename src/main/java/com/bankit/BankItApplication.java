package com.bankit;

import com.bankit.web.models.Client;
import com.bankit.web.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BankItApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankItApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository) {
        return (args) -> {
            // save a couple of customers
            clientRepository.save(new Client("Camila", "Balmaceda", "camilabal1@gmail.com", "hoLa32-"));
            clientRepository.save(new Client("Fabian", "Sepulveda", "fabisepulveda@gmail.com", "pUyol21"));
            clientRepository.save(new Client("Antonio", "Carcamo", "carcamo32@gmail.com", "Heeloow1-"));

        };
    }
}

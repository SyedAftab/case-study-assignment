package com.justlife.customerservice;

import com.justlife.customerservice.domain.Customer;
import com.justlife.customerservice.repository.CustomerRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Aftab
 */
@SpringBootTest
class CustomerServiceApplicationTests {

    @Autowired
    CustomerRepository repository;

    @Test
    void createCustomerAndFind() {
        Customer c = new Customer();
        c.setFullName("Test User");
        c.setEmail("test@example.com");
        Customer saved = repository.save(c);
        assertThat(saved.getId()).isNotNull();
        assertThat(repository.findById(saved.getId())).isPresent();
    }
}

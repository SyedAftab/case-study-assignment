package com.justlife.customerservice.repository;

import com.justlife.customerservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aftab
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

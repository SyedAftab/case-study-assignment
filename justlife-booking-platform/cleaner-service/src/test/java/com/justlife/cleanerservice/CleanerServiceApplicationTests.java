package com.justlife.cleanerservice;

import com.justlife.cleanerservice.domain.Cleaner;
import com.justlife.cleanerservice.repository.CleanerRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Aftab
 */
@SpringBootTest
class CleanerServiceApplicationTests {

    @Autowired
    CleanerRepository repository;

    @Test
    void saveCleaner() {
        Cleaner c = new Cleaner();
        c.setFullName("Test Cleaner");
        c.setVehicleId(1L);
        Cleaner saved = repository.save(c);
        assertThat(saved.getId()).isNotNull();
    }
}

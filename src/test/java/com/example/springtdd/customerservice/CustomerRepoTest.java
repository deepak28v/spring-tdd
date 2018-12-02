package com.example.springtdd.customerservice;

import com.example.springtdd.SpringTddApplication;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringTddApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void saveShouldMapCorrectly() {
        Customer customer = new Customer(null, "first", "last", "email@email.com");
        Customer saved = this.testEntityManager.persistFlushFind(customer);
        BDDAssertions.then(saved.getId()).isNotNull();
        BDDAssertions.then(saved.getFirstName()).isNotNull().isEqualTo("first");
        BDDAssertions.then(saved.getLastName()).isNotNull().isEqualTo("last");
        BDDAssertions.then(saved.getEmail()).isNotNull().isEqualTo("email@email.com");
    }
}

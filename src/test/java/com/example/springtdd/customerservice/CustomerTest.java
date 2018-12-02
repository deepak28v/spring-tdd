package com.example.springtdd.customerservice;


import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CustomerTest {

    private Validator validator;

    @Before
    public void init() {
        LocalValidatorFactoryBean validatorFactoryBeanFactory = new LocalValidatorFactoryBean();
        validatorFactoryBeanFactory.afterPropertiesSet();
        this.validator = validatorFactoryBeanFactory.getValidator();
    }

    @Test
    public void newInstanceWithValidValues() {
        Customer customer = new Customer(1L, "first", "last", "email@email.com");

        //Using Matchers style
        /*assertThat(customer.getId(), is(1l));
        assertThat(customer.getFirstName(), is("first"));
        assertThat(customer.getLastName(), is("last"));
        assertThat(customer.getEmail(), is("email@email.com"));*/

        BDDAssertions.then(customer.getId()).isEqualTo(1L);
        BDDAssertions.then(customer.getFirstName()).isEqualTo("first");
        BDDAssertions.then(customer.getLastName()).isEqualTo("last");
        BDDAssertions.then(customer.getEmail()).isEqualTo("email@email.com");
    }

    @Test
    public void newInstanceConstraintVoilations(){
        Customer customer = new Customer(null, null, null, null);
        Set<ConstraintViolation<Customer>>  constraintViolationsSet = validator.validate(customer);
        BDDAssertions.then(constraintViolationsSet.size()).isEqualTo(3);
    }
}

package com.indigitalxp.services.customer.repository;

import com.indigitalxp.services.customer.model.entity.Customer;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface CustomerRepository extends RxJava3CrudRepository<Customer, Long> {
  Maybe<Customer> findCustomerByDniOrEmail(String dni, String email);
}

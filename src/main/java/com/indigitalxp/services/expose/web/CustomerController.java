package com.indigitalxp.services.expose.web;

import com.indigitalxp.services.customer.business.CustomerServices;
import com.indigitalxp.services.customer.model.dto.CustomerRequest;
import com.indigitalxp.services.customer.model.entity.Customer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {

  @Autowired
  private CustomerServices customerServices;

  @PostMapping
  public Completable create(@RequestBody CustomerRequest customerRequest) {
    return customerServices.create(customerRequest);
  }

  @GetMapping
  public Flowable<Customer> list() {
    return customerServices.getCustomers();
  };
}

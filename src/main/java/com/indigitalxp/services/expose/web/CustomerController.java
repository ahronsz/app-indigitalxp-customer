package com.indigitalxp.services.expose.web;

import com.indigitalxp.services.customer.business.CustomerServices;
import com.indigitalxp.services.customer.model.dto.request.CustomerParam;
import com.indigitalxp.services.customer.model.dto.request.CustomerRequest;
import com.indigitalxp.services.customer.model.entity.Customer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "customers")
@Validated
public class CustomerController {

  @Autowired
  private CustomerServices customerServices;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Completable create(@RequestBody @Valid CustomerRequest customerRequest) {
    return customerServices.create(customerRequest);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public Flowable<Customer> list(@Valid @ModelAttribute CustomerParam customerParam) {
    return customerServices.findCustomer(customerParam);
  };
}

package com.indigitalxp.services.customer.business.impl;

import com.indigitalxp.services.customer.business.CustomerServices;
import com.indigitalxp.services.customer.mapper.CustomerMapper;
import com.indigitalxp.services.customer.model.dto.CustomerRequest;
import com.indigitalxp.services.customer.model.entity.Customer;
import com.indigitalxp.services.customer.repository.CustomerRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServicesImpl implements CustomerServices {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerMapper customerMapper;

  @Override
  public Completable create(CustomerRequest customerRequest) {
    return Completable.fromSingle(this.save(customerMapper.toEntity(customerRequest)));
  }

  @Override
  public Single<Customer> save(Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  public Flowable<Customer> getCustomers() {
    return customerRepository.findAll();
  }
}


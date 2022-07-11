package com.indigitalxp.services.customer.business.impl;

import com.indigitalxp.services.customer.business.CustomerServices;
import com.indigitalxp.services.customer.exception.GlobalExceptionHandler;
import com.indigitalxp.services.customer.mapper.CustomerMapper;
import com.indigitalxp.services.customer.model.dto.request.CustomerParam;
import com.indigitalxp.services.customer.model.dto.request.CustomerRequest;
import com.indigitalxp.services.customer.model.dto.response.CustomerResponse;
import com.indigitalxp.services.customer.model.entity.Customer;
import com.indigitalxp.services.customer.repository.CustomerRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.apache.commons.lang3.StringUtils;
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
  public Flowable<CustomerResponse> getCustomers() {
    return customerRepository.findAll().map(customer -> customerMapper.toResponse(customer));
  }

  @Override
  public Flowable<Customer> findAll() {
    return customerRepository.findAll();
  }

  @Override
  public Flowable<Customer> findCustomer(CustomerParam param) {
    if ((param.getDni() != null && param.getEmail() == null) || (param.getDni() == null && param.getEmail() != null)) {
      return Flowable.fromSingle(
          this.valiteIfExists(
              customerRepository.findCustomerByDniOrEmail(
                  StringUtils.isNotBlank(param.getDni())  ?
                      param.getDni() : "", StringUtils.isNotBlank(param.getEmail()) ? param.getEmail() : "")));
    } else {
      return this.findAll();
    }
  }

  private Single<Customer> valiteIfExists(Maybe<Customer> customerMaybe) {
    return customerMaybe.switchIfEmpty(Single.error(GlobalExceptionHandler.notFound()));
  }
}


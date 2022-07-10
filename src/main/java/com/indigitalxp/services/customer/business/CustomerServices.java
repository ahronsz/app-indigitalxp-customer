package com.indigitalxp.services.customer.business;

import com.indigitalxp.services.customer.model.dto.CustomerRequest;
import com.indigitalxp.services.customer.model.entity.Customer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface CustomerServices {

  Completable create(CustomerRequest customerRequest);

  Single<Customer> save(Customer customer);

  Flowable<Customer> getCustomers();


}

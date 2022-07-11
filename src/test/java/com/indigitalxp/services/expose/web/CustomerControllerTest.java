package com.indigitalxp.services.expose.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.indigitalxp.services.customer.business.CustomerServices;
import com.indigitalxp.services.customer.model.dto.request.CustomerRequest;
import io.reactivex.rxjava3.core.Completable;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@WebFluxTest(CustomerController.class)
@ImportAutoConfiguration(classes = {RefreshAutoConfiguration.class})
@ContextConfiguration(classes = CustomerController.class)
public class CustomerControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  CustomerServices customerServices;

  @Test
  public void createTest() {
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.setDni("7677714");
    customerRequest.setLastName("Ahron");
    customerRequest.setName("Sotomayor Zegarra");
    customerRequest.setEmail("ahronsz32@gmail.com");
    customerRequest.setDateBirth(LocalDate.now());

    when(customerServices.create(any())).thenReturn(Completable.complete());

    this.webTestClient
        .post()
        .uri("/customers")
        .body(BodyInserters.fromValue(customerRequest))
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody();
    verify(this.customerServices, times(1))
        .create(any());

  }
}

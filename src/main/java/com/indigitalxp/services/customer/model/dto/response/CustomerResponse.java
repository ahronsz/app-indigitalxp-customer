package com.indigitalxp.services.customer.model.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

  private String name;
  private String lastName;
  private String email;
  private String dni;
  private LocalDateTime creationDate;
  private LocalDate dateBirth;
}

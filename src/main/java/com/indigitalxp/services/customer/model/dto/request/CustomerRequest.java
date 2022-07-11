package com.indigitalxp.services.customer.model.dto.request;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

  @NotNull
  private String name;
  @NotNull
  private String lastName;
  @NotNull
  @Email
  private String email;
  @NotNull
  private String dni;
  @NotNull
  private LocalDate dateBirth;
}

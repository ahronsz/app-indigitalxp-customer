package com.indigitalxp.services.customer.model.dto.request;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
  @Pattern(regexp = "[0-9]{8}", message = "Ingrese un formato correcto de DNI")
  private String dni;
  @NotNull
  private LocalDate dateBirth;
}

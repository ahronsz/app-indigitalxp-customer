package com.indigitalxp.services.customer.model.dto.request;

import java.io.Serializable;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerParam implements Serializable {
  @Pattern(regexp = "[0-9]{8}", message = "Ingrese un formato correcto de DNI")
  private String dni;
  @Email
  private String email;

  @AssertTrue(message = "Por favor consulte DNI o CORREO , no ambos")
  public boolean isValid(){
    return !(dni != null && email != null);
  }
}

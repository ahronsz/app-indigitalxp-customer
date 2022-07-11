package com.indigitalxp.services.customer.model.dto.request;

import java.io.Serializable;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicatorParam implements Serializable {
  private String month;
  private String year;


  @Pattern(regexp = "mayor|menor")
  private String lessOrMore;

  @AssertTrue(message = "Por favor ingresar: Mes y Año, Mayor/Menor o sin parametros")
  public boolean isValid(){
    if (lessOrMore == null) {
      return !((month != null && year == null) || (month == null && year != null));
    }
    return month == null && year == null;
  }

}

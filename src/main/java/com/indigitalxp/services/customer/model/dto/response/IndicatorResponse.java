package com.indigitalxp.services.customer.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IndicatorResponse {
  private String description;
  @JsonIgnore
  private String quantity;
  private MonthsOfYearResponse monthsOfYearResponse;
}

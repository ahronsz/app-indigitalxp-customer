package com.indigitalxp.services.expose.web;

import com.indigitalxp.services.customer.business.IndicatorServices;
import com.indigitalxp.services.customer.model.dto.request.IndicatorParam;
import com.indigitalxp.services.customer.model.dto.response.IndicatorResponse;
import io.reactivex.rxjava3.core.Single;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "indicators")
@Validated
public class IndicatorController {

  @Autowired
  private IndicatorServices indicatorServices;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public Single<IndicatorResponse> indicators(@Valid @ModelAttribute IndicatorParam indicatorParam){
    return indicatorServices.getIndicators(indicatorParam);
  }
}

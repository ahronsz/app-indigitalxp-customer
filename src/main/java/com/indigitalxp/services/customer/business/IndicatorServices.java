package com.indigitalxp.services.customer.business;

import com.indigitalxp.services.customer.model.dto.request.IndicatorParam;
import com.indigitalxp.services.customer.model.dto.response.IndicatorResponse;
import io.reactivex.rxjava3.core.Single;

public interface IndicatorServices {

  Single<IndicatorResponse> getIndicators(IndicatorParam indicatorParam);

  Single<IndicatorResponse> getByMonthAndYear(IndicatorParam indicatorParam);

  Single<IndicatorResponse> getWithHighestOrFewestQuantity(String query);

  Single<IndicatorResponse> getBirthRateEachMonth();

}

package com.indigitalxp.services.customer.mapper;

import com.indigitalxp.services.customer.model.dto.response.IndicatorResponse;
import com.indigitalxp.services.customer.model.dto.response.MonthsOfYearResponse;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface IndicatorMapper {

  //@Mapping(target = "monthsOfYearResponse", defaultExpression = "java(null)")
  IndicatorResponse toResponse(
      String quantity, String description, MonthsOfYearResponse monthsOfYearResponse);
}


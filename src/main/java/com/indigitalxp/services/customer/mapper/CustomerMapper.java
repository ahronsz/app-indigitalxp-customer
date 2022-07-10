package com.indigitalxp.services.customer.mapper;

import com.indigitalxp.services.customer.model.dto.CustomerRequest;
import com.indigitalxp.services.customer.model.entity.Customer;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface CustomerMapper {

  @Mapping(target = "creationDate", expression = "java(LocalDateTime.now())")
  Customer toEntity(CustomerRequest request);
}

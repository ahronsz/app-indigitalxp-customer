package com.indigitalxp.services.customer.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "Customers")
@Getter
@Setter
public class Customer {

  @Id
  @Column
  private Long id;
  @Column
  private String name;
  @Column
  private String lastName;
  @Column
  private String email;
  @Column
  private String dni;
  @Column
  private LocalDateTime creationDate;
  @Column
  private LocalDate dateBirth;

}

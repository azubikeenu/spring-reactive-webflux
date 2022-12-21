package com.azubike.ellipsis.spring_webflux_test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("product")
public class ProductEntity {
  @Id private long id;
  private String name;
  private String description;

  @Builder
  public ProductEntity(String name, String description) {
    this.name = name;
    this.description = description;
  }
}

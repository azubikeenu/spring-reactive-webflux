package com.azubike.ellipsis.spring_webflux_test.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {
  private Long id;
  private String description;
  private String name;
}

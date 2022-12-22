package com.azubike.ellipsis.spring_webflux_test.mappers;

import com.azubike.ellipsis.spring_webflux_test.entity.ProductEntity;
import com.azubike.ellipsis.spring_webflux_test.response.ProductResponse;
import lombok.Synchronized;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductResponse {

  @Synchronized
  public ProductResponse convert(ProductEntity source) {
    return new ModelMapper().map(source, ProductResponse.class);
  }
}

package com.azubike.ellipsis.spring_webflux_test.mappers;

import com.azubike.ellipsis.spring_webflux_test.entity.ProductEntity;
import com.azubike.ellipsis.spring_webflux_test.request.ProductRequest;
import lombok.Synchronized;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestToProduct  {
  @Synchronized
  public ProductEntity convert(ProductRequest source) {
    return new ModelMapper().map(source, ProductEntity.class);
  }
}

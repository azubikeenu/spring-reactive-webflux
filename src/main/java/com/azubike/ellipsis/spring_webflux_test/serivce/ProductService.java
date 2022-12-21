package com.azubike.ellipsis.spring_webflux_test.serivce;

import com.azubike.ellipsis.spring_webflux_test.entity.ProductEntity;
import com.azubike.ellipsis.spring_webflux_test.repositories.ProductRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ProductService {
  private final ProductRepo productRepo;

  public ProductService(ProductRepo productRepo) {
    this.productRepo = productRepo;
  }

  public Flux<ProductEntity> getAll() {
    return productRepo.findAll();
  }
}

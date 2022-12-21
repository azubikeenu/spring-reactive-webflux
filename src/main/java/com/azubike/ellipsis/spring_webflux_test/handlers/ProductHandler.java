package com.azubike.ellipsis.spring_webflux_test.handlers;

import com.azubike.ellipsis.spring_webflux_test.entity.ProductEntity;
import com.azubike.ellipsis.spring_webflux_test.serivce.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {
  private final ProductService productService;

  public ProductHandler(ProductService productService) {
    this.productService = productService;
  }

  public Mono<ServerResponse> getAllProducts(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(productService.getAll(), ProductEntity.class);
  }
}

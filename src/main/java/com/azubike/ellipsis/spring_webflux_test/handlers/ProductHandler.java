package com.azubike.ellipsis.spring_webflux_test.handlers;

import com.azubike.ellipsis.spring_webflux_test.exceptions.ProductException;
import com.azubike.ellipsis.spring_webflux_test.mappers.ProductRequestToProduct;
import com.azubike.ellipsis.spring_webflux_test.mappers.ProductToProductResponse;
import com.azubike.ellipsis.spring_webflux_test.request.ProductRequest;
import com.azubike.ellipsis.spring_webflux_test.response.ProductResponse;
import com.azubike.ellipsis.spring_webflux_test.serivce.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {
  private final ProductService productService;
  private final ProductRequestToProduct productRequestToProduct;
  private final ProductToProductResponse productToProductResponse;

  public ProductHandler(
      ProductService productService,
      ProductRequestToProduct productRequestToProduct,
      ProductToProductResponse productToProductResponse) {
    this.productService = productService;
    this.productRequestToProduct = productRequestToProduct;
    this.productToProductResponse = productToProductResponse;
  }

  public Mono<ServerResponse> getAllProducts(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            productService.getAll().map(productToProductResponse::convert), ProductResponse.class);
  }

  public Mono<ServerResponse> getProduct(ServerRequest request) {
    final Long id = Long.valueOf(request.pathVariable("id"));
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            productService
                .getProductById(id)
                .onErrorResume(
                    e -> Mono.error(new ProductException(e.getMessage(), HttpStatus.BAD_REQUEST)))
                .map(productToProductResponse::convert),
            ProductResponse.class);
  }

  public Mono<ServerResponse> getProductByParam(ServerRequest request) {
    final Long id = Long.valueOf(request.queryParam("id").orElse("0"));
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            productService
                .getProductById(id)
                .onErrorResume(
                    e -> Mono.error(new ProductException(e.getMessage(), HttpStatus.BAD_REQUEST)))
                .map(productToProductResponse::convert),
            ProductResponse.class);
  }

  public Mono<ServerResponse> createProduct(ServerRequest request) {
    final Mono<ProductRequest> productRequestMono = request.bodyToMono(ProductRequest.class);
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            productRequestMono
                .map(productRequestToProduct::convert)
                .flatMap(productService::saveProduct)
                .onErrorResume(
                    e -> Mono.error(new ProductException(e.getMessage(), HttpStatus.BAD_REQUEST)))
                .map(productToProductResponse::convert),
            ProductResponse.class);
  }
}

package com.azubike.ellipsis.spring_webflux_test.router;

import com.azubike.ellipsis.spring_webflux_test.handlers.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class Router {
  private static final String BASE_URL = "/api/v1/";
  private final ProductHandler productHandler;

  public Router(ProductHandler productHandler) {
    this.productHandler = productHandler;
  }

  @Bean
  public RouterFunction<ServerResponse> productRoutes() {
    return RouterFunctions.route(
            RequestPredicates.GET(BASE_URL + "products"), productHandler::getAllProducts)
        .andRoute(
            RequestPredicates.GET(BASE_URL + "products/search"), productHandler::getProductByParam)
        .andRoute(RequestPredicates.GET(BASE_URL + "products/{id}"), productHandler::getProduct)
        .andRoute(
            RequestPredicates.POST(BASE_URL + "products")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
            productHandler::createProduct);
  }
}

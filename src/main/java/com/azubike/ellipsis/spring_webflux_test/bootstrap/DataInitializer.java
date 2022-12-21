package com.azubike.ellipsis.spring_webflux_test.bootstrap;

import com.azubike.ellipsis.spring_webflux_test.entity.ProductEntity;
import com.azubike.ellipsis.spring_webflux_test.repositories.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {
  private final ProductRepo productRepo;

  public DataInitializer(ProductRepo productRepo) {
    this.productRepo = productRepo;
  }

  @Override
  public void run(String... args) throws Exception {
    final Flux<ProductEntity> products = productRepo.findAll();
    final List<ProductEntity> productList = products.toStream().collect(Collectors.toList());
    if (productList.size() == 0) {
      List<ProductEntity> newProducts =
          List.of(
              ProductEntity.builder()
                  .name("product_one")
                  .description("This is a dummy description")
                  .build(),
              ProductEntity.builder()
                  .name("product_two")
                  .description("This is a dummy description")
                  .build(),
              ProductEntity.builder()
                  .name("product_three")
                  .description("This is a dummy description")
                  .build());

      log.info("Saving new data");

      productRepo.saveAll(newProducts);
    }
  }
}

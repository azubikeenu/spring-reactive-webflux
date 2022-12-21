package com.azubike.ellipsis.spring_webflux_test.repositories;

import com.azubike.ellipsis.spring_webflux_test.entity.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends ReactiveCrudRepository<ProductEntity, Long> { }
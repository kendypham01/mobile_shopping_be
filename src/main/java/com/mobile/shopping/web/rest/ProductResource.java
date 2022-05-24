package com.mobile.shopping.web.rest;

import com.mobile.shopping.domain.Product;
import com.mobile.shopping.domain.User;
import com.mobile.shopping.repository.ProductRepository;
import com.mobile.shopping.service.UserService;
import com.mobile.shopping.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
@Transactional
public class ProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private static final String ENTITY_NAME = "product";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductRepository productRepository;

    private final UserService userService;

    public ProductResource(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws URISyntaxException {
        log.debug("REST request to save Product : {}", product);
        Optional<User> userLogin = userService.getUserWithAuthorities();
        if (product.getId() == null) {
            product.setCreateDate(Instant.now());
            product.setCreateName(userLogin.get().getLogin());
        }else{
            Product oldProduct = productRepository.findById(product.getId()).get();
            product.setCreateDate(oldProduct.getCreateDate());
            product.setCreateName(oldProduct.getCreateName());
            product.setUpdateDate(Instant.now());
            product.setUpdateName(userLogin.get().getLogin());
        }
        product.setStatus(true);
        Product result = productRepository.save(product);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        log.debug("REST request to get all Products");
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        Optional<Product> product = productRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        productRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

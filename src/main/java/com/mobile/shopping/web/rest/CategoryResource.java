package com.mobile.shopping.web.rest;

import com.mobile.shopping.domain.Category;
import com.mobile.shopping.domain.User;
import com.mobile.shopping.repository.CategoryRepository;
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
public class CategoryResource {

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    private static final String ENTITY_NAME = "category";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryRepository categoryRepository;

    private final UserService userService;

    public CategoryResource(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws URISyntaxException {
        Optional<User> userLogin = userService.getUserWithAuthorities();
        if (category.getId() == null) {
            category.setCreateDate(Instant.now());
            category.setCreateName(userLogin.get().getLogin());
        }else{
            Category oldCategory = categoryRepository.findById(category.getId()).get();
            category.setCreateDate(oldCategory.getCreateDate());
            category.setCreateName(oldCategory.getCreateName());
            category.setUpdateDate(Instant.now());
            category.setUpdateName(userLogin.get().getLogin());
        }
        category.setStatus(true);
        Category result = categoryRepository.save(category);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        log.debug("REST request to get all Categories");
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(category);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.debug("REST request to delete Category : {}", id);
        categoryRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

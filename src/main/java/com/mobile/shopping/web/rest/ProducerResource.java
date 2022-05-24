package com.mobile.shopping.web.rest;

import com.mobile.shopping.domain.Producer;
import com.mobile.shopping.domain.User;
import com.mobile.shopping.repository.ProducerRepository;
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
public class ProducerResource {

    private final Logger log = LoggerFactory.getLogger(ProducerResource.class);

    private static final String ENTITY_NAME = "producer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProducerRepository producerRepository;

    private final UserService userService;

    public ProducerResource(ProducerRepository producerRepository, UserService userService) {
        this.producerRepository = producerRepository;
        this.userService = userService;
    }

    @PostMapping("/producers")
    public ResponseEntity<Producer> createProducer(@RequestBody Producer producer) throws URISyntaxException {
        Optional<User> userLogin = userService.getUserWithAuthorities();
        if (producer.getId() == null) {
            producer.setCreateDate(Instant.now());
            producer.setCreateName(userLogin.get().getLogin());
        }else{
            Producer oldProducer = producerRepository.findById(producer.getId()).get();
            producer.setCreateDate(oldProducer.getCreateDate());
            producer.setCreateName(oldProducer.getCreateName());
            producer.setUpdateDate(Instant.now());
            producer.setUpdateName(userLogin.get().getLogin());
        }
        producer.setStatus(true);
        Producer result = producerRepository.save(producer);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/producers")
    public List<Producer> getAllProducers() {
        return producerRepository.findAll();
    }

    @GetMapping("/producers/{id}")
    public ResponseEntity<Producer> getProducer(@PathVariable Long id) {
        log.debug("REST request to get Producer : {}", id);
        Optional<Producer> producer = producerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(producer);
    }

    @DeleteMapping("/producers/{id}")
    public ResponseEntity<Void> deleteProducer(@PathVariable Long id) {
        log.debug("REST request to delete Producer : {}", id);
        producerRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

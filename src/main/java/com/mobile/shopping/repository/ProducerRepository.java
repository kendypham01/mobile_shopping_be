package com.mobile.shopping.repository;

import com.mobile.shopping.domain.Producer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {}

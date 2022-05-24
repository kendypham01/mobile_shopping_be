package com.mobile.shopping.repository;

import com.mobile.shopping.domain.Image;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {}

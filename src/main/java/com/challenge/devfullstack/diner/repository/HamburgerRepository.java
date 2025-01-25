package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.Hamburger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HamburgerRepository extends JpaRepository<Hamburger, Long> {
    Page<Hamburger> findAllByDeletedAtNull(Pageable page);
    List<Hamburger> findAllByDeletedAtNullAndDescriptionContainingIgnoreCase(String description);
}

package com.ROI.test.model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {
    Collection<User> findByUsername(String username);
}


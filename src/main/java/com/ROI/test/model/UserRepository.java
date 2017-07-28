package com.ROI.test.model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Collection<User> findByBirthday(Date birthday);

    Optional<User> findByEmail(String email);

    Collection<User> findByUserNameContaining(String partOfUsername);
}


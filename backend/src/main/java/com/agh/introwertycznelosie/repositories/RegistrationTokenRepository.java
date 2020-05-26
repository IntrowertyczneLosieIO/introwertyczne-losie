package com.agh.introwertycznelosie.repositories;

import com.agh.introwertycznelosie.data.RegistrationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationTokenRepository  extends JpaRepository<RegistrationToken, Long> {
    RegistrationToken findByToken(String token);
}

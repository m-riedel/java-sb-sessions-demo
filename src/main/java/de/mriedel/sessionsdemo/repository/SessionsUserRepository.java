package de.mriedel.sessionsdemo.repository;

import de.mriedel.sessionsdemo.entity.SessionsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionsUserRepository extends JpaRepository<SessionsUser, Long> {
    Optional<SessionsUser> findByUsername(String username);

}

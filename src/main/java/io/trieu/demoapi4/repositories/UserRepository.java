package io.trieu.demoapi4.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.trieu.demoapi4.models.Users;
public interface UserRepository extends JpaRepository<Users, Long>{

    Optional<Users> findById(Long id);

    Optional<Users> findByName(String trim);
    
}

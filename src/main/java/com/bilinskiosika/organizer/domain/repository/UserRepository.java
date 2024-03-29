package com.bilinskiosika.organizer.domain.repository;

import com.bilinskiosika.organizer.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByIdUser(long idUser);
    Optional<User> findUserByUsername(String username);
}

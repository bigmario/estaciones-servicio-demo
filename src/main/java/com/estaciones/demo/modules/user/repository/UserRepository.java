package com.estaciones.demo.modules.user.repository;

import com.estaciones.demo.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAll();
    boolean existsByEmail(String email);
    @Modifying
    @Transactional
    @Query(value="ALTER TABLE public.users DISABLE ROW LEVEL SECURITY", nativeQuery = true)
    void disableRls();
    @Modifying
    @Transactional
    @Query( value = "ALTER TABLE public.users ENABLE ROW LEVEL SECURITY",nativeQuery = true)
    void activateRls();
}


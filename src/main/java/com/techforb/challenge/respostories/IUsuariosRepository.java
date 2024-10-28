package com.techforb.challenge.respostories;

import com.techforb.challenge.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuario,Long> {
    @Query("SELECT u FROM Usuario u JOIN u.credencial c WHERE c.email = :email")
    Optional<Usuario> findByEmail(String email);
    @Query("SELECT COUNT(u) > 0 FROM Usuario u JOIN u.credencial c WHERE c.email = :email")
    boolean existsByEmail(String email);

}

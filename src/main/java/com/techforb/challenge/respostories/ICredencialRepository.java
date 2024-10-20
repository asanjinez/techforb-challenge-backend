package com.techforb.challenge.respostories;

import com.techforb.challenge.models.Credencial;
import com.techforb.challenge.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICredencialRepository extends JpaRepository<Credencial,Long> {
    Optional<Credencial> findByEmail(String email);
    Boolean existsByEmail(String email);
}

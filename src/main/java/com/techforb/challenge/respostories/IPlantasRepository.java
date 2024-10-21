package com.techforb.challenge.respostories;

import com.techforb.challenge.models.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPlantasRepository extends JpaRepository<Planta,Long> {
    List<Planta> findAll();
    Optional<Planta> findByIdPlanta(Long idPlanta);

    boolean existsById(Long idPlanta);
    boolean existsByNombreAndPais(String nombre, String pais);
}

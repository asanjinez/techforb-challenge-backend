package com.techforb.challenge.respostories;

import com.techforb.challenge.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuario,Long> {

}

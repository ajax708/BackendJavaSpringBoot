package com.example.digital.repository;

import com.example.digital.models.Patito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatitoRepository extends JpaRepository<Patito, Integer> {

    @Query (value = "select * from Patito where borrado != 1", nativeQuery = true)
    List<Patito> FindAllByBorrado();

    @Query (value = "select * from Patito  where id = :_id and borrado != 1", nativeQuery = true )
    Patito FindByIAndBorrado(Integer _id);
}

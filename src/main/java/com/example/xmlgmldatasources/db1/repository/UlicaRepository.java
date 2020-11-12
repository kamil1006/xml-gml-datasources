package com.example.xmlgmldatasources.db1.repository;


import com.example.xmlgmldatasources.db1.entity.Ulica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UlicaRepository extends CrudRepository<Ulica,Integer> {
}

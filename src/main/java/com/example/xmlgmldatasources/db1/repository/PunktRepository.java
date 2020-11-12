package com.example.xmlgmldatasources.db1.repository;


import com.example.xmlgmldatasources.db1.entity.Punkt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PunktRepository extends CrudRepository<Punkt,Integer> {
}

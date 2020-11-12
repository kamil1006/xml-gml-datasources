package com.example.xmlgmldatasources.db1.repository;


import com.example.xmlgmldatasources.db1.entity.Adres;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresRepository extends CrudRepository<Adres,Integer> {
}

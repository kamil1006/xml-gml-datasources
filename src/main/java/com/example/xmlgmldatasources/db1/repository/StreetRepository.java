package com.example.xmlgmldatasources.db1.repository;


import com.example.xmlgmldatasources.db1.entity.Street;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends CrudRepository<Street,Integer> {
}

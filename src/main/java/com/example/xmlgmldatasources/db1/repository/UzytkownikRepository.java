package com.example.xmlgmldatasources.db1.repository;

import com.example.xmlgmldatasources.db1.entity.Uzytkownik;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UzytkownikRepository extends CrudRepository<Uzytkownik,String> {
}

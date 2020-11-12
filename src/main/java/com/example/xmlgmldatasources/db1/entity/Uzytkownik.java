package com.example.xmlgmldatasources.db1.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Uzytkownik {

    @Id
    @Column
    private String nazwa;
    @Column
    private  String haslo;

}

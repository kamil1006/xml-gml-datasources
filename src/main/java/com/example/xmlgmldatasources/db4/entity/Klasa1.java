package com.example.xmlgmldatasources.db4.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="tabela_jeden")
public class Klasa1 {

    @Id
    @Column
    private int id;

    @Column
    private String pole1;
    @Column
    private String pole2;
    @Column
    private String pole3;

}

package com.example.xmlgmldatasources.db2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="klasa_testowa")
@Data
@NoArgsConstructor
public class KlasaTestowa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column
    private String pole1;
    @Column
    private String pole2;

}

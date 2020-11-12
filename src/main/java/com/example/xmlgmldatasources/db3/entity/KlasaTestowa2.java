package com.example.xmlgmldatasources.db3.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="klasa_testowa2")
@Data
@NoArgsConstructor
public class KlasaTestowa2 {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column
    private String pole1;

}

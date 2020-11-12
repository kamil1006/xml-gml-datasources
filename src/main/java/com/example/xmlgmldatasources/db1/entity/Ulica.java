package com.example.xmlgmldatasources.db1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="ulice")
@Data
@NoArgsConstructor
public class Ulica {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    private String idTERYT;
    @Column
    private String przedrostek1Czesc;
    @Column
    private String nazwaGlownaCzesc;

    public Ulica(String idTERYT, String nazwaGlownaCzesc) {
        this.idTERYT = idTERYT;
        this.nazwaGlownaCzesc = nazwaGlownaCzesc;
        this.przedrostek1Czesc = "";
    }

    public Ulica(String idTERYT, String przedrostek1Czesc, String nazwaGlownaCzesc) {
        this.idTERYT = idTERYT;
        this.przedrostek1Czesc = przedrostek1Czesc;
        this.nazwaGlownaCzesc = nazwaGlownaCzesc;

    }
}

package com.example.xmlgmldatasources.db1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="adres")
@Data
@NoArgsConstructor
public class Adres {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column
    private String href;
    @Column
    private String idTeryt;

   // @Column
   // @ManyToOne
   // private Ulica2 ulica2;


    public Adres(String href) {
        this.href = href;
    }

    public Adres(String href, String idTeryt) {
        this.href = href;
        this.idTeryt = idTeryt;
    }
}

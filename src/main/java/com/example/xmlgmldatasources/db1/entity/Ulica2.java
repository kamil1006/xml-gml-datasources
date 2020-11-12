package com.example.xmlgmldatasources.db1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="ulice2")
@Data
@NoArgsConstructor
public class Ulica2 {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    private String idTERYT;
    @Column
    private String przedrostek1Czesc;
    @Column
    private String nazwaGlownaCzesc;

    //@Column
   // @OneToMany
  //  private List<Adres> adresList;



    public Ulica2(String idTERYT, String nazwaGlownaCzesc) {
        this.idTERYT = idTERYT;
        this.nazwaGlownaCzesc = nazwaGlownaCzesc;
        this.przedrostek1Czesc = "";
    }

    public Ulica2(String idTERYT, String przedrostek1Czesc, String nazwaGlownaCzesc) {
        this.idTERYT = idTERYT;
        this.przedrostek1Czesc = przedrostek1Czesc;
        this.nazwaGlownaCzesc = nazwaGlownaCzesc;

    }
}

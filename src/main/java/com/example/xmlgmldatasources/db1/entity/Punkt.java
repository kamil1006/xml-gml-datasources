package com.example.xmlgmldatasources.db1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="punkty")
@Data
@NoArgsConstructor
public class Punkt {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column
    private String href;
    @Column
    private String nr;
    @Column
    private String kod;


   // @Column
   // @ManyToOne
   // private Ulica2 ulica2;


    public Punkt(String href, String nr, String kod) {
        this.href = href;
        this.nr = nr;
        this.kod = kod;
    }
}

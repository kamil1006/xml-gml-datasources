package com.example.xmlgmldatasources.db1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="street")
@Data
@NoArgsConstructor
public class Street {

    @Id
    private int id;
    @Column
    private String namePrefix;
    @Column
    private String namePrefix2;
    @Column
    private String nameOfficial;
    @Column
    private String typeSymbol;
    @Column
    private String typeName;
    @Column
    private String status;

    public Street(int id, String namePrefix, String namePrefix2, String nameOfficial, String typeSymbol, String typeName, String status) {
        this.id = id;
        this.namePrefix = namePrefix;
        this.namePrefix2 = namePrefix2;
        this.nameOfficial = nameOfficial;
        this.typeSymbol = typeSymbol;
        this.typeName = typeName;
        this.status = status;
    }
}

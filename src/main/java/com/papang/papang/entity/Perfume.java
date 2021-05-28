package com.papang.papang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name="perfume")
public class Perfume implements Serializable {

    @Id
    @Column(name="perfumeID", nullable = false)
    int perfumeID;

    @Column(name="brand")
    String brand;

    @Column(name="name")
    String name;

    @Column(name="size")
    int size;

    @Column(name="concentration")
    int concentration;

    @Column(name="style")
    int style;

    @Column(name="main")
    int main;

    @Column(name="first")
    int first;

    @Column(name="second")
    int second;

    @Column(name="url")
    String url;

    public String getBrand(){
        return this.brand;
    }
    public String getName(){
        return this.name;
    }
    public String getUrl(){
        return this.url;
    }
    public int getPerfumeID(){
        return this.perfumeID;
    }
    public int getSize(){
        return this.size;
    }
    public int getConcentration(){
        return this.concentration;
    }
    public int getStyle(){
        return this.style;
    }
    public int getMain(){
        return this.main;
    }
    public int getFirst(){
        return this.first;
    }
    public int getSecond(){
        return this.second;
    }

    public Perfume(int perfumeID, String brand, String name, int size, int concentration, int style, int main, int first, int second, String url){
        this.perfumeID = perfumeID;
        this.brand = brand;
        this.name = name;
        this.size = size;
        this.concentration = concentration;
        this.style = style;
        this.main = main;
        this.first = first;
        this.second = second;
        this.url = url;
    }
}

package com.temp.model.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "conferences")
public class Conference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public Conference(String name) {
        this.name = name;
    }
}

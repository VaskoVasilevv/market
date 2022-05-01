package com.example.market.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {

    private String name;
    @ManyToOne
    private User owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ryzen5
 */
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chicken;
    private Long beef;
    private Long vegetarian;

    // Constructors, getters, setters, and other methods can be added as needed

    // Default constructor
    public Stock() {
    }

    // Parameterized constructor
    public Stock(Long chicken, Long beef, Long vegetarian) {
        this.chicken = chicken;
        this.beef = beef;
        this.vegetarian = vegetarian;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChicken() {
        return chicken;
    }

    public void setChicken(Long chicken) {
        this.chicken = chicken;
    }

    public Long getBeef() {
        return beef;
    }

    public void setBeef(Long beef) {
        this.beef = beef;
    }

    public Long getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Long vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", chicken='" + chicken + '\'' +
                ", beef='" + beef + '\'' +
                ", vegetarian='" + vegetarian + '\'' +
                '}';
    }
}

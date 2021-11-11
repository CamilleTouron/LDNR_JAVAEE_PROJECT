/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Groupe 1
 */
public class Dish {
    /**
     * Attribu nom d'un plat.
     */
    private String name;
    /**
     * Attribu quantité d'un plat.
     */
    private int qty;
    /**
     * Contructeur d'un plat
     * @param name
     * @param qty 
     */
    public Dish(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }

    /**
     * Méthode de récupération du nom d'un plat.
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * Méthode d'attribution des valeurs des attribus d'un plat.
     * @param name
     * @param qty 
     */
    public void set(String name,int qty) {
        this.name = name;
        this.qty = qty;
    }
    /**
     * Méthode de récupération de la quantité d'un plat.
     * @return 
     */
    public int getQty() {
        return qty;
    }   
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author nicol
 */
public class Especie {
    //Definindo variaveis
    private int idEspecie;
    private String nomeEspecie;
    
    //Metodos de encapsulamento getters e setters   
    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getNomeEspecie() {
        return nomeEspecie;
    }
    
    public void setNomeEspecie(String nomeEspecie) {
        this.nomeEspecie = nomeEspecie;
    }
    
    @Override
    public String toString() {
        return nomeEspecie;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import java.time.LocalDate;

/**
 *
 * @author nicol
 */
public class Interesse {
    //Definindo as variaveis da classe interesse. É a classe de intemediaria de muitos pra muitos
    private int idUsuario; //PK, FK para Usuario (chave primaria composta)
    private int idPet; //PK, FK para Pet (chave primaria composta)
    private LocalDate dataInteresse;
    
    //Para facilitar o uso, buscamos o objeto completo
    private Usuario usuario;
    private Pet pet;
    
    //Construtor com parâmetros (ADICIONE ESTE)
    public Interesse(int idUsuario, int idPet) {
        this.idUsuario = idUsuario;
        this.idPet = idPet;
    }
    
    //Metodos de encapsulamento getters e setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {   
        this.idUsuario = idUsuario;
    }

    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
        this.idPet = idPet;
    }

    public LocalDate getDataInteresse() {
        return dataInteresse;
    }

    public void setDataInteresse(LocalDate dataInteresse) {
        this.dataInteresse = dataInteresse;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
    
    @Override
    public String toString() {
        return "Interesse em " + dataInteresse;
    }
}

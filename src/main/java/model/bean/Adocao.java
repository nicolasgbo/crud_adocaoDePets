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
public class Adocao {
    //Definindo variaveis
    private int idAdocao;
    private int idUsuarioAdotante; //FK para usuario
    private int idPet; //FK para pet
    private LocalDate dataAdocao;
    
    //Para facilitar o uso, buscamos o Objeto Completo
    private Usuario adotante;
    private Pet pet;
    
    //Metodos de encapsulamento
    public int getIdAdocao() {
        return idAdocao;
    }

    public void setIdAdocao(int idAdocao) {
        this.idAdocao = idAdocao;
    }

    public int getIdUsuarioAdotante() {
        return idUsuarioAdotante;
    }

    public void setIdUsuarioAdotante(int idUsuarioAdotante) {
        this.idUsuarioAdotante = idUsuarioAdotante;
    }

    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
        this.idPet = idPet;
    }

    public LocalDate getDataAdocao() {
        return dataAdocao;
    }

    public void setDataAdocao(LocalDate dataAdocao) {
        this.dataAdocao = dataAdocao;
    }
    
    public Usuario getAdotante() {
        return adotante;
    }

    public void setAdotante(Usuario adotante) {
        this.adotante = adotante;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
    
    @Override
    public String toString() {
        return "Adoção #" + idAdocao + " em " + dataAdocao;
    }
}

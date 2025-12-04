/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author nicol
 */
public class Pet {
    //Definindo variaveis
    private int idPet;
    private String nomePet;
    private int idadePet;
    private String statusPet; //Usamos ENUM no BD, sao eles "disponivel" e "adotado"
    private int idUsuarioDono; //FK para usuario
    private int idEspecie; //FK para especie
    
    //Metodo de encapsulamento
    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
        this.idPet = idPet;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public int getIdadePet() {
        return idadePet;
    }

    public void setIdadePet(int idadePet) {
        this.idadePet = idadePet;
    }

    public String getStatusPet() {
        return statusPet;
    }

    public void setStatusPet(String statusPet) {
        this.statusPet = statusPet;
    }

    public int getIdUsuarioDono() {
        return idUsuarioDono;
    }

    public void setIdUsuarioDono(int idUsuario) {
        this.idUsuarioDono = idUsuario;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }
    
    @Override
    public String toString() {
        return nomePet + " - " + idadePet + " anos (" + statusPet + ")";
    }
}

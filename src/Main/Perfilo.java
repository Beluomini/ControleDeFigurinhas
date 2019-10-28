/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Date;

/**
 *
 * @author Lucas
 */
public class Perfilo {
    private String nome;
    private String idade;
    private String time;
    private String datan;

    public Perfilo() {
    }

    public Perfilo(String nome, String idade, String time, String datan) {
        this.nome = nome;
        this.idade = idade;
        this.time = time;
        this.datan = datan;
    }

    @Override
    public String toString() {
        return "Perfil{" + "Nome=" + nome
                + ", Idade=" + idade 
                + ",Time favorito" + time 
                + ", Data Nasc" + datan +'}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDatan() {
        return datan;
    }

    public void setDatan(String datan) {
        this.datan = datan;
    }
}

package com.example.gabriele.play4me;

import java.io.Serializable;

/**
 * Created by anto on 19/10/15.
 */
public class Person implements Serializable {

    private String nome;
    private String username;
    private String password;
    private int punteggioTot;
    private int voti;
    private String citta;
    private String fotoProfilo;
    private String banner;

    public Person() {
        this.nome = "";
        this.username = "";
        this.password = "";
    }

    public Person(String nome, String username, String password, int punteggioTot, int voti, String citta, String fotoProfilo, String banner) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.punteggioTot = punteggioTot;
        this.voti = voti;
        this.citta = citta;
        this.fotoProfilo = fotoProfilo;
        this.banner = banner;
    }

    public String getNome() {
        return nome;
    }


    public boolean match(String username, String password) {
        return (this.username.equals(username) && this.password.equals(password));
    }

}

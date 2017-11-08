package br.com.wl.model;

import java.io.Serializable;
import java.util.Date;

public class Pessoa implements Serializable {

    private int id;
    private String nome;
    private String cpf;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    private Pessoa(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public static Pessoa of(int id, String nome, String cpf) {
        return new Pessoa(id, nome, cpf);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

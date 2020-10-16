package com.cadernetadegastos;

public class Fornecedor {

    private long id;
    private String nome;
    private String telefone;
    private String email;
    private String uf;

    public Fornecedor(){

    }

    public Fornecedor(long id, String nome, String telefone, String email, String uf){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.uf = uf;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String toString(){
        return "Informacoes Fornecedor " + "\n"
                + "Id: " + id + "\n"
                + "Nome: " + nome + "\n"
                + "Telefone: " + telefone + "\n"
                + "Email: " + email + "\n"
                + "UF: " + uf;
    }
}

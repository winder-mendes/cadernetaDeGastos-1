package com.cadernetadegastos;

public class Usuario {

    private long id;
    private String login;
    private String senha;
    private String email;
    private String fone;
    private float renda;

    public Usuario(){

    }

    public Usuario(long id, String login, String senha, String email, String fone, float renda){
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.fone = fone;
        this.renda = renda;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public float getRenda() {
        return renda;
    }

    public void setRenda(float renda) {
        this.renda = renda;
    }

    public String toString(){
        return "Informacoes Usuario " + "\n"
                + "Id: " + id + "\n"
                + "Login: " + login + "\n"
                + "Senha: " + senha + "\n"
                + "Email: " + email + "\n"
                + "Fone: " + fone + "\n"
                + "Renda: " + renda;
    }
}

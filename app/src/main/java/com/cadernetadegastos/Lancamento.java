package com.cadernetadegastos;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lancamento {

    private long id;
    private String tipo;
    private Date data;
    private float valor;
    private String descricao;
    private Categoria categoria;
    private Usuario usuario;
    private Fornecedor fornecedor;

    public Lancamento(){

    }

    public Lancamento(long id, String tipo, Date data, float valor, String descricao){
        this.id = id;
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return this.descricao + "        " + this.valor;
    }

    public String conteudo(){
        String categoria = this.categoria == null ? "" : "categoria= " + this.categoria.getDescricao() + '\n';
        String fornecedor = this.fornecedor == null ? "" : "fornecedor= " + this.fornecedor.getNome();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String data = df.format(this.data);
        return
                "id=" + id + '\n' +
                        "tipo= " + tipo + '\n' +
                        "data= " + data + '\n' +
                        "valor= " + valor + '\n' +
                        "descricao= " + descricao + '\n' +
                        categoria +
                        fornecedor;
    }
}

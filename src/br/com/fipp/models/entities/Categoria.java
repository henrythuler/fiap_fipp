package br.com.fipp.models.entities;

import br.com.fipp.models.enums.Tipo;

public class Categoria
{
    private int id;
    private Tipo tipo;
    private String descricao;
    private int idUsuario;


    public Categoria(int id, int idUsuario, Tipo tipo, String descricao)
    {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
    }

    public void update(Tipo tipo, String descricao)
    {
        this.tipo = tipo;
        this.descricao = descricao;
    }


    public int getId() { return id; }
    public String getDescricao() { return this.descricao; }
    public int getIdUsuario() { return idUsuario; }
    public Tipo getTipo() { return tipo; }
    public void setId(int id) { this.id = id; }


    public void setTipo(Tipo tipo) { this.tipo = tipo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }


    @Override
    public String toString(){
        return String.format("ID: %s | TIPO: %s | DESCRIÇÃO: %s%n", getId(), getTipo(), getDescricao());
    }

}

package com.fipp.models.entities;

import com.fipp.models.enums.*;

public class Subcategoria
{
    private int id;
    private final int categoriaId;
    private final int idUsuario;
    private String descricao;
    private final Tipo tipo;


    public Subcategoria(int id, int categoriaId, int idUsuario, Tipo tipo, String descricao) {
        this.id = id;
        this.categoriaId = categoriaId;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.descricao = descricao;
    }


    public String getDescricao()
    {
        return this.descricao;
    }
    public int getId() { return id; }
    public int getCategoriaId() { return categoriaId; }
    public Tipo getTipo() { return tipo; }
    public int getIdUsuario() { return idUsuario; }
    public void setId(int id) { this.id = id; }
    //public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    //public void setTipo(Tipo tipo) { this.tipo = tipo; }
    //public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
}

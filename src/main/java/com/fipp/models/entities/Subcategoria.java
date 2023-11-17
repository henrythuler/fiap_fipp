package com.fipp.models.entities;

import com.fipp.dao.CategoriaDAOImpl;
import com.fipp.models.enums.*;

public class Subcategoria
{
    private int id;
    private int categoriaId;
    private int idUsuario;
    private String descricao;
    private Tipo tipo;


    public Subcategoria(int id, int categoriaId, int idUsuario, Tipo tipo, String descricao)
    {
        this.id = id;
        this.categoriaId = categoriaId;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.descricao = descricao;
    }


    public void update(int categoriaId, String descricao)
    {
        this.categoriaId = categoriaId;
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
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }


    @Override
    public String toString(){
        var categoriaDescricao = new CategoriaDAOImpl().getById(getCategoriaId()).getDescricao();
        return String.format("ID: %s | CATEGORIA: %s (%s) | TIPO: %s | DESCRIÇÃO: %s%n", getId(),  categoriaDescricao, getCategoriaId(), getTipo(), getDescricao());
    }
}

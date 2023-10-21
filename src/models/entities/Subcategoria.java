package models.entities;

import models.enums.Tipo;

public class Subcategoria
{
    private int id;
    private int categoriaId;
    private String descricao;
    private Tipo tipo;
    private int idUsuario;


    public Subcategoria(int id, int categoriaId, Tipo tipo, String descricao)
    {
        this.id = id;
        this.categoriaId = categoriaId;
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


}

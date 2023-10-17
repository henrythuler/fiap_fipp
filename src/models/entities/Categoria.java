package models.entities;

import models.enums.Tipo;

public class Categoria
{
    private int id;
    private Tipo tipo;
    private String descricao;

    public Categoria(int id, Tipo tipo, String descricao)
    {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public void update(Tipo tipo, String descricao)
    {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public String getDescricao()
    {
        return this.descricao;
    }

}

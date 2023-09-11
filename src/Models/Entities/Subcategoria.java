package Models.Entities;

import Models.Enums.Tipo;

public class Subcategoria
{
    private int id;
    private int categoriaId;
    private String descricao;

    public Subcategoria(int id, int categoriaId, String descricao)
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
}

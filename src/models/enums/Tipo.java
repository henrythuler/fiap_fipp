package models.enums;

public enum Tipo {
    DESPESA(0, "Despesa"),
    RECEITA(1, "Receita");


    private final int id;
    private final String descricao;


    private Tipo(int id, String descricao)
    {
        this.id = id;
        this.descricao = descricao;
    }


    public int getId()
    {
        return id;
    }

    public String getDescricao()
    {
        return descricao;
    }

}
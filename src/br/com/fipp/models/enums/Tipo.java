package br.com.fipp.models.enums;

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


    public static Tipo valueById(int id)
    {

        for(Tipo t : Tipo.values()){

            if(t.getId() == id) return t;

        }

        return null;

    }

}
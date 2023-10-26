package br.com.fipp.models.enums;

public enum Status {
	PAGO(0, "Pago"),
	PARCELADO(1, "Parcelado"),
	ATRASADO(2, "Atrasado"),
	CANCELADO(3, "Cancelado"),
	AGENDADO(4, "Agendado");


	private final int id;
	private final String descricao;


	private Status(int id, String descricao)
	{
		this.id = id;
		this.descricao = descricao;
	}


	public static Status valueById(int id)
	{

		for(Status s : Status.values()){

			if(s.getId() == id) return s;

		}

		return null;

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
package models.enums;

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


	public int getId()
	{
		return id;
	}

	public String getDescricao()
	{
		return descricao;
	}

}
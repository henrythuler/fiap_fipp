
package Models.Enums;

public enum Metodo {
	DEBITO(0, "Débito"), 
	CREDITO(1, "Crédito"),
	BOLETO(2, "Boleto"),
	PIX(3, "Pix"),
	DINHEIRO(4, "Dinheiro");

	private final int id;
	private final String descricao;

	private Metodo(int id, String descricao)
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

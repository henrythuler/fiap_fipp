package Models.Entities;

import Models.Enums.Status;
import Models.Enums.Metodo;
import java.util.Date;

public class Despesa extends Transacao
{
	protected String beneficiario;

	public Despesa(int id, Date data, double valor, Metodo metodo, String descricao, int categoria, int subcategoria, Status status, String beneficiario)
	{
		super(id, data, valor, metodo, descricao, categoria, subcategoria, status);
		this.beneficiario = beneficiario;
	}


	@Override
 	public void update(Date data, double valor, Metodo metodo, String descricao, int categoria, int subcategoria, Status status, String interessado)
	{
		super.update(data, valor, metodo, descricao, categoria, subcategoria, status, null);
		this.beneficiario = interessado;
	}


	@Override
	public void show()
	{
		super.show();
		System.out.println("Beneficiário: " + this.beneficiario);
	}

}
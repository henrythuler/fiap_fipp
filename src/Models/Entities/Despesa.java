package Models.Entities;

import Models.Enums.Status;
import Models.Enums.Metodo;
import java.math.BigDecimal;
import java.util.Date;

public class Despesa extends Transacao
{
	protected String beneficiario;

	public Despesa(int id, Date data, BigDecimal valor, Metodo metodo, String descricao, int categoriaId, int subcategoriaId, Status status, String beneficiario)
	{
		super(id, data, valor, metodo, descricao, categoriaId, subcategoriaId, status);
		this.beneficiario = beneficiario;
	}


	@Override
 	public void update(Date data, BigDecimal valor, Metodo metodo, String descricao, int categoriaId, int subcategoriaId, Status status, String interessado)
	{
		super.update(data, valor, metodo, descricao, categoriaId, subcategoriaId, status, null);
		this.beneficiario = interessado;
	}


	@Override
	public void show()
	{
		super.show();
		System.out.println("Beneficiário: " + this.beneficiario);
	}

}
package Models.Entities;

import Models.Enums.*;
import java.math.BigDecimal;
import java.util.Date;

public class Receita extends Transacao
{
	protected String pagador;
	
	public Receita(int id, Date data, BigDecimal valor, Metodo metodo, String descricao, Categoria categoria, Subcategoria subcategoria, Status status, String pagador)
	{
		super(id, data, valor, metodo, descricao, categoria, subcategoria, status);
		this.pagador = pagador;
	}


	@Override
	public void update(Date data, BigDecimal valor, Metodo metodo, String descricao, Categoria categoria, Subcategoria subcategoria, Status status, String interessado)
	{
		super.update(data, valor, metodo, descricao, categoria, subcategoria, status, null);
		this.pagador = interessado;
	}


	@Override
	public void show()
	{
		super.show();
		System.out.println("Pagador: " + this.pagador);
	}

}
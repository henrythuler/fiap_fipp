package models.entities;

import models.enums.*;
import java.math.BigDecimal;
import java.sql.Date;

public class Receita extends Transacao
{
	protected String pagador;

	public Receita(int id, int idUsuario, Date data, BigDecimal valor, Metodo metodo, String descricao, Categoria categoria, Subcategoria subcategoria, Status status, String pagador)
	{
		super(id, idUsuario, data, valor, metodo, descricao, categoria, subcategoria, status);
		this.pagador = pagador;
	}


	public Receita(){}


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


	public String getPagador() { return pagador; }


	public void setPagador(String pagador) { this.pagador = pagador; }
}
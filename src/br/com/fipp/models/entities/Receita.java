package br.com.fipp.models.entities;

import br.com.fipp.models.enums.*;
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


	@Override
	public void update(Date data, BigDecimal valor, Metodo metodo, String descricao, Categoria categoria, Subcategoria subcategoria, Status status, String interessado)
	{
		super.update(data, valor, metodo, descricao, categoria, subcategoria, status, null);
		this.pagador = interessado;
	}


	public String getPagador() { return this.pagador; }
	public void setPagador(String pagador) { this.pagador = pagador; }


	@Override
	public String toString()
	{
		return String.format("ID: %s | DATA: %s | VALOR: %s | MÉTODO: %s | DESCRIÇÃO: %s | CATEGORIA: %s | SUBCATEGORIA: %s | STATUS: %s | PAGADOR: %s%n", getId(), getData(), getValor(), getMetodo(), getDescricao(), getCategoria().getDescricao(), getSubcategoria().getDescricao(), getStatus().getDescricao(), getPagador());
	}

}
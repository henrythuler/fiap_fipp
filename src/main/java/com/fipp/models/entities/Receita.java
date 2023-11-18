package com.fipp.models.entities;

import com.fipp.models.enums.*;
import java.math.BigDecimal;
import java.sql.Date;

public class Receita extends Transacao
{
	protected String pagador;

	public Receita(int id, int idUsuario, Date data, BigDecimal valor, Metodo metodo, String descricao, Categoria categoria, Subcategoria subcategoria, Status status, String pagador) {
		super(id, idUsuario, data, valor, metodo, descricao, categoria, subcategoria, status);
		this.pagador = pagador;
	}


	public String getPagador() { return this.pagador; }
	//public void setPagador(String pagador) { this.pagador = pagador; }
}
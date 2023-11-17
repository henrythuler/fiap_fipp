package com.fipp.models.entities;

import com.fipp.models.enums.*;
import java.math.BigDecimal;
import java.sql.Date;

public class Despesa extends Transacao
{
	protected String beneficiario;

	public Despesa(int id, int idUsuario, Date data, BigDecimal valor, Metodo metodo, String descricao, Categoria categoria, Subcategoria subcategoria, Status status, String beneficiario)
	{
		super(id, idUsuario, data, valor, metodo, descricao, categoria, subcategoria, status);
		this.beneficiario = beneficiario;
	}


	@Override
 	public void update(Date data, BigDecimal valor, Metodo metodo, String descricao, Categoria categoria, Subcategoria subcategoria, Status status, String interessado)
	{
		super.update(data, valor, metodo, descricao, categoria, subcategoria, status, null);
		this.beneficiario = interessado;
	}


	public String getBeneficiario() { return this.beneficiario;}
	public void setBeneficiario(String beneficiario) { this.beneficiario = beneficiario; }


	@Override
	public String toString(){
		return String.format("ID: %s | DATA: %s | VALOR: %s | MÉTODO: %s | DESCRIÇÃO: %s | CATEGORIA: %s | SUBCATEGORIA: %s | STATUS: %s | BENEFICIÁRIO: %s%n", getId(), getData(), getValor(), getMetodo(), getDescricao(), getCategoria().getDescricao(), getSubcategoria().getDescricao(), getStatus().getDescricao(), getBeneficiario());
	}

}
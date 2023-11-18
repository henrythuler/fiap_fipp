package com.fipp.models.entities;

import com.fipp.models.enums.*;
import java.math.BigDecimal;
import java.sql.Date;

public class Despesa extends Transacao
{
	protected String beneficiario;

	public Despesa(int id, int idUsuario, Date data, BigDecimal valor, Metodo metodo, String descricao, Categoria categoria, Subcategoria subcategoria, Status status, String beneficiario) {
		super(id, idUsuario, data, valor, metodo, descricao, categoria, subcategoria, status);
		this.beneficiario = beneficiario;
	}


	public String getBeneficiario() { return this.beneficiario;}
	//public void setBeneficiario(String beneficiario) { this.beneficiario = beneficiario; }

}
package br.com.fipp.models.entities;

import br.com.fipp.models.enums.Status;
import br.com.fipp.models.enums.Metodo;

import java.math.BigDecimal;
import java.sql.Date;

public abstract class Transacao
{
	protected int id;
	protected Date data;
	protected BigDecimal valor;
	protected Metodo metodo;
	protected String descricao;
	protected Categoria categoria;
	protected Subcategoria subcategoria;
	protected Status status;
	protected int idUsuario;

	
	public Transacao(int id, int idUsuario, Date data, BigDecimal valor, Metodo metodo, String descricao, Categoria categoria, Subcategoria subcategoria, Status status)
	{
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.metodo = metodo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.status = status;
		this.idUsuario = idUsuario;
	}


	public void update(Date data, BigDecimal valor, Metodo metodo, String descricao, Categoria categoria, Subcategoria subcategoria, Status status, String interessado)
	{
		this.data = data;
		this.valor = valor;
		this.metodo = metodo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.status = status;
	}



	public int getId() {
		return id;
	}
	public Date getData() {
		return data;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public Metodo getMetodo() {
		return metodo;
	}
	public String getDescricao() {
		return descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}
	public Status getStatus() {
		return status;
	}
	public int getIdUsuario() {
		return idUsuario;
	}


	public void setId(int id) { this.id = id; }
	public void setData(Date data) { this.data = data; }
	public void setValor(BigDecimal valor) { this.valor = valor; }
	public void setMetodo(Metodo metodo) { this.metodo = metodo; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	public void setCategoria(Categoria categoria) { this.categoria = categoria; }
	public void setSubcategoria(Subcategoria subcategoria) { this.subcategoria = subcategoria; }
	public void setStatus(Status status) { this.status = status; }
	public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

}

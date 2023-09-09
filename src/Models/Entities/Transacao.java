package Models.Entities;

import Models.Enums.Metodo;
import Models.Enums.Status;
import java.util.Date;

public abstract class Transacao
{
	protected int id;
	protected Date data;
	protected double valor;
	protected Metodo metodo;
	protected String descricao;
	protected int categoria;
	protected int subcategoria;
	protected Status status;

	
	public Transacao(int id, Date data, double valor, Metodo metodo, String descricao, int categoria, int subcategoria, Status status)
	{
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.metodo = metodo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.status = status;
	}


	public void update(Date data, double valor, Metodo metodo, String descricao, int categoria, int subcategoria, Status status, String interessado)
	{
		this.data = data;
		this.valor = valor;
		this.metodo = metodo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.status = status;
	}

	public void show()
	{
		System.out.println("Id: " + this.id);
		System.out.println("Data: " + this.data);
		System.out.println("Valor: " + this.valor);
		System.out.println("Método: " + this.metodo.getDescricao());
		System.out.println("Descrição: " + this.descricao);
		System.out.println("Categoria: " + this.categoria);
		System.out.println("Subcategoria: " + this.subcategoria);
		System.out.println("Status: " + this.status.getDescricao());
	}

}

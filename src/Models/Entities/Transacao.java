package Models.Entities;

import Models.Enums.Metodo;
import Models.Enums.Status;
import java.math.BigDecimal;
import java.util.Date;

public abstract class Transacao
{
	protected int id;
	protected Date data;
	protected BigDecimal valor;
	protected Metodo metodo;
	protected String descricao;
	protected int categoriaId;
	protected int subcategoriaId;
	protected Status status;

	
	public Transacao(int id, Date data, BigDecimal valor, Metodo metodo, String descricao, int categoriaId, int subcategoriaId, Status status)
	{
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.metodo = metodo;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.subcategoriaId = subcategoriaId;
		this.status = status;
	}


	public void update(Date data, BigDecimal valor, Metodo metodo, String descricao, int categoriaId, int subcategoriaId, Status status, String interessado)
	{
		this.data = data;
		this.valor = valor;
		this.metodo = metodo;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.subcategoriaId = subcategoriaId;
		this.status = status;
	}

	public void show()
	{
		System.out.println("Id: " + this.id);
		System.out.println("Data: " + this.data);
		System.out.println("Valor: " + this.valor);
		System.out.println("Método: " + this.metodo.getDescricao());
		System.out.println("Descrição: " + this.descricao);
		System.out.println("Categoria: " + this.categoriaId);
		System.out.println("Subcategoria: " + this.subcategoriaId);
		System.out.println("Status: " + this.status.getDescricao());
	}

}

package Models.Entities;

import Models.Enums.Metodo;
import Models.Enums.Status;
import java.util.Date;

public class Receita extends Transacao
{
	protected String pagador;
	
	public Receita(int id, Date data, double valor, Metodo metodo, String descricao, int categoria, int subcategoria, Status status, String pagador)
	{
		super(id, data, valor, metodo, descricao, categoria, subcategoria, status);
		this.pagador = pagador;
	}


	public void showReceita(){
		System.out.println("---------------------------------------------");
		System.out.println("Id: " + this.id);
		System.out.println("Data: " + this.data);
		System.out.println("Valor: " + this.valor);
		System.out.println("Método: " + this.metodo.getDescricao());
		System.out.println("Descrição: " + this.descricao);
		System.out.println("Categoria: " + this.categoria);
		System.out.println("Subcategoria: " + this.subcategoria);
		System.out.println("Status: " + this.status.getDescricao());
		System.out.println("Pagador: " + this.pagador);
		System.out.println("---------------------------------------------");
	}



    /* TO DO:
	public void create(Receita receita) {
		TransacaoRepository.create(receita);
	}
	
	public Receita read(int id) {
		return (Receita) TransacaoRepository.read(id);
	}
	
	public void update (Receita receita) {
		TransacaoRepository.update(receita);
	}
    */
}

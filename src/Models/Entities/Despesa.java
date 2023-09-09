package Models.Entities;

import Models.Enums.Status;
import Models.Enums.Metodo;
import java.util.Date;

public class Despesa extends Transacao
{
	protected String beneficiario;

	public Despesa(int id, Date data, double valor, Metodo metodo, String descricao, int categoria, int subcategoria, Status status, String beneficiario)
	{
		super(id, data, valor, metodo, descricao, categoria, subcategoria, status);
		this.beneficiario = beneficiario;
	}

	public void showDespesa(){
		System.out.println("---------------------------------------------");
		System.out.println("Id: " + this.id);
		System.out.println("Data: " + this.data);
		System.out.println("Valor: " + this.valor);
		System.out.println("Método: " + this.metodo.getDescricao());
		System.out.println("Descrição: " + this.descricao);
		System.out.println("Categoria: " + this.categoria);
		System.out.println("Subcategoria: " + this.subcategoria);
		System.out.println("Status: " + this.status.getDescricao());
		System.out.println("Beneficiário: " + this.beneficiario);
		System.out.println("---------------------------------------------");
	}




    /* TO DO:
	public void create(Despesa despesa) {
		TransacaoRepository.create(despesa);
	}
	
	public Despesa read(int id) {
		return (Despesa) TransacaoRepository.read(id);
	}
	
	public void update (Despesa despesa) {
		TransacaoRepository.update(despesa);
	}
	*/
}
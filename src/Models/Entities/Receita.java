package Models.Entities;


import Models.Enums.Metodo;
import Models.Enums.Status;
import java.math.BigDecimal;
import java.util.Date;
//import Repositories.TransacaoRepository;


public class Receita extends Transacao
{
	protected String pagador;
	
	public Receita(int id, Date data, BigDecimal valor, Metodo metodo, String descricao, int categoria, int subcategoria, Status status, String pagador)
	{
		super(id, data, valor, metodo, descricao, categoria, subcategoria, status);
		this.pagador = pagador;
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

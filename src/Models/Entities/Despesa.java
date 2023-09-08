package Models.Entities;


import Models.Enums.Metodo;
import Models.Enums.Status;
import java.math.BigDecimal;
import java.util.Date;
//import Repositories.TransacaoRepository;


public class Despesa extends Transacao
{
	protected String beneficiario;

	public Despesa(int id, Date data, BigDecimal valor, Metodo metodo, String descricao, int categoria, int subcategoria, Status status, String beneficiario)
	{
		super(id, data, valor, metodo, descricao, categoria, subcategoria, status);
		this.beneficiario = beneficiario;
	}





    /*
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
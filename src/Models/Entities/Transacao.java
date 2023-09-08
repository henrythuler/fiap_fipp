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
	protected int categoria;
	protected int subcategoria;
	protected Status status;

	
	public Transacao(int id, Date data, BigDecimal valor, Metodo metodo, String descricao, int categoria,
			int subcategoria, Status status) {
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.metodo = metodo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.status = status;
	}

    /*
	public void create(Transacao transacao) {
		TransacaoRepository.create(transacao);
	}
	
	public Transacao read(int id) {
		return TransacaoRepository.read(id);
	}
	
	public void update (Transacao transacao) {
		TransacaoRepository.update(transacao);
	}
	
	public void delete (int id) {
		TransacaoRepository.delete(id);
	}
     */
}

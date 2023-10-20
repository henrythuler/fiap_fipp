import dao.CategoriaDAO;
import dao.ReceitaDAO;
import models.entities.*;
import models.enums.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;


public class Main
{
    public static void main(String[] args)
    {

        List<Receita> result = ReceitaDAO.getAll();
        System.out.println(result.size());

        for(Receita r : result){
            System.out.println(r.getDescricao());
        }

        //Pegando a data atual
//        long miliseconds = System.currentTimeMillis();
//        Date dataAtual = new Date(miliseconds);
//
//        Categoria alimentacao = new Categoria(1, 1, Tipo.DESPESA, "Alimentação");
//        Subcategoria supermercado = new Subcategoria(1, 1, "Supermercado");
//        Subcategoria lanchonete = new Subcategoria(2, 1, "Lanchonete");
//
//
//        Despesa despesa1 = new Despesa(1, 1, dataAtual, new BigDecimal("205.50"), Metodo.PIX,"descrição da despesa", alimentacao, supermercado, Status.AGENDADO,"Felipe Ferreira Gandra");
//        System.out.println("--------------DESPESA--------------");
//        despesa1.show();
//
//        despesa1.update(dataAtual, new BigDecimal("350.5"), Metodo.CREDITO,"descrição da despesa atualizada", alimentacao, lanchonete, Status.PAGO, "Felipe Ferreira Gandra");
//        System.out.println("---------DESPESA ATUALIZADA---------");
//        despesa1.show();
//
//
//        Categoria aluguel = new Categoria(3, 1, Tipo.RECEITA, "Aluguel");
//
//        //Inserindo aluguel no Banco de Dados
//        //CategoriaDAO.inserir(aluguel);
//
//        Receita receita1 = new Receita(2, 1, dataAtual, new BigDecimal("504.25"), Metodo.DINHEIRO,"Aluguel do apto da Av. Paulista", aluguel, null, Status.PAGO,"João da Silva");
//        System.out.println("--------------RECEITA--------------");
//        receita1.show();
//
//        //Inserindo receita1 no Banco de Dados
//        ReceitaDAO.inserir(receita1);
//
//        receita1.update(dataAtual, new BigDecimal("580.25"), Metodo.DINHEIRO,"Aluguel do apto da Av. Paulista", aluguel, null, Status.PAGO,"João da Silva");
//        System.out.println("---------RECEITA ATUALIZADA---------");
//        receita1.show();

    }
}
import models.entities.*;
import models.enums.*;
import java.math.BigDecimal;
import java.util.Date;


public class Main
{
    public static void main(String[] args)
    {
        Categoria alimentacao = new Categoria(1, Tipo.DESPESA, "Alimentação");
        Subcategoria supermercado = new Subcategoria(1, 1, "Supermercado");
        Subcategoria lanchonete = new Subcategoria(2, 1, "Lanchonete");


        Despesa despesa1 = new Despesa(1, new Date(), new BigDecimal("205.50"), Metodo.PIX,"descrição da despesa", alimentacao, supermercado, Status.AGENDADO,"Felipe Ferreira Gandra");
        System.out.println("--------------DESPESA--------------");
        despesa1.show();

        despesa1.update(new Date(), new BigDecimal("350.5"), Metodo.CREDITO,"descrição da despesa atualizada", alimentacao, lanchonete, Status.PAGO, "Felipe Ferreira Gandra");
        System.out.println("---------DESPESA ATUALIZADA---------");
        despesa1.show();


        Categoria aluguel = new Categoria(3, Tipo.RECEITA, "Aluguel");

        Receita receita1 = new Receita(1, new Date(), new BigDecimal("504.25"), Metodo.DINHEIRO,"Aluguel do apto da Av. Paulista", aluguel, null, Status.PAGO,"João da Silva");
        System.out.println("--------------RECEITA--------------");
        receita1.show();

        receita1.update(new Date(), new BigDecimal("580.25"), Metodo.DINHEIRO,"Aluguel do apto da Av. Paulista", aluguel, null, Status.PAGO,"João da Silva");
        System.out.println("---------RECEITA ATUALIZADA---------");
        receita1.show();

    }
}
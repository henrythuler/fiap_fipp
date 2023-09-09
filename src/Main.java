import Models.Entities.Despesa;
import Models.Entities.Receita;
import Models.Enums.Metodo;
import Models.Enums.Status;
import java.math.BigDecimal;
import java.util.Date;


public class Main
{
    public static void main(String[] args)
    {
        Despesa despesa1 = new Despesa(1, new Date(), new BigDecimal("205.50"), Metodo.PIX,"descrição da despesa", 2, 1, Status.AGENDADO,"Felipe Ferreira Gandra");
        System.out.println("--------------DESPESA--------------");
        despesa1.show();

        despesa1.update(new Date(), new BigDecimal("350.5"), Metodo.CREDITO,"descrição da despesa atualizada", 2, 1, Status.PAGO, "Felipe Ferreira Gandra");
        System.out.println("---------DESPESA ATUALIZADA---------");
        despesa1.show();



        Receita receita1 = new Receita(1, new Date(), new BigDecimal("504.25"), Metodo.DEBITO,"descrição da receita", 1, 2, Status.AGENDADO,"João da Silva");
        System.out.println("--------------RECEITA--------------");
        receita1.show();

        receita1.update(new Date(), new BigDecimal("520.25"), Metodo.DEBITO,"descrição da receita atualizada", 1, 2, Status.PAGO,"João da Silva");
        System.out.println("---------RECEITA ATUALIZADA---------");
        receita1.show();

    }
}
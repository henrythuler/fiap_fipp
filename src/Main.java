import Models.Entities.Despesa;
import Models.Entities.Receita;
import Models.Enums.Metodo;
import Models.Enums.Status;

import java.util.Date;


public class Main
{
    public static void main(String[] args)
    {
        Despesa despesa = new Despesa(1, new Date(), 205.5, Metodo.PIX,"descrição da despesa", 2, 1, Status.PAGO,"Felipe Ferreira Gandra");
        despesa.showDespesa();


        Receita receita = new Receita(1, new Date(), 504.25, Metodo.DEBITO,"descrição da receita", 1, 2, Status.AGENDADO,"João da Silva");
        receita.showReceita();

    }
}
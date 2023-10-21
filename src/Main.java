import dao.*;
import models.entities.*;
import models.enums.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main
{
    public static void main(String[] args)
    {


        System.out.println("\n################ CATEGORIA ################\n");

            //TO DO: Implementar
            System.out.println("------------ getAll ------------\n");

            System.out.println("------------ getById ------------\n");
            var categoriaId = 2;
            var resultCategoria1 = CategoriaDAO.getById(categoriaId);
            System.out.println("Categoria " + categoriaId + ": " + resultCategoria1.getDescricao());
            System.out.println("\n");

            System.out.println("------------ inserir ------------\n");
            var categoria1 = new Categoria(6, 1, Tipo.RECEITA, "Salário"); //mudar o id antes de executar pq não está usando auto-increment
            var resultCategoria2 = CategoriaDAO.inserir(categoria1);
            System.out.println(resultCategoria2);

            //TO DO: Implementar
            System.out.println("------------ excluir ------------\n");

        System.out.println("\n###########################################\n");





        System.out.println("\n################ SUBCATEGORIA ################\n");

            System.out.println("------------ getById ------------\n");
            var subcatId1 = 1;
            var resultSubCat1 = SubcategoriaDAO.getById(subcatId1);
            System.out.println("Subcategoria " + subcatId1 + ": " + resultSubCat1.getDescricao());
            System.out.println("\n");

            System.out.println("------getByCategoriaId------\n");
            var subcatId2 = 2;
            var resultSubCat2 = SubcategoriaDAO.getByCategoriaId(subcatId2);
            var categoria = CategoriaDAO.getById(subcatId2);
            System.out.println("Da categoria '" + categoria.getDescricao() + "'(" + subcatId2 + "):\n");
            for(var item : resultSubCat2)
                System.out.println(item.getDescricao());

            //TO DO: Implementar
            System.out.println("------------ inserir ------------\n");

            //TO DO: Implementar
            System.out.println("------------ excluir ------------\n");

        System.out.println("\n###########################################\n");





        System.out.println("\n################ RECEITA ################\n");

            System.out.println("------------ getAll ------------\n");
            var resultReceita1 = ReceitaDAO.getAll();
            for(var item : resultReceita1)
                System.out.println(item.getDescricao());

            //TO DO: Implementar
            System.out.println("------------ getById ------------\n");

            System.out.println("------------ inserir ------------\n");
            var categoriaX = new Categoria(6, 1, Tipo.RECEITA, "Salário");
            var subcategoriaX = new Subcategoria(4,6,Tipo.RECEITA,"Salário do mês");
            var data = Date.valueOf(LocalDate.parse("21/10/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            var receita1 = new Receita(2,1, data, BigDecimal.valueOf(1500.00),Metodo.PIX,"Salário do mês",categoriaX,subcategoriaX,Status.PAGO,"Meu outro patrão");
            var resultReceita2 = ReceitaDAO.inserir(receita1);
            System.out.println(resultReceita2);

            //TO DO: Implementar
            System.out.println("------------ excluir ------------\n");

        System.out.println("\n#########################################\n");


    }
}
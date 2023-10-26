import br.com.fipp.dao.*;
import br.com.fipp.models.entities.*;
import br.com.fipp.models.enums.*;
import java.math.*;
import java.time.*;
import java.time.format.*;
import java.sql.Date;

public class Main
{
    public static void main(String[] args)
    {

        //################## CATEGORIA ##################
            //inserirCincoCategoriasMock();
            //retornarCategoriaPorId(1);
            //retornarCategorias();



        //################ SUBCATEGORIA ################
            //inserirCincoSubCategoriasMock();
            //retornarSubCategoriaPorCategoriaId(2);
            //retornarSubCategorias();



        //################### RECEITA ###################
            //inserirCincoReceitasMock();
            //retornarReceitas();
            //retornarReceitaPorId(2);



        //################### DESPESA ###################
            //inserirCincoDespesasMock();
            //retornarDespesas();
            //retornarDespesaPorId(4);
    }



    private static void inserirCincoCategoriasMock () {
        if (new CategoriaDAOImpl().getById(1) == null)
        {
            var categoria1 = new Categoria(1, 1, Tipo.DESPESA, "Moradia");
            var categoria2 = new Categoria(2, 1, Tipo.DESPESA, "Alimentação");
            var categoria3 = new Categoria(3, 1, Tipo.DESPESA, "Educação");
            var categoria4 = new Categoria(4, 1, Tipo.RECEITA, "Imóvel");
            var categoria5 = new Categoria(5, 1, Tipo.RECEITA, "Freelance");

            var categorias = new Categoria[]{categoria1, categoria2, categoria3, categoria4, categoria5};

            for (var item : categorias) {
                var result = new CategoriaDAOImpl().inserir(item);

                if (result == 1)
                    System.out.println("Categoria " + item.getDescricao() + " cadastrada com sucesso!");
                else
                    System.out.println("Erro ao inserir a categoria " + item.getDescricao() + " !");
            }
        }
        else
            System.out.println("Mock de categorias já aplicado na base de dados");
    }

    private static void retornarCategoriaPorId (int id){
        var categoria = new CategoriaDAOImpl().getById(id);
        if (categoria == null) {
            System.out.printf("Categoria %s não econtrada!%n",id);
        }
        else{
            System.out.printf(categoria.toString());
        }
    }

    private static void retornarCategorias () {
        var categorias = new CategoriaDAOImpl().getAll();
        if (categorias.isEmpty())
            System.out.println("Nenhuma categoria encontrada!");
        else {
            for (var item : categorias)
                System.out.printf(item.toString());
        }
    }



    private static void inserirCincoSubCategoriasMock() {
        if (new SubcategoriaDAOImpl().getById(1) == null) {
            var subcategoria1 = new Subcategoria(1, 1, 1, Tipo.DESPESA, "Aluguel");
            var subcategoria2 = new Subcategoria(2, 2, 1, Tipo.DESPESA, "Restaurante");
            var subcategoria3 = new Subcategoria(3, 2, 1, Tipo.DESPESA, "Lanchonete");
            var subcategoria4 = new Subcategoria(4, 4, 1, Tipo.RECEITA, "Aluguel");
            var subcategoria5 = new Subcategoria(5, 5, 1, Tipo.RECEITA, "99Freelas");

            var subcategorias = new Subcategoria[]{subcategoria1, subcategoria2, subcategoria3, subcategoria4, subcategoria5};

            for (var item : subcategorias) {
                var success = new SubcategoriaDAOImpl().inserir(item);
                if (success == 1)
                    System.out.println("Subcategoria " + item.getDescricao() + " cadastrada com sucesso!");
                else
                    System.out.println("Erro ao inserir a subcategoria " + item.getDescricao() + " !");
            }
        }
        else
            System.out.println("Mock de subcategorias já aplicado na base de dados");
    }

    private static void retornarSubCategoriaPorCategoriaId (int id){
        var subcategorias = new SubcategoriaDAOImpl().getByCategoriaId(id);
        if (subcategorias.isEmpty())
            System.out.printf("Nenhuma subcategoria da categoria %s econtrada!%n",id);
        else{
            for (var item : subcategorias)
                System.out.printf(item.toString());
        }
    }

    private static void retornarSubCategorias(){
        var subcategorias = new SubcategoriaDAOImpl().getAll();
        if (subcategorias.isEmpty())
            System.out.println("Nenhuma subcategoria encontrada!");
        else {
            for (var item : subcategorias)
                System.out.printf(item.toString());
        }
    }



    private static void inserirCincoReceitasMock(){
        if (new ReceitaDAOImpl().getById(1) == null) {
            var data = Date.valueOf(LocalDate.parse("22/10/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            var catImovel = new CategoriaDAOImpl().getById(4); //Imóvel
            var catFreelance = new CategoriaDAOImpl().getById(5); //Freelance

            var subAluguel = new SubcategoriaDAOImpl().getById(4); // [Imóvel] -> Aluguel
            var sub99Freelas = new SubcategoriaDAOImpl().getById(5); // [Freelance] -> 99Freelas

            var receita1 = new Receita(1, 1, data, BigDecimal.valueOf(1800.00), Metodo.PIX,"Apartamento Higienópolis/SP", catImovel, subAluguel, Status.PAGO, "João dos Santos");
            var receita2 = new Receita(2, 1, data, BigDecimal.valueOf(2200.00),Metodo.PIX,"Casa do Morumbi/SP", catImovel, subAluguel, Status.PAGO, "Antônio da Silva");
            var receita3 = new Receita(3, 1, data, BigDecimal.valueOf(650.00),Metodo.PIX,"Feature site FIAP", catFreelance, sub99Freelas, Status.PAGO, "FIAP");
            var receita4 = new Receita(4, 1, data, BigDecimal.valueOf(900.00),Metodo.PIX,"Padaria Pão e Cia", catFreelance, sub99Freelas, Status.PAGO, "Manoel Pereira");
            var receita5 = new Receita(5, 1, data, BigDecimal.valueOf(2400.00),Metodo.PIX,"Site da loja TechTudo", catFreelance, sub99Freelas, Status.PAGO, "Adriana Fernandes");

            var receitas = new Receita[]{receita1, receita2, receita3, receita4, receita5};

            for (var item : receitas) {
                var result = new ReceitaDAOImpl().inserir(item);
                if (result == 1)
                    System.out.println("Receita '" + item.getDescricao() + "' cadastrada com sucesso!");
                else
                    System.out.println("Erro ao inserir a receita '" + item.getDescricao() + "' !");
            }
        }
        else
            System.out.println("Mock de receitas já aplicado na base de dados");

    }

    private static void retornarReceitas(){
        var receitas = new ReceitaDAOImpl().getAll();
        if (receitas.isEmpty())
            System.out.println("Nenhuma receita encontrada!");
        else {
            for (var item : receitas)
                System.out.printf(item.toString());
        }
    }

    private static void retornarReceitaPorId (int id){
        var receita = new ReceitaDAOImpl().getById(id);
        if (receita == null)
            System.out.printf("Receita %s não econtrada!%n",id);
        else
            System.out.println(receita.toString());
    }



    private static void inserirCincoDespesasMock(){
        if (new DespesaDAOImpl().getById(1) == null) {
            var data = Date.valueOf(LocalDate.parse("22/10/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            var catMoradia = new CategoriaDAOImpl().getById(1); //Moradia
            var catAlimentacao = new CategoriaDAOImpl().getById(2); //Alimentação

            var subAluguel = new SubcategoriaDAOImpl().getById(1); // [Moradia] -> Aluguel
            var subRestaurante = new SubcategoriaDAOImpl().getById(2); // [Alimentação] -> Restaurante

            var despesa1 = new Despesa(1, 1, data, BigDecimal.valueOf(1300.00), Metodo.PIX, "Apartamento Osasco/SP", catMoradia, subAluguel, Status.PAGO, "José Henrique");
            var despesa2 = new Despesa(2, 1, data, BigDecimal.valueOf(33.20), Metodo.PIX, "Almoço", catAlimentacao, subRestaurante, Status.PAGO, "Restaurante da Facul");
            var despesa3 = new Despesa(3, 1, data, BigDecimal.valueOf(24.50), Metodo.DEBITO, "Almoço", catAlimentacao, subRestaurante, Status.PAGO, "Restaurante da Facul");
            var despesa4 = new Despesa(4, 1, data, BigDecimal.valueOf(19.80), Metodo.DINHEIRO, "Jantar", catAlimentacao, subRestaurante, Status.PAGO, "Restaurante BomDemais");
            var despesa5 = new Despesa(5, 1, data, BigDecimal.valueOf(26.00), Metodo.PIX, "Almoço", catAlimentacao, subRestaurante, Status.PAGO, "Restaurante da Facul");

            var despesas = new Despesa[]{despesa1, despesa2, despesa3, despesa4, despesa5};

            for (var item : despesas) {
                var result = new DespesaDAOImpl().inserir(item);
                if (result == 1)
                    System.out.println("Despesa '" + item.getDescricao() + "' cadastrada com sucesso!");
                else
                    System.out.println("Erro ao inserir a despesa '" + item.getDescricao() + "' !");
            }
        }
        else
            System.out.println("Mock de despesas já aplicado na base de dados");

    }

    private static void retornarDespesas(){
        var despesas = new DespesaDAOImpl().getAll();
        if (despesas.isEmpty())
            System.out.println("Nenhuma receita encontrada!");
        else {
            for (var item : despesas)
                System.out.printf(item.toString());
        }
    }

    private static void retornarDespesaPorId (int id){
        var despesa = new DespesaDAOImpl().getById(id);
        if (despesa == null)
            System.out.printf("Despesa %s não econtrada!%n",id);
        else
            System.out.println(despesa.toString());
    }

}
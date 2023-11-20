<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fipp</title>

    <%@ include file="./snippets/head.html"%>

</head>


<body>

<%

    if(session.getAttribute("categories") == null){
        request.getRequestDispatcher("/category?load=1").forward(request, response);
    }

%>

<!-- Mock iPhone -->
<section class="__mockup__iphone__navbar">

    <!-- Screen -->
    <section class="__screen">

        <header class="my-4 flex flex-col">
            <h2 class="font-bold text-black  flex justify-center items-center border-b-2 h-12">Despesa</h2>
        </header>

        <main class="flex flex-col h-[580px] px-2 overflow-y-scroll __hide__scroll__bar">

            <form name="income-form" id="income-form" method="post" action="expense" class="flex flex-col box-border gap-4 mt-4" >
                <div class="pr-8 flex gap-4 items-center self-center mb-8">
                    <label for="status" class="m-2 text-gray-700">Status</label>
                    <input list="status" name="status" class="placeholder-gray-600 rounded-md px-2 py-3 w-full bg-gray-300" placeholder="Ex: Agendado">
                    <datalist id="status">
                        <option value="Pago">
                        <option value="Parcelado">
                        <option value="Atrasado">
                        <option value="Cancelado">
                        <option value="Agendado">
                    </datalist>
                </div>


                <div class="flex flex-col justify-center">
                    <label for="date" class="text-gray-300">Data</label>
                    <input type="date" id="date" name="date" class="rounded-md px-2 py-3 w-full  bg-gray-300">
                </div>
                <div class="flex flex-col justify-center">
                    <label for="value" class="text-gray-300">Valor</label>
                    <input type="number" id="value" name="value" class="placeholder-gray-600 rounded-md px-2 py-3 w-full  bg-gray-300" required placeholder="Ex: R$ 152,30">
                </div>


                <div class="flex flex-col justify-center">
                    <label for="beneficiary" class="text-gray-300">Beneficiário</label>
                    <input type="text" id="beneficiary" name="beneficiary" class="placeholder-gray-600 px-2 rounded-md py-3 w-full  bg-gray-300" required minlength="5" maxlength="15" placeholder="Ex: Restaurante do bairro">
                </div>




                <div class="flex flex-col justify-center">
                    <label for="category-expense" class="text-gray-300">Categoria</label>
                    <input list="category-expense" name="category" class="placeholder-gray-600 rounded-md px-2 py-3 w-full  bg-gray-300" placeholder="Ex: Alimentação">
                    <datalist id="category-expense">
                        <c:forEach var="category" items="${categories}">

                            <c:if test="${category.getType().getId() == 0}">
                                <option value="${category.getDescription()}" category-id="${category.getId()}">
                                    <input type="hidden" name="category-id" value="${category.getId()}"/>
                                </option>
                            </c:if>

                        </c:forEach>
                    </datalist>
                </div>
                <div class="flex flex-col justify-center">
                    <label for="subcategory-expense" class="text-gray-300">Subcategoria</label>
                    <input list="subcategory-expense" name="subcategory" class="placeholder-gray-600 rounded-md px-2 py-3 w-full bg-gray-300" placeholder="Ex: Restaurante">
                    <datalist id="subcategory-expense">
                        <c:forEach var="subcategory" items="${subcategories}">

                            <c:if test="${subcategory.getType().getId() == 0}">

                                <option value="${subcategory.getDescription()}">
                                    <input type="hidden" name="subcategory-id" value="${subcategory.getId()}"/>
                                </option>

                            </c:if>

                        </c:forEach>
                    </datalist>
                </div>


                <div class="flex flex-col justify-center mt-2">
                    <label for="method" class="text-gray-300">Método</label>
                    <input list="method" name="method" class="placeholder-gray-600 flex rounded-md px-2 py-3 w-full bg-gray-300" placeholder="Ex: Débito">
                    <datalist id="method">
                        <option value="Débito">
                        <option value="Crédito">
                        <option value="Boleto">
                        <option value="Pix">
                        <option value="Dinheiro">
                    </datalist>
                </div>



                <div class="flex flex-col justify-center mt-2">
                    <label for="description" class="text-gray-300">Descrição</label>
                    <input type="text" id="description" name="description" class="placeholder-gray-600 rounded-md px-2 py-3 w-full bg-gray-300" required placeholder="Ex: Website">
                </div>

                <input type="submit" class="material-symbols-outlined self-end bg-green-600 p-4 w-46 h-46 mr-2 mt-12 mb-8 rounded-full text-white text-center flex items-center justify-center" value="check">

            </form>

        </main>

        <%@ include file="./snippets/footer.html"%>

    </section>

</section>

</body>

</html>
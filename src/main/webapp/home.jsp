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

<!-- Mock iPhone -->
<section class="__mockup__iphone__navbar">

    <!-- Screen -->
    <section class="__screen">

        <main class="flex flex-col h-[580px] px-2 overflow-y-scroll __hide__scroll__bar">

            <h1 class="flex justify-center pt-[10%] text-5xl text-slate-700">Fipp</h1>


            <table class="w-full mt-8">
                <thead>
                <tr class="__background-tertiary-color leading-[3rem] text-left">
                    <th class="px-1">Data</th>
                    <th>Categoria</th>
                    <th>Valor</th>
                    <th></th>
                </tr>
                </thead>

                <tbody class="divide-y divide-gray-700">
                <tr class="text-green-600 leading-[3rem]">
                    <td class="px-1">05/04/2023</td>
                    <td class="px-1">Salário</td>
                    <td class="px-1">R$ 2.800,00</td>

                    <td><a href="#" class="material-symbols-outlined text-gray-400 text-sm">arrow_right_alt</a></td>
                    <!-- Call method that redirect object to income-form.html or expense-form.html -->

                </tr>

                <tr class="text-red-500 leading-[3rem]">
                    <td class="px-1">05/04/2023</td>
                    <td class="px-1">Moradia</td>
                    <td class="px-1">R$ 1.500,00</td>

                    <td><a href="#" class="material-symbols-outlined text-gray-400 text-sm">arrow_right_alt</a></td>
                    <!-- Call method that redirect object to income-form.html or expense-form.html -->

                </tr>

                <tr class="text-red-500 leading-[3rem]">
                    <td class="px-1">07/04/2023</td>
                    <td class="px-1">Compras online</td>
                    <td class="px-1">R$ 262,30</td>

                    <td><a href="#" class="material-symbols-outlined text-gray-400 text-sm">arrow_right_alt</a></td>
                    <!-- Call method that redirect object to income-form.html or expense-form.html -->

                </tr>

                <tr class="text-red-500 leading-[3rem]">
                    <td class="px-1">10/04/2023</td>
                    <td class="px-1">Cartão de Crédito</td>
                    <td class="px-1">R$ 850,00</td>

                    <td><a href="#" class="material-symbols-outlined text-gray-400 text-sm">arrow_right_alt</a></td>
                    <!-- Call method that redirect object to income-form.html or expense-form.html -->

                </tr>

                <tr class="text-red-500 leading-[3rem]">
                    <td class="px-1">12/04/2023</td>
                    <td class="px-1">Veículo</td>
                    <td class="px-1">R$ 215,00</td>

                    <td><a href="#" class="material-symbols-outlined text-gray-400 text-sm">arrow_right_alt</a></td>
                    <!-- Call method that redirect object to income-form.html or expense-form.html -->

                </tr>

                <tr class="text-green-600 leading-[3rem]">
                    <td class="px-1">15/04/2023</td>
                    <td class="px-1">Freelance</td>
                    <td class="px-1">R$ 1.000,00</td>

                    <td><a href="#" class="material-symbols-outlined text-gray-400 text-sm">arrow_right_alt</a></td>
                    <!-- Call method that redirect object to income-form.html or expense-form.html -->

                </tr>
                </tbody>
            </table>


        </main>

        <%@ include file="./snippets/footer.html"%>


    </section>

</section>

</body>

</html>
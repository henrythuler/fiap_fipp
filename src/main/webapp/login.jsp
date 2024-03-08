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

            <main class="flex flex-col items-center justify-center h-[630px] px-10 rounded-b-[25px] w-full">

                <h1 class="flex justify-center pt-[10%] text-5xl text-slate-700">Login</h1>

                <form name="login-form" id="login-form" method="post" action="login" class="flex flex-col box-border w-full gap-4 mt-4" >

                    <div class="flex flex-col justify-center">
                        <label for="user" class="text-gray-300">Usuário</label>
                        <input type="text" id="user" name="user" class="placeholder-gray-600 rounded-md px-2 py-3 w-full bg-gray-300" required placeholder="Ex: joaodasilva">
                    </div>

                    <div class="flex flex-col justify-center">
                        <label for="password" class="text-gray-300">Senha</label>
                        <input type="password" id="password" name="password" class="placeholder-gray-600 rounded-md px-2 py-3 w-full bg-gray-300" required>
                    </div>

                    <input type="submit" class="mt-2 text-white bg-gradient-to-r from-cyan-400 via-cyan-500 to-cyan-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-cyan-300 dark:focus:ring-cyan-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2" value="Entrar">

                </form>

            </main>

        </section>

    </section>

</body>

</html>

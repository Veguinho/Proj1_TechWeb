<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login V8</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>

<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form p-l-55 p-r-55 p-t-178" action="NovoUsuario">
					<span class="login100-form-title">
						Criar uma conta
					</span>
					<div class="wrap-input100 validate-input m-b-22" data-validate="Usuário inválido">
						<input class="input100" type="text" name="usuario" placeholder="Escolha seu usuário">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-22	" data-validate = "Senha inválida">
						<input class="input100" type="password" name="senha" placeholder="Escolha sua senha">
						<span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100 validate-input m-b-22	" data-validate = "Senha inválida">
						<input class="input100" type="text" name="SteamID" placeholder="Insira seu SteamID">
						<span class="focus-input100"></span>
					</div>
					
					<div>
						<p>Para achar seu id, va ate seu perfil na steam e pegue o numero da url depois de /profiles/</p>
						<a href ="https://store.steampowered.com/" target="_blank">Pegue seu id aqui</a>
					</div>

					<div class="text-right p-t-13 p-b-23">
						<span class="txt1">

						</span>


					</div>

					<div class="container-login100-form-btn p-t-30">
						<button class="login100-form-btn">
							Criar Conta
						</button>
						
						
					</div>
					<div class="flex-col-c p-t-40 p-b-40">
					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>
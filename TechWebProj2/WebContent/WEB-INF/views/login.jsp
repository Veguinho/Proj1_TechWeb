

<%@ page import="java.util.*,mvc.model.*" %>

<head>
	<title>Login V8</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">

</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form p-l-55 p-r-55 p-t-178" action='ConfereLogin'>
					<span class="login100-form-title">
						Login
					</span>

					<div class="wrap-input100 validate-input m-b-16" data-validate="Favor inserir um usu�rio">
						<input class="input100" type="text" name="usuario" placeholder="Usu�rio">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Favor inserir uma senha">
						<input class="input100" type="password" name="senha" placeholder="Senha">
						<span class="focus-input100"></span>
					</div>

					<div class="text-right p-t-13 p-b-23">
						<span class="txt1">
							Precisa modificar sua senha?
						</span>

						<a href="EditaLogin.jsp" class="txt2">
							Clique aqui
						</a>
					<br>
						<span class="txt1">
							Quer deletar seu usuario?
						</span>

						<a href="DeletarLogin.jsp" class="txt2">
							Clique aqui
						</a>
					</div>


					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Log in
						</button>
					</div>


					<div class="flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
							N�o possui uma conta?
						</span>
					<a href="Registro">Crie sua conta</a>
						
					</div>
					
				
			
				
			</div>
			
		</div>
		
	</div>
	
	
 						

</body>
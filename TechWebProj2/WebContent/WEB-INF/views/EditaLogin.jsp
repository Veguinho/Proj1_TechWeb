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
				<form class="login100-form validate-form p-l-55 p-r-55 p-t-178" action='EditarLogin'>
					<span class="login100-form-title">
						Alterar a sua senha
					</span>

					<div class="wrap-input100 validate-input m-b-16" data-validate="Favor inserir seu usuário">
						<input class="input100" type="text" name="usuario" placeholder="Favor inserir seu usuário">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Favor inserir a nova senha">
						<input class="input100" type="password" name="senha" placeholder="Favor inserir a nova senha">
						<span class="focus-input100"></span>
					</div>

					<div class="text-right p-t-13 p-b-23">
						<span class="txt1">

						</span>

					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Alterar senha
						</button>
					</div>
					<div class="flex-col-c p-t-40 p-b-40">
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
<body>

<!--  <link rel="stylesheet" href="css/Posts.css"> -->

<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

<%@ page import="java.util.*,br.insper.proj1.*" %>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form p-l-55 p-r-55 p-t-178" action='Cria'>
					<span class="login100-form-title">
						Novo post
					</span>
					
				<div class="wrap-input100 validate-input m-b-16" data-validate="Titulo">
					<input class="input100" type="text" name="titulo" placeholder="Titulo">
					<span class="focus-input100"></span>
				</div>
				
				<div class="wrap-input100 validate-input m-b-16" data-validate="Data">
					<input class="input100" type="date" name="data" placeholder="Data">
					<span class="focus-input100"></span>
				</div>
				
				<div class="wrap-input100 validate-input m-b-16" data-validate="Usuario">
					<input class="input100" type="number" name="usuario" placeholder="Usuario">
					<span class="focus-input100"></span>
				</div>
				
				<div class="wrap-input100 validate-input m-b-16" data-validate="Texto">
					<input class="input100" type="text" name="texto" placeholder="Texto">
					<span class="focus-input100"></span>
				</div>
				
				<div class="container-login100-form-btn">
					<button class="login100-form-btn">
						Enviar
					</button>
				<div class="flex-col-c p-t-40 p-b-40">
					</div>
				</div>
	 		</form> 
	 		</div>
	 	</div>
	 </div>
	<%
		Dao dao = new Dao();
			 List<Posts> posts = dao.getLista();
			 for (Posts post : posts ) {
	%>
		 
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
			<div class="login100-form validate-form p-l-55 p-r-55 p-t-178">
				<span class="login100-form-title">
					<%=post.getTitulo()%>
				</span>
    		<h1 style="font-size:14px;">Autor: <%=post.getUsuario()%></h1>
    		<h1 style="font-size:14px;">Data: <%=post.getData().getTime()%></h1>
    		<p> - - - - - - - - - - - - - - - - - - - - - - - - - <p>		
    		<p> <%=post.getTexto()%> </p>
    		
    		<div class="text-right p-t-13 p-b-23">
						<span class="txt1">

						</span>


					</div>
    		
             <form class="container-login100-form-btn" action="Remove">
             	<button class="login100-form-btn">
				 <input style="display: none" type="text" name="id" value="<%=post.getId()%>">
					Apagar Post
				</button>
			 </form>
      	
	  			  <br>  	
      	
      		<form class="container-login100-form-btn" action="Editar">	             
				<button class="login100-form-btn">
				 <input style="display: none" type="text" name="id" value="<%=post.getId()%>">
					Editar Post
				</button>
			</form>
			<div class="flex-col-c p-t-40 p-b-40">
					</div>
	      </div>
    	</div>
   	  </div>
    </div>

	<% } %>
	
	
</body>
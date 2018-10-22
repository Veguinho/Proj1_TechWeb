
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

<%@ page import="java.util.*,mvc.model.*" %>
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
		SteamWebApiDemo games = new SteamWebApiDemo();
		games.getRecentlyPlayed(dao.logado) {
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
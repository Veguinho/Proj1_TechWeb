
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

<%@ page import="java.util.*,mvc.model.*" %>
	
	<%
		Dao dao = new Dao();
		SteamWebApiDemo games = new SteamWebApiDemo();
		List<Usuarios> logged = dao.getLogged();
		
		String[] gamesList = games.getRecentlyPlayed(logged.get(0).getSteamID().toString());
		for (String i:gamesList){
			String gameName = i;
		
	%>
		 
	<div class="limiter">
		<div class="container-login50">
			<div class="wrap-login100">
			<div class="login100-form validate-form p-l-55 p-r-55 p-t-178">
				<form action="Cria" method="get">
    				<button class="login100-form-title" name="titulo" value="<%=gameName%>">
					<%=gameName%>
					
					</button>
    	
				</form>
				
			
	      </div>
    	</div>
   	  </div>
    </div>
   

	<% } %>
	
	
</body>
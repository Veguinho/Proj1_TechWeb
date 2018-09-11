<body>
<%@ page import="java.util.*,br.insper.proj1.*" %>
	<table border='1'>
	<form action='Cria'>
	 Titulo: <input type='text' name='titulo'><br>
	 Data: <input type='date' name='data'><br>
	 Usuario: <input type='number' name='usuario'> <br>
	 Texto: <input type='text' name='texto'><br>
	 <input type='submit' value='Submit'>
	 </form>
	<%
		Dao dao = new Dao();
			 List<Posts> posts = dao.getLista();
			 for (Posts post : posts ) {
	%>
		 
		 <tr>
		 <td><%=post.getTitulo()%></td>
		 <td><%=post.getData().getTime()%></td>
		 <td><%=post.getUsuario()%></td>
		 <td><%=post.getTexto()%></td>
		 <td>
		 <form action="Remove">
             <input style="display: none" type="text" name="id" value="<%=post.getId()%>">
             <input type="submit" value="Apagar Post">
      	</form>
		 </form>
		 </td>
		 </tr>


	<% } %>
	</table>
	</br>
</body>
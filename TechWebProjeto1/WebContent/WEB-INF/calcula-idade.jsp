<body>
<%@ page import="java.util.*,br.insper.proj1.*" %>
	<table border='1'>
	<% Dao dao = new Dao();
		 List<Pessoas> pessoas = dao.getLista();
		 for (Pessoas pessoa : pessoas ) { %>
		 <tr>
		 <td><%=pessoa.getNome()%></td>
		 <td><%=pessoa.getNascimento().getTime()%></td>
		 <td><%=pessoa.getAltura()%></td>
		 </tr>
	<% } %>
	</table>
A sua idade é de ${ param.idade * 365 } dias.
</br>
Você está ${ param.idade > 40 ? "velho" : "novo" }
</body>
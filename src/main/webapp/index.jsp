<%@ page import="java.time.LocalDateTime" %>
<html>
<body>
<h2>Aplicacion Web Jersey RESTful!</h2>
<p><a href="webapi/myresource">Comprobar conexion</a></p>

<%LocalDateTime fechaActual = LocalDateTime.now();%>

<h2>Son las: <%=fechaActual%></h2>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html-->


<html>
  <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>GlassFish JSP Page</title>
  </head>
  <body>
  	
    <a href="<%= request.getContextPath() %>/home/portada.do" >Inscripci�n</a>
    <br>
    <br>
    <br>
    <a href="<%= request.getContextPath() %>/home/inscripcionGarantia.do" >Anotaci�n</a>
    <br>
    <br>
    <br>
    <a href="<%= request.getContextPath() %>/home/inscripcionBuscador.do" >Consulta</a>
    <br>
    <br>
    <br>
    <a href="<%= request.getContextPath() %>/">index</a>
    <br>
    
  </body>
</html> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Bienvenido a UpoCultura</title>
        <link rel="stylesheet" href="estilos/estilos.css">
    </head>
    <body>
        <h1>Bienvenido a UpoCultura</h1>
        <s:form action="registroForm" method="POST">
            <s:submit cssClass="btn" value="Registrar Usuario"/>
        </s:form>
        
        <s:form action="loginForm" method="POST">
            <s:submit cssClass="btn" value="Iniciar Sesión"/>
        </s:form>
    </body>
</html>

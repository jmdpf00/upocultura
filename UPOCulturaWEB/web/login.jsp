<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Iniciar Sesión</title>
        <link rel="stylesheet" href="estilos/estilo.css">
    </head>
    <body>
        <h2>Iniciar Sesión</h2>

        <s:form action="login" method="POST" cssClass="card-form">
            <s:textfield name="email" label="Email"/>
            <s:password name="contrasena" label="Contraseña"/>
            <s:submit value="Entrar"/>
        </s:form>

        <s:actionerror/>
        <s:actionmessage/>
    </body>
</html>


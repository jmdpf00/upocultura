<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Registro de Usuario</title>
        <link rel="stylesheet" href="estilos/estilo.css">
    </head>
    <body>
        <h2>Registro de Usuario</h2>

        <s:actionerror/>
        <s:fielderror/>
        <s:actionmessage/>

        <s:form action="registro" method="POST" cssClass="card-form">
            <s:textfield name="usuario.nombre" label="Nombre" required="true"/>
            <s:textfield name="usuario.email" label="Email" required="true"/>
            <s:password name="usuario.contrasena" label="Contraseña" required="true"/>


            <s:select name="usuario.tipo" label="Tipo" list="tiposUsuario" required="true"/>

            <s:submit value="Registrar"/>
        </s:form>
    </body>
</html>


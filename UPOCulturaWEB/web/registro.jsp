<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Registro de Usuario</title>
    </head>
    <body>
        <h2>Registro de Usuario</h2>

        <s:actionerror/>
        <s:fielderror/>
        <s:actionmessage/>

        <s:form action="registro" method="post">
            <s:textfield name="usuario.nombre" label="Nombre" required="true"/>
            <s:textfield name="usuario.email" label="Email" required="true"/>
            <s:password name="usuario.contrasena" label="Contraseña" required="true"/>

            <!-- Aquí usamos el map que devuelve el Action -->
            <s:select name="usuario.tipo" label="Tipo" list="tiposUsuario" required="true"/>

            <s:submit value="Registrar"/>
        </s:form>
    </body>
</html>


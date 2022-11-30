<%-- 
    Document   : modificar
    Created on : 29/11/2022, 09:29:21 PM
    Author     : Daniel Arias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>almacen</title>
    </head>
    <body>
        <h2>Modificar registro</h2>
        <form action="productosController?accion=actualizar" method="POST" autocomplete="off">
            <input type="hidden" id="id" name="id" value="<c:out value="${producto.id}"/>"/>
            <p>
                Código:
                <input id="codigo" name="codigo" type="text" value="<c:out value="${producto.codigo}"/>"/>
            </p>
            <p>
                Nombre:
                <input id="nombre" name="nombre" type="text" value="<c:out value="${producto.nombre}"/>"/>
            </p>
            <p>
                Precio:
                <input id="precio" name="precio" type="text" value="<c:out value="${producto.precio}"/>"/>
            </p>
            <p>
                Existencia:
                <input id="existencia" name="existencia" type="text" value="<c:out value="${producto.existencia}"/>"/>
            </p>
            <button id="guardar" name="guardar" type="submit">Guardar</button>
        </form>
    </body>
</html>

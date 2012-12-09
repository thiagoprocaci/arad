<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <script src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js"></script>
    </head>
    <body>
        <f:form id="formPrincipal" modelAttribute="topicoDto" action="grafoConceitos" method="get">
           <div class="topicoAlvo">
                <div class="selecioneTopico">
                   Selecione o que deseja aprender no domínio da programação de computadores
                </div>
                <div>
                      <f:select path="topicoID" items="${topicoList}" multiple="false" />
                </div>
                <div>
                      <input type="submit" value="selecionar" />
                </div>
            </div>
        </f:form>
    </body>
</html>
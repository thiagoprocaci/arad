<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <body>
        <f:form id="formPrincipal" modelAttribute="grafoDto" action="selecionarConceito.do" method="post">
            <div>
                  <f:select path="topicoID" items="${topicoList}" multiple="false" />
            </div>
            <div>
                  <input type="submit" value="selecionar" />
            </div>
        </f:form>
    </body>
</html>
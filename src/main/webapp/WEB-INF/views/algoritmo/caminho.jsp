<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <body>
        <f:form id="formPrincipal" action="selecionarConceito.do" method="post">
            <div>
                <c:out value="${grafoDto.topico.nome}" escapeXml="true" />
            </div>
        </f:form>
        <f:form id="form_2" action="selecionarConceito.do" method="get">
               <div>
                    <input type="submit" value="voltar" />
              </div>
        </f:form>
    </body>

</html>
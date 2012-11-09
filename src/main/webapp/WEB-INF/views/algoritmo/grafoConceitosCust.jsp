<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <body>

            <div>
                Caminho para
                <c:out value="${topico.nome}" escapeXml="true" />
                <br /><br />
                <c:forEach var="tree" items="${treeList}">
                     <c:out value="  ${tree.disciplina}"/>   <br />
                     <c:forEach var="t" items="${tree.topicoList}">
                            <c:out value="${t}"/>
                            <c:out value="-"/>
                            <c:out value="${t.nodeWeight}"/>
                            <br />
                     </c:forEach>
                     <br />
                </c:forEach>
            </div>

        <f:form id="form_2" action="listarConceitos" method="get">
               <div>
                    <input type="submit" value="voltar" />
              </div>
        </f:form>
    </body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <body>
        <f:form id="formPrincipal" action="aStartSearch" method="get">
            <div>
                Caminho para
                <c:out value="${topico.nome}" escapeXml="true" />
                <br /><br />
                <c:forEach var="tree" items="${treeList}">
                     <c:out value="  ${tree.disciplina}"/>   <br />
                     <c:forEach var="t" items="${tree.topicoList}">
                        <c:if test="${t.root == false}">
                            <c:out value="${t}"/>
                            <br />
                            1 <input type="radio" name="${t.rdfId}" value="1">
                            2 <input type="radio" name="${t.rdfId}" value="2">
                            3 <input type="radio" name="${t.rdfId}" value="3">
                            4 <input type="radio" name="${t.rdfId}" value="4">
                            5 <input type="radio" name="${t.rdfId}" value="5">
                            <br />
                        </c:if>
                     </c:forEach>
                     <br />
                </c:forEach>
            </div>
            <div>
                <input type="hidden" name="topicoId" value="${topico.rdfId}"/>
                <input type="submit" value="Submit" />
            </div>
        </f:form>
        <f:form id="form_2" action="listarConceitos" method="get">
               <div>
                    <input type="submit" value="voltar" />
              </div>
        </f:form>
    </body>

</html>
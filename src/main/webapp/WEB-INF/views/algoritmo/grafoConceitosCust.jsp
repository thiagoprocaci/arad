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
        <div class="caminho">
            Caminho para "<c:out value="${topico.nome}" escapeXml="true" />"
        </div>
        <br /><br />
        <div>
            <fieldset>
                <c:forEach var="tree" items="${treeList}">
                <c:if test="${not empty tree.disciplina.antecessors}">
                         <div class="disciplina">
                            <c:out value="${tree.disciplina}"/>
                         </div>
                         <br />
                         <c:forEach var="t" items="${tree.topicoList}">
                            <c:if test="${t.root == false}">
                            <div class="topico">
                                 <c:if test="${t.goal == false}">
                                       <c:out value="${t}"/>
                                 </c:if>
                                 <c:if test="${t.selecionado}">
                                       <span class="goal"> <c:out value="${t}"/> </span>
                                 </c:if>
                                 <br />
                            </div>
                            <div class="oa">
                                  <c:forEach var="oa" items="${t.oaList}" varStatus="index">
                                         <a href="${oa.url}" target="_blank" >material <c:out value="${index.count}"/></a>
                                         <br />
                                  </c:forEach>
                            </div>
                            </c:if>
                         </c:forEach>
                         <br />
                     </c:if>
                </c:forEach>
            </fieldset>
        </div>
        <f:form id="form_2" action="listarConceitos" method="get">
               <div>
                    <input type="submit" value="voltar" />
              </div>
        </f:form>
    </body>

</html>
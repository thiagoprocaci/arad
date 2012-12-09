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
        <f:form id="formPrincipal" action="aStartSearch" method="get">
                <div class="caminho">
                    Caminho para "<c:out value="${topico.nome}" escapeXml="true" />"
                </div>
                <br /><br />
                <fieldset>
                    <c:forEach var="tree" items="${treeList}" varStatus="index">
                    <c:if test="${not empty tree.disciplina.antecessors}">
                             <div class="disciplina">
                                <c:if test="${not index.last}">
                                    <input type="checkbox" name="nn" id="nn_${index.count}" checked="true" value="${index.count}">
                                </c:if>
                                <c:out value="${tree.disciplina}"/>
                                <input type="hidden" name="${tree.disciplina.rdfId}" value="1" id="disciplina_${index.count}"/>
                             </div>
                             <br />
                             <div id="topico_${index.count}">
                                 <c:forEach var="t" items="${tree.topicoList}">
                                    <c:if test="${t.root == false}">
                                     <div class="topico">
                                            <c:if test="${t.goal == false}">
                                                  <span class="notGoal"><c:out value="${t}"/></span>
                                            </c:if>
                                            <c:if test="${t.selecionado}">
                                                  <span class="goal"> <c:out value="${t}"/> </span>
                                            </c:if>
                                            <br />
                                            <c:if test="${t.goal == false}">
                                                <div class="horizontalDiv">
                                                    <div>
                                                         <input type="radio" name="${t.rdfId}" value="1"> Nenhum
                                                    </div>
                                                    <div>
                                                         <input type="radio" name="${t.rdfId}" value="2"> Básico
                                                    </div>
                                                    <div>
                                                         <input type="radio" name="${t.rdfId}" value="3"> Requer Aprofundamento
                                                    </div>
                                                  </div>
                                              </c:if>
                                            <div class="clearBothDiv"></div>
                                       </div>
                                    </c:if>
                                 </c:forEach>
                             </div>
                             <br />
                         </c:if>
                    </c:forEach>
                    <div>
                        <input type="hidden" name="topicoId" value="${topico.rdfId}"/>
                        <input type="submit" value="Submit" />
                    </div>
                </fieldset>
        </f:form>
        <f:form id="form_2" action="listarConceitos" method="get">
               <div>
                    <input type="submit" value="voltar" />
              </div>
        </f:form>

       <script>

           $('input[name="nn"]').click(function(){
                    var idTopico = "#topico_" + $(this).val()
                    var idDisciplina = "#disciplina_" + $(this).val()
                    if($(this).is(':checked')) {
                        $(idTopico).show()
                        $(idDisciplina).attr("value", "1")
                    } else {
                        $(idTopico).hide()
                        $(idDisciplina).attr("value", "5")
                    }

           });

       </script>

    </body>

</html>
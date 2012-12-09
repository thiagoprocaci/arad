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
                                    <span class="notGoal"> <c:out value="${t}"/> </span>
                                 </c:if>
                                 <c:if test="${t.selecionado}">
                                       <span class="goal"> <c:out value="${t}"/> </span>
                                 </c:if>
                                 <br />
                            </div>
                            <div class="oa">
                                  <c:forEach var="oa" items="${t.oaList}" varStatus="index">
                                         <c:if test="${oa.tipo != 'video'}">
                                            <br />
                                            <a href="${oa.url}" target="_blank" >material base para download</a>
                                            <br />
                                         </c:if>
                                         <c:if test="${oa.tipo == 'video'}">
                                            <div class="container">
                                             <input type="hidden" value="${oa.videoId}" name="preview_${index.count}"/>
                                             <input type="hidden" value="${oa.videoId}" name="api"/>

                                              <div class="preview" id="preview_${index.count}">
                                                <img class="thumb" id="${oa.videoId}">
                                                <img class="play" src="https://s-static.ak.fbcdn.net/rsrc.php/v2/yG/r/Gj2ad6O09TZ.png">
                                              </div>
                                              <div class="info" id="info_${oa.videoId}">

                                              </div>
                                              <div class="info-small">
                                                www.youtube.com
                                              </div>
                                              <div style="clear: both;"></div>
                                            </div>​


                                          </c:if>
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

                    <script>



                          $(function() {

                            var a = document.getElementsByName('api');

                            $('input[name="api"]').each(function(index) {
                                    var hiddenValue =  $(this).val();
                                    var api_url = "https://gdata.youtube.com/feeds/api/videos/" + hiddenValue + "?v=2&alt=json-in-script&callback=?";
                                    var video_url = "http://youtube.com/watch?v=" + hiddenValue;
                                  // Get video information and set the title.
                                  $.getJSON(api_url, function(data) {
                                    var idElem = "#info_" + hiddenValue;
                                    $(idElem).html("<b><a href='" + video_url + "' target='_blank'>" + data.entry.title.$t + "</a></b>");

                                  });
                            });


                            // Set the thumbnail image for the video.
                            $(".preview img.thumb").attr("src", "http://img.youtube.com/vi/" + $(this).attr('id') + "/1.jpg");


                            $(".preview img.thumb").each(function(index) {
                                var t = "http://img.youtube.com/vi/" + $(this).attr('id') + "/1.jpg"
                                $(this).attr("src", t);

                              });


                            // Switch to the iframe when the image is clicked.
                            $(".preview").click(function() {
                              var hiddenName = $(this).attr('id')

                              var hiddenValue =  $('input[name="' + hiddenName +'"]').val();

                              var iframe_url = "http://www.youtube.com/embed/" + hiddenValue + "?autoplay=1";
                              $(this).html("<iframe width='400' height='250' src='" + iframe_url + "' frameborder='0' allowfullscreen></iframe>");
                              $(this).css("float", "none");
                            });
                          });
                         </script>




</html>
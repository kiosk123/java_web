<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="styleshee" href="/modal/XMLDisplay.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script src="/modal/XMLDisplay.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<button type="button" onclick="modalHandler(this)">테스트 모달 창 생성</button>

<%@include file="modal.jsp" %>
<script>

var contextPath = "${pageContext.request.contextPath}";

function modalHandler(ele){
	request(contextPath+"/data",function(data){
		$("#testModal .modal-body").empty();
		$('#testModal .modal-body').jstree({
		    core: {
		      data: xmlToJson(xml.documentElement)
		    }
		});  
		$("#testModal").modal('show');
	});
}

function request(url,func){
	$.ajax({
        url: url,
        success: function (data) {
   			func(data);
        },
        error: function(xhr) {
        	alert("ERROR : "+xhr.status);
        }
    });
}

function xmlToJson(xmlNode) {
	console.log(xmlNode.nodeName);
    return {
        text: xmlNode.firstChild && xmlNode.firstChild.nodeType === 3 ? 
                  xmlNode.firstChild.textContent : xmlNode.nodeName, 
        icon:false,
        children: [...xmlNode.children].map(childNode => xmlToJson(childNode))
    };
}

var xmlText = "<root>A<node>B<node>C</node></node></root>";

var xml = (new DOMParser()).parseFromString(xml_text,'text/xml');




</script>	
</body>
</html>
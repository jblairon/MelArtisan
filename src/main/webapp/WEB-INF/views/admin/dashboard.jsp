<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page session="true" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>

<div class="div-titre-h1" style="margin-top: 150px">
	<h1 class="titre-h1">
		Bonjour
		<c:out value="${sessionScope.user_prenom} ${sessionScope.user_nom }" />
	</h1>

</div>
<br /><br /><br />

<div id='calendar' style="background-color: #222222; color: #ffffff"></div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#calendar').fullCalendar({
			locale : 'fr',
			editable : true,
			eventLimit : true,
			events : [ <c:out value="${eventsJSON}" escapeXml="false" /> ]
		});
	});
</script>

<%--defaultDate : '<%=new SimpleDateFormat("").format(new Date())%>',	 --%>


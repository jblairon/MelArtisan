<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h1>
	Bonjour
	<c:out value="${sessionScope.user_name}" />
</h1>

<div id='calendar'></div>

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
	

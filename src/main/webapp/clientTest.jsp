<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,com.gerhardkarl.ws.*, com.gerhardkarl.wsclient.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GK REST WebService Test page</title>
</head>
<body>
	<%
		PersonWSClient client = new PersonWSClient();
	%>
	<p>
		Display Person over age <br>

		<%
			Collection<Person> Persons = client.getPersonsOverAge("20");
		%>

		Person over age Size :	<%=Persons.size()%><br>
		<%
			for (Person aPerson : Persons) {
		%>
		<%=aPerson.toString()%><br>
		<%
			}
		%>
	<p>
		Display Persons by name (Hanne, Moritz, Gerhard) <br>

		<%
			List<String> PersonsName = Arrays.asList("Hanne", "Moritz", "Gerhard");
			Collection<Person> PersonList = client.getPersonsByNameJson(PersonsName);

		%>

		Person over age Size :	<%=PersonList.size()%><br>
		<%
			for (Person aPerson : PersonList) {
		%>
		<%=aPerson.toString()%><br>
		<%
			}
		%>	
</body>
</html>
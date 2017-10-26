package com.gerhardkarl.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/json")
public class PersonResource {

	List<Person> Persons = new ArrayList<Person>();
	StringListConverter converter = new StringListConverter();

	public PersonResource() {
		// Populate test data. Later each WS method will retrieve data from DB.
		Persons.add(new Person(12344, "Moritz", 13));
		Persons.add(new Person(12345, "Hanne", 39));
		Persons.add(new Person(12346, "Gerhard", 44));
		Persons.add(new Person(12347, "Tony", 6));
		Persons.add(new Person(12348, "Bodo", 14));
	}

	@GET
	@Path("Persons")
	@Produces("application/json")
	public Response getPersonList() {
		PersonWrapper wrapper = new PersonWrapper();

		wrapper.setList(Persons);

		return Response.status(200).entity(wrapper).build();
	}

	@GET
	@Path("overAge/{age}")
	@Produces("application/json")
	public Response getPersonOverAgeList(@PathParam("age") String anAge) {
		PersonWrapper wrapper = new PersonWrapper();

		List<Person> newList = new ArrayList<Person>();
		for (Person Person : Persons) {
			if (Person.getAge() > Integer.parseInt(anAge)) {
				newList.add(Person);
			}
		}
		wrapper.setList(newList);
		return Response.status(200).entity(wrapper).build();
	}

	/**
	 * Pass concatenated names as a single string
	 * 
	 * @param namesString
	 * @return
	 */
	@GET
	@Path("PersonsByName")
	@Produces("application/json")
	public Response getPersonsByNames(@QueryParam("names") String namesString) {
		PersonWrapper wrapper = new PersonWrapper();

		List<String> nameList = converter.fromString(namesString);
		List<Person> newList = new ArrayList<Person>();

		for (Person Person : Persons) {
			if (nameList.contains(Person.getName())) {
				newList.add(Person);
			}
		}
		wrapper.setList(newList);
		return Response.status(200).entity(wrapper).build();
	}

	/**
	 * Pass concatenated names as a single string
	 * 
	 * @param namesString
	 * @return
	 */
	@PUT
	@Path("PersonsByNameJson")
	@Produces("application/json")
	public Response getPersonsByNamesJson(String namesJson) {
		PersonWrapper wrapper = new PersonWrapper();

		List<String> nameList = converter.fromJson(namesJson);
		List<Person> newList = new ArrayList<Person>();

		for (Person Person : Persons) {
			if (nameList.contains(Person.getName())) {
				newList.add(Person);
			}
		}
		wrapper.setList(newList);
		return Response.status(200).entity(wrapper).build();
	}

}

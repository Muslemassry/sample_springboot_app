package sample_springboot_app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import hp.linux.ubuntu.MountClass;
import hp.linux.ubuntu.model.Person;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = { MountClass.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
public class MountClassTest {
	private static final String API_ROOT = "http://localhost:8081/person";
	
	private String createPersonAsUri(Person person) {
        Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(person).post(API_ROOT);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        return API_ROOT + "/" + response.jsonPath().get("id");
    }
	
	private String updatePersonAsUri(Person person) {
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(person).put(API_ROOT);
        assertEquals(HttpStatus.ACCEPTED.value(), response.getStatusCode());
        return API_ROOT + "/" + response.jsonPath().get("id");
    }
	
	@Test
	public void whenGetPersonById_thenOk() {
		Person p = new Person(10, "Salmani", 55, "Sohaj");
		Response response = 
	        	RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).get(createPersonAsUri(p));
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertTrue(response.as(List.class).size() > 0);
	}
	
	@Test
	public void whenGetUpdatedPersonById_thenOk() {
		Person p = new Person(1, "Naser", 45, "Aswan");
		Response response = 
	        	RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).get(updatePersonAsUri(p));
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		System.out.println("ay 7aga ya usta");
		assertTrue(response.as(List.class).size() > 0);
	}
}

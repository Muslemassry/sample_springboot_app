package hp.linux.ubuntu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hp.linux.ubuntu.components.SystemComponent;
import hp.linux.ubuntu.model.Person;

@RestController
@RequestMapping("/person")
public class PersonService {
	
	@Autowired
	private SystemComponent systemComponent;
	
	@GetMapping("/{id}")
	public List<Person> getPerson(@PathVariable Integer id) {
		return systemComponent.getPerson(id);
	}
	
	@GetMapping("/")
	public List<Person> getAllPersons() {
		return systemComponent.getAllPersons();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Person addNewPerson(@RequestBody Person newPerson) {
		return systemComponent.addNewPerson(newPerson);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Person updatePerson(@RequestBody Person newPersonDetails) {
		return systemComponent.updatePerson(newPersonDetails);		
	}
}

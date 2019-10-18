package hp.linux.ubuntu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hp.linux.ubuntu.model.Person;

@RestController
@RequestMapping("/person")
public class PersonService {
	private static Map<Integer, Person> personMap;
	
	static {
		personMap = new HashMap<Integer, Person>();
		Person tempPerson = new Person(1, "Ahmed", 21, "Alex");
		personMap.put(tempPerson.getId(), tempPerson);
		tempPerson = new Person(2, "Badawi", 22, "Beheyra");
		personMap.put(tempPerson.getId(), tempPerson);
		tempPerson = new Person(3, "Camal", 23, "Cairo");
		personMap.put(tempPerson.getId(), tempPerson);
		tempPerson = new Person(4, "Dawood", 24, "Damanhour");
		personMap.put(tempPerson.getId(), tempPerson);
	}
	
	@GetMapping("/{id}")
	public List<Person> getPerson(@PathVariable Integer id) {
		List personList = new ArrayList();
		personList.add(personMap.get(id));
		return personList;
	}
	
	@GetMapping("/")
	public List<Person> getAllPersons() {
		List personList = new ArrayList(personMap.values());
		return personList;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Person addNewPerson(@RequestBody Person newPerson) {
		Integer maxId = null;
		if (personMap.size() == 0) {
			maxId = 1;
		} else {
			maxId =  personMap.keySet().stream().mapToInt(v -> v).max().getAsInt();
			++maxId;
		}		
		
		newPerson.setId(maxId);
		personMap.put(newPerson.getId(), newPerson);
		return newPerson;
	}
}

package hp.linux.ubuntu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import hp.linux.ubuntu.model.Person;

@Repository
public class SystemDAO {
	
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
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Person> getPerson(Integer id) {
		List personList = new ArrayList();
		personList.add(personMap.get(id));
		return personList;
	}
	
	public List<Person> getAllPersons() {
		List personList = new ArrayList(personMap.values());
		return personList;
	}
	
	public Person addNewPerson(Person newPerson) {
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
	
	public Person updatePerson(Person newPersonDetails) {
		personMap.remove(newPersonDetails.getId());
		personMap.put(newPersonDetails.getId(), newPersonDetails);
		return personMap.get(newPersonDetails.getId());
		
	}
}

package hp.linux.ubuntu.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import hp.linux.ubuntu.dao.SystemDAO;
import hp.linux.ubuntu.model.Person;

@Component
public class SystemComponent {

	@Autowired
	private SystemDAO systemDAO;
	
	public List<Person> getPerson(Integer id) {
		return systemDAO.getPerson(id);
	}
	
	public List<Person> getAllPersons() {
		return systemDAO.getAllPersons();
	}
	
	@Transactional
	public Person addNewPerson(Person newPerson) {
		return systemDAO.addNewPerson(newPerson);
	}
	
	@Transactional
	public Person updatePerson(Person newPersonDetails) {
		return systemDAO.updatePerson(newPersonDetails);
		
	}

	public SystemDAO getSystemDAO() {
		return systemDAO;
	}

	public void setSystemDAO(SystemDAO systemDAO) {
		this.systemDAO = systemDAO;
	}
}

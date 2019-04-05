package br.com.esig.todoslist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.esig.todoslist.model.Task;
import br.com.esig.todoslist.repository.TaskRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoListApplicationTests {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private TaskRepository taskRepository;
	
	
	private String name1 = "task 01"; 
	private String name2 = "task 02"; 
	private String name3 = "task 03"; 
	private String name4 = "task 04"; 
	private String name5 = "task 05"; 
 
	@Before
    public void setup() {
		 entityManager.persist(new Task(name1, false));
		 entityManager.persist(new Task(name2, false));
		 entityManager.persist(new Task(name3, true));
		 entityManager.persist(new Task(name4, true));
		 entityManager.persist(new Task(name5, true));
    }
	
	
	@Test
    public void retornaTodosPesistidos()  {
		// do 
		Iterable<Task> taskList = taskRepository.findAll();
		Task task1 = taskRepository.findByName(name1);
		Task task2 = taskRepository.findByName(name2);
		Task task3 = taskRepository.findByName(name3);
		Task task4 = taskRepository.findByName(name4);
		Task task5 = taskRepository.findByName(name5);
		
		// then
		assertThat(taskList).hasSize(10);
		assertThat(taskList).contains(task1);
		assertThat(taskList).contains(task2);
		assertThat(taskList).contains(task3);
		assertThat(taskList).contains(task4);
		assertThat(taskList).contains(task5);
	
		assertThat(task1.getName()).isEqualTo(name1);
		assertThat(task2.getName()).isEqualTo(name2);
		assertThat(task3.getName()).isEqualTo(name3);
		assertThat(task4.getName()).isEqualTo(name4);
		assertThat(task5.getName()).isEqualTo(name5);
		
    }
	@Test
	public void removeTask() {
		Task taskFound = taskRepository.findByName("task 01"); 
		taskRepository.delete(taskFound);

		assertThat(taskRepository.findByName("task 01")).isNull();
	}
	
}

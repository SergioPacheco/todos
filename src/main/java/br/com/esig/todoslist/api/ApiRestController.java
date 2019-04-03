package br.com.esig.todoslist.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.esig.todoslist.model.Task;
import br.com.esig.todoslist.repository.TaskRepository;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiRestController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Task> findAll() {
		List<Task> items = new ArrayList<Task>();
		items = taskRepository.findAll();
		return items;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void createOrUpdate(@RequestBody Task task) {
		taskRepository.save(task);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable("id") Long id) {
		taskRepository.delete(id);
	}

}
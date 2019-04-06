package br.com.esig.todoslist.api;

import java.util.List;

import org.primefaces.component.rating.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.esig.todoslist.model.Task;
import br.com.esig.todoslist.repository.TaskRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class ApiRestController {

	@Autowired
	private TaskRepository taskRepository;

	@ApiOperation(value = "List all Todos", notes = "List all ToDos", response = Task.class, responseContainer = "List" )
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved ToDo list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Add an Task", notes = "Add new Task", response = Task.class )
	@ApiResponses({
        @ApiResponse(code = 201, message = "Task Successfuly inserted")
    })
	@PostMapping
	public Task insertTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove a task", notes = "Remove a task")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Task successfuly deleted")
    })
	@DeleteMapping("{id}")
	public void deleteTask(@PathVariable("id") Long id) {
		taskRepository.delete(id);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Update an task", notes = "Update an task")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Task successfuly updated")
    })
	@PutMapping("{id}")
	public void updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
		taskRepository.delete(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a task", notes = "Get a task")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Task exists")
    })
	@GetMapping("/search/findByName")
	public Task findByBookId(@RequestParam("name") String name) {
		return taskRepository.findByName(name);
	}

}
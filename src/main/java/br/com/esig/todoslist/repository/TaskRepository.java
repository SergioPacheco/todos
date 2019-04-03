package br.com.esig.todoslist.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.esig.todoslist.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query
	public List<Task> findByCompletedTrue();
	
	@Query
	public List<Task> findByCompletedFalse();


}

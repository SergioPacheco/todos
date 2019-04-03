package br.com.esig.todoslist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	private String name;
	private boolean completed;
	
	public Task() {
	}

	public Task(String name, boolean completed) {
		this.name = name;
		this.completed = completed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
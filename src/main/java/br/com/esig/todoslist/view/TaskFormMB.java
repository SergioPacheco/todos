package br.com.esig.todoslist.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.esig.todoslist.model.Task;
import br.com.esig.todoslist.repository.TaskRepository;
import br.com.esig.todoslist.utils.Utils;

@Named
@ViewScoped
public class TaskFormMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TaskRepository taskRepository;
	
	private Task task;
	private List<Task> taskList;
	private int totalActive;
	private int totalCompleted;
	private int total; 
	private boolean selectAll; 

	@PostConstruct
	public void init() {
		task = new Task();
		totalActive = 0; 
		totalCompleted = 0; 
		total = 0;
		this.findAll();
	}

	public Task getTask() {
		return task;
	}

	public List<Task> getTaskList() {
		return taskList;
	}
	
	public int getTotal() {
		return total;
	}
	
	public int getTotalActive() {
		totalActive =  taskRepository.findByCompletedFalse().size(); 
		return totalActive;
	}
				 
	public int getTotalCompleted() {
		totalCompleted=  taskRepository.findByCompletedTrue().size(); 
		return totalCompleted;
	}
		
	public boolean getSelectAll() {
		return selectAll;
	}
	 
	public void save() throws IOException {
		if (!task.getName().isEmpty()) {
			System.out.println(task.toString());
			if (task.getId() == null) {
				task.setCompleted(false);
			}
			try {
				taskRepository.save(task);
				taskList.add(task);
				task = new Task();
			} catch (Exception e) {
				Utils.addDetailMessage("Error when saving.");
			}
			findAll();
		}
	}
	
	public void edit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		FacesContext context = FacesContext.getCurrentInstance();
		if (newValue != null && !newValue.equals(oldValue)) {
			Task task = context.getApplication().evaluateExpressionGet(context, "#{task}", Task.class);
			taskRepository.save(task);
		}
	}

	public void updateTotals(Task task) {
		try {
			task.setCompleted(task.getCompleted() ? false : true);
			taskRepository.save(task);
			findAll();

		} catch (Exception e) {
			Utils.addDetailMessage("Error when change task.", FacesMessage.SEVERITY_ERROR);
		}

	}

	public void remove(Task task) {
		try {
			taskRepository.delete(task);
			findAll();
		} catch (Exception e) {
			Utils.addDetailMessage("Error when delete task.", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void removeCompleted() {
		try {
			List<Task> completed = taskRepository.findByCompletedTrue();
			for (Task task : completed) {
				taskRepository.delete(task);
			}
			findAll();
		} catch (Exception e) {
			Utils.addDetailMessage("Error when delete completed task.", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void select() {
		try {
			for (Task task : taskList) {
				task.setCompleted(selectAll);
				taskRepository.save(task);
			}
			findAll();
		} catch (Exception e) {
			Utils.addDetailMessage("Error when select all tasks.", FacesMessage.SEVERITY_ERROR);
		}
		selectAll = selectAll ? false : true;
	}
	 

	public void findAll() {
		try {
			taskList = taskRepository.findAll();
			total = taskList.size();
			totalActive = taskRepository.findByCompletedFalse().size();
			totalCompleted = taskRepository.findByCompletedTrue().size();
			
		} catch (Exception e) {
			Utils.addDetailMessage("Error when finding all tasks.", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void findActive() {
		try {
			taskList = taskRepository.findByCompletedFalse();
		} catch (Exception e) {
			Utils.addDetailMessage("Error when finding actives tasks.", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void findCompleted() {
		try {
			taskList  = taskRepository.findByCompletedTrue();
		} catch (Exception e) {
			Utils.addDetailMessage("Error when find completed tasks.", FacesMessage.SEVERITY_ERROR);
		}
	}

}

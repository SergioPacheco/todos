package br.com.esig.todoslist.utils;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.esig.todoslist.model.Task;
import br.com.esig.todoslist.repository.TaskRepository;

@Named
@ApplicationScoped
public class Utils implements Serializable {
	
	public static void addDetailMessage(String message) {
        addDetailMessage(message, null);
    }
	
	public static void addDetailMessage(String message, FacesMessage.Severity severity) {
        FacesMessage facesMessage = new FacesMessage(severity, null, null);
        if (severity != null && severity != FacesMessage.SEVERITY_INFO) {
            facesMessage.setSeverity(severity);
        }
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
	
}	

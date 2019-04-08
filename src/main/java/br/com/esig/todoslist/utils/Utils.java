package br.com.esig.todoslist.utils;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Utils implements Serializable {
	
	private static final long serialVersionUID = -6112066850581836066L;

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

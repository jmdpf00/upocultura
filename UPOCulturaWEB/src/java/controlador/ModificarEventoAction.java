/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.opensymphony.xwork2.ActionSupport;
import modelo.Evento;
import modelo.UPOCultura;

/**
 *
 * @author Josema
 */
public class ModificarEventoAction extends ActionSupport {
    
    private int id;
    private Evento evento;
    private UPOCultura upocultura = new UPOCultura();
    
    public ModificarEventoAction() {
    }
    
    // Método para cargar el formulario de modificación
    public String form() {
        evento = upocultura.obtenerEventoPorId(id);
        if (evento != null) {
            return SUCCESS;
        } else {
            addActionError("No se pudo encontrar el evento.");
            return ERROR;
        }
    }
    
    public String execute() {
        if (upocultura.actualizarEvento(evento)) {
            return SUCCESS;
        } else {
            addActionError("Error al actualizar el evento.");
            return INPUT;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    
    
}

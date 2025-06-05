/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.opensymphony.xwork2.ActionSupport;
import modelo.UPOCultura;

/**
 *
 * @author Josema
 */
public class EliminarEventoAction extends ActionSupport {
    
    private int id;
    private UPOCultura upocultura = new UPOCultura();
    
    public EliminarEventoAction() {
    }
    
    public String execute() throws Exception {
        if (upocultura.eliminarEvento(id)) {
            return SUCCESS;
        } else {
            addActionError("No se pudo eliminar el evento.");
            return ERROR;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}

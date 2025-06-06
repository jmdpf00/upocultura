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
public class AsignarTareaAction extends ActionSupport {
    
    private int idSolicitud;
    private int idTarea;
    private UPOCultura upoCultura = new UPOCultura();
    
    public AsignarTareaAction() {
    }
    
    public String execute() throws Exception {
        upoCultura.asignarTareaASolicitud(idSolicitud, idTarea);
        return SUCCESS;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import modelo.SolicitudVoluntariado;
import modelo.TareaVoluntario;
import modelo.UPOCultura;

/**
 *
 * @author Josema
 */
public class SolicitudesEventoAction extends ActionSupport {
    
    private int id;
    private List<SolicitudVoluntariado> solicitudes;
    private List<TareaVoluntario> tareas;
    
    private UPOCultura upoCultura = new UPOCultura();
    
    public SolicitudesEventoAction() {
    }
    
    public String execute() throws Exception {
        solicitudes = upoCultura.obtenerSolicitudesPorEvento(id);
        tareas = upoCultura.obtenerTodasTareas();
        return SUCCESS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SolicitudVoluntariado> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudVoluntariado> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<TareaVoluntario> getTareas() {
        return tareas;
    }

    public void setTareas(List<TareaVoluntario> tareas) {
        this.tareas = tareas;
    }
    
    
    
}

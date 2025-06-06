/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.TareaVoluntario;
import modelo.UPOCultura;

/**
 *
 * @author Josema
 */
public class CrearTareaAction extends ActionSupport {
    
    private String descripcion;
    private String horaInicio;
    private String horaFin;
    
    private UPOCultura upoCultura = new UPOCultura();
    
    public CrearTareaAction() {
    }
    
    public String execute() throws Exception {
        TareaVoluntario t = new TareaVoluntario();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        Date horaInicioDate = formatoHora.parse(horaInicio);
        Date horaFinDate = formatoHora.parse(horaFin);
        t.setDescripcion(descripcion);
        t.setHoraInicio(horaInicioDate);
        t.setHoraFin(horaFinDate);
        upoCultura.crearTarea(t);
        return SUCCESS;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    
    
    
    
}

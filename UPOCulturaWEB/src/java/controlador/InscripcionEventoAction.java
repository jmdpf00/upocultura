/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import modelo.Evento;
import modelo.UPOCultura;
import modelo.Usuario;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Josema
 */
public class InscripcionEventoAction extends ActionSupport implements SessionAware{
    
    private int id;
    private Map<String, Object> session;
    private UPOCultura upoCultura = new UPOCultura();
    
    public InscripcionEventoAction() {
    }
    
    public String execute() throws Exception {
        Usuario usuario = (Usuario) session.get("usuario");
        Evento evento = upoCultura.obtenerEventoPorId(id);

        if (usuario != null && evento != null) {
            boolean yaInscrito = upoCultura.estaInscrito(usuario.getId(), id);
            if (!yaInscrito && evento.getPlazas() > 0) {
                upoCultura.inscribirAsistente(usuario.getId(), id);
                evento.setPlazas(evento.getPlazas() - 1);
                upoCultura.actualizarEvento(evento);
            }
        }
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}

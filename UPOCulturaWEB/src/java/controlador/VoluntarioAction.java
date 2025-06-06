/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.Map;
import modelo.Evento;
import modelo.SolicitudVoluntariado;
import modelo.UPOCultura;
import modelo.Usuario;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Josema
 */
public class VoluntarioAction extends ActionSupport implements SessionAware{
    
    private int id;
    private UPOCultura upoCultura = new UPOCultura();
    
    private Map<String, Object> session;
    
    public VoluntarioAction() {
    }
    
    public String execute() throws Exception {
        Usuario usuario = (Usuario) session.get("usuario");
        if (usuario == null) {
            addActionError("Debes iniciar sesión para crear una publicación.");
            return INPUT;
        }
        int idUsuario = usuario.getId();
        Usuario usuarioReal = upoCultura.obtenerUsuarioPorId(usuario.getId());
        
        Evento evento = upoCultura.obtenerEventoPorId(id);
        SolicitudVoluntariado s = new SolicitudVoluntariado();
        s.setEvento(evento);
        s.setUsuario(usuario);
        s.setEstado("PENDIENTE");
        s.setFechaSolicitud(new Date());
        upoCultura.crearSolicitudVoluntario(s);
        return SUCCESS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.Evento;
import modelo.Publicacion;
import modelo.SolicitudVoluntariado;
import modelo.TareaVoluntario;
import modelo.UPOCultura;
import modelo.Usuario;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Josema
 */
public class PrincipalAction extends ActionSupport implements SessionAware {

    private List<Evento> listaEventos;
    private List<Publicacion> listaPublicaciones;
    private List<Integer> eventosSolicitados; // lista de los ids de los eventos solicitados por el usuario
    private List<String> mensajesSeleccionVoluntario;
    private List<Integer> eventosInscritos; // eventos a los que se han inscrito como asistente


    private UPOCultura upoCultura = new UPOCultura();

    private Map<String, Object> session;

    public PrincipalAction() {
    }

    public String execute() throws Exception {
        listaEventos = upoCultura.obtenerTodosEventos();
        listaPublicaciones = upoCultura.obtenerTodasPublicaciones();
        Usuario usuario = (Usuario) session.get("usuario");
        if (usuario != null) {
            eventosSolicitados = upoCultura.obtenerIDsEventoPorUsuario(usuario.getId());
            eventosInscritos = upoCultura.obtenerIDsEventoInscritosPorUsuario(usuario.getId());

            
            List<SolicitudVoluntariado> solicitudesAprobadas = upoCultura.obtenerSolicitudesAprobadasPorUsuario(usuario.getId());
            mensajesSeleccionVoluntario = new ArrayList<>();
            for (SolicitudVoluntariado solicitud : solicitudesAprobadas) {
                String nombreEvento = solicitud.getEvento().getTitulo();

                // Concatenar descripciones de todas las tareas asignadas para esta solicitud
                StringBuilder tareasDesc = new StringBuilder();
                for (Object obj : solicitud.getTareaVoluntarios()) {
                    TareaVoluntario tarea = (TareaVoluntario) obj;
                    if (tareasDesc.length() > 0) {
                        tareasDesc.append(", ");
                    }
                    tareasDesc.append(tarea.getDescripcion());
                }

                String mensaje = "¡Has sido seleccionado como voluntario para el evento \""
                        + nombreEvento + "\" con la(s) tarea(s) asignada(s): \"" + tareasDesc.toString() + "\"";

                mensajesSeleccionVoluntario.add(mensaje);
            }

        }
        return SUCCESS;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public List<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public List<Integer> getEventosSolicitados() {
        return eventosSolicitados;
    }

    public List<String> getMensajesSeleccionVoluntario() {
        return mensajesSeleccionVoluntario;
    }

    public void setMensajesSeleccionVoluntario(List<String> mensajesSeleccionVoluntario) {
        this.mensajesSeleccionVoluntario = mensajesSeleccionVoluntario;
    }

    public List<Integer> getEventosInscritos() {
        return eventosInscritos;
    }

    public void setEventosInscritos(List<Integer> eventosInscritos) {
        this.eventosInscritos = eventosInscritos;
    }
    
    
    

}

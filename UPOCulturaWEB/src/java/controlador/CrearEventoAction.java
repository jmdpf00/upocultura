/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import modelo.Evento;
import modelo.Organizador;
import modelo.UPOCultura;
import modelo.Usuario;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Josema
 */
public class CrearEventoAction extends ActionSupport implements SessionAware{
    
    private String titulo;
    private String descripcion;
    private String ubicacion;
    private String fechaInicio;
    private String fechaFin;
    private int plazas;
    
    private UPOCultura upoCultura = new UPOCultura();
    
    private Map<String, Object> session;
    
    public CrearEventoAction() {
    }
    
    public String execute() throws Exception {
        try {
            // Paso 1: recuperar el usuario de la sesión
            Usuario usuario = (Usuario) session.get("usuario");
            //System.out.println("Usuario logueado: " + usuario.getId());

            // Verifica si está logueado y es organizador
            if (usuario == null || !"organizador".equalsIgnoreCase(usuario.getTipo())) {
                addActionError("Solo los organizadores pueden crear eventos.");
                return INPUT;
            }

            // Paso 2: obtener el objeto Organizador del usuario
            Organizador organizador = upoCultura.obtenerOrganizadorPorUsuario(usuario);

            if (organizador == null) {
                addActionError("No se ha encontrado el organizador vinculado al usuario.");
                return INPUT;
            }
            
            Evento evento = new Evento();
            
            evento.setOrganizador(organizador);
            evento.setTitulo(titulo);
            evento.setDescripcion(descripcion);
            evento.setUbicacion(ubicacion);
            evento.setFechaInicio(java.sql.Date.valueOf(fechaInicio));
            evento.setFechaFin(java.sql.Date.valueOf(fechaFin));
            evento.setPlazas(plazas);

            upoCultura.crearEvento(evento);
            addActionMessage("Evento creado correctamente");
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Error al crear el evento: " + e.getMessage());
            return INPUT;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.Map;
import modelo.Publicacion;
import modelo.UPOCultura;
import modelo.Usuario;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Josema
 */
public class CrearPublicacionAction extends ActionSupport implements SessionAware{
    
    private String titulo;
    private String contenido;
    private String fechaPublicacion;
    private UPOCultura upoCultura = new UPOCultura();
    
    private Map<String, Object> session;
    
    public CrearPublicacionAction() {
    }
    
    public String execute() throws Exception {
        try {
            // recuperar usuario de sesion
            Usuario usuario = (Usuario) session.get("usuario");
            if (usuario == null) {
                addActionError("Debes iniciar sesión para crear una publicación.");
                return INPUT;
            }
            Usuario usuarioReal = upoCultura.obtenerUsuarioPorId(usuario.getId());
            // set de la publicacion
            Publicacion publicacion = new Publicacion();
            publicacion.setUsuario(usuarioReal);
            publicacion.setTitulo(titulo);
            publicacion.setContenido(contenido);
            publicacion.setFechaPublicacion(java.sql.Date.valueOf(fechaPublicacion));

            // guardar la publicación
            upoCultura.crearPublicacion(publicacion);
            addActionMessage("Publicacion creada correctamente");
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Error al crear la publicacion: " + e.getMessage());
            return INPUT;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    
}

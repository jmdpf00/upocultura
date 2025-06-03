/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import modelo.UPOCultura;
import modelo.Usuario;

/**
 *
 * @author Josema
 */
public class RegistrarUsuarioAction extends ActionSupport {

    private Usuario usuario;
    private UPOCultura upoCultura = new UPOCultura();

    public RegistrarUsuarioAction() {
    }

    public String execute() throws Exception {
        if (usuario == null) {
            addActionError("No se han recibido datos del formulario");
            return INPUT;
        }

        usuario.setFechaRegistro(new Date());
        boolean exito = upoCultura.guardarUsuario(usuario);

        if (exito) {
            addActionMessage("Usuario registrado correctamente");
            return SUCCESS;
        } else {
            addActionError("Error al registrar el nuevo usuario");
            return INPUT;
        }
    }

    public Map<String, String> getTiposUsuario() {
        Map<String, String> tipos = new LinkedHashMap<>();
        tipos.put("asistente", "Asistente");
        tipos.put("organizador", "Organizador");
        tipos.put("voluntario", "Voluntario");
        return tipos;
    }

    public String form() {
        return SUCCESS;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

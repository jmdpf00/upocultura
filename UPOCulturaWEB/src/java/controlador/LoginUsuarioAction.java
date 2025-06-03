/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpSession;
import modelo.UPOCultura;
import modelo.Usuario;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Josema
 */
public class LoginUsuarioAction extends ActionSupport implements SessionAware{

    private String email;
    private String contrasena;
    private UPOCultura upoCultura = new UPOCultura();
    private Map<String, Object> session;

    public LoginUsuarioAction() {
    }

    public String execute() throws Exception {
        Usuario original = upoCultura.verificarCredenciales(email, contrasena);

        if (original != null) {
            // Clonar manualmente sin proxy para evitar lazy loading
            Usuario usuario = new Usuario();
            usuario.setId(original.getId());
            usuario.setEmail(original.getEmail());
            usuario.setContrasena(original.getContrasena());
            usuario.setTipo(original.getTipo());

            session.put("usuario", usuario); // este no es proxy
            return SUCCESS;
        } else {
            addActionError("Email o contraseña incorrectos");
            return INPUT;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    
}

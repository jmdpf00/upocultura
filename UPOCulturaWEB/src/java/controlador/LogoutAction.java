/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Josema
 */
public class LogoutAction extends ActionSupport implements SessionAware{
    
    private Map<String, Object> session;
    
    public LogoutAction() {
    }
    
    public String execute() throws Exception {
        if (session != null) {
            session.clear();  // Limpia toda la sesión
        }
        return SUCCESS; // Redirige a index.jsp
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    
}

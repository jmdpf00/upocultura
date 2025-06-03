/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import modelo.Evento;
import modelo.UPOCultura;

/**
 *
 * @author Josema
 */
public class PrincipalAction extends ActionSupport {
    
    private List<Evento> listaEventos;
    private UPOCultura upoCultura = new UPOCultura();
    
    public PrincipalAction() {
    }
    
    public String execute() throws Exception {
        UPOCultura upo = new UPOCultura();
        listaEventos = upoCultura.obtenerTodosEventos();
        return SUCCESS;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }
    
    
}

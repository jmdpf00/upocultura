/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Josema
 */
public class UPOCultura {

    // Metodo para guardar un nuevo usuario
    public boolean guardarUsuario(Usuario u) {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            tx = s.beginTransaction();
            s.save(u);
            if ("organizador".equalsIgnoreCase(u.getTipo())) {
                Organizador o = new Organizador();
                o.setUsuario(u); // Asocia el usuario
                s.save(o); // Guardar organizador
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // Metodo para comprobar las credenciales de un usuario
    public Usuario verificarCredenciales(String email, String contrasena) {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            tx = s.beginTransaction();
            Query q = s.createQuery("FROM Usuario WHERE email = '" + email + "' AND contrasena = '" + contrasena + "'");
            Usuario usuario = (Usuario) q.uniqueResult();
            tx.commit();
            s.close(); // Se cierra aquí antes de salir si todo fue bien
            return usuario;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
    
    // Metodo para obtener todos los eventos registrados
    public List<Evento> obtenerTodosEventos() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM Evento");
        List<Evento> eventos = q.list();
        s.close();
        return eventos;
    }
    
    // Metodo para crear un nuevo evento
    public void crearEvento(Evento evento) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(evento);
        tx.commit();
        s.close();
    }

    // Metodo para obtener el id del organizador por usuario
    public Organizador obtenerOrganizadorPorUsuario(Usuario usuario) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Organizador o JOIN FETCH o.usuario u WHERE u.id = :userId";
        Query query = s.createQuery(hql);
        query.setParameter("userId", usuario.getId());
        List<Organizador> lista = query.list();
        s.close();
        return lista.isEmpty() ? null : lista.get(0);
    }








}

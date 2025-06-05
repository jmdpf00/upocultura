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

    // Metodo que obtiene un evento por su ID
    public Evento obtenerEventoPorId(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM Evento WHERE id = :id");
        q.setParameter("id", id);
        List<Evento> resultados = q.list();
        s.close();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    // Metodo para actualizar un evento
    public boolean actualizarEvento(Evento evento) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("UPDATE Evento SET titulo = :titulo, descripcion = :descripcion, ubicacion = :ubicacion, fechaInicio = :fechaInicio, fechaFin = :fechaFin, plazas = :plazas WHERE id = :id");
        q.setParameter("titulo", evento.getTitulo());
        q.setParameter("descripcion", evento.getDescripcion());
        q.setParameter("ubicacion", evento.getUbicacion());
        q.setParameter("fechaInicio", evento.getFechaInicio());
        q.setParameter("fechaFin", evento.getFechaFin());
        q.setParameter("plazas", evento.getPlazas());
        q.setParameter("id", evento.getId());
        int filas = q.executeUpdate();
        tx.commit();
        s.close();
        return filas > 0;
    }

    // Metodo para eliminar un evento
    public boolean eliminarEvento(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("DELETE FROM Evento WHERE id = :id");
        q.setParameter("id", id);
        int filas = q.executeUpdate();
        tx.commit();
        s.close();
        return filas > 0;
    }
    
    // Metodo para obtener todas las publicaciones registradas
    public List<Publicacion> obtenerTodasPublicaciones() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM Publicacion");
        List<Publicacion> publicaciones = q.list();
        s.close();
        return publicaciones;
    }

    // Metodo para crear una nueva publicacion
    public void crearPublicacion(Publicacion publicacion) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(publicacion);
        tx.commit();
        s.close();
    }
    
    // Metodo para obtener un usuario a traves su ID
    public Usuario obtenerUsuarioPorId(int userId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM modelo.Usuario u WHERE u.id = :userId";
        Query query = s.createQuery(hql);
        query.setParameter("userId", userId);
        Usuario usuario = (Usuario) query.uniqueResult();
        s.close();
        return usuario;
    }






}

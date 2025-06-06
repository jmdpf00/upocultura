/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
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

    // Metodo para crear una nueva tarea
    public void crearTarea(TareaVoluntario t) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(t);
        tx.commit();
        s.close();
    }

    // Metodo para crear una nueva solicitud de voluntariado
    public void crearSolicitudVoluntario(SolicitudVoluntariado sol) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(sol);
        tx.commit();
        s.close();
    }

    // Metodo para obtener una lista de los ids de los eventos solicitados por el usuario
    public List<Integer> obtenerIDsEventoPorUsuario(int idUsuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT s.evento.id FROM SolicitudVoluntariado s WHERE s.usuario.id = :idUsuario";
        Query query = session.createQuery(sql);
        query.setParameter("idUsuario", idUsuario);
        List<Integer> result = query.list();
        session.close();
        return result;
    }

    // Metodo para obtener todas las tareas
    public List<TareaVoluntario> obtenerTodasTareas() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM TareaVoluntario");
        List<TareaVoluntario> tareas = q.list();
        s.close();
        return tareas;
    }

    // Metodo que devuelve solicitudes por id de evento
    public List<SolicitudVoluntariado> obtenerSolicitudesPorEvento(int idEvento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM SolicitudVoluntariado s WHERE s.evento.id = :idEvento";
        List<SolicitudVoluntariado> lista = session.createQuery(sql)
                .setParameter("idEvento", idEvento)
                .list();
        session.close();
        return lista;
    }

    // Método que devuelve una tarea por su id
    public TareaVoluntario obtenerTareaPorID(int idTarea) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM TareaVoluntario t WHERE t.id = :idTarea";
        List<TareaVoluntario> lista = session.createQuery(hql)
                .setParameter("idTarea", idTarea)
                .list();
        session.close();
        return lista.isEmpty() ? null : lista.get(0);
    }

    public void asignarTareaASolicitud(int idSolicitud, int idTarea) throws Exception {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Cargar tarea
            TareaVoluntario tarea = (TareaVoluntario)session.get(TareaVoluntario.class, idTarea);
            if (tarea == null) {
                throw new Exception("No existe la tarea con id: " + idTarea);
            }
            if (tarea.getSolicitudVoluntariado() != null) {
                throw new Exception("La tarea ya está asignada a una solicitud.");
            }

            // Cargar solicitud
            SolicitudVoluntariado solicitud = (SolicitudVoluntariado)session.get(SolicitudVoluntariado.class, idSolicitud);
            if (solicitud == null) {
                throw new Exception("No existe la solicitud con id: " + idSolicitud);
            }

            // Asignar solicitud a tarea
            tarea.setSolicitudVoluntariado(solicitud);
            session.update(tarea);

            // Cambiar estado solicitud
            solicitud.setEstado("aprobada");
            session.update(solicitud);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    // Metodo para obtener solicitudes aprobadas por usuario
    public List<SolicitudVoluntariado> obtenerSolicitudesAprobadasPorUsuario(int idUsuario) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "SELECT DISTINCT sv FROM SolicitudVoluntariado sv "
                    + "JOIN FETCH sv.tareaVoluntarios "
                    + "WHERE sv.usuario.id = :idUsuario AND sv.estado = 'aprobada'";
            Query q = s.createQuery(sql);
            q.setParameter("idUsuario", idUsuario);
            return q.list();
        } finally {
            s.close();
        }
    }

    public boolean estaInscrito(int idUsuario, int idEvento) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT COUNT(*) FROM Inscripcion i WHERE i.usuario.id = :idUsuario AND i.evento.id = :idEvento";
            Query q = s.createQuery(hql);
            q.setParameter("idUsuario", idUsuario);
            q.setParameter("idEvento", idEvento);
            return (Long) q.uniqueResult() > 0;
        } finally {
            s.close();
        }
    }
    
    public void inscribirAsistente(int idUsuario, int idEvento) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        try {
            Usuario u = (Usuario)s.get(Usuario.class, idUsuario);
            Evento e = (Evento)s.get(Evento.class, idEvento);
            Inscripcion insc = new Inscripcion();
            insc.setUsuario(u);
            insc.setEvento(e);
            insc.setFechaInscripcion(new Date());
            s.save(insc);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            s.close();
        }
    }

    public List<Integer> obtenerIDsEventoInscritosPorUsuario(int idUsuario) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT i.evento.id FROM Inscripcion i WHERE i.usuario.id = :idUsuario";
            Query q = s.createQuery(hql);
            q.setParameter("idUsuario", idUsuario);
            return q.list();
        } finally {
            s.close();
        }
    }


}

package com.mojafirma.cinema.model.dao;

import com.mojafirma.cinema.HibernateUtil;
import com.mojafirma.cinema.model.Movie;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;
//@Service
public class MovieDAO {

    public static MovieDAO movieDAO;

    public MovieDAO() {
        movieDAO = this;
    }

    public void addMovie(Movie movie) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(movie);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            // HibernateUtil.getSessionFactory().close();
        }
    }

    public Movie getMovie(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Movie movie = null;
        try {
            tx = session.beginTransaction();
            movie = session.get(Movie.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            //HibernateUtil.getSessionFactory().close();
        }
        return movie;
    }

    public List<Movie> getAllTheMovies() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        TypedQuery<Movie> query = session.createQuery("select m from Movie m", Movie.class);
        List<Movie> allMovies = query.getResultList();
        session.close();
        // HibernateUtil.getSessionFactory().close();
        return allMovies;
    }

    public void updateMovie(Movie movie) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(movie);
        tx.commit();
        session.close();
    }

}

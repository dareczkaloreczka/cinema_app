package com.mojafirma.cinema.presenter;

import com.mojafirma.cinema.MovieApp;
import com.mojafirma.cinema.model.Movie;
import com.mojafirma.cinema.model.MovieCategory;
import com.mojafirma.cinema.model.MovieGenre;
import com.mojafirma.cinema.model.dao.MovieDAO;
import com.mojafirma.cinema.view.AddMovieFrame;
import com.mojafirma.cinema.view.EditFrame;
import com.mojafirma.cinema.view.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import java.sql.Date;
import java.util.List;
//@Service
public class MoviePresenter {

   // @Autowired
    private MainFrame mainFrame;
   // @Autowired
    private MovieDAO movieDAO= MovieDAO.movieDAO;
    private AddMovieFrame addMovieFrame;
    private EditFrame editFrame;

    public static MoviePresenter moviePresenter;

    public MoviePresenter() {
        moviePresenter =this;
    }

    public void fillTheTable() {
        mainFrame =  MainFrame.frame;
        List<Movie> movieList = movieDAO.getAllTheMovies();
        for (Movie m : movieList) {

            String[] movieData = {String.valueOf(m.getId()), m.getTitle(), String.valueOf(m.getGenre())};
                mainFrame.getModel().addRow(movieData);

        }
    }
    public void addMovie() {
        addMovieFrame = AddMovieFrame.addMovieFrame;
        Movie movie = new Movie();
        movie.setTitle(addMovieFrame.getTitleData().getText());
        movie.setYear(Date.valueOf(addMovieFrame.getPremiereData().getText()));
        movie.setDirector(addMovieFrame.getDirectorData().getText());
        movie.setGenre((MovieGenre) addMovieFrame.getGenreBox().getSelectedItem());
        movie.setDuration(Integer.parseInt(addMovieFrame.getDurationData().getText()));
        movie.setAgeCategory((MovieCategory) addMovieFrame.getCategoryBox().getSelectedItem());
        movieDAO.addMovie(movie);
        mainFrame.getModel().setRowCount(0);
        fillTheTable();
    }
    public void editMovie(Movie movie){
        editFrame = EditFrame.editFrame;
        movie.setTitle(editFrame.getTitleData().getText());
        movie.setYear(Date.valueOf(editFrame.getPremiereData().getText()));
        movie.setDirector(editFrame.getDirectorData().getText());
        movie.setGenre((MovieGenre) editFrame.getGenreBox().getSelectedItem());
        movie.setDuration(Integer.parseInt(editFrame.getDurationData().getText()));
        movie.setAgeCategory((MovieCategory) editFrame.getCategoryBox().getSelectedItem());
        movieDAO.updateMovie(movie);
        mainFrame.getModel().setRowCount(0);
        fillTheTable();
    }


    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public MovieDAO getMovieDAO() {
        return movieDAO;
    }

    public void setMovieDAO(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }
}

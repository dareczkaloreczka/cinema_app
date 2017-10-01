package com.mojafirma.cinema.presenter;

import com.mojafirma.cinema.model.Movie;
import com.mojafirma.cinema.model.MovieCategory;
import com.mojafirma.cinema.model.MovieGenre;
import com.mojafirma.cinema.model.dao.MovieDAO;
import com.mojafirma.cinema.view.AddMovieFrame;
import com.mojafirma.cinema.view.DetailsPanel;
import com.mojafirma.cinema.view.EditFrame;
import com.mojafirma.cinema.view.MainFrame;
import com.mojafirma.cinema.view.TablePanel;
import java.sql.Date;
import java.util.List;


public class MoviePresenter {

    private MainFrame mainFrame;
    private MovieDAO movieDAO;
    private AddMovieFrame addMovieFrame;
    private EditFrame editFrame;
    private TablePanel tablePanel;
    private DetailsPanel detailsPanel;
    private MovieObservable movieObservable;

    public static MoviePresenter moviePresenter;

    public MoviePresenter() {
        moviePresenter =this;
        movieDAO = new MovieDAO();
        tablePanel = TablePanel.tablePanel;

    }

    public void fillTheTable() {
        tablePanel = TablePanel.tablePanel;
        List<Movie> movieList = movieDAO.getAllTheMovies();
        for (Movie m : movieList) {
            String[] movieData = {String.valueOf(m.getId()), m.getTitle(), String.valueOf(m.getGenre())};
                tablePanel.getModel().addRow(movieData);
        }
    }
    public Movie getSelectedMovie() {
        tablePanel = TablePanel.tablePanel;
        Movie movie = null;
        if (tablePanel.getMovieTable().getSelectedRow() > -1) {
            int id = Integer.parseInt((String) tablePanel.getMovieTable().getModel().getValueAt(tablePanel.getMovieTable().getSelectedRow(), 0));
            movie = movieDAO.getMovie(id);
        }
        return movie;
    }
    public void showSelected() {
        mainFrame = MainFrame.frame;
        detailsPanel = DetailsPanel.detailsPanel;
        Movie movie = getSelectedMovie();
        detailsPanel.getTitleData().setText(movie.getTitle());
        detailsPanel.getPremiereData().setText(String.valueOf(movie.getYear()));
        detailsPanel.getDirectorData().setText(movie.getDirector());
        detailsPanel.getGenreData().setText(movie.getGenre().toString());
        detailsPanel.getDurationData().setText(String.valueOf(movie.getDuration()));
        detailsPanel.getAgeData().setText(movie.getAgeCategory().toString());
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
        updateView();

    }
    public void removeMovie(){
        Movie movie = getSelectedMovie();
        movieDAO.removeMovie(movie);
        updateView();
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

    }

    public void updateView(){
      //  tablePanel.getModel().setRowCount(0);
       // fillTheTable();
        movieObservable = new MovieObservable(null);
        movieObservable.addObserver(tablePanel);
        movieObservable.setMoviesDB(movieDAO.getAllTheMovies());
    }

    public AddMovieFrame getAddMovieFrame() {
        return addMovieFrame;
    }

    public EditFrame getEditFrame() {
        return editFrame;
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }

    public DetailsPanel getDetailsPanel() {
        return detailsPanel;
    }

    public static MoviePresenter getMoviePresenter() {
        return moviePresenter;
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

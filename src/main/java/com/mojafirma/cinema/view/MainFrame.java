package com.mojafirma.cinema.view;

import com.mojafirma.cinema.model.Movie;
import com.mojafirma.cinema.model.dao.MovieDAO;
import com.mojafirma.cinema.presenter.MoviePresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
//@Component
public class MainFrame extends JFrame {



    private static MyTableModel model;
    private MovieDAO movieDAO = new MovieDAO();
    private MoviePresenter presenter = new MoviePresenter();
    private static JTable movieTable;
    public static MainFrame frame;
    JTextArea titleData;
    JTextArea premiereData;
    JTextArea directorData;
    JTextArea genreData;
    JTextArea durationData;
    JTextArea ageData;

    public MainFrame() throws HeadlessException {
        init();
        frame = this;
    }

    private void init() {

        setTitle("Cinema Pitu-pitu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(760, 500);
        JMenuBar menuBar = new JMenuBar();
        JMenu repertoire = new JMenu("Repertoire");
        JMenu reservations = new JMenu("Reservations");
        JMenuItem addNewReservation = new JMenuItem("Create new..");
        JMenuItem manageReservations = new JMenuItem("Manage reservations");
        reservations.add(addNewReservation);
        reservations.add(manageReservations);
        menuBar.add(repertoire);
        menuBar.add(reservations);
        getContentPane().add(BorderLayout.NORTH, menuBar);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        JLabel nowPlaying = new JLabel("NOW PLAYING");
        model = new MyTableModel();
        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Genre");
        movieTable = new JTable(model);
        JScrollPane scrollPanePanel = new JScrollPane(movieTable);
        scrollPanePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tablePanel.add(nowPlaying);
        tablePanel.add(scrollPanePanel);
        mainPanel.add(tablePanel);

        JPanel movieTools = new JPanel();
        movieTools.setLayout(new BoxLayout(movieTools, BoxLayout.Y_AXIS));
        JLabel details = new JLabel("DETAILS");
        JPanel movieDetails = new JPanel();
        movieDetails.setLayout(new GridLayout(3, 2));
        JPanel titlePane = new JPanel();
        JLabel title = new JLabel("Tittle: ");
        titleData = new JTextArea("_____________________");
        titlePane.add(title);
        titlePane.add(titleData);
        JPanel premierePane = new JPanel();
        JLabel premiere = new JLabel("Premiere: ");
        premiereData = new JTextArea("_____________________");
        premierePane.add(premiere);
        premierePane.add(premiereData);
        JPanel directorPane = new JPanel();
        JLabel director = new JLabel("Director: ");
        directorData = new JTextArea("_____________________");
        directorPane.add(director);
        directorPane.add(directorData);
        JPanel genrePane = new JPanel();
        JLabel genre = new JLabel("Genre: ");
        genreData = new JTextArea("_____________________");
        genrePane.add(genre);
        genrePane.add(genreData);
        JPanel durationPane = new JPanel();
        JLabel duration = new JLabel("Duration: ");
        durationData = new JTextArea("_____________________");
        durationPane.add(duration);
        durationPane.add(durationData);
        JPanel agePane = new JPanel();
        JLabel age = new JLabel("Age restrictions: ");
        ageData = new JTextArea("_____________________");
        agePane.add(age);
        agePane.add(ageData);
        movieDetails.add(titlePane);
        movieDetails.add(premierePane);
        movieDetails.add(directorPane);
        movieDetails.add(genrePane);
        movieDetails.add(durationPane);
        movieDetails.add(agePane);
        movieTools.add(details);
        movieTools.add(movieDetails);

        JPanel buttonPane = new JPanel();
        JButton show = new JButton("Show seances");
        JButton add = new JButton("Add new movie");
        JButton edit = new JButton("Edit");
        JButton remove = new JButton("Remove");
        buttonPane.add(show);
        buttonPane.add(edit);
        buttonPane.add(remove);
        buttonPane.add(add);
        movieTools.add(buttonPane);
        mainPanel.add(movieTools);
        getContentPane().add(mainPanel);


        movieTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                showSelected();
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editMovie();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMovie();
            }
        });

        presenter.fillTheTable();
    }

    protected void fillTheTable() {
        List<Movie> movieList = movieDAO.getAllTheMovies();
        for (Movie m : movieList) {
            String[] movieData = {String.valueOf(m.getId()), m.getTitle(), String.valueOf(m.getGenre())};
            System.out.println(m.getTitle());
            model.addRow(movieData);
        }
    }

    private void showSelected() {
            Movie movie = getSelectedMovie();
            titleData.setText(movie.getTitle());
            premiereData.setText(String.valueOf(movie.getYear()));
            directorData.setText(movie.getDirector());
            genreData.setText(movie.getGenre().toString());
            durationData.setText(String.valueOf(movie.getDuration()));
            ageData.setText(movie.getAgeCategory().toString());
    }

    private void editMovie() {
            Movie movie = getSelectedMovie();
            EditFrame editFrame = new EditFrame(movie);
            editFrame.setVisible(true);
    }

    private void addMovie(){
        AddMovieFrame addMovieFrame = new AddMovieFrame();
        addMovieFrame.setVisible(true);
    }

    public Movie getSelectedMovie() {
        Movie movie = null;
        if (movieTable.getSelectedRow() > -1) {
            int id = Integer.parseInt((String) movieTable.getModel().getValueAt(movieTable.getSelectedRow(), 0));
            movie = movieDAO.getMovie(id);
        }
        return movie;
    }

    public static MyTableModel getModel() {
        return model;
    }

    public  void setModel(MyTableModel model) {
        this.model = model;
    }

    public  MovieDAO getMovieDAO() {
        return movieDAO;
    }

    public void setMovieDAO(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    public MoviePresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(MoviePresenter presenter) {
        this.presenter = presenter;
    }

    public JTable getMovieTable() {
        return movieTable;
    }

    public void setMovieTable(JTable movieTable) {
        this.movieTable = movieTable;
    }

    public JTextArea getTitleData() {
        return titleData;
    }

    public void setTitleData(JTextArea titleData) {
        this.titleData = titleData;
    }

    public JTextArea getPremiereData() {
        return premiereData;
    }

    public void setPremiereData(JTextArea premiereData) {
        this.premiereData = premiereData;
    }

    public JTextArea getDirectorData() {
        return directorData;
    }

    public void setDirectorData(JTextArea directorData) {
        this.directorData = directorData;
    }

    public JTextArea getGenreData() {
        return genreData;
    }

    public void setGenreData(JTextArea genreData) {
        this.genreData = genreData;
    }

    public JTextArea getDurationData() {
        return durationData;
    }

    public void setDurationData(JTextArea durationData) {
        this.durationData = durationData;
    }

    public JTextArea getAgeData() {
        return ageData;
    }

    public void setAgeData(JTextArea ageData) {
        this.ageData = ageData;
    }

}

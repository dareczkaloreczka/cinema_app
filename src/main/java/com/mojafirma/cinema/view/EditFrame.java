package com.mojafirma.cinema.view;

import com.mojafirma.cinema.model.Movie;
import com.mojafirma.cinema.model.MovieCategory;
import com.mojafirma.cinema.model.MovieGenre;
import com.mojafirma.cinema.model.dao.MovieDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Set;

 class EditFrame extends JFrame {

     ApplicationContext context = new AnnotationConfigApplicationContext(ViewConfig.class);
    JTextArea titleData;
    JTextArea premiereData;
    JTextArea directorData;
    JComboBox<MovieGenre> genreBox;
    JTextArea durationData;
    JComboBox<MovieGenre> categoryBox;

    public EditFrame(Movie movie) throws HeadlessException {
        init(movie);
    }

    private void init(Movie movie) {
        setTitle("Edit movie");
        setSize(500, 250);
        JPanel mainPanel = new JPanel();
        JPanel movieDetails = new JPanel();
        movieDetails.setLayout(new GridLayout(3, 2));
        JPanel titlePane = new JPanel();
        JLabel title = new JLabel("Tittle: ");
        titleData = new JTextArea(movie.getTitle());
        titlePane.add(title);
        titlePane.add(titleData);
        JPanel premierePane = new JPanel();
        JLabel premiere = new JLabel("Premiere: ");
        premiereData = new JTextArea(movie.getYear().toString());
        premierePane.add(premiere);
        premierePane.add(premiereData);
        JPanel directorPane = new JPanel();
        JLabel director = new JLabel("Director: ");
        directorData = new JTextArea(movie.getDirector());
        directorPane.add(director);
        directorPane.add(directorData);
        JPanel genrePane = new JPanel();
        JLabel genre = new JLabel("Genre: ");
        Set<MovieGenre> genreSet = MovieGenre.getGenres();
        genreBox = new JComboBox(genreSet.toArray());
        genrePane.add(genre);
        genrePane.add(genreBox);
        JPanel durationPane = new JPanel();
        JLabel duration = new JLabel("Duration: ");
        durationData = new JTextArea(String.valueOf(movie.getDuration()));
        durationPane.add(duration);
        durationPane.add(durationData);
        JPanel agePane = new JPanel();
        JLabel age = new JLabel("Age restrictions: ");
        Set<MovieCategory> categorySet = MovieCategory.getCategories();
        categoryBox = new JComboBox(categorySet.toArray());
        agePane.add(age);
        agePane.add(categoryBox);
        movieDetails.add(titlePane);
        movieDetails.add(premierePane);
        movieDetails.add(directorPane);
        movieDetails.add(genrePane);
        movieDetails.add(durationPane);
        movieDetails.add(agePane);
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        JPanel buttonPane = new JPanel();
        buttonPane.add(save);
        buttonPane.add(cancel);
        mainPanel.add(movieDetails);
        mainPanel.add(buttonPane);
        getContentPane().add(mainPanel);



        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges(movie);
                setVisible(false);
            }
        });


    }

    private void saveChanges(Movie movie){
        MovieDAO movieDAO = new MovieDAO();
        movie.setTitle(titleData.getText());
        movie.setYear(Date.valueOf(premiereData.getText()));
        movie.setDirector(directorData.getText());
        movie.setGenre((MovieGenre) genreBox.getSelectedItem());
        movie.setDuration(Integer.parseInt(durationData.getText()));
        movie.setAgeCategory((MovieCategory) categoryBox.getSelectedItem());
        movieDAO.updateMovie(movie);
        MainFrame mainFrame = context.getBean("mainFrame", MainFrame.class);
        mainFrame.getModel().setRowCount(0);
        mainFrame.fillTheTable();


    }


}

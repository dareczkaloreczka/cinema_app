package com.mojafirma.cinema.view;

import com.mojafirma.cinema.model.Movie;
import com.mojafirma.cinema.model.MovieCategory;
import com.mojafirma.cinema.model.MovieGenre;
import com.mojafirma.cinema.presenter.MoviePresenter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
 public class EditFrame extends JDialog {

     public static EditFrame editFrame;

    JTextArea titleData;
    JTextArea premiereData;
    JTextArea directorData;
    JComboBox<MovieGenre> genreBox;
    JTextArea durationData;
    JComboBox<MovieGenre> categoryBox;
    MainFrame mainFrame;
    MoviePresenter presenter = new MoviePresenter();

    public EditFrame(Movie movie) throws HeadlessException {
        init(movie);
        editFrame = this;
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
                presenter.editMovie(movie);
                presenter.updateView();
                setVisible(false);
            }
        });


    }

    public static EditFrame getEditFrame() {
        return editFrame;
    }

    public static void setEditFrame(EditFrame editFrame) {
        EditFrame.editFrame = editFrame;
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

    public JComboBox<MovieGenre> getGenreBox() {
        return genreBox;
    }

    public void setGenreBox(JComboBox<MovieGenre> genreBox) {
        this.genreBox = genreBox;
    }

    public JTextArea getDurationData() {
        return durationData;
    }

    public void setDurationData(JTextArea durationData) {
        this.durationData = durationData;
    }

    public JComboBox<MovieGenre> getCategoryBox() {
        return categoryBox;
    }

    public void setCategoryBox(JComboBox<MovieGenre> categoryBox) {
        this.categoryBox = categoryBox;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}

package com.mojafirma.cinema.view;

import com.mojafirma.cinema.presenter.MoviePresenter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private MoviePresenter presenter = new MoviePresenter();
    public static MainFrame frame;

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
        JMenuItem showRepertoire = new JMenuItem("Show Repertoire");
        JMenuItem addNewReservation = new JMenuItem("Create new..");
        JMenuItem manageReservations = new JMenuItem("Manage reservations");
        repertoire.add(showRepertoire);
        reservations.add(addNewReservation);
        reservations.add(manageReservations);
        menuBar.add(repertoire);
        menuBar.add(reservations);
        getContentPane().add(BorderLayout.NORTH, menuBar);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        TablePanel tablePanel = new TablePanel();
        mainPanel.add(tablePanel);
        DetailsPanel detailsPanel = new DetailsPanel();
        mainPanel.add(detailsPanel);
        getContentPane().add(mainPanel);

        showRepertoire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RepertoireFrame repertoireFrame = new RepertoireFrame();
                repertoireFrame.setVisible(true);
            }
        });

        presenter.fillTheTable();
    }

    public MoviePresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(MoviePresenter presenter) {
        this.presenter = presenter;
    }

}

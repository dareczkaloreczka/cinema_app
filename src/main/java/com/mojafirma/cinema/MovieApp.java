package com.mojafirma.cinema;

import com.mojafirma.cinema.model.Movie;
import com.mojafirma.cinema.model.dao.MovieDAO;
import com.mojafirma.cinema.view.MainFrame;
import com.mojafirma.cinema.view.ViewConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.sql.Date;

public class MovieApp {

    public static void main(String[] args) {



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplicationContext context = new AnnotationConfigApplicationContext(ViewConfig.class);
                MainFrame mainFrame = context.getBean("mainFrame", MainFrame.class);
                mainFrame.setVisible(true);
            }
        });



    }
}

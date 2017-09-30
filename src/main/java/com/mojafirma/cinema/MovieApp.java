package com.mojafirma.cinema;

import com.mojafirma.cinema.presenter.MoviePresenter;
import com.mojafirma.cinema.view.MainFrame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.applet.Main;

import javax.swing.*;

public class MovieApp {

    public static void main(String[] args) {



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
               context.getBean("mainFrame", MainFrame.class).setVisible(true);
            }
        });



    }
}

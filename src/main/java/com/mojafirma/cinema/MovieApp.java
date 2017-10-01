package com.mojafirma.cinema;

import com.mojafirma.cinema.view.MainFrame;
import javax.swing.*;

public class MovieApp {

    public static void main(String[] args) {



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               MainFrame mainFrame = new MainFrame();
               mainFrame.setVisible(true);
            }
        });



    }
}

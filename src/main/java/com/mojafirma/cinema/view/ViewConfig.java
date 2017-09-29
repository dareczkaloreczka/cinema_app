package com.mojafirma.cinema.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewConfig {

    @Bean
    public MainFrame mainFrame(){
        return new MainFrame();
    }
}

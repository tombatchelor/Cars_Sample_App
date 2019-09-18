/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.prometheus;

import io.prometheus.client.hotspot.DefaultExports;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author tombatchelor
 */
public class Init implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        DefaultExports.initialize();
        System.out.println("Done default Prometheus exports");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        return;
    }
}

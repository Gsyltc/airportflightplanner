/* @(#)Starter.java
 *
 * Copyright (c) 2016 Sylvain Goubaud. All rights reserved.
 */
package com.airportflightplanner.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.airportflightplanner.main.visualelements.MainPanel;

/**
 *
 * @author Goubaud Sylvain
 *
 */
public class Starter {
    /**
     *
     */
    private static final String                   MAIN_TITLE = "Airport Flight Planner";
    /** */
    private static ClassPathXmlApplicationContext context    = new ClassPathXmlApplicationContext("./config/imports.xml");

    /**
     * Start Application
     *
     * @param args
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    JFrame mainFrame = new JFrame(MAIN_TITLE);
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mainFrame.getContentPane().add(new MainPanel(), BorderLayout.CENTER);
                    mainFrame.setMinimumSize(new Dimension(1024, 768));
                    // mainFrame.pack();
                    mainFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}

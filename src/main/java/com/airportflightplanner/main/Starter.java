/* @(#)Starter.java
 *
 * Copyright (c) 2016 Sylvain Goubaud. All rights reserved.
 */
package com.airportflightplanner.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.airportflightplanner.common.model.FlighPlanCollectionModel;
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
    private static final String                   MAIN_TITLE     = "Airport Flight Planner";
    /** */
    protected static ClassPathXmlApplicationContext context;
    /** */
    private static final int                      MINIMUM_WEIGHT = 1024;
    /** */
    private static final int                      MINIMUM_HEIGHT = 920;
    /** The logger of this class. */
    protected static final Log                      LOGGER         = LogFactory.getLog(Starter.class);

    /**
     *
     */
    private Starter() {
        //
    }

    /**
     * Start Application.
     *
     * @param args
     *            .
     */
    public static void main(final String[] args) {
        context = new ClassPathXmlApplicationContext("./config/imports.xml");

        EventQueue.invokeLater(new Runnable() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void run() {
                try {
                    if (context.getBean("id-FlightPlansCollection") instanceof FlighPlanCollectionModel) {
                        final FlighPlanCollectionModel model = (FlighPlanCollectionModel) getContext().getBean("id-FlightPlansCollection");
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        final JMenuBar menu = new JMenuBar();
                        menu.add(new JMenu("Fichiers"));
                        menu.add(new JMenu("Configuration"));
                        menu.add(new JMenu("Help"));
                        final JFrame mainFrame = new JFrame(MAIN_TITLE);
                        mainFrame.setJMenuBar(menu);
                        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mainFrame.getContentPane().add(new MainPanel(model), BorderLayout.CENTER);
                        mainFrame.setMinimumSize(new Dimension(MINIMUM_WEIGHT, MINIMUM_HEIGHT));
                        mainFrame.pack();
                        mainFrame.setVisible(true);
                    }

                } catch (final UnsupportedLookAndFeelException | //
                        ClassNotFoundException | //
                        InstantiationException | //
                        IllegalAccessException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("Look and feel error : ", e);
                    }
                }
            }
        });

    }

    /**
     *
     * @return application context.
     */
    public static ClassPathXmlApplicationContext getContext() {
        return context;
    }
}

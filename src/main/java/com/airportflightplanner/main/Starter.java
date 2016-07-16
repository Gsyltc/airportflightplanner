/*
 * @(#)Starter.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.airportflightplanner.common.api.adapter.CommonAdapter;
import com.airportflightplanner.common.models.FlighPlanCollectionModel;
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
    private static final String                           MAIN_TITLE     = "Airport Flight Planner";
    /** */
    protected final static ClassPathXmlApplicationContext CONTEXT        = new ClassPathXmlApplicationContext("./config/imports.xml");
    /** */
    private static final int                              MINIMUM_WEIGHT = 1024;
    /** */
    private static final int                              MINIMUM_HEIGHT = 920;
    /** The logger of this class. */
    private static final Log                              LOGGER         = LogFactory.getLog(Starter.class);

    /**
     * Start Application.
     *
     * @param args
     *            .
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
                    if (CONTEXT.getBean("id-FlightPlansCollection") instanceof FlighPlanCollectionModel) {
                        final FlighPlanCollectionModel model = (FlighPlanCollectionModel) CONTEXT.getBean("id-FlightPlansCollection");
                        final Map<String, CommonAdapter> adapters = (Map<String, CommonAdapter>) CONTEXT.getBean("id-Adapters");
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        final JMenuBar menu = new JMenuBar();
                        menu.add(new JMenu("Fichiers"));
                        menu.add(new JMenu("Configuration"));
                        menu.add(new JMenu("Help"));
                        final JFrame mainFrame = new JFrame(MAIN_TITLE);
                        mainFrame.setJMenuBar(menu);
                        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mainFrame.getContentPane().add(new MainPanel(model, adapters), BorderLayout.CENTER);
                        mainFrame.setMinimumSize(new Dimension(MINIMUM_WEIGHT, MINIMUM_HEIGHT));
                        mainFrame.pack();
                        mainFrame.setVisible(true);
                    }

                } catch (final UnsupportedLookAndFeelException | //
                        ClassNotFoundException | //
                        InstantiationException | //
                        IllegalAccessException e) {
                    if (getLogger().isErrorEnabled()) {
                        getLogger().error("Look and feel error : ", e);
                    }
                }
            }
        });

    }

    /**
     * @return the logger
     */
    protected static Log getLogger() {
        return LOGGER;
    }
}

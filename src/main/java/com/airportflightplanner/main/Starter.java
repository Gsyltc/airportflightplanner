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
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.main.visualelements.MainPanel;

import fr.gsyltc.framework.lifecycle.LifeCycleManager;

/**
 *
 * @author Goubaud Sylvain
 *
 */
public class Starter {
    
    
    /**
     *
     */
    private static final String MAIN_TITLE = "Airport Flight Planner";
    // /** */
    // protected final static ClassPathXmlApplicationContext CONTEXT = new
    // ClassPathXmlApplicationContext("./config/imports.xml");
    /** The logger of this class. */
    private static final Log LOGGER = LogFactory.getLog(Starter.class);

    /**
     * Start Application.
     *
     * @param args
     *            .
     */
    public static void main(final String... args) {
        // Load spring configuration
        LifeCycleManager.initApplication();

        EventQueue.invokeLater(new Runnable() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    final JMenuBar menu = new JMenuBar();
                    menu.add(new JMenu("Fichiers"));
                    menu.add(new JMenu("Configuration"));
                    menu.add(new JMenu("Help"));
                    final JFrame mainFrame = new JFrame(MAIN_TITLE);
                    mainFrame.setJMenuBar(menu);
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    mainFrame.getContentPane().add(new MainPanel(), BorderLayout.CENTER);
                    mainFrame.pack();
                    mainFrame.setMinimumSize(mainFrame.getPreferredSize());
                    mainFrame.setVisible(true);

                    LifeCycleManager.registerSlots();

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

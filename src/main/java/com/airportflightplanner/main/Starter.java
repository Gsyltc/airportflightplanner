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
  private static final String                   MAIN_TITLE = "Airport Flight Planner";
  /** */
  private static ClassPathXmlApplicationContext context    = new ClassPathXmlApplicationContext(
      "./config/imports.xml");

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
          if (getContext().getBean(
              "id-FlightPlansCollection") instanceof FlighPlanCollectionModel) {
            FlighPlanCollectionModel model = (FlighPlanCollectionModel) getContext().getBean(
                "id-FlightPlansCollection");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JMenuBar menu = new JMenuBar();
            menu.add(new JMenu("Fichiers"));
            menu.add(new JMenu("Configuration"));
            menu.add(new JMenu("Help"));
            JFrame mainFrame = new JFrame(MAIN_TITLE);
            mainFrame.setJMenuBar(menu);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.getContentPane().add(new MainPanel(model), BorderLayout.CENTER);
            mainFrame.setMinimumSize(new Dimension(1024, 920));
            mainFrame.pack();
            mainFrame.setVisible(true);
          }

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

  }

  /**
   *
   * @return
   */
  public static ClassPathXmlApplicationContext getContext() {
    return context;
  }
}

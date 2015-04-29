package com.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.model.MemoryManagerModel;

/**
 * Deallocate Memory
 *
 * @author Tyler Andjel
 *
 */
public class DeallocateDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    JLabel lID;
    JTextField tID;
    JButton bRemoveDone;
    JButton bRemoveMore;
    MemoryManagerModel model;

    public DeallocateDialog(JFrame frame, MemoryManagerModel model) {
        super(frame, "Deallocate Memory");
        this.model = model;
        setResizable(false);
        lID = new JLabel("Process ID: ");
        tID = new JTextField(5);
        bRemoveDone = new JButton("Deallocate & Done");
        bRemoveDone.addActionListener(this);
        bRemoveMore = new JButton("Deallocate & More");
        bRemoveMore.addActionListener(this);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        addComponents(mainPanel);
        getContentPane().add(mainPanel);
        pack();
        setVisible(true);
    }

    /**
     * Add needed components
     *
     * @param panel
     *            The panel to add components to
     */
    private void addComponents(JPanel panel) {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        p.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        p.add(lID, g);
        g.gridx++;
        g.gridy = 0;
        p.add(tID, g);
        panel.add(p);
        p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(bRemoveDone);
        p.add(bRemoveMore);
        panel.add(p);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bRemoveDone) {
            try {
                int id = Integer.parseInt(tID.getText());
                if (id < 0) {
                    JOptionPane.showMessageDialog(this,
                                                  "ID must be a positive number.", "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                } else {
                    model.deallocateMemory(id);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this,
                                              "Valid integer must be inputed.\n" + nfe.getMessage(),
                                              "Error", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        } else if (e.getSource() == bRemoveMore) {
            try {
                int id = Integer.parseInt(tID.getText());
                if (id < 0) {
                    JOptionPane.showMessageDialog(this,
                                                  "ID must be a positive number.", "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                } else {
                    model.deallocateMemory(id);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this,
                                              "Valid integer must be inputed.\n" + nfe.getMessage(),
                                              "Error", JOptionPane.ERROR_MESSAGE);
            }
            tID.setText("");
        }
    }
}

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
 * Allocate Dialog
 * 
 * @author Tyler Andjel
 *
 */
public class AllocateDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel lID;
	JLabel lSize;
	JTextField tID;
	JTextField tSize;
	JButton bAddDone;
	JButton bAddMore;
	MemoryManagerModel model;

	public AllocateDialog(JFrame frame, MemoryManagerModel model) {
		super(frame, "Allocate Memory");
		setResizable(false);
		this.model = model;
		lID = new JLabel("Process ID: ");
		lSize = new JLabel("Process Size: ");
		tID = new JTextField(5);
		tSize = new JTextField(5);
		bAddDone = new JButton("Allocate & Done");
		bAddDone.addActionListener(this);
		bAddMore = new JButton("Allocate & More");
		bAddMore.addActionListener(this);
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
		g.gridy++;
		p.add(lSize, g);
		g.gridx++;
		g.gridy = 0;
		p.add(tID, g);
		g.gridy++;
		p.add(tSize, g);
		panel.add(p);
		p = new JPanel();
		p.setLayout(new FlowLayout());
		p.add(bAddDone);
		p.add(bAddMore);
		panel.add(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bAddDone) {
			try {
				long size = Long.parseLong(tSize.getText());
				int id = Integer.parseInt(tID.getText());
				if (id < 0) {
					JOptionPane.showMessageDialog(this,
							"ID must be greater than or equal to zero",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					model.allocateMemory(size, id);
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(
						this,
						"ID and process size must be positive integers\n"
								+ nfe.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			dispose();
		} else if (e.getSource() == bAddMore) {
			try {
				long size = Long.parseLong(tSize.getText());
				int id = Integer.parseInt(tID.getText());
				if (id < 0) {
					JOptionPane.showMessageDialog(this,
							"ID must be greater than or equal to zero",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					model.allocateMemory(size, id);
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(
						this,
						"ID and process size must be positive integers\n"
								+ nfe.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			tID.setText("");
			tSize.setText("");

		}
	}
}

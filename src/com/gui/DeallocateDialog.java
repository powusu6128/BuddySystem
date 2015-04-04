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

	public DeallocateDialog(JFrame frame) {
		super(frame, "Deallocate Memory");
		setResizable(false);
		lID = new JLabel("Process ID: ");
		tID = new JTextField(5);
		bRemoveDone = new JButton("Allocate & Done");
		bRemoveDone.addActionListener(this);
		bRemoveMore = new JButton("Allocate & More");
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
			// TODO create memory. Remember to account for not enough memory and
			// id already memory.
			JOptionPane.showMessageDialog(null, "Memory Deallocated & Done");
			dispose();
		} else if (e.getSource() == bRemoveMore) {
			// TODO create memory. Remember to account for not enough memory and
			// an id already in memory.
			JOptionPane.showMessageDialog(null, "Memory Deallocated & More");
			tID.setText("");

		}
	}
}

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
			// TODO create memory. Remember to account for not enough memory and
			// id already memory.
			model.allocateMemory(Long.parseLong(tSize.getText()), Integer.parseInt(tID.getText()));
			dispose();
		} else if (e.getSource() == bAddMore) {
			// TODO create memory. Remember to account for not enough memory and
			// an id already in memory.
			model.allocateMemory(Long.parseLong(tSize.getText()), Integer.parseInt(tID.getText()));
			tID.setText("");
			tSize.setText("");

		}
	}
}

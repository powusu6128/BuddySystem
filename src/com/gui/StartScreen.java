package com.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import ImportantFunctions.Functions;

import com.model.MemoryManagerModel;

/**
 * Start Screen when Memory Manager starts for set up
 * 
 * @author Tyler Andjel
 *
 */
public class StartScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JLabel lMinSize;
	private JLabel lMaxSize;
	private JTextField tMinSize;
	private JTextField tMaxSize;
	private JRadioButton rManualInput;
	private JRadioButton rFileInput;
	private JTextField tFilePath;
	private JButton bBrowse;
	private JButton bStartMemoryManager;
	private JFileChooser fc;

	/**
	 * Constructor for StartScreen
	 */
	public StartScreen() {
		super("Memory Manager Start Up");

		setResizable(false);
		setMinimumSize(new Dimension(325, 200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setSize(150, 100);
		lMinSize = new JLabel("Min Memory Size: ");
		lMaxSize = new JLabel("Max Memory Size: ");
		tMinSize = new JTextField("1", 10);
		tMaxSize = new JTextField("64", 10);
		rManualInput = new JRadioButton("Manual Input");
		rManualInput.setSelected(true);
		rManualInput.addActionListener(this);
		rFileInput = new JRadioButton("File Input");
		rFileInput.addActionListener(this);
		tFilePath = new JTextField(20);
		tFilePath.setEnabled(false);
		bBrowse = new JButton("Open File ...");
		bBrowse.setEnabled(false);
		bBrowse.addActionListener(this);
		bStartMemoryManager = new JButton("Start Memory Manager");
		bStartMemoryManager.addActionListener(this);
		fc = new JFileChooser();
		getContentPane();
		addComponents();
		add(mainPanel, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	/**
	 * Adds all needed components
	 */
	private void addComponents() {
		GridBagConstraints g = new GridBagConstraints();
		// Top
		g.gridx = 0;
		g.gridy = 0;
		JPanel p = new JPanel(new GridBagLayout());
		p.add(lMinSize, g);
		g.gridy++;
		p.add(lMaxSize, g);
		g.gridx++;
		g.gridy = 0;
		p.add(tMinSize, g);
		g.gridy++;
		p.add(tMaxSize, g);
		mainPanel.add(p);
		// Input Types
		p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		ButtonGroup group = new ButtonGroup();
		group.add(rManualInput);
		group.add(rFileInput);
		g.gridx = 0;
		g.gridy = 0;
		p.add(rManualInput, g);
		g.gridy++;
		p.add(rFileInput, g);
		g.gridy++;
		g.gridwidth = GridBagConstraints.REMAINDER;
		p.add(tFilePath, g);
		g.gridx = 1;
		g.gridy = 1;
		p.add(bBrowse, g);
		mainPanel.add(p);
		// Bottom Button
		p = new JPanel();
		p.add(bStartMemoryManager);
		mainPanel.add(p);
	}

	@Override
	/**
	 * Overrides Action Performed
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rFileInput) {
			tFilePath.setEnabled(rFileInput.isSelected());
			tFilePath.setText("");
			bBrowse.setEnabled(rFileInput.isSelected());
		} else if (e.getSource() == rManualInput) {
			tFilePath.setEnabled(rFileInput.isSelected());
			tFilePath.setText("");
			bBrowse.setEnabled(rFileInput.isSelected());
		} else if (e.getSource() == bBrowse) {
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				tFilePath.setText(fc.getSelectedFile().getAbsolutePath());
			}
		} else if (e.getSource() == bStartMemoryManager) {
			try {
				if (Functions.getLongValue(tMinSize) > Functions
						.getLongValue(tMaxSize)) {
					JOptionPane
							.showMessageDialog(mainPanel,
									"Min text box value must be less than max text box value");
				} else {
					try {
						new MainObserver(new MemoryManagerModel(
								Functions.getLongValue(tMinSize),
								Functions.getLongValue(tMaxSize)),
								rManualInput.isSelected() ? true : false,
								tFilePath.getText());
						dispose();
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(mainPanel,
								"Only valid input is an integer that is a power of 2.\n"
										+ ex.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(
						this,
						"Memory size must be integer power of 2 value\n"
								+ nfe.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
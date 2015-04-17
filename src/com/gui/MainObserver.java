package com.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import com.model.MemoryManagerModel;

public class MainObserver extends JFrame implements Observer, ActionListener {

	private static final long serialVersionUID = 1L;
	private MemoryManagerModel model;
	private boolean isManual;
	private String sFile;
	private JLabel tMode;
	private JPanel mainPanel;
	private JPanel visualSide;
	private JPanel logSide;
	private JSplitPane splitPlane;
	private JMenuBar menuBar;
	private JMenu mFile;
	private JMenu mMode;
	private JMenu mHelp;
	private JMenuItem iAbout;
	private JMenuItem iAllocate;
	private JMenuItem iDeallocate;
	private JMenuItem iRestart;
	private JMenuItem iExit;
	private JRadioButtonMenuItem iManualMode;
	private JRadioButtonMenuItem iFileMode;
	private JTextArea tLogArea;
	private JButton bAllocate;
	private JButton bDeallocate;
	private JButton bMode;
	private JProgressBar memoryUsage;

	@Override
	public void update(Observable arg0, Object arg1) {
		// Will be called on allocate() or deallocate()
		// We will loop through the model's memoryBlocks and re-draw the screen
		if (arg1.toString().startsWith("Progress"))
		{
			String[] splited = arg1.toString().split(" ");
			int memoryInUse = Integer.parseInt(splited[2]);
			memoryUsage.setValue(memoryInUse);
		}
		else
		{
			tLogArea.append(arg1.toString() + "\n");
		}
	}

	public MainObserver(MemoryManagerModel model, boolean isManual, String file) {
		super("Memory Manager");
		setResizable(false);
		setMinimumSize(new Dimension(1000, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tMode = new JLabel();
		this.model = model;
		model.addObserver(this);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		visualSide = new JPanel();
		logSide = new JPanel();
		splitPlane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		menuBar = new JMenuBar();
		mFile = new JMenu("File");
		iAllocate = new JMenuItem("Allocate");
		iDeallocate = new JMenuItem("Deallocate");
		iRestart = new JMenuItem("Restart");
		iExit = new JMenuItem("Exit");
		mMode = new JMenu("Mode");
		mHelp = new JMenu("Help");
		iAbout = new JMenuItem("About");
		iFileMode = new JRadioButtonMenuItem("File");
		iManualMode = new JRadioButtonMenuItem("Manual");
		tLogArea = new JTextArea(30, 25);
		bAllocate = new JButton("Allocate");
		bAllocate.addActionListener(this);
		bDeallocate = new JButton("Deallocate");
		bDeallocate.addActionListener(this);
		bMode = new JButton("Mode");
		bMode.addActionListener(this);
		memoryUsage = new JProgressBar((int) model.getMinMemorySize(),
				(int) model.getMaxMemorySize());
		memoryUsage.setStringPainted(true);
		if (isManual) {
			this.isManual = true;
			this.sFile = "";
			tMode.setText("Manual Input");
			iManualMode.setSelected(true);
		} else {
			tMode.setText("File Input");
			this.isManual = false;
			this.sFile = file;
			bAllocate.setEnabled(false);
			bDeallocate.setEnabled(false);
			iAllocate.setEnabled(false);
			iDeallocate.setEnabled(false);
			iFileMode.setSelected(true);
		}
		setUpMenuBar();
		getContentPane();
		addLogComponents();
		addVisualComponents();
		splitPlane.setRightComponent(visualSide);
		splitPlane.setLeftComponent(logSide);
		splitPlane.setDividerSize(0);
		mainPanel.add(splitPlane, BorderLayout.CENTER);
		mainPanel.add(menuBar, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		pack();
		setVisible(true);
		getGraphics().drawRect(500, 500, 500, 500);

	}

	/**
	 * Menu bar setup
	 */
	private void setUpMenuBar() {
		iAllocate.addActionListener(this);
		iAllocate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				Event.CTRL_MASK));
		iDeallocate.addActionListener(this);
		iDeallocate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				Event.CTRL_MASK));
		iRestart.addActionListener(this);
		iRestart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				Event.CTRL_MASK));
		iExit.addActionListener(this);
		iExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				Event.CTRL_MASK));
		iManualMode.addActionListener(this);
		iManualMode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				Event.CTRL_MASK));
		iFileMode.addActionListener(this);
		iFileMode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				Event.CTRL_MASK));
		iAbout.addActionListener(this);
		iAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				Event.CTRL_MASK));
		ButtonGroup group = new ButtonGroup();
		group.add(iFileMode);
		group.add(iManualMode);
		mFile.add(iAllocate);
		mFile.add(iDeallocate);
		mFile.addSeparator();
		mFile.add(iRestart);
		mFile.add(iExit);
		mMode.add(iManualMode);
		mMode.add(iFileMode);
		mHelp.add(iAbout);
		menuBar.add(mFile);
		menuBar.add(mMode);
		menuBar.add(mHelp);
	}

	/**
	 * Setup for the log side (left side)
	 */
	private void addLogComponents() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Log", TitledBorder.CENTER,
				TitledBorder.TOP));
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		tLogArea.setEditable(false);
		tLogArea.setWrapStyleWord(true);
		DefaultCaret caret = (DefaultCaret) tLogArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		p.add(new JScrollPane(tLogArea));
		logSide.add(p);
	}

	/**
	 * Set up visual components (right side)
	 */
	private void addVisualComponents() {
		JPanel p = new JPanel();
		visualSide.setLayout(new BorderLayout());
		p.setLayout(new FlowLayout());
		p.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		p.add(tMode);
		visualSide.add(p, BorderLayout.NORTH);
		p = new JPanel();
		p.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		// TODO Add more visuals
		p.add(new JLabel("Memory Usage"));
		p.add(memoryUsage);
		visualSide.add(p, BorderLayout.CENTER);
		p = new JPanel();
		GridBagConstraints g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 0;
		p.setLayout(new GridBagLayout());
		p.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		p.add(bAllocate, g);
		g.gridx++;
		p.add(bDeallocate, g);
		g.gridx = 0;
		g.gridy++;
		g.gridwidth = 2;
		p.add(bMode, g);
		visualSide.add(p, BorderLayout.SOUTH);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bAllocate || e.getSource() == iAllocate) {
			new AllocateDialog(new JFrame(), model);
		} else if (e.getSource() == bDeallocate || e.getSource() == iDeallocate) {
			new DeallocateDialog(new JFrame(), model);
		} else if (e.getSource() == iRestart) {
			new StartScreen();
			dispose();
		} else if (e.getSource() == iExit) {
			dispose();
		} else if (e.getSource() == iManualMode) {
			tMode.setText("Manual Mode");
			bAllocate.setEnabled(true);
			bDeallocate.setEnabled(true);
			isManual = true;
		} else if (e.getSource() == iFileMode) {
			tMode.setText("File Mode");
			bAllocate.setEnabled(false);
			bDeallocate.setEnabled(false);
			isManual = false;
		} else if (e.getSource() == bMode) {
			if (isManual) {
				actionPerformed(new ActionEvent(iFileMode,
						ActionEvent.ACTION_PERFORMED, ""));
			} else {
				actionPerformed(new ActionEvent(iManualMode,
						ActionEvent.ACTION_PERFORMED, ""));
			}
		} else if (e.getSource() == iAbout) {
			JOptionPane
					.showMessageDialog(
							mainPanel,
							"Rowan University Binary Memory Manager Operating Systems Project:\n"
									+ "Created By: Tyler Andjel, Edward Carter, Justin Hyland, Ryan Smith\n"
									+ "Dr. Baliga OS Class", "About",
							JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

package com.gui;

import com.model.Driver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

import com.model.BlockOMemory;
import com.model.MemoryManagerModel;

import javax.swing.Timer;

public class MainObserver extends JFrame implements Observer, ActionListener {

    private static final long serialVersionUID = 1L;
    private MemoryManagerModel model;
    private boolean isManual;
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
    private JRadioButtonMenuItem iAutoMode;
    private JTextArea tLogArea;
    private JButton bAllocate;
    private JButton bDeallocate;
    private JButton bMode;
    private JProgressBar memoryUsage;
    private MemoryPanel memPanel;
    private Timer timer;
    private JSlider slider;
    private static int START_DELAY = 250; //250 ms

    /**
     *
     * Drawing Panel for memory
     *
     */
    class MemoryPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private static final int WIDTH = 1000/* 1160 */;
        private static final int HEIGHT = 400;
        private int blockWidth = WIDTH / (int) model.getMaxMemorySize();

        public MemoryPanel() {

        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            int left = 0;
            for (BlockOMemory m : model.getMemoryBlocks()) {
                if (m.isProcess()) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(left, 10, (int) (blockWidth * m.getMemorySize()),
                        HEIGHT);
                g.setColor(Color.black);
                g.setFont(new Font("Arial", 0, 9));
                g.drawString("ID = " + m.getProcessID(), left, 10);
                left = left + (int) (blockWidth * m.getMemorySize());
            }
        }
    }

    /** Called upon update, i.e. when an allocation or deallocation is performed
     * (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable arg0, Object arg1) {
		// Will be called on allocate() or deallocate()
        // We will loop through the model's memoryBlocks and re-draw the screen
        if (arg1.toString().startsWith("Progress")) {
            String[] splited = arg1.toString().split(" ");
            int memoryInUse = Integer.parseInt(splited[2]);
            memoryUsage.setValue(memoryInUse);
        } else {
            tLogArea.append(arg1.toString() + "\n");
        }
        memPanel.repaint();
    }

    /**
     * Creates a new window (JFrame) that observes the allocation and deallocation requests
     * @param model The model serving the allocation and deallocation requests
     * @param isManual To use manual mode or automatic mode
     * @throws InterruptedException
     */
    public MainObserver(MemoryManagerModel model, boolean isManual) throws InterruptedException {
        super("Memory Manager");
        setResizable(true);
        setMinimumSize(new Dimension(1300, 350));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tMode = new JLabel();
        this.model = model;
        model.addObserver(this);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        memPanel = new MemoryPanel();
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
        iAutoMode = new JRadioButtonMenuItem("Simulate");
        iManualMode = new JRadioButtonMenuItem("Manual");
        tLogArea = new JTextArea(30, 25);
        bAllocate = new JButton("Allocate");
        bAllocate.addActionListener(this);
        bDeallocate = new JButton("Deallocate");
        bDeallocate.addActionListener(this);
        bMode = new JButton("Mode");
        bMode.addActionListener(this);
        // progress bar is a percentage between 0 and max memory size
        memoryUsage = new JProgressBar(0, (int) model.getMaxMemorySize());
        memoryUsage.setString("Memory Usage");
        memoryUsage.setStringPainted(true);
        if (isManual) {
            this.isManual = true;
            tMode.setText("Manual Input");
            iManualMode.setSelected(true);

        } else {
            tMode.setText("Auto Input");
            this.isManual = false;
            bAllocate.setEnabled(false);
            bDeallocate.setEnabled(false);
            iAllocate.setEnabled(false);
            iDeallocate.setEnabled(false);
            iAutoMode.setSelected(true);

                 
           

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
        
        final Driver driver = new Driver(model);
        timer = new Timer(START_DELAY, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                driver.drive();

            }
        });
        if (!isManual)
            timer.start();

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
        iAutoMode.addActionListener(this);
        iAutoMode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                Event.CTRL_MASK));
        iAbout.addActionListener(this);
        iAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
                Event.CTRL_MASK));
        ButtonGroup group = new ButtonGroup();
        group.add(iAutoMode);
        group.add(iManualMode);
        mFile.add(iAllocate);
        mFile.add(iDeallocate);
        mFile.addSeparator();
        mFile.add(iRestart);
        mFile.add(iExit);
        mMode.add(iManualMode);
        mMode.add(iAutoMode);
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
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        p.add(memoryUsage);
        p.add(memPanel);
		// JPanel panel = new JPanel();
        // panel.add(memPanel);
        // p.add(panel);
        visualSide.add(p, BorderLayout.CENTER);

        // Bottom stuff
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        slider = new JSlider(75, 1000);
        slider.setMinorTickSpacing(50);
        slider.setMajorTickSpacing(200);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setValue(START_DELAY);
        slider.setPreferredSize(new Dimension(325, 50));
        slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0)
			{
        		timer.setDelay(slider.getValue());
			}
        });
        JPanel t = new JPanel(new FlowLayout()); //Create new panel to place jlabel and slide side by side
        t.add(new JLabel("<html>Delay <i>(ms)</i></html>"));
        t.add(slider);
        p.add(t);
        
        JPanel buttons = new JPanel();
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        buttons.setLayout(new GridBagLayout());
        buttons.add(bAllocate, g);
        g.gridx++;
        buttons.add(bDeallocate, g);
        g.gridx = 0;
        g.gridy++;
        g.gridwidth = 2;
        buttons.add(bMode, g);
        // visualSide.add(memPanel, BorderLayout.CENTER);
        
       p.add(buttons);
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
            timer.stop();
            slider.setEnabled(false);
        } else if (e.getSource() == iAutoMode) {
            tMode.setText("Auto Mode");
            bAllocate.setEnabled(false);
            bDeallocate.setEnabled(false);
            isManual = false;
            timer.start();
            slider.setEnabled(true);
        } else if (e.getSource() == bMode) {
            if (isManual) {
                actionPerformed(new ActionEvent(iAutoMode,
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

package com.SFEM.TestSimulator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SFEMTestSimulator {

	private static final Object constraints = null;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SFEMTestSimulator window = new SFEMTestSimulator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SFEMTestSimulator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(200, 200, 650, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane_1, BorderLayout.CENTER);
		
		JPanel StationPanel = new JPanel();
		tabbedPane_1.addTab("Station", null, StationPanel, null);
		
		JButton btnSimulate = new JButton("Simulate");
		
		
		Vector<String> ckckControllerValues = new Vector<String>();
		ckckControllerValues.addElement("None");
		ckckControllerValues.addElement("HW_F0001");
		ckckControllerValues.addElement("HW_F0002");
		ckckControllerValues.addElement("HW_F0003");
		ckckControllerValues.addElement("HW_F0004");
		ckckControllerValues.addElement("HW_F0005");
		ckckControllerValues.addElement("HW_S0001");
		ckckControllerValues.addElement("HW_S0002");
		ckckControllerValues.addElement("HW_S0003");
		ckckControllerValues.addElement("HW_S0004");
		ckckControllerValues.addElement("HW_S0005");
		ckckControllerValues.addElement("HW_S0006");
		ckckControllerValues.addElement("HW_S0007");
		ckckControllerValues.addElement("HW_S0008");
		
		
		JComboBox comboBoxControllers = new JComboBox(ckckControllerValues);
		StationPanel.add(comboBoxControllers);
		StationPanel.add(btnSimulate);
		
		JButton btnResolve = new JButton("Resolve");
		StationPanel.add(btnResolve);
		
		SFEMController cntrllr = new SFEMController();
		
		btnSimulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object CntrllrValue = comboBoxControllers.getSelectedItem();
				cntrllr.SendFaultyController(CntrllrValue.toString());
			}
		});
		
		btnResolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object CntrllrValue = comboBoxControllers.getSelectedItem();
				cntrllr.SendResolvedController(CntrllrValue.toString());
			}
		});
		
		JPanel EnergyManagerPanel = new JPanel();
		tabbedPane_1.addTab("EnergyManager", null, EnergyManagerPanel, null);
		
		JDateChooser dateChoser = new JDateChooser();

		EnergyManagerPanel.add(dateChoser);
		
		JButton btnImport = new JButton("Import");
		EnergyManagerPanel.add(btnImport);
		
		Vector<String> listData = new Vector<String>();
		listData.addElement("None");
		listData.addElement("Small Fan");
		listData.addElement("Hair Dryer");
		listData.addElement("Halogen Lamp");
		listData.addElement("Computer");
		listData.addElement("Printer");
		listData.addElement("Incandescnet light");
		listData.addElement("Television");
		listData.addElement("Large lamp");
		listData.addElement("Stereo System");
		listData.addElement("Air Conditioner");
		listData.addElement("Paddle Fan");
		listData.addElement("Reading Light");
		listData.addElement("Dish Washer");
		listData.addElement("Electric Stove");
		listData.addElement("Refrigerator");
		listData.addElement("Microwave oven");
		listData.addElement("Coffee Maker");
		listData.addElement("Kettle");
		listData.addElement("Kitchen Light");
		
		Hashtable wattMap= new Hashtable();
		wattMap.put("None", 0);
		wattMap.put("Small Fan",240);
		wattMap.put("Hair Dryer",1584);
		wattMap.put("Hair Dryer",300);
		wattMap.put("Computer",108);
		wattMap.put("Printer",300);
		wattMap.put("Incandescnet light",60);
		wattMap.put("Television",300);
		wattMap.put("Large lamp",150);
		wattMap.put("Stereo System",150);
		wattMap.put("Air Conditioner",900);
		wattMap.put("Paddle Fan",60);
		wattMap.put("Reading Light",60);
		wattMap.put("Dish Washer",1290);
		wattMap.put("Electric Stove",4950);
		wattMap.put("Refrigerator",330);
		wattMap.put("Microwave oven",1200);
		wattMap.put("Coffee Maker",660);
		wattMap.put("Kettle",1500);
		wattMap.put("Kitchen Light",300);
		
		JList list = new JList<String>(listData);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		EnergyManagerPanel.add(list);
		
		JButton btnNewButton = new JButton("Power Consumption");
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List values = list.getSelectedValuesList();
				long totalPpower = 0;
				for(int index = 0; index < values.size(); ++index){
					String equipment = values.get(index).toString();
					int watt= wattMap.get(equipment).hashCode();
					totalPpower = totalPpower + watt;
				}
				String totalPowerString = String.valueOf(totalPpower);
				cntrllr.SendConsumedPower(totalPowerString);
			}
		});
		EnergyManagerPanel.add(btnNewButton);
		
		
		
		
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				int nRet = fileChooser.showOpenDialog(frame);
				if(nRet == fileChooser.APPROVE_OPTION)
				{
					File file = fileChooser.getSelectedFile();
					Date date = dateChoser.getDate();
					if(null == date){
						JOptionPane.showMessageDialog(frame, "Date cannot be empty");
						return;
					}
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String strDate = dateFormat.format(date);
					
					String filePath = file.getPath();
					cntrllr.SendConsumedEnergy(strDate, filePath);
					
				}
				//EnergyManagerPanel.add(fileChooser);
				
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				cntrllr.close();
			}
		});

	}

}

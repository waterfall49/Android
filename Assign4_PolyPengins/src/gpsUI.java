import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class gpsUI {

	private JFrame frame;
	public JComboBox comboPenguinSex;
	public JTextField txtPenguinWeight;
	public JTextField txtPenguinBP;
	public JTextField txtPenguinGPSInfo;
	public JButton btnPenguinMain;
	public JComboBox comboLionSex;
	public JTextField txtLionWeight;
	public JTextField txtLionSpot;
	public JTextField txtSealionGPSInfo;
	public JButton btnSealionMain;
	public JComboBox comboWalrusSex;
	public JTextField txtWalrusWeight;
	public JComboBox comboWalrusDH;
	public JTextField txtWalrusGPSInfo;
	public JButton btnWalrusMain;
	public JTextArea txtAreaReportResult;
	public JButton btnSave;
	public JButton btnSave2;
	public JButton btnSave3;

	public Controller myController = new Controller(); //aggregate
	private String infoSex;
	private double infoWeight;
	private String infoGPS;
	private double infoBP;
	private int infoSpot;
	private String infoDH;
	private String finalreport;
	private JLabel lblWeight;
	
	private String regInt = "^\\d+$";
	private String regDouble = "^\\d+(\\.\\d+)?$"; //for weight, bloodpressure
	private String regGPS = "^[-+]?([1-8]?\\d(\\.\\d{1,8})?|90(\\.0{1,8})?)\\s*[-+]?(180(\\.0{1,8})?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d{1,8})?)$";
	
	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gpsUI window = new gpsUI();
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
	/**
	 * 
	 */
	public gpsUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setLocationRelativeTo(null);
		
		JPanel MainPnl = new JPanel();
		frame.getContentPane().add(MainPnl, "name_118844860514991");
		MainPnl.setLayout(null);
		
		JPanel PenguinPnl = new JPanel();
		frame.getContentPane().add(PenguinPnl, "name_118858906209951");
		PenguinPnl.setLayout(null);

		JPanel SealionPnl = new JPanel();
		SealionPnl.setForeground(Color.BLACK);
		frame.getContentPane().add(SealionPnl, "name_118882395531721");
		SealionPnl.setLayout(null);
		
		JPanel WalrusPnl = new JPanel();
		frame.getContentPane().add(WalrusPnl, "name_118907643770950");
		WalrusPnl.setLayout(null);
		
		JPanel reportPnl = new JPanel();
		frame.getContentPane().add(reportPnl, "name_527808108997369");
		reportPnl.setLayout(null);
		
		/**
		 * Main Panel
		 */

		JButton btnSeaLions = new JButton("Sea Lions");
		btnSeaLions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPnl.setVisible(false);
				SealionPnl.setVisible(true);
				btnSealionMain.setEnabled(false);
			}
		});
		
		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finalreport = myController.doRead("info.txt");
//				System.out.println(finalreport);
				txtAreaReportResult.setText(finalreport);
				MainPnl.setVisible(false);
				reportPnl.setVisible(true);
			}
		});
		
		btnReport.setFont(new Font("Ebrima", Font.BOLD, 20));
		btnReport.setBounds(569, 436, 199, 54);
		MainPnl.add(btnReport);
		btnSeaLions.setFont(new Font("Ebrima", Font.BOLD, 20));
		btnSeaLions.setBounds(296, 370, 199, 54);
		MainPnl.add(btnSeaLions);
		
		JButton btnWalrus = new JButton("Walrus");
		btnWalrus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPnl.setVisible(false);
				WalrusPnl.setVisible(true);
				btnWalrusMain.setEnabled(false);
			}
		});
		
		btnWalrus.setFont(new Font("Ebrima", Font.BOLD, 20));
		btnWalrus.setBounds(544, 370, 199, 54);
		MainPnl.add(btnWalrus);
		
		JLabel lblPenguin = new JLabel("");
		lblPenguin.setIcon(new ImageIcon(gpsUI.class.getResource("/img/Penguin.png")));
		lblPenguin.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblPenguin.setBounds(14, 56, 255, 305);
		MainPnl.add(lblPenguin);
		
		JLabel lblSealion = new JLabel("");
		lblSealion.setIcon(new ImageIcon(gpsUI.class.getResource("/img/sealion1.jpg")));
		lblSealion.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblSealion.setBounds(275, 56, 232, 305);
		MainPnl.add(lblSealion);
		
		JLabel lblWalrus = new JLabel("");
		lblWalrus.setIcon(new ImageIcon(gpsUI.class.getResource("/img/walrus1.jpg")));
		lblWalrus.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblWalrus.setBounds(509, 56, 259, 305);
		MainPnl.add(lblWalrus);
		
		JButton btnPenguin = new JButton("Penguins");
		btnPenguin.setFont(new Font("Ebrima", Font.BOLD, 20));
		btnPenguin.setBounds(38, 370, 199, 54);
		MainPnl.add(btnPenguin);
		
		JLabel lblChooseAnimal = new JLabel("Choose animal");
		lblChooseAnimal.setForeground(Color.RED);
		lblChooseAnimal.setFont(new Font("Ebrima", Font.BOLD, 30));
		lblChooseAnimal.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAnimal.setBounds(233, 12, 348, 42);
		MainPnl.add(lblChooseAnimal);
		
		btnPenguin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPnl.setVisible(false);
				PenguinPnl.setVisible(true);
				btnPenguinMain.setEnabled(false);
			}
		});
		
		
		/**
		 * Penguins Panel
		 */
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboPenguinSex.getSelectedIndex()==0)
					infoSex = "Male";
				else if(comboPenguinSex.getSelectedIndex()==1)
					infoSex = "Female";
								
				if(txtPenguinWeight.getText().matches(regDouble) && txtPenguinBP.getText().matches(regDouble) && txtPenguinGPSInfo.getText().matches(regGPS)){
					String tmp = txtPenguinWeight.getText();
					infoWeight = Double.parseDouble(tmp);
					String tmp1 = txtPenguinBP.getText();
					infoBP = Double.parseDouble(tmp1);
					infoGPS = txtPenguinGPSInfo.getText();					
					myController.generateObject(infoSex, infoWeight, infoBP, infoGPS);
					myController.doWrite(myController.myAnimal.doString());
					btnPenguinMain.setEnabled(true);}
				else{
					btnPenguinMain.setEnabled(false);}
							
			}
		});
		
		btnSave.setFont(new Font("Ebrima", Font.BOLD, 18));
		btnSave.setBounds(395, 420, 152, 38);
		PenguinPnl.add(btnSave);
		
		lblWeight = new JLabel("WEIGHT");
		lblWeight.setForeground(Color.BLACK);
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblWeight.setBounds(339, 202, 114, 28);
		PenguinPnl.add(lblWeight);
		
		JLabel lblPenguinGpsInfo = new JLabel("GPS INFO");
		lblPenguinGpsInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPenguinGpsInfo.setForeground(Color.BLACK);
		lblPenguinGpsInfo.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblPenguinGpsInfo.setBounds(339, 346, 114, 28);
		PenguinPnl.add(lblPenguinGpsInfo);
		
		txtPenguinGPSInfo = new JTextField();
		txtPenguinGPSInfo.setFont(new Font("Arial", Font.PLAIN, 17));
		txtPenguinGPSInfo.setColumns(10);
		txtPenguinGPSInfo.setBounds(473, 344, 180, 38);
		PenguinPnl.add(txtPenguinGPSInfo);
		
		btnPenguinMain = new JButton("Go To Main");
		btnPenguinMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PenguinPnl.setVisible(false);
				MainPnl.setVisible(true);
			}
		});
		
		btnPenguinMain.setFont(new Font("Ebrima", Font.BOLD, 18));
		btnPenguinMain.setBounds(570, 420, 152, 38);
		PenguinPnl.add(btnPenguinMain);
		
		JLabel lblPenguinPic = new JLabel("");
		lblPenguinPic.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblPenguinPic.setBounds(30, 125, 295, 314);
		PenguinPnl.add(lblPenguinPic);
		
		lblPenguinPic.setIcon(new ImageIcon(gpsUI.class.getResource("/img/Penguin.png")));
		
		JLabel lblSex = new JLabel("SEX");
		lblSex.setForeground(Color.BLACK);
		lblSex.setHorizontalAlignment(SwingConstants.CENTER);
		lblSex.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblSex.setBounds(339, 141, 114, 28);
		PenguinPnl.add(lblSex);
		
		txtPenguinWeight = new JTextField();
		txtPenguinWeight.setFont(new Font("Arial", Font.PLAIN, 17));
		txtPenguinWeight.setColumns(10);
		txtPenguinWeight.setBounds(473, 200, 180, 38);
		PenguinPnl.add(txtPenguinWeight);
		
		JLabel lblBloodPressure = new JLabel("BLOOD");
		lblBloodPressure.setForeground(Color.BLACK);
		lblBloodPressure.setHorizontalAlignment(SwingConstants.CENTER);
		lblBloodPressure.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblBloodPressure.setBounds(339, 261, 114, 28);
		PenguinPnl.add(lblBloodPressure);
		
		txtPenguinBP = new JTextField();
		txtPenguinBP.setFont(new Font("Arial", Font.PLAIN, 17));
		txtPenguinBP.setColumns(10);
		txtPenguinBP.setBounds(473, 270, 180, 42);
		PenguinPnl.add(txtPenguinBP);
		
		JLabel lblPressure = new JLabel("PRESSURE");
		lblPressure.setForeground(Color.BLACK);
		lblPressure.setHorizontalAlignment(SwingConstants.CENTER);
		lblPressure.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblPressure.setBounds(339, 284, 114, 28);
		PenguinPnl.add(lblPressure);
		
		JLabel lblPenguins = new JLabel("[ PENGUINS ]");
		lblPenguins.setHorizontalAlignment(SwingConstants.CENTER);
		lblPenguins.setFont(new Font("Ebrima", Font.BOLD, 28));
		lblPenguins.setBounds(235, 34, 323, 47);
		PenguinPnl.add(lblPenguins);
		
		comboPenguinSex = new JComboBox();
		comboPenguinSex.setFont(new Font("Ebrima", Font.PLAIN, 18));
		comboPenguinSex.setEditable(true);
		comboPenguinSex.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboPenguinSex.setBounds(473, 137, 180, 38);
		PenguinPnl.add(comboPenguinSex);
		

		/**
		 * Sea Loin Panel
		 */
		
		btnSave2 = new JButton("Save");
		btnSave2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboLionSex.getSelectedIndex()==0)
					infoSex = "Male";
				else if(comboLionSex.getSelectedIndex()==1)
					infoSex = "Female";
				
				if(txtLionWeight.getText().matches(regDouble) && txtLionSpot.getText().matches(regInt) && txtSealionGPSInfo.getText().matches(regGPS)){
					String tmp = txtLionWeight.getText();
					infoWeight = Double.parseDouble(tmp);
					String tmp1 = txtLionSpot.getText();
					infoSpot = Integer.parseInt(tmp1);
					infoGPS = txtSealionGPSInfo.getText();					
					myController.generateObject(infoSex, infoWeight, infoSpot, infoGPS);
					myController.doWrite(myController.myAnimal.doString());
					btnSealionMain.setEnabled(true);}
				else{
					btnSealionMain.setEnabled(false);}
				
			}
		});
		
		btnSealionMain = new JButton("Go To Main");
		btnSealionMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SealionPnl.setVisible(false);
				MainPnl.setVisible(true);
			}
		});
		btnSealionMain.setFont(new Font("Ebrima", Font.BOLD, 18));
		btnSealionMain.setBounds(586, 440, 152, 38);
		SealionPnl.add(btnSealionMain);
		
		btnSave2.setFont(new Font("Ebrima", Font.BOLD, 18));
		btnSave2.setBounds(420, 440, 152, 38);
		SealionPnl.add(btnSave2);
		
		comboLionSex = new JComboBox();
		comboLionSex.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboLionSex.setFont(new Font("Ebrima", Font.PLAIN, 18));
		comboLionSex.setEditable(true);
		comboLionSex.setBounds(493, 143, 180, 38);
		SealionPnl.add(comboLionSex);
		
		JLabel lblSealionGPSInfo = new JLabel("GPS INFO");
		lblSealionGPSInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSealionGPSInfo.setForeground(Color.BLACK);
		lblSealionGPSInfo.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblSealionGPSInfo.setBounds(359, 357, 114, 28);
		SealionPnl.add(lblSealionGPSInfo);
		
		txtSealionGPSInfo = new JTextField();
		txtSealionGPSInfo.setFont(new Font("Arial", Font.PLAIN, 17));
		txtSealionGPSInfo.setColumns(10);
		txtSealionGPSInfo.setBounds(493, 355, 180, 38);
		SealionPnl.add(txtSealionGPSInfo);
		
		JLabel lblSeaLoins = new JLabel("[ SEA LOINS ]");
		lblSeaLoins.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeaLoins.setFont(new Font("Ebrima", Font.BOLD, 28));
		lblSeaLoins.setBounds(252, 62, 323, 47);
		SealionPnl.add(lblSeaLoins);
		
		JLabel lblSealionPic = new JLabel("");
		lblSealionPic.setIcon(new ImageIcon(gpsUI.class.getResource("/img/sealion1.jpg")));
		lblSealionPic.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblSealionPic.setBounds(37, 131, 277, 297);
		SealionPnl.add(lblSealionPic);
		
		JLabel lblLionSex = new JLabel("SEX");
		lblLionSex.setForeground(Color.BLACK);
		lblLionSex.setHorizontalAlignment(SwingConstants.CENTER);
		lblLionSex.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblLionSex.setBounds(340, 147, 114, 28);
		SealionPnl.add(lblLionSex);
		
		JLabel lblLionWeight = new JLabel("WEIGHT");
		lblLionWeight.setForeground(Color.BLACK);
		lblLionWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblLionWeight.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblLionWeight.setBounds(351, 211, 114, 28);
		SealionPnl.add(lblLionWeight);
		
		txtLionWeight = new JTextField();
		txtLionWeight.setFont(new Font("Arial", Font.PLAIN, 17));
		txtLionWeight.setColumns(10);
		txtLionWeight.setBounds(493, 209, 180, 38);
		SealionPnl.add(txtLionWeight);
		
		JLabel lblLionNumber = new JLabel("NUMBER OF");
		lblLionNumber.setForeground(Color.BLACK);
		lblLionNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblLionNumber.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblLionNumber.setBounds(340, 272, 132, 28);
		SealionPnl.add(lblLionNumber);
		
		txtLionSpot = new JTextField();
		txtLionSpot.setFont(new Font("Arial", Font.PLAIN, 17));
		txtLionSpot.setColumns(10);
		txtLionSpot.setBounds(493, 272, 180, 42);
		SealionPnl.add(txtLionSpot);
		
		JLabel lblLionSpot = new JLabel("SPOTS");
		lblLionSpot.setForeground(Color.BLACK);
		lblLionSpot.setHorizontalAlignment(SwingConstants.CENTER);
		lblLionSpot.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblLionSpot.setBounds(350, 295, 114, 28);
		SealionPnl.add(lblLionSpot);
		
		/**
		 * Walrus Panel
		 */
		btnWalrusMain = new JButton("Go To Main");
		btnWalrusMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WalrusPnl.setVisible(false);
				MainPnl.setVisible(true);
			}
		});
		
		btnSave3 = new JButton("Save");
		btnSave3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboWalrusSex.getSelectedIndex()==0)
					infoSex = "Male";
				else if(comboWalrusSex.getSelectedIndex()==1)
					infoSex = "Female";
				
				if(comboWalrusDH.getSelectedIndex()==0)
					infoDH = "Good";
				else if(comboWalrusDH.getSelectedIndex()==1)
					infoDH = "Average";
				else if(comboWalrusDH.getSelectedIndex()==2)
					infoDH = "Poor";
				
				if(txtWalrusWeight.getText().matches(regDouble) && txtWalrusGPSInfo.getText().matches(regGPS)){	
					String tmp = txtWalrusWeight.getText();
					infoWeight = Double.parseDouble(tmp);
					infoGPS = txtWalrusGPSInfo.getText();
					myController.generateObject(infoSex, infoWeight, infoDH, infoGPS);
					myController.doWrite(myController.myAnimal.doString());
					btnWalrusMain.setEnabled(true);}
				else
					btnWalrusMain.setEnabled(false);
			}
		});
		
		btnSave3.setFont(new Font("Ebrima", Font.BOLD, 18));
		btnSave3.setBounds(429, 439, 152, 38);
		WalrusPnl.add(btnSave3);
		
		btnWalrusMain.setFont(new Font("Ebrima", Font.BOLD, 18));
		btnWalrusMain.setBounds(595, 439, 152, 38);
		WalrusPnl.add(btnWalrusMain);
		
		JLabel lblWalrusGPSInfo = new JLabel("GPS INFO");
		lblWalrusGPSInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblWalrusGPSInfo.setForeground(Color.BLACK);
		lblWalrusGPSInfo.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblWalrusGPSInfo.setBounds(359, 369, 114, 28);
		WalrusPnl.add(lblWalrusGPSInfo);
		
		txtWalrusGPSInfo = new JTextField();
		txtWalrusGPSInfo.setFont(new Font("Arial", Font.PLAIN, 17));
		txtWalrusGPSInfo.setColumns(10);
		txtWalrusGPSInfo.setBounds(493, 367, 180, 38);
		WalrusPnl.add(txtWalrusGPSInfo);
		
		JLabel lblWalrus_1 = new JLabel("[ WALRUS ]");
		lblWalrus_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblWalrus_1.setFont(new Font("Ebrima", Font.BOLD, 28));
		lblWalrus_1.setBounds(255, 53, 323, 47);
		WalrusPnl.add(lblWalrus_1);
		
		JLabel lblWalrusPic = new JLabel("");
		lblWalrusPic.setIcon(new ImageIcon(gpsUI.class.getResource("/img/walrus1.jpg")));
		lblWalrusPic.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblWalrusPic.setBounds(52, 112, 248, 307);
		WalrusPnl.add(lblWalrusPic);
		
		JLabel lblWalrusSex = new JLabel("SEX");
		lblWalrusSex.setForeground(Color.BLACK);
		lblWalrusSex.setHorizontalAlignment(SwingConstants.CENTER);
		lblWalrusSex.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblWalrusSex.setBounds(341, 160, 114, 28);
		WalrusPnl.add(lblWalrusSex);
		
		comboWalrusSex = new JComboBox();
		comboWalrusSex.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboWalrusSex.setFont(new Font("Ebrima", Font.PLAIN, 18));
		comboWalrusSex.setEditable(true);
		comboWalrusSex.setBounds(493, 156, 180, 38);
		WalrusPnl.add(comboWalrusSex);
		
		JLabel lblWalrusWeight = new JLabel("WEIGHT");
		lblWalrusWeight.setForeground(Color.BLACK);
		lblWalrusWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWalrusWeight.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblWalrusWeight.setBounds(351, 223, 114, 28);
		WalrusPnl.add(lblWalrusWeight);
		
		txtWalrusWeight = new JTextField();
		txtWalrusWeight.setFont(new Font("Arial", Font.PLAIN, 17));
		txtWalrusWeight.setColumns(10);
		txtWalrusWeight.setBounds(493, 221, 180, 38);
		WalrusPnl.add(txtWalrusWeight);
		
		JLabel lblHealth = new JLabel("HEALTH");
		lblHealth.setForeground(Color.BLACK);
		lblHealth.setHorizontalAlignment(SwingConstants.CENTER);
		lblHealth.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblHealth.setBounds(351, 312, 114, 28);
		WalrusPnl.add(lblHealth);
		
		JLabel lblDental = new JLabel("DENTAL");
		lblDental.setForeground(Color.BLACK);
		lblDental.setHorizontalAlignment(SwingConstants.CENTER);
		lblDental.setFont(new Font("Ebrima", Font.BOLD, 20));
		lblDental.setBounds(341, 289, 132, 28);
		WalrusPnl.add(lblDental);
		
		comboWalrusDH = new JComboBox();
		comboWalrusDH.setModel(new DefaultComboBoxModel(new String[] {"Good", "Average", "Poor"}));
		comboWalrusDH.setFont(new Font("Ebrima", Font.PLAIN, 18));
		comboWalrusDH.setEditable(true);
		comboWalrusDH.setBounds(493, 294, 180, 38);
		WalrusPnl.add(comboWalrusDH);
		
		/**
		 * Report Panel
		 */
		
		JButton btnReportMain = new JButton("Go To Main");
		btnReportMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reportPnl.setVisible(false);
				MainPnl.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 155, 633, 245);
		reportPnl.add(scrollPane);
		
		txtAreaReportResult = new JTextArea();
		scrollPane.setViewportView(txtAreaReportResult);
		txtAreaReportResult.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAreaReportResult.setFont(new Font("Ebrima", Font.PLAIN, 17));
		btnReportMain.setFont(new Font("Ebrima", Font.BOLD, 18));
		btnReportMain.setBounds(592, 432, 152, 38);
		reportPnl.add(btnReportMain);
		
		JLabel lblreport = new JLabel("[ Report ]");
		lblreport.setHorizontalAlignment(SwingConstants.CENTER);
		lblreport.setFont(new Font("Ebrima", Font.BOLD, 30));
		lblreport.setBounds(192, 53, 402, 90);
		reportPnl.add(lblreport);
	}
}

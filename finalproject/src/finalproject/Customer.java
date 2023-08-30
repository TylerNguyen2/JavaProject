package finalproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class Customer {
	//defining fields
	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldPhone;
	private JTextField textFieldAddress;
	private JTextField textFieldTariff;
	private JTable table;
	private JTextField textFieldM3;
	private JTextField textFieldKWH;
	private JTextField textFieldDays;
	
	//Data members
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	//Formating to two decimal places
	DefaultTableModel model = new DefaultTableModel();
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	//ConnectDB Method which connects to database
	public static Connection ConnectDB() {
		try {
			//using location of schema, accesing with username and password
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema","root","mar092002");
			
			return conn;
			
		}
		catch(Exception e)
		{
			// If no connection is able to be made display error message
			JOptionPane.showMessageDialog(null,e);
			return null;
			
		}
	}
	

	
	
	//public method update table which updates table to mysql
	public void updateTable() {
		conn = Customer.ConnectDB();
		//if able to establish connection then run code
		if(conn !=null)
		{
			// set string sql to columns of table
			String sql="Select Name, Phone, MeterType,Address, EnergyTariff, Paid, m3used, KWHused, Daysused";
			try
			{
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				// J Table has 9 columns so create columndata as a array of the 9 objects.
				Object[] columnData = new Object[9];
				
				while (rs.next())
				{
					//Set each column data to get string
					columnData[0] = rs.getString("Name");
					columnData[1] = rs.getString("Phone");
					columnData[2] = rs.getString("MeterType");
					columnData[3] = rs.getString("Address");
					columnData[4] = rs.getString("EnergyTarff");
			
					columnData[5] = rs.getString("Paid");
					columnData[6] = rs.getString("m3 used");
					columnData[7] = rs.getString("KWH used");
					columnData[8] = rs.getString("Days used");
					model.addRow(columnData);
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}

	//Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer window = new Customer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}


	
	//Public class customer which contains all code relating to gui
	public Customer() {
		// call object initialize 
		initialize();
		// set connect method to conn
		conn = connect.ConnectionDb();
		
	
		//Tabbed to switch between tabs
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1072, 403);
		frame.getContentPane().add(tabbedPane);
		
		//Name of tabbed pane
		JPanel panel = new JPanel();
		tabbedPane.addTab("Customers", null, panel, null);
		panel.setLayout(null);
		
		//Labels that will be correlated to text fields
		JLabel lblNewLabel = new JLabel("name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 107, 28);
		panel.add(lblNewLabel);
		
		JLabel lblPhone = new JLabel("phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPhone.setBounds(10, 50, 84, 25);
		panel.add(lblPhone);
		
		JLabel lblAddress = new JLabel("address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(10, 125, 84, 28);
		panel.add(lblAddress);
		
		JLabel lblEnergyTariff = new JLabel("energy tariff");
		lblEnergyTariff.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnergyTariff.setBounds(10, 200, 135, 29);
		panel.add(lblEnergyTariff);
		
		JLabel lblMeter = new JLabel("meter type");
		lblMeter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMeter.setBounds(10, 86, 107, 28);
		panel.add(lblMeter);
		
		//Text fields correlated to jlabels
		textFieldName = new JTextField();
		textFieldName.setBounds(130, 18, 114, 20);
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(130, 55, 114, 20);
		panel.add(textFieldPhone);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(130, 132, 114, 20);
		panel.add(textFieldAddress);
		
		textFieldTariff = new JTextField();
		textFieldTariff.setColumns(10);
		textFieldTariff.setBounds(130, 207, 114, 20);
		panel.add(textFieldTariff);
		
		JLabel lblNewLabel_1 = new JLabel("Paid/Unpaid");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 163, 107, 25);
		panel.add(lblNewLabel_1);
		
		//Combo box which when selected gives two options of either paid or unpaid
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Paid", "Unpaid"}));
		comboBox.setBounds(130, 167, 114, 22);
		panel.add(comboBox);
		
		JLabel lblMUsage = new JLabel("m3 used");
		lblMUsage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMUsage.setBounds(10, 232, 135, 29);
		panel.add(lblMUsage);
		
		JLabel lblKwhUsed = new JLabel("KWH used");
		lblKwhUsed.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblKwhUsed.setBounds(10, 266, 135, 29);
		panel.add(lblKwhUsed);
		
		JLabel lblDaysUsed = new JLabel("Days used");
		lblDaysUsed.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDaysUsed.setBounds(10, 295, 135, 29);
		panel.add(lblDaysUsed);
		
		textFieldM3 = new JTextField();
		textFieldM3.setColumns(10);
		textFieldM3.setBounds(130, 240, 114, 20);
		panel.add(textFieldM3);
		
		textFieldKWH = new JTextField();
		textFieldKWH.setColumns(10);
		textFieldKWH.setBounds(130, 273, 114, 20);
		panel.add(textFieldKWH);
		
		textFieldDays = new JTextField();
		textFieldDays.setColumns(10);
		textFieldDays.setBounds(130, 302, 114, 20);
		panel.add(textFieldDays);
		table.setModel(model);
		
		//Jpanel to set all objects inside of the gui
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(271, 11, 786, 291);
		panel.add(panel_1);
		panel_1.setBackground(new Color(128, 255, 255));
		panel_1.setBorder(new LineBorder(new Color(0, 128, 255), 6));
		panel_1.setLayout(null);
		
		//Scroll pane for the JTable so it is able to contain infinite amount of data.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 766, 269);
		panel_1.add(scrollPane);
		//Create jtable
		table = new JTable();
		scrollPane.setViewportView(table);
		//set jtable columns
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Phone", "Meter Type", "address", "Paid/unpaid", "Energy Tariff", "m3 used", "KWH Used", "Days Used"
			}
		));
		
		//Jbutton to delete row of data
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(203, 327, 89, 23);
		panel.add(btnDelete);
		
		
		
		
		
		
		
		//Jbutton to update data into mysql
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			if(tblModel.getRowCount()==0) {
				//if row is empty display error message
				JOptionPane.showMessageDialog(null, "the row is empty");
			}else {
				//else wise display message that it has been added
				JOptionPane.showMessageDialog(null, "It has been added to the database");
				//Set strings that will be correlated to each textfield
				String nam,pho,met,add,pai,ene,m3,kwh,day ;
				try {
					
				
				//Creates connection to mysql
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema","root","mar092002");
				for(int i = 0; i < tblModel.getColumnCount() ;i++) {
					//Set each value of the table to the variable
					nam = tblModel.getValueAt(i, 0).toString();
					pho = tblModel.getValueAt(i, 1).toString();
					met = tblModel.getValueAt(i, 2).toString();
					add = tblModel.getValueAt(i, 3).toString();
					pai = tblModel.getValueAt(i, 4).toString();
					ene = tblModel.getValueAt(i, 5).toString();
					m3 = tblModel.getValueAt(i, 6).toString();
					kwh = tblModel.getValueAt(i, 7).toString();
					day = tblModel.getValueAt(i, 8).toString();
					
					//set query so that data can be later added into sql
					String query = "insert into database(nam,pho,met,add,pai,ene,m3,kwh,day)values ("+nam+","+pho+","+met+","+add+","+pai+","+ene+","+m3+","+kwh+","+day+")";
					
					//add each statement into sql
					PreparedStatement prepstmt = con.prepareStatement(query);
					prepstmt.setString(1, nam);
					prepstmt.setString(2, pho);
					prepstmt.setString(3, met);
					prepstmt.setString(4, add);
					prepstmt.setString(5, pai);
					prepstmt.setString(6, ene);
					prepstmt.setString(7, m3);
					prepstmt.setString(8, kwh);
					prepstmt.setString(9, day);
					
					prepstmt.execute();
				}
				JOptionPane.showInputDialog(null, "It has been added to the database");
				
				tblModel.setRowCount(0);
				
				}catch(Exception e1 ) {
				
			}
				
				
			}
				
		}
		});
		btnUpdate.setBounds(104, 327, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnRecord = new JButton("Record");
		btnRecord.setBounds(5, 327, 89, 23);
		panel.add(btnRecord);
		
		//Exit button to leave the gui
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("exist");
				if (JOptionPane.showConfirmDialog(frame, "Do you want to exit","Data entry for",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(302, 327, 89, 23);
		panel.add(btnExit);
		
		//Combo box to select between either electrical or analog
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Electric\t\t", "Analog"}));
		comboBox_1.setBounds(130, 92, 114, 22);
		panel.add(comboBox_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Dashboard", null, panel_2, null);
		panel_2.setLayout(null);
		
		JTextPane displayResultsArea = new JTextPane();
		displayResultsArea.setBounds(253, 11, 715, 359);
		panel_2.add(displayResultsArea);
		
		
		
		//User dashboard that prints out user infromation including their meter readings payments bills and tariff details
		JButton btnNewButton = new JButton("User Dashboard");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create new array
				String[] arr = new String[9];
				//set textfield entrys as postions in the array jtextpane
				arr[0] = textFieldName.getText();
				arr[1] = textFieldPhone.getText();
				arr[2] = (String) comboBox_1.getSelectedItem();
				arr[3] = textFieldAddress.getText();
				arr[4] = (String) comboBox.getSelectedItem();
				arr[5] = textFieldTariff.getText();
				arr[6] = textFieldM3.getText();
				arr[7] = textFieldKWH.getText();
				arr[8] = textFieldDays.getText();
				
			
		

				
				
				//Date format to display date
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				String dateString = dateFormat.format(date);
				//Calculating rates and converting string into double
				double ccf = Double.parseDouble(arr[6]);
				double tariff = Double.parseDouble(arr[5]) / 100;
				// if user was marked as paid show their infromation
				if(arr[4].equals("Paid")) {
					displayResultsArea.setText(arr[2] + " Meter\n\n\n\n" + arr[6] + " CCF(gas) usage\n" + arr[7] + " kWh(electric) usage\nAfter " + arr[8] + " days." 
							+ "Recent bill for " + dateString + " is currently paid off. \nThere are no more payments due until 30 days from this date."
				
							+"Current bill for recent " + arr[8] + " day(s).\n\n\n" + arr[2] +" Meter\n"+ arr[6] + " CCF(gas) usage\n" + arr[7] + " kWh(electric) usage\n" 
							+ arr[6] + " CCF * 3.591 (price per ccf) + " + arr[5] + " % = $" + (ccf * 3.591 + ccf * tariff) + "\nBill is " + arr[4] + "." +
							
							"Tariff Details\n\n\n" + arr[0] + " has an energy tariff rate of " + arr[5] + "% included with each bill." 
							
							
							
							
							);
				}else {
					//like wise, if not then say that the bill is unpaid for
					displayResultsArea.setText("Recent bill for " + dateString + " is currently unpaid. \nPlease make a payment within 30 days of this bill date.");
				}
				
				
				
			
				
				
			}
		});
		
		
		
		
		
		
		btnNewButton.setBounds(42, 35, 151, 72);
		panel_2.add(btnNewButton);
		//Invoice button which display users invoice
		JButton btnInvoice = new JButton("Invoice");
		btnInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Set an array to hold 9 positions
				String[] arr = new String[9];
				//Set each textfield as a position in the array
				arr[0] = textFieldName.getText();
				arr[1] = textFieldPhone.getText();
				arr[2] = (String) comboBox_1.getSelectedItem();
				arr[3] = textFieldAddress.getText();
				arr[4] = (String) comboBox.getSelectedItem();
				arr[5] = textFieldTariff.getText();
				arr[6] = textFieldM3.getText();
				arr[7] = textFieldKWH.getText();
				arr[8] = textFieldDays.getText();
				//Variables to calculate invoice math
				double ccf = Double.parseDouble(arr[6]);
				double kWh = Double.parseDouble(arr[7]);
				double tariff = Double.parseDouble(arr[5]) / 100;
				double totalCharges = (ccf * 3.591 + ccf * tariff) + (kWh * 1.09 + kWh * tariff);
				double tax = totalCharges * 0.8;
				//Date format to display dates
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				String dateString = dateFormat.format(date);
				//Display results in textfield in jtextpane
				displayResultsArea.setText("\t\t\t Invoice date: " + dateString + "\n\t\tINVOICE\n" + arr[0]+ "\n" + arr[1] + "\n" + arr[3] + "\nKC Energy Co.\n\n\n\n\n\t\tService address:" + arr[3] + "\n" + arr[2] +" Meter\n"+ arr[6] + " CCF(gas) usage\n" + arr[7] + " kWh(electric) usage\n" 
						+ arr[6] + " CCF * $1.3782 (price per CCF) + " + arr[5] + " % (tariff) = $" + formatter.format(ccf * 1.3782 + ccf * tariff) + " gas cost\n" + arr[7] + " kWh * $1.09 (price per kWh) + " + arr[5] + " % (tariff) = $" + formatter.format(kWh * 1.09 + kWh * tariff) + " electricity cost."
						+ "\n\nFranchise tax: " + formatter.format(tax)  +"\n\n\nTotal Current Charges $" + formatter.format(totalCharges + tax) + "\n\nPayment due within 30 days from " + dateString);
				
				
				
				
			}
		});
		btnInvoice.setBounds(42, 188, 151, 72);
		panel_2.add(btnInvoice);
		
	
		
		
		
		

		//Record button which records infromation into jtable
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create an array of 9 positions
				String[] arr = new String[9];
				//set each array position to a textfield
				arr[0] = textFieldName.getText();
				arr[1] = textFieldPhone.getText();
				arr[2] = (String) comboBox_1.getSelectedItem();
				arr[3] = textFieldAddress.getText();
				arr[4] = (String) comboBox.getSelectedItem();
				arr[5] = textFieldTariff.getText();
				arr[6] = textFieldM3.getText();
				arr[7] = textFieldKWH.getText();
				arr[8] = textFieldDays.getText();
		
				
				
				
				
				
				String sql="INSERT INTO 'database'( 'Name', 'Phone', 'Meter Type','Address', 'EnergyTariff',  'Paid', 'm3used', 'KWHused', 'Daysused')" 
						+ "VALUES(?,?,?,?,?,?,?,?,?)";
				try
				{
					pst = conn.prepareStatement(sql);
					//set each pst to the string and its position
					pst.setString(1,textFieldName.getText());
					pst.setString(2,textFieldPhone.getText());
					pst.setString(3,(String) comboBox_1.getSelectedItem());
					pst.setString(4,textFieldAddress.getText());
					pst.setString(5, (String) comboBox.getSelectedItem());
					pst.setString(6,textFieldTariff.getText());
					pst.setString(7,textFieldM3.getText());
					pst.setString(8,textFieldKWH.getText());
					pst.setString(9,textFieldDays.getText());
			
					pst.execute();
					
					rs.close();
					pst.close();
				}
				catch(Exception ev)
				{
					JOptionPane.showMessageDialog(null, "It has been added to the table");
				}
				//add each textfield into the row of the table model
	              DefaultTableModel model = (DefaultTableModel) table.getModel();
	                model.addRow(new Object[]{

	                		textFieldName.getText(),textFieldPhone.getText(),comboBox_1.getSelectedItem(),textFieldAddress.getText(),comboBox.getSelectedItem(),
	                		textFieldTariff.getText(),textFieldM3.getText(),textFieldKWH.getText(), textFieldDays.getText()
	                });
	                if (table.getSelectedRow() ==-1) {
	                    if (table.getRowCount() == 0) {
	                        JOptionPane.showMessageDialog(null, "Added!", "Database", JOptionPane.OK_OPTION);
	                    }
	                }
				
				
				
				
			}
		});
		
		//Delete row button which deletes selected row
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//set table as default model
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						//if no row to delete, then display message
						JOptionPane.showMessageDialog(null, "No data to delete", null, JOptionPane.OK_OPTION);
					}else {
						//deletes row
						JOptionPane.showMessageDialog(null, "Select Row to delete", null, JOptionPane.OK_OPTION);
					}
				}else {
					model.removeRow(table.getSelectedRow());
				}
				
				
				
				
			}
		});
		
	
		
		updateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1108, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		table = new JTable();
		Object col[] = {"Select","ID","Name","Phone","MeterType","Address", "EnergyTariff","Paid","m3used","KWHused","Daysused"};
		model.setColumnIdentifiers(col);
		table.setModel(model);
		//call update table method
		updateTable();
	}
}
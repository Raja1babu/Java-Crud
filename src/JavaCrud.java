
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class JavaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
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
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con=null;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	
	
	public void Connect()
	{
		String url,name,pass;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			  url="jdbc:mysql://localhost:3306/javacrud";  
              name="root";  
              pass="@Raja1babu";  
              con = DriverManager.getConnection(url,name,pass);  
		    System.out.println("Connection created");  
           // con.close();  
            //System.out.println("Connection closed");  
		}
		catch(ClassNotFoundException ex) 
		{
			
		}
		catch (SQLException ex) 
		{
			
		}
	}
	
	
	public void table_load() 
	{
		try 
		{   String sql ="select * from book";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			//System.out.println("Query Executed");
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 814, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(318, 36, 151, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 127, 361, 200);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(39, 20, 90, 32);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(39, 76, 90, 32);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(39, 137, 90, 32);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setBounds(139, 29, 184, 19);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(139, 85, 184, 19);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setBounds(139, 146, 184, 19);
		panel.add(txtprice);
		txtprice.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String bname,edition, price;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price =txtprice.getText();
				
				try {
					pst=con.prepareStatement("insert into book(Name,Edition,Price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!!!!!!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					
					
				}
				
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton.setBounds(10, 350, 96, 42);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(116, 350, 96, 42);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
			}
		});
		btnClear.setBounds(222, 350, 96, 42);
		frame.getContentPane().add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 404, 308, 80);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book ID");
		lblNewLabel_1_1_1.setBounds(10, 19, 68, 17);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try 
				{
					String id = txtbid.getText();
					
					  pst = con.prepareStatement("select Name,Edition,Price from book where id = ?");
					  pst.setString(1 , id);
					  ResultSet rs = pst.executeQuery();
					  
					if(rs.next()==true)
					{
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtbname.setText(name);
						txtedition.setText(edition);
						txtprice.setText(price);
						
						
					}
					else
					{
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
					}
					
				}
				catch(SQLException ex) {
					}
				}
		});
		txtbid.setBounds(117, 20, 153, 19);
		txtbid.setColumns(10);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                 String bname,edition, price, bid;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price =txtprice.getText();
				bid = txtbid.getText();
				
				try {
					pst=con.prepareStatement("update book set Name=?, Edition=?, Price=? where id=?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Update!!!!!!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					
					
				}
				
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
			
			
			
			}
		});
		btnUpdate.setBounds(463, 404, 96, 42);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnClear_1_1 = new JButton("Delete");
		btnClear_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  String  bid;
					
					
					bid = txtbid.getText();
					
					try {
						pst=con.prepareStatement("delete from book where id=?");
						pst.setString(1, bid);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Delete!!!!!!");
						table_load();
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
						txtbname.requestFocus();
						
						
					}
					
					catch(SQLException e1) {
						e1.printStackTrace();
					}
				
			}
		});
		btnClear_1_1.setBounds(633, 404, 96, 42);
		frame.getContentPane().add(btnClear_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 93, 379, 299);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}

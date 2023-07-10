package gr.aueb.cf.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeachersUpdateDeleteForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public TeachersUpdateDeleteForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";
				Connection connection = Menu.getConnection();
				
				
				try {
					ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getTeachersSearchForm().getLastname() + "%");
					rs = ps.executeQuery();
					
					if (rs.next()) {
						idTxt.setText(rs.getString("ID"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
					} else {
						JOptionPane.showMessageDialog(null,  "No Teachers found", "Teachers", JOptionPane.WARNING_MESSAGE);
						Main.getTeachersUpdateDeleteForm().setVisible(false);
						Main.getTeachersSearchForm().setEnabled(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(85, 22, 17, 14);
		contentPane.add(lblNewLabel);
		
		idTxt = new JTextField();
		idTxt.setBackground(new Color(255, 255, 183));
		idTxt.setEditable(false);
		idTxt.setBounds(109, 19, 44, 20);
		contentPane.add(idTxt);
		idTxt.setColumns(10);
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstnameLbl.setBounds(53, 61, 49, 14);
		contentPane.add(firstnameLbl);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(109, 58, 151, 20);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Επώνυμο");
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(38, 100, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setBounds(109, 97, 151, 20);
		contentPane.add(lastnameTxt);
		lastnameTxt.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 317, 125);
		contentPane.add(panel);
		
		JButton firstBtn = new JButton("");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						idTxt.setText(rs.getString("ID"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));	
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		firstBtn.setIcon(new ImageIcon(TeachersUpdateDeleteForm.class.getResource("/resources/First record.png")));
		firstBtn.setBounds(69, 151, 49, 36);
		contentPane.add(firstBtn);
		
		JButton prevBtn = new JButton("");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						idTxt.setText(rs.getString("ID"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));	
					} else {
						rs.first();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		prevBtn.setIcon(new ImageIcon(TeachersUpdateDeleteForm.class.getResource("/resources/Previous_record.png")));
		prevBtn.setBounds(119, 151, 49, 36);
		contentPane.add(prevBtn);
		
		JButton nextBtn = new JButton("");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						idTxt.setText(rs.getString("ID"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));	
					} else {
						rs.last();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		nextBtn.setIcon(new ImageIcon(TeachersUpdateDeleteForm.class.getResource("/resources/Next_track.png")));
		nextBtn.setBounds(169, 151, 49, 36);
		contentPane.add(nextBtn);
		
		JButton lastBtn = new JButton("");
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.last()) {
						idTxt.setText(rs.getString("ID"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));	
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		lastBtn.setIcon(new ImageIcon(TeachersUpdateDeleteForm.class.getResource("/resources/Last_Record.png")));
		lastBtn.setBounds(219, 151, 49, 36);
		contentPane.add(lastBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.setForeground(new Color(0, 0, 255));
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		updateBtn.setBounds(99, 225, 100, 46);
		contentPane.add(updateBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM TEACHERS WHERE ID = ?";
				
				try {
					Connection connection = Menu.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(idTxt.getText()));
					
					
					int response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος", "Warning", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						int n = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, n + " rows affected", "Delete", JOptionPane.INFORMATION_MESSAGE);
					} else {
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		});
		deleteBtn.setForeground(Color.BLUE);
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteBtn.setBounds(202, 225, 100, 46);
		contentPane.add(deleteBtn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersSearchForm().setEnabled(true);
				Main.getTeachersUpdateDeleteForm().setVisible(false);
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(305, 225, 100, 46);
		contentPane.add(closeBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 202, 390, 1);
		contentPane.add(separator);
	}
}

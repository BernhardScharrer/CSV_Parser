import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import packets.TableData;

public class FilePicker extends JFrame {
	
	private static final long serialVersionUID = -6577071314786445633L;
	private CSVParser table;
	
	public FilePicker() {
		super("Choose your file:");
		super.setBounds(0,0,600,600);
		super.setLayout(new BorderLayout());
		super.setLocationRelativeTo(null);
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
		
		chooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		chooser.setControlButtonsAreShown(false);
		JPanel buttons = new JPanel();
		JButton next_button = new JButton("OPEN");
		JButton save_button = new JButton("SAVE");
		JButton upload_button = new JButton("UPLOAD");
		
		final FilePicker instance = this;
		
		next_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File choosed_file = chooser.getSelectedFile();
				
				if (choosed_file != null) {
					
					table = new CSVParser(choosed_file);
					
					JScrollPane pane = new JScrollPane(table.getTable(), 
				            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
					
					instance.add(pane);
					chooser.setVisible(false);
					
				} else {
					JOptionPane.showMessageDialog(null, "Es ist kein CSV-File ausgewählt", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		upload_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chooser.getSelectedFile()==null) return;
				
				int result = JOptionPane.showConfirmDialog(null, "JA/NEIN", "Upload to Database?", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					Client.send(new TableData(table.generate(), "upload"));
				}
			}
		});
		
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chooser.getSelectedFile()==null) return;
				
				int result = JOptionPane.showConfirmDialog(null, "JA/NEIN", "Save CSV-File?", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					new Saver(chooser.getSelectedFile().getPath(), table);
				}
			}
		});
		
		buttons.add(next_button);
		buttons.add(save_button);
		buttons.add(upload_button);
		
		super.add(buttons, BorderLayout.SOUTH);
		
		super.add(chooser);
		
		super.setVisible(true);
	}
	
}

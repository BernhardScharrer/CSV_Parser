import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTable;

import data.Functions;
import data.Table;
import data.User;

public class CSVParser {
	
	private JTable table;
	private int count;
	String[][] data;
	
	public static void main(String[] args) {
		
		new FilePicker();
		
	}
	
	public CSVParser(File file) {
		
		new Client();
		
		data = new String[150][4];
		
		String line, telephon;
		String[] parts;
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			count = -1;
			Functions functions;
			
			while ((line=reader.readLine())!=null) {
				
				if (count == -1) {
					count = 0;
					continue;
				}
				
				for (int n=0;n<line.split(",,").length;n++) line = seperateCommas(line);
				
				parts = line.split(",");
				
				try {
					
					telephon = parts[7];
					
					if (isTelephonNumber(telephon)) {
						try {
							telephon = parts[8];
							if (telephon.equals("-")) {
								telephon = parts[7];
								functions = getFunctionData(parts, 7);
							} else {
								functions = getFunctionData(parts, 8);
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							telephon = parts[7];
							functions = getFunctionData(parts, 7);
						}
					} else {
						functions = getFunctionData(parts, 8);
					}
					
					telephon = telephon.replace("+43", "0");
					telephon = telephon.replace(" ", "");
					telephon = telephon.replace("/", "");
					
				} catch (ArrayIndexOutOfBoundsException e) {
					telephon = "-";
					functions = getFunctionData(parts, 7);
				}
				
				System.out.println(parts[0] + "|" + parts[1] + "|" + telephon);
				
				data[count][0] = parts[1];
				data[count][1] = parts[0];
				data[count][2] = telephon;
				data[count][3] = functions.print();
				
				count++;
				
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		table = new JTable(data, new String[] {"VORNAME", "NACHNAME", "TELEPHON", "FUNKTIONEN"});
	}
	
	public JTable getTable() {
		return table;
	}
	
	private String seperateCommas(String line) {
		String result = "";
		for (String part : line.split(",,")) {
			result = result + part + ",-,";
		}
		return result.substring(0, result.length()-3);
	}
	
	private boolean isTelephonNumber(String number) {
		return number.startsWith("07") || number.startsWith("+43 7") || number.startsWith("+437");
	}
	
	private Functions getFunctionData(String[] array, int start) {
		start++;
		Functions functions = new Functions();
		
		for (int n = 0; n< array.length-start;n++) {
			String function = array[n+start];
			if (!function.equals("-")) functions.addFunction(function);
		}
		
		return functions;
	}
	
	public int getLineCount() {
		return count;
	}
	
	public Table generate() {
		Table table = new Table();
		
		for (int n = 0;n<getLineCount();n++) {
			Functions functions = new Functions();
			for (String function : data[n][3].split(",")) if (!function.equalsIgnoreCase("-")) functions.addFunction(function);
			table.getUsers().add(new User(-1, "Unknown", data[n][0], data[n][1], data[n][2], functions));
		}
		
		return table;
	}
	
}

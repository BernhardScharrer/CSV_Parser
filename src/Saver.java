import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {
	
	public Saver(String path, CSVParser table) {
		
		File file = new File(path.substring(0,path.length()-4)+"_save.csv");
		
		try {
			file.createNewFile();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			writer.write("# Saved CSV-File\n");
			
			for (int n = 0;n<table.getLineCount();n++) {
				writer.write((String) table.getTable().getValueAt(n, 1) + ",");
				writer.flush();
				writer.write((String) table.getTable().getValueAt(n, 0) + ",,,,,,");
				writer.flush();
				writer.write((String) table.getTable().getValueAt(n, 2)+ ",,");
				writer.flush();
				writer.write((String) table.getTable().getValueAt(n, 3));
				writer.flush();
				writer.write("\n");
				writer.flush();
			}
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

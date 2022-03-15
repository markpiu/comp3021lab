package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;




public class TextNote extends Note {
	String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	/**
	 * load a TextNote from File f
	 * 
	 * the tile of the TextNote is the name of the file
	 * the content of the TextNote is the content of the file
	 * 
	 * @param File f 
	 */
	public TextNote(File f) throws IOException,FileNotFoundException {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	
	public TextNote(String title,String content) {
		super(title);
		this.content = content;
	}
	
	/**
	 * get the content of a file
	 * 
	 * @param absolutePath of the file
	 * @return the content of the file
	 */
	private String getTextFromFile(String absolutePath)throws IOException,FileNotFoundException {
		String result = "";
		FileInputStream fis = new FileInputStream(absolutePath);
		InputStreamReader reader = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(reader);
		String line = br.readLine();
        while(line!=null)
            {
            	result = result+line+'\n';
            	line = br.readLine();
            }
        br.close();
        reader.close();
        fis.close();
        
		return result;
	}
	
	/**
	 * export text note to file
	 * 
	 * 
	 * @param pathFolder path of the folder where to export the note
	 * the file has to be named as the title of the note with extension ".txt"
	 * 
	 * if the tile contains white spaces " " they has to be replaced with underscores "_"
	 * 
	 * 
	 */
		public void exportTextToFile(String pathFolder)throws IOException {
				if(pathFolder == "") {
					pathFolder = ".";
				}
			    String filename = this.getTitle().replaceAll(" ", "_");    
				File file = new File( pathFolder + File.separator + filename + ".txt");
				FileOutputStream fos = new FileOutputStream(file);
		        OutputStreamWriter writer = new OutputStreamWriter(fos);
		        BufferedWriter bw = new BufferedWriter(writer);
		        bw.write(content);
		        bw.close();
		        writer.close();
		        fos.close();
		        
	         }	
	
}

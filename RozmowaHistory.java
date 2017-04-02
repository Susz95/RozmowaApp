import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;


public class RozmowaHistory {
	
	
	private static String curdir = System.getProperty("user.dir").toString(); //Pobieranie katalogu projektu
	private static final String FILENAME = curdir + "\\src\\History";
	private LineNumberReader br = null;
	private FileReader fr = null;
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	private String[] output;
	private Integer linenumbers= 0;
	
	
	// Zwracanie tekstu z pliku do łańcucha znaków.
	public String getOutput(String otp) {
		for(int i = 0; i< linenumbers && i<15; i++) {
			if(otp==null) otp = output[i] + "\n";
			else otp += output[i] + "\n";
		}
		return otp;
	}

	
	//Konstruktor
	RozmowaHistory(Boolean odczyt, Rozmowa roz, String data, Boolean nclear) {
		
		try {
			if(odczyt) {
				fr = new FileReader(FILENAME);
				br = new LineNumberReader(fr);
			
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					linenumbers++;
				}
				
				output = new String[linenumbers];
				fr = new FileReader(FILENAME);
				br = new LineNumberReader(fr);
				
				while ((sCurrentLine = br.readLine()) != null) {
					output[linenumbers - br.getLineNumber()] = sCurrentLine;
				
				}
			} else {
				fw = new FileWriter(FILENAME, nclear);
				bw = new BufferedWriter(fw);
				
				
				
				if(nclear) {
					bw.newLine();
					bw.write(data+ "   -   " + roz.getImie() + "       " + roz.getLata() + "       " + roz.getMiejscowosc() + "        " + roz.getZajecie());
				} else bw.write(32);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
			
		} finally {
			
			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}

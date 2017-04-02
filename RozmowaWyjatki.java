import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class RozmowaWyjatki extends Exception{
	private static final long serialVersionUID = 1L;

	RozmowaWyjatki(int w_id, JLabel l ,JTextArea c, String s) {
		switch (w_id) {
			case 0:	l.setForeground(new Color(255,0,0));
					c.setForeground(new Color(255,0,0));
					c.setText("Pole " + s + " nie jest wypełnione!");
					break;
			case 1:	l.setForeground(new Color(255,0,0));
					c.setForeground(new Color(255,0,0));
					c.setText("Podaj tylko jedno imię, wiek, miejscowość i hobby!");
					break;
			case 2:	l.setForeground(new Color(255,0,0));
					c.setForeground(new Color(255,0,0));
					c.setText("Pole " + s + " nie może zawierać liter!");
					break;
			case 3:	l.setForeground(new Color(255,0,0));
					c.setForeground(new Color(255,0,0));
					c.setText("Pole " + s + " nie może zawierać liczb!");
					break;
			default:
					break;
		}
	}

}

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class RozmowaGUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JButton[] but = new JButton[5];
	private JPanel panform, panbut, panwys, panhistbut,panhisttab,panhistlab;
	private Container[] container = new Container[2];
	private JTextField[] tefd = new JTextField[4];
	private JLabel[] lab = new JLabel[9];
	private String history;
	private JTextArea teaa1, teaa2;
	private Rozmowa roz1;
	private RozmowaHistory rozhist;
	
	
	private SimpleDateFormat formatter = new SimpleDateFormat("d MMM", Locale.getDefault());
	private Date dzisiaj = new Date();
	private String data;
	
	
	//Tworzenie Formularza
	private JPanel createForm(GridLayout g, EmptyBorder eb)  {
		panform = new JPanel(g);
		panform.setBorder(eb);
		
		panform.add(lab[0]);
		panform.add(tefd[0]);
		panform.add(lab[1]);
		panform.add(tefd[1]);
		panform.add(lab[2]);
		panform.add(tefd[2]);
		panform.add(lab[3]);
		panform.add(tefd[3]);
		
		return panform;
	}
	
	
	//Tworzenie Nazw Tabeli Historii
		private JPanel createHistTabLab(FlowLayout f, EmptyBorder eb)  {
			panhistlab = new JPanel(f);
			panhistlab.setBorder(eb);
				
			panhistlab.add(lab[4]);
			panhistlab.add(lab[5]);
			panhistlab.add(lab[6]);
			panhistlab.add(lab[7]);
			panhistlab.add(lab[8]);
				
				return panhistlab;
		}
	
	//Tworzenie Przycisków Historii
	private JPanel createHistBut(FlowLayout f, EmptyBorder eb)  {
		panhistbut = new JPanel(f);
			panhistbut.setBorder(eb);
			
			panhistbut.add(but[3]);
			panhistbut.add(but[4]);
			
			return panhistbut;
	}
	//Tworzenie Tablicy Historii
	private JPanel createHistTab(FlowLayout g, EmptyBorder eb)  {
		panhisttab = new JPanel(g);
		panhisttab.setBorder(eb);
			
		panhisttab.add(teaa2);
	
		return panhisttab;
	}
		
	//Tworzenie Wyświetlacza
	private JPanel createTextArea(FlowLayout f, EmptyBorder eb)  {
		panwys = new JPanel(f);
		panwys.setBorder(eb);
		
		panwys.add(teaa1);
		
		return panwys;
	}
	
	
	//Tworzenie Przycisków
	private JPanel createButtons(FlowLayout f, EmptyBorder eb)  {
		panbut = new JPanel(f);
		panbut.setBorder(eb);
		
		
		panbut.add(but[0]);
		panbut.add(but[1]);
		panbut.add(but[2]);
		return panbut;
	}
	
	
	//Modyfikowanie komponentów
	private void UpdateComp() {
		but[0].setSize(60, 20);
		but[0].setText("HISTORY");
		but[0].addActionListener(this);
		but[1].setSize(60, 20);
		but[1].setText("CLEAR");
		but[1].addActionListener(this);
		but[2].setSize(60, 20);
		but[2].setText("OK");
		but[2].addActionListener(this);
		but[3].setSize(60, 20);
		but[3].setText("BACK");
		but[3].addActionListener(this);
		but[4].setText("CLEAR");
		but[4].addActionListener(this);
		
		lab[0].setText("Imię :");
		lab[0].setHorizontalAlignment(SwingConstants.RIGHT);
		lab[1].setText("Wiek :");
		lab[1].setHorizontalAlignment(SwingConstants.RIGHT);
		lab[2].setText("Miejscowość :");
		lab[2].setHorizontalAlignment(SwingConstants.RIGHT);
		lab[3].setText("Hobby :");
		lab[3].setHorizontalAlignment(SwingConstants.RIGHT);
		lab[4].setText("  DD/MM  ");
		lab[4].setHorizontalAlignment(SwingConstants.CENTER);
		lab[5].setText("  Imię  ");
		lab[5].setHorizontalAlignment(SwingConstants.CENTER);
		lab[6].setText("  Wiek  ");
		lab[6].setHorizontalAlignment(SwingConstants.CENTER);
		lab[7].setText("  Miejscowość  ");
		lab[7].setHorizontalAlignment(SwingConstants.CENTER);
		lab[8].setText("  Hobby  ");
		lab[8].setHorizontalAlignment(SwingConstants.CENTER);
		
		
		tefd[0].setText(roz1.getImie());
		tefd[0].setName("Imię");
		tefd[1].setText(roz1.getLata());
		tefd[1].setName("Wiek");
		tefd[2].setText(roz1.getMiejscowosc());
		tefd[2].setName("Miejscowość");
		tefd[3].setText(roz1.getZajecie());
		tefd[3].setName("Hobby");
	}
	
  
	//Sprawdzanie poprawności pól
	private void Poprawne() throws RozmowaWyjatki{
		for (int i = 0; i < 4; i++) {
			//czy pusta
			if(tefd[i].getText().isEmpty()) {
				throw new RozmowaWyjatki(0, lab[i], teaa1, tefd[i].getName());
				
			} else if(tefd[i].getText().trim().contains(" ")){
				throw new RozmowaWyjatki(1, lab[i], teaa1, tefd[i].getName());
				
			} else if(i==1) {
				Rozmowa.chars = Rozmowa.characters.matcher(tefd[i].getText());
				if(Rozmowa.chars.find()) {
					throw new RozmowaWyjatki(2, lab[i], teaa1, tefd[i].getName());
				}
			} else if(i!=1) {
				Rozmowa.nums = Rozmowa.numbers.matcher(tefd[i].getText());
				if(Rozmowa.nums.find()) {
					throw new RozmowaWyjatki(3, lab[i], teaa1, tefd[i].getName());
				}
			}  	
		}
	}
	
	//Konstruktor
	RozmowaGUI() {

		data = formatter.format(dzisiaj);
		
		GridLayout glay = new GridLayout(4,2);
		glay.setVgap(10);
		glay.setHgap(10);
		
		FlowLayout flay = new FlowLayout();
		flay.setHgap(10);
		flay.setVgap(10);
		
		EmptyBorder borform = new EmptyBorder(10, 50, 0, 70);
		EmptyBorder bor2 = new EmptyBorder(20, 20, 10, 20);
		
		
		roz1 = new Rozmowa(null,null,null,null);
		
		setTitle("Rozmowa");
		setSize(400, 400);
		setResizable(false);
		setLocationRelativeTo(null); //centrowanie
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container[0] = getContentPane();
		container[1] = (Container) getGlassPane();
		
		container[0].setLayout(new BorderLayout());
		container[1].setLayout(new BorderLayout());
		container[1].setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		for (int i = 0; i < 9; i++) {
			lab[i] = new JLabel();
			if(i<4) tefd[i] = new JTextField();
			if(i<5) but[i] = new JButton();
		}
		
		UpdateComp();
		
		teaa1 = new JTextArea("Podaj informacje o sobie.");
		teaa1.setEditable(false);
		teaa1.setPreferredSize(new Dimension(350,200));
		
		teaa2 = new JTextArea();
		teaa2.setEditable(false);
		teaa2.setPreferredSize(new Dimension(320,250));
		
		container[0].add(createForm(glay, borform), BorderLayout.NORTH);
		container[0].add(createTextArea(flay, bor2), BorderLayout.CENTER);
		container[0].add(createButtons(flay, bor2), BorderLayout.SOUTH);
		
		container[1].add(createHistBut(flay, new EmptyBorder(10, 20, 10, 20)), BorderLayout.SOUTH);
		container[1].add(createHistTab(flay, new EmptyBorder(0, 0, 0, 0)), BorderLayout.CENTER);
		container[1].add(createHistTabLab(flay, new EmptyBorder(10, 20, 0, 20)), BorderLayout.NORTH);
	}
	
	
	
	//Obsługa zdarzeń
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == but[0]) {
			container[0].setVisible(false);
			container[1].setVisible(true);
			rozhist = new RozmowaHistory(true,roz1,data,true);
			teaa2.setText(rozhist.getOutput(history));
		}
		else if(e.getSource() == but[1]) {
			for (int i = 0; i < 4; i++) {
				tefd[i].setText(null);
				lab[i].setForeground(but[0].getForeground());
			}
			teaa1.setForeground(but[0].getForeground());
			teaa1.setText("Podaj informacje o sobie.");
		}
		else if (e.getSource() == but[2]) {
			for (int i = 0; i < 4; i++) {
				lab[i].setForeground(but[0].getForeground());
			}
			teaa1.setForeground(but[0].getForeground());
			roz1.setImie(tefd[0].getText().trim());
			roz1.setLata(tefd[1].getText().trim());
			roz1.setMiejscowosc(tefd[2].getText().trim());
			roz1.setZajecie(tefd[3].getText().trim());
			try { Poprawne(); 

			} catch (RozmowaWyjatki e1) {
				System.err.println("BŁĄD!");
				e1.printStackTrace();
			} finally {
				roz1.setImie(roz1.getImie().substring(0, 1).toUpperCase() + roz1.getImie().substring(1));
				roz1.setMiejscowosc(roz1.getMiejscowosc().substring(0, 1).toUpperCase() + roz1.getMiejscowosc().substring(1));
				teaa1.setForeground(but[0].getForeground());
				teaa1.setText(" Cześć "+ roz1.getImie() +"! Masz "+roz1.getLata() +" lat \n"
						+ " mieszkasz w " + roz1.getMiejscowosc() +" i twoim hobby jest "+ roz1.getZajecie());
				rozhist = new RozmowaHistory(false,roz1,data,true);
			}
		} 
		else if (e.getSource() == but[3]) {
			container[0].setVisible(true);
			container[1].setVisible(false);
		}
		else if(e.getSource() == but[4]) {
			rozhist = new RozmowaHistory(false,roz1,data,false);
			teaa2.setText(rozhist.getOutput(history));
		}
	}
	
	
	//metoda uruchomieniowa
	public static void main(String[] args) {
		RozmowaGUI rgui = new RozmowaGUI();
		rgui.setVisible(true);
	}
}

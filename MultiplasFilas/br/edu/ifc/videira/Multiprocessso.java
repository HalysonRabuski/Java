package br.edu.ifc.videira;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import br.edu.ifc.videira.Processo;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
public class Multiprocessso extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	
	int fila = 1;
	int seconds=0;
	int i;
	Random rand = new Random();
	private JTextField tf5;
	private JTextField tf6;
	private JTextField tf7;
	private JTextField tf8;
	
	Random r = new Random();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Multiprocessso frame = new Multiprocessso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	/**
	 * Create the frame.
	 */
	public Multiprocessso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSimuladorDeMultiprocesso = new JLabel("Simulador de multiprocessos");
		lblSimuladorDeMultiprocesso.setBounds(233, 10, 170, 14);
		contentPane.add(lblSimuladorDeMultiprocesso);
		
		JLabel lblQuantidadeDeProcessos = new JLabel("Quantidade de processos:");
		lblQuantidadeDeProcessos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblQuantidadeDeProcessos.setBounds(20, 36, 202, 14);
		contentPane.add(lblQuantidadeDeProcessos);
		
		JSpinner spN = new JSpinner();
		spN.setModel(new SpinnerNumberModel(5, 5, 75, 1));
		spN.setBounds(194, 35, 53, 25);
		contentPane.add(spN);
		
		JLabel lblFilas = new JLabel("Fila 1");
		lblFilas.setBounds(269, 40, 53, 14);
		contentPane.add(lblFilas);
		JTextArea ta1 = new JTextArea();

		tf1 = new JTextField();
		tf1.setEditable(false);
		tf1.setBounds(306, 37, 86, 20);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		tf2 = new JTextField();
		tf2.setEditable(false);
		tf2.setColumns(10);
		tf2.setBounds(306, 63, 86, 20);
		contentPane.add(tf2);
		
		tf3 = new JTextField();
		tf3.setEditable(false);
		tf3.setColumns(10);
		tf3.setBounds(306, 90, 86, 20);
		contentPane.add(tf3);
		
		tf4 = new JTextField();
		tf4.setEditable(false);
		tf4.setColumns(10);
		tf4.setBounds(306, 118, 86, 20);
		contentPane.add(tf4);
		
		JLabel lblSeg = new JLabel("5 SEG");
		lblSeg.setBounds(543, 40, 46, 14);
		contentPane.add(lblSeg);
		
		JLabel lblSeg_1 = new JLabel("6 SEG");
		lblSeg_1.setBounds(543, 66, 46, 14);
		contentPane.add(lblSeg_1);
		
		JButton btnNewButton = new JButton("INICIAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Timer timer = new Timer();
				int number;
				
				btnNewButton.setEnabled(false);
				
			
				number = (int) spN.getValue();
				
				Processo[] pr = new Processo[number];
				for(i=0;i<number;i++) {
					pr[i] = new Processo();
					pr[i].setN(i+1);
					pr[i].setTempo(r.nextInt(20)+5);
				}
				i=0;
				TimerTask ttask = new TimerTask() {
					
					@Override
					public void run() {
						seconds++;
						if(fila==1) {
							tf5.setText(seconds+"");
							tf1.setText(pr[i].getN()+"");
						}else if(fila==2) {
							tf6.setText(seconds+"");
							tf2.setText(pr[i].getN()+"");
							tf1.setText(null);
							tf5.setText(null);
						}else if(fila==3) {
							tf7.setText(seconds+"");
							tf3.setText(pr[i].getN()+"");
							tf2.setText(null);
							tf6.setText(null);
						}else if(fila==4) {
							tf8.setText(seconds+"");
							tf4.setText(pr[i].getN()+"");
							tf3.setText(null);
							tf7.setText(null);
						}
						
						if((pr[i].getTempo()==0)&&(i<(number-1))) {
							i++;
						}
						
						if(fila<4) {
							pr[i].setExecutou(pr[i].getExecutou()+1);
							if((pr[i].getTempo()-pr[i].getExecutou()>0)) {
								ta1.append("O processo " +pr[i].getN()+ " executou por "+pr[i].getExecutou()+" e faltam "+ (pr[i].getTempo()-pr[i].getExecutou())+" para terminar\n");
							}else if(pr[i].getExecutou()==pr[i].getTempo()) {
								ta1.append("O processo " +pr[i].getN()+ " executou por "+pr[i].getExecutou()+" segundos e terminou na fila "+fila+"\n");
								pr[i].setTempo(0);
							}
							if((seconds>=5)&&(fila==1)) {
								i++;
								seconds=0;
							}else if((seconds>=6)&&(fila==2)) {
								i++;
								seconds=0;
							}else if((seconds>=7)&&(fila==3)) {
								i++;
								seconds=0;
							}
							if(i==number) {
								seconds = 0;
								fila++;
								i=0;
							}
						}else if(fila>=4){						
							if((pr[i].getTempo()==0)&&(i<number-1)) {
								i++;
							}else if(pr[i].getTempo()!=0) {
								pr[i].setExecutou(pr[i].getExecutou()+1);
								if(pr[i].getTempo()-pr[i].getExecutou()==0) {
									ta1.append("O processo " +pr[i].getN()+ " executou por "+pr[i].getExecutou()+" segundos e terminou na fila "+fila+"\n");
									pr[i].setTempo(0);
								}
							}
						}
					}
				};
				
					timer.scheduleAtFixedRate(ttask, 0, 1000);
				

				
				
			}
		});
		btnNewButton.setBounds(269, 179, 89, 23);
		contentPane.add(btnNewButton);
		
		tf5 = new JTextField();
		tf5.setEditable(false);
		tf5.setBounds(447, 37, 86, 20);
		contentPane.add(tf5);
		tf5.setColumns(10);
		
		tf6 = new JTextField();
		tf6.setEditable(false);
		tf6.setColumns(10);
		tf6.setBounds(447, 63, 86, 20);
		contentPane.add(tf6);
		
		tf7 = new JTextField();
		tf7.setEditable(false);
		tf7.setColumns(10);
		tf7.setBounds(447, 90, 86, 20);
		contentPane.add(tf7);
		
		tf8 = new JTextField();
		tf8.setEditable(false);
		tf8.setColumns(10);
		tf8.setBounds(447, 118, 86, 20);
		contentPane.add(tf8);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 244, 479, 158);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(ta1);
		
		JLabel lblFilas_1 = new JLabel("Fila 2\r\n");
		lblFilas_1.setBounds(269, 66, 53, 14);
		contentPane.add(lblFilas_1);
		
		JLabel lblFila = new JLabel("Fila 3");
		lblFila.setBounds(269, 94, 53, 14);
		contentPane.add(lblFila);
		
		JLabel lblFila_1 = new JLabel("Fila 4");
		lblFila_1.setBounds(269, 121, 53, 14);
		contentPane.add(lblFila_1);
		
		JLabel lblSeg_3 = new JLabel("7 SEG");
		lblSeg_3.setBounds(543, 93, 53, 14);
		contentPane.add(lblSeg_3);
		
		JLabel lblInfinito = new JLabel("infinito");
		lblInfinito.setBounds(543, 121, 53, 14);
		contentPane.add(lblInfinito);
	}
}

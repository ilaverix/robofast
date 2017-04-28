package com.robofast.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import com.robofast.data.FicherosSeleccionados;

public class Gui {

	JFileChooser fc;
	FicherosSeleccionados ficheros;
	String origen;
	String destino;
	JFrame console;
	Process p;
	
	public Gui()
	{
		realizarCopiado();
		//showConsole(null);
		//generateJFrame();
	}
	
	private void operacionesSalida()
	{
		p.destroy();
	}
	private JTextArea generateJFrame()
	{
		console=new JFrame();
		console.setTitle("Copying Files....");
		console.setPreferredSize(new Dimension(500,500));
		console.setSize(new Dimension(500,500));
		console.setLocationRelativeTo(null);
		Image im = Toolkit.getDefaultToolkit().getImage("images/robofast.gif");
		console.setIconImage(im);
		console.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		console.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				operacionesSalida();
			}
		});
		JTextArea textArea=new JTextArea("Test");
		textArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		Font fuente=textArea.getFont();
		fuente=new Font(fuente.getName(),fuente.getStyle(),16);
		textArea.setFont(fuente);
		JScrollPane scroll=new JScrollPane(textArea);
		console.add(scroll);
		console.setVisible(true);
		return textArea;
	}
	
	public void showConsole(InputStream in,Process p)
	{
		this.p=p;
		JTextArea l=generateJFrame();
		BufferedReader inr = new BufferedReader(  
                new InputStreamReader(in));  
		String line = null;  
		try{
			while ((line = inr.readLine()) != null) {
				String lineact=l.getText()+line+"\n";
				l.setText(lineact);
				console.repaint();
			}
		}
			catch(Exception e)
			{
				System.exit(2);
			}
		
	}
	
	
	private void realizarCopiado()
	{
		fc=new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setMultiSelectionEnabled(false);
		
		int respuesta = fc.showOpenDialog(null);
		
		if(respuesta==JFileChooser.APPROVE_OPTION)
		{
			origen = fc.getSelectedFile().getAbsolutePath();
			
			int respuestaDestino = fc.showSaveDialog(null);
			
			if(respuestaDestino==JFileChooser.APPROVE_OPTION)
			{
				destino = fc.getSelectedFile().getAbsolutePath();
				FicherosSeleccionados copy=new FicherosSeleccionados(origen,destino,this);
				if(copy.execute())
				{
					//JOptionPane.showMessageDialog(null, "Success!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Error during copying files");
				}
			}
			
		}
		
		fc.removeAll();
	}
}

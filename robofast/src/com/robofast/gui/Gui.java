package com.robofast.gui;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.robofast.data.FicherosSeleccionados;

public class Gui {

	JFileChooser fc;
	FicherosSeleccionados ficheros;
	String origen;
	String destino;
	
	public Gui()
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
				FicherosSeleccionados copy=new FicherosSeleccionados(origen,destino);
				if(copy.execute())
				{
					JOptionPane.showMessageDialog(null, "Success!");
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

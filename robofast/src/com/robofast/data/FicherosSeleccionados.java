package com.robofast.data;

import com.robofast.gui.Gui;

public class FicherosSeleccionados {
	
	private String origen;
	private String destino;
	private Gui gui;
	
	public FicherosSeleccionados(String origen,String destino,Gui gui)
	{
		this.gui=gui;
		if(origen.length()==3)
		{
			//Se ha indicado una unidad
			this.origen=origen.substring(0, 2);
		}
		else
		{
			this.origen=origen;
		}
		if(destino.length()==3)
		{
			//Se ha indicado una unidad
			this.destino=destino.substring(0, 2);
		}
		else
		{
			this.destino=destino;
		}
		
		
	}
	
	public boolean execute()
	{
		
		try
		{
			String comando="ROBOCOPY /E \""+origen+"\" \""+destino+"\"";
			
			//System.out.println(comando);
			
			Process p=Runtime.getRuntime().exec(comando);
			
			gui.showConsole(p.getInputStream(),p);
			
			
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}

}

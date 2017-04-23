package com.robofast.data;


public class FicherosSeleccionados {
	
	private String origen;
	private String destino;
	
	public FicherosSeleccionados(String origen,String destino)
	{
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
			
			Runtime.getRuntime().exec(comando).waitFor();
			
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}

}

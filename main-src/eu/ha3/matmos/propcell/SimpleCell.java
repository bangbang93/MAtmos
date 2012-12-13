package eu.ha3.matmos.propcell;

import eu.ha3.matmos.propcell.contract.PCell;
import eu.ha3.matmos.propcell.contract.PPropertyHolder;
import eu.ha3.matmos.propcell.contract.PWorkspace;

/*
            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE 
                    Version 2, December 2004 

 Copyright (C) 2004 Sam Hocevar <sam@hocevar.net> 

 Everyone is permitted to copy and distribute verbatim or modified 
 copies of this license document, and changing it is allowed as long 
 as the name is changed. 

            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE 
   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION 

  0. You just DO WHAT THE FUCK YOU WANT TO. 
*/

public abstract class SimpleCell implements PCell
{
	private PWorkspace workspace;
	private PPropertyHolder properties;
	private PPropertyHolder state;
	
	public SimpleCell(PWorkspace workspace, PPropertyHolder properties)
	{
		this.workspace = workspace;
		this.properties = properties;
		this.state = new SimpleProperties();
	}
	
	@Override
	public PWorkspace workspace()
	{
		return this.workspace;
	}
	
	@Override
	public PPropertyHolder properties()
	{
		return this.properties;
	}
	
	@Override
	public PPropertyHolder state()
	{
		return this.state;
	}
	
}

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

public class ConditionCell extends SimpleCell
{
	public ConditionCell(PWorkspace workspace, PPropertyHolder properties)
	{
		super(workspace, properties);
	}
	
	@Override
	public boolean isStartThinking()
	{
		return false;
	}
	
	@Override
	public int think()
	{
		PCell cell = workspace().getCell(properties().getString("leftcell"));
		String id = properties().getString("id");
		
		if (cell instanceof CellNullObject)
			return 0;
		
		String type = cell.properties().getString(id + "$type");
		boolean result = false;
		
		if (type.equals("INT"))
		{
			result = cell.state().getInteger(id) > 0;
		}
		
		if (result != state().getBoolean("result"))
		{
			state().setProperty("result", result);
			PUtils.wake(state().getString("listeners"));
		}
		
		return PUtils.intOf(properties(), "__think", 1);
	}
}

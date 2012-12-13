package eu.ha3.matmos.propcell.contract;

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

public interface PCell
{
	public PWorkspace workspace();
	
	/**
	 * Should this cell think as soon as the system starts?
	 * 
	 * @param enabled
	 */
	public boolean isStartThinking();
	
	/**
	 * Think routine. Returns number of ticks before it runs again. If the cell
	 * returns 0, it stops running until woken up.
	 * 
	 * @return
	 */
	public int think();
	
	/**
	 * Extracts the properties of a cell.
	 * 
	 * @return
	 */
	public PPropertyHolder properties();
	
	/**
	 * Extracts the state of a cell
	 * 
	 * @return
	 */
	public PPropertyHolder state();
}

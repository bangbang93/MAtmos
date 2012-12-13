package eu.ha3.matmos.propcell;

import eu.ha3.matmos.propcell.contract.PPropertyHolder;
import eu.ha3.util.property.simple.PropertyMissingException;
import eu.ha3.util.property.simple.PropertyTypeException;

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

public class PUtils
{
	public static int intOf(PPropertyHolder holder, String name, int def)
	{
		try
		{
			return holder.getInteger(name);
		}
		catch (PropertyMissingException e)
		{
			return def;
		}
		catch (PropertyTypeException e)
		{
			return def;
		}
	}
	
	public static long longOf(PPropertyHolder holder, String name, long def)
	{
		try
		{
			return holder.getLong(name);
		}
		catch (PropertyMissingException e)
		{
			return def;
		}
		catch (PropertyTypeException e)
		{
			return def;
		}
	}
	
	public static float floatOf(PPropertyHolder holder, String name, float def)
	{
		try
		{
			return holder.getFloat(name);
		}
		catch (PropertyMissingException e)
		{
			return def;
		}
		catch (PropertyTypeException e)
		{
			return def;
		}
	}
	
	public static double intOf(PPropertyHolder holder, String name, double def)
	{
		try
		{
			return holder.getDouble(name);
		}
		catch (PropertyMissingException e)
		{
			return def;
		}
		catch (PropertyTypeException e)
		{
			return def;
		}
	}
	
	public static String stringOf(PPropertyHolder holder, String name, String def)
	{
		try
		{
			return holder.getString(name);
		}
		catch (PropertyMissingException e)
		{
			return def;
		}
		catch (PropertyTypeException e)
		{
			return def;
		}
	}
	
	public static void wake(String list)
	{
	}
}

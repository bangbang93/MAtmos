package eu.ha3.matmos.engine0.datacustom;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import eu.ha3.matmos.engine0.core.interfaces.Data;
import eu.ha3.matmos.engine0.game.data.abstractions.processor.ProcessorModel;

/* x-placeholder */

@Deprecated
public class MAtProcessorSeasonsModAPI extends ProcessorModel
{
	private Map<Season, Integer> seasonsHash;
	
	public MAtProcessorSeasonsModAPI(Data dataIn, String normalNameIn, String deltaNameIn)
	{
		super(dataIn, normalNameIn, deltaNameIn);
		
		this.seasonsHash = new HashMap<Season, Integer>();
		this.seasonsHash.put(Season.NotInstalled, -1);
		this.seasonsHash.put(Season.NoSeason, 0);
		this.seasonsHash.put(Season.Spring, 1);
		this.seasonsHash.put(Season.Summer, 2);
		this.seasonsHash.put(Season.Autumn, 3);
		this.seasonsHash.put(Season.Winter, 4);
	}
	
	@Override
	protected void doProcess()
	{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.thePlayer;
		int x = (int) Math.floor(player.posX);
		int y = (int) Math.floor(player.posY);
		int z = (int) Math.floor(player.posZ);
		
		if (y < 0)
		{
			y = 0;
		}
		else if (y > 255)
		{
			y = 255;
		}
		
		Integer ic = this.seasonsHash.get(Season.getSeason_Client(x, y, z));
		if (ic == null)
		{
			ic = -2;
		}
		
		setValueLegacyIntIndexes(0, 1);
		setValueLegacyIntIndexes(1, ic);
	}
}

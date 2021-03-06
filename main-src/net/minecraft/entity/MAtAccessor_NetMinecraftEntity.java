package net.minecraft.entity;

/*
--filenotes-placeholder
*/

public class MAtAccessor_NetMinecraftEntity
{
	private static final MAtAccessor_NetMinecraftEntity instance = new MAtAccessor_NetMinecraftEntity();
	
	private MAtAccessor_NetMinecraftEntity()
	{
		System.err.println(MAtAccessor_NetMinecraftEntity.class.toString() + " WON'T WORK");
	}
	
	public static MAtAccessor_NetMinecraftEntity getInstance()
	{
		return instance;
	}
	
	public boolean isJumping(EntityLivingBase entityLiving)
	{
		return entityLiving.isJumping;
	}
	
	public boolean isInWeb(EntityLivingBase entityLiving)
	{
		return entityLiving.isInWeb;
	}
}

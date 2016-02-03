package com.cheenar.minecraft.bukkit.pde;

import org.bukkit.plugin.java.JavaPlugin;

public class PercentDeathEXP extends JavaPlugin
{

	public static double percent;
	
	@Override
	public void onEnable()
	{
		this.saveDefaultConfig();
		
		if(this.getConfig().getDouble("percent") != -1)
		{
			percent = this.getConfig().getDouble("percent")/100.0;
		}
		else
		{
			
		}
		this.getLogger().info("[PercentDeathEXP] Percent Set: " + percent);
		
		this.getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
}

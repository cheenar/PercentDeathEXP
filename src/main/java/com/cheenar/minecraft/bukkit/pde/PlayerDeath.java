package com.cheenar.minecraft.bukkit.pde;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerDeath implements Listener
{

	private HashMap<String, Double> players = new HashMap<String, Double>();
	
	private JavaPlugin core;
	
	public PlayerDeath(JavaPlugin plugin)
	{
		this.core = plugin;
	}
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event)
	{
		core.getLogger().info("dead");
		double currentExp = event.getEntity().getLevel();
		players.put(event.getEntity().getName(), currentExp);
	}
	
	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event)
	{
		final PlayerRespawnEvent e = event;
		core.getLogger().info("spawn");
		Runnable addExp = new Runnable() {
			@Override
			public void run()
			{
				double exp = players.get(e.getPlayer().getName()) * PercentDeathEXP.percent;
				e.getPlayer().setLevel((int)exp);
				players.remove(e.getPlayer().getName());
			}
		};
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(core, addExp, 20L);
	}
	
}

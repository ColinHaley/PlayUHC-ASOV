package com.gmail.val59000mc.playuhc.mc1_8.schematics;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class SchematicHandler {
	
	public static ArrayList<Integer> pasteSchematic(Location loc, String path) throws MaxChangedBlocksException, com.sk89q.worldedit.data.DataException, IOException{
		WorldEditPlugin we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
		Bukkit.getLogger().info("[PlayUHC] Pasting "+path);
		File schematic = new File(path);
		EditSession session = we.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(loc.getWorld()), 1000000);
		
		CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
		clipboard.paste(session, new Vector(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()), false);

		ArrayList<Integer> dimensions = new ArrayList<Integer>();
		dimensions.add(clipboard.getHeight());
		dimensions.add(clipboard.getLength());
		dimensions.add(clipboard.getWidth());
		
		Bukkit.getLogger().info("[PlayUHC] Successfully pasted '"+path+"' at "+loc.getBlockX()+" "+loc.getBlockY()+" "+loc.getBlockZ());
		return dimensions;
		
	}
}

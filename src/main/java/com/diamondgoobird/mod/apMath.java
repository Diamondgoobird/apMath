package com.diamondgoobird.mod;

import java.io.File;
import java.util.regex.Pattern;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommand;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.*;

@Mod(modid = DGBGlobal.MOD_ID, name = DGBGlobal.MOD_NAME, version = DGBGlobal.VERSION)
public class apMath {
	public final static Configuration Config = new Configuration(new File("config/ApMath.cfg"));
	public static boolean toggle;
	public static boolean both;
	public final static String modPrefix = "\u00A7b[APMATH]: \u00A7r";	
	@Instance(DGBGlobal.MOD_ID)
	public static apMath instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		loadConfig();
	}
	@EventHandler
	public void Init(FMLInitializationEvent Event) {
		System.out.println(modPrefix + "Initiallizing Commands...");
		ClientCommandHandler.instance.registerCommand(new apMathCommand());
		System.out.println(modPrefix + "Completed Initiallizing Commands");
		MinecraftForge.EVENT_BUS.register(new apDetector());
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
	}
	public static void loadConfig() {
		System.out.println(modPrefix + "Initiallizing Mod...");	
		Config.load();
		toggle = Config.getBoolean("toggle", "values", true, "Toggles whether the mod is enabled");
		both = Config.getBoolean("both", "values", true, "If true, then it tells you the regular message and how much you have left.");
		Config.save();
	}
}



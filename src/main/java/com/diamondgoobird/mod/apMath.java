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

@Mod(modid = apMathVariables.MOD_ID, name = apMathVariables.MOD_NAME, version = apMathVariables.VERSION)
public class apMath {
	public final static String modPrefix = "\u00A7b[APMATH]: \u00A7r";	
	@Instance(apMathVariables.MOD_ID)
	public static apMath instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		apMathVariables.checkConfig(null);
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
}



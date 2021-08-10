package com.diamondgoobird.mod;

import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.regex.Matcher;

public class apDetector {
	
	
	@SubscribeEvent
	public void getNumbers(ClientChatReceivedEvent event) {
		if (event.message.getUnformattedText().contains("a>>") && event.message.getUnformattedText().contains("Progress")) {	
		apMath.Config.load();
		apMath.toggle = apMath.Config.getBoolean("toggle", "values", true, "Toggles whether the mod is enabled");
		if (apMath.toggle == false) {
			
		}
		else {
		String num1;
		String num2;
		String pattern = "(?<numbers>\\d+)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(event.message.getFormattedText());
		int x = 0;
		String[] q = new String[2];
		try {
			m.find();
			q[0] = m.group("numbers");
			m.find();
			m.find();
			q[1] = m.group("numbers");
		}
		catch (Exception oop) {
			oop.printStackTrace();
		}
		int number1;
		int number2;
		num1 = q[0];
		num2 = q[1];
			if (num1 != null && num2 != null) {
				number1 = Integer.parseInt(num1);
				number2 = Integer.parseInt(num2);
				System.out.println("I got numbers " + num1 + " and " + num2);
				//String replacing = "\u00A7" + "r" + "\u00A7" + "a" +number1 + "\u00A7" + "r" + "\u00A7" + "7/" + "\u00A7" + "r" + "\u00A7" + "a" + number2 + "\u00A7" + "r" + "\u00A7" + "r";
				String replacing = "\u00A7r\u00A7a" + number1 + "\u00A7r\u00A77/\u00A7r\u00A7a" + number2 + "\u00A7r";
				System.out.println("Replacing: " + replacing);
				String replacement;
				if (apMath.both == false) {
					int difference = number2 - number1;
					event.message = new ChatComponentText(event.message.getFormattedText().replace(replacing, "\u00A7" + "a" + difference + " left."));
					Minecraft.getMinecraft().thePlayer.addChatComponentMessage(event.message);
				}
				else {
					int difference = number2 - number1;
					event.message = new ChatComponentText(event.message.getFormattedText().replace(replacing, "\u00A7" + "a" + difference + " left. " + "\u00A7" + "a" + "(" + num1 + "\u00A7" + "7/" + "\u00A7" + "a" + num2 + ")"));
					Minecraft.getMinecraft().thePlayer.addChatComponentMessage(event.message);
				}
				event.setCanceled(true);
			}
			}
		}
	}
}
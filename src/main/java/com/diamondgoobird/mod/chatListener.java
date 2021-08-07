package com.diamondgoobird.mod;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class chatListener {
	@SubscribeEvent
	public void getNumbers(ClientChatReceivedEvent event) {
		apMath.Config.load();
		apMath.toggle = apMath.Config.getBoolean("toggle", "values", true, "Toggles whether the mod is enabled");
		if (apMath.toggle == false) {
			
		}
		else {
		String message = event.message.getUnformattedText();
		String num1 = "";
		String num2 = "";
		String withoutNumbers = "";
		int number1;
		int number2;
		boolean number = false;
		int[] e = new int[message.length()];
		int funnycharacter = 0;
		char[] u = EnumChatFormatting.RESET.toString().toCharArray();
		
		if (message.contains("a>>")) {
			char[] characters = message.toCharArray();
			int x = 0;
			int z = -1;
			while (x < message.length()) {
				if (characters[x] == ':') {
					int xd = 0;
					while (x > xd) {
						String a = 
						withoutNumbers = withoutNumbers + characters[xd];
						xd++;
					}
					withoutNumbers = withoutNumbers + ":";
					z = x + 3;
				}
				else if (characters[x] == '/') {
					number = true;
				}
				else if (characters[x] == u[0] && z != -1) {
					funnycharacter++;
				}
				
				else {
					if (z != -1) {
						String a = "";
						a = a + characters[x];
						boolean fail = false;
						try {
							//System.out.println("Converting " + a + " to integer");
							e[x] = Integer.parseInt(a);
						}
						catch (Exception jhjhj) {
							fail = true;
						}
						if (fail == false) {
							if (number == true) {
								num2 = num2 + characters[x];
							}
							else {
								if (funnycharacter > 1) {
									
								}
								else {
									num1 = num1 + characters[x];
								}
							}
						}
					}
				}
				x++;
			}
			if (num1 != null && num2 != null) {
				number1 = Integer.parseInt(num1);
				number2 = Integer.parseInt(num2);
				System.out.println("I got numbers " + num1 + " and " + num2);
				event.setCanceled(true);
				if (apMath.both == false) {
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(withoutNumbers + EnumChatFormatting.GREEN + " " + (number2 - number1) + " left."));
				}
				else {
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(withoutNumbers + EnumChatFormatting.GREEN + " " + (number2 - number1) + " left. (" + number1 + EnumChatFormatting.GRAY + "/" + EnumChatFormatting.GREEN + number2 + ")"));
				}
			}
			}
		}
	}
}

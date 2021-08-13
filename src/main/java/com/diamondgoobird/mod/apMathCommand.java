package com.diamondgoobird.mod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class apMathCommand extends CommandBase {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "apm";
	}

	@Override
	public int getRequiredPermissionLevel()
    {
        return 0;
    }
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "Toggles whether Tiered Achievements tell you how much is left.";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length <= 0) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Please try again (Type '/apm help' for options)"));
			return;
        }
		
		else if (args[0].equalsIgnoreCase("enable")) {
			try {
				DGBGlobal.set(apMath.Config.getConfigFile(),"toggle","true","    B:toggle=","boolean");
				apMath.loadConfig();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Successfully" + EnumChatFormatting.GREEN + " Enabled " + EnumChatFormatting.AQUA + "AP Math!"));
		}
		
		else if (args[0].equalsIgnoreCase("disable")) {
			try {
				DGBGlobal.set(apMath.Config.getConfigFile(),"toggle","false","    B:toggle=","boolean");
				apMath.loadConfig();
				//DGBGlobal.set(Main.Config.getConfigFile(),"both","false","    B:both=","boolean");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Successfully" + EnumChatFormatting.RED + " Disabled " + EnumChatFormatting.AQUA + "AP Math!"));
		}
		
		else if (args[0].equalsIgnoreCase("both")) {
			try {
				if (apMath.both == true) {
					DGBGlobal.set(apMath.Config.getConfigFile(),"both","false","    B:both=","boolean");
					apMath.loadConfig();
					apMath.both = false;
					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Successfully" + EnumChatFormatting.GOLD + " Disabled " + EnumChatFormatting.AQUA + "making it print both modes."));
				}
				else {
					DGBGlobal.set(apMath.Config.getConfigFile(),"both","true","    B:both=","boolean");
					apMath.loadConfig();
					apMath.both = true;
					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Successfully" + EnumChatFormatting.GOLD + " Enabled " + EnumChatFormatting.AQUA + "making it print both modes."));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (args[0].equalsIgnoreCase("help")) {
			apMath.loadConfig();
			EnumChatFormatting a = EnumChatFormatting.AQUA;
			EnumChatFormatting da = EnumChatFormatting.DARK_AQUA;
        	sender.addChatMessage(new ChatComponentText(a + "/apm " + a + "enable" + da + "/" + a + "disable " + da + "- " + a + "Disables/Enables automatically calculating how many are left."));
        	sender.addChatMessage(new ChatComponentText(a + "/apm " + a + "both " + da + "- " + a + "Toggles whether it tells you how many left and the regular output."));
        	sender.addChatMessage(new ChatComponentText(a + "/apm " + a + "help " + da + "- " + a + "Displays this message."));
		}
		
	}
	
	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
    	return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[] {"help","enable", "disable", "both"}): null;
    }
	@Override
	public List<String> getCommandAliases() {
		List<String> a = new ArrayList();
		a.add("apmath");
		a.add("achievementpointmath");
		return a;
		
	}

}

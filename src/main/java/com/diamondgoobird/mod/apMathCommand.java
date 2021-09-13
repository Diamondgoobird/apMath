package com.diamondgoobird.mod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class apMathCommand extends CommandBase {
	public final String[] options = {"help","enable","disable","debug","both","variable"};
	public final String help[] = {
			"&b/apm enable&3/&bdisable&3 - &bDisables/Enables automatically calculating how many are left.",
			"&b/apm both&3 - &bToggles whether it tells you how many left and the regular output.",
			"&b/apm debug&3 - &bMainly used for mod testing, prints out config.",
			"&b/apm help&3 - &bDisplays this message."};
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return apMathVariables.CommandName;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		apMathVariables.printLines(help);
		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		
		if (args.length <= 0) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Please try again (Type '/"+ apMathVariables.CommandName + " help' for options)"));
			return;
        }
		
		else if (args[0].equalsIgnoreCase(options[0])) {
			apMathVariables.printLines(help);
		}
		
		else if (args[0].equalsIgnoreCase(options[1])) {
			apMathVariables.changeVariable("Toggle", "Enabled");
		}
		
		else if (args[0].equalsIgnoreCase(options[2])) {
			apMathVariables.changeVariable("Toggle", "Disabled");
		}
		else if (args[0].equalsIgnoreCase(options[3])) {
			debug();
		}
		else if (args[0].equalsIgnoreCase(options[4])) {
			if (apMathVariables.checkVariable("Both").equalsIgnoreCase("yes")) {
				apMathVariables.changeVariable("Both", "no");
			}
			else {
				apMathVariables.changeVariable("Both", "yes");
			}
		}
	}
	
	public static void debug() {
		String[] stuff = apMathVariables.checkConfig(null);
		String[] things = apMathVariables.things;
		String output = "";
		int x = 0;
		while (stuff.length > x) {
			String comma = ", ";
			if (stuff.length == x + 1) {
				comma = "";
			}
			output = output + things[x] + ": " + stuff[x] + comma;
			x++;
		}
		apMathVariables.print(output);
		apMathVariables.printConsole(output);
	
	}
	
	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, options): null;
    }
	
	@Override
	public List<String> getCommandAliases() {
		List<String> a = new ArrayList();
		a.add("apmath");
		a.add("achievementpointmath");
		return a;
		
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
	
	@Override
	public int getRequiredPermissionLevel()
    {
        return 0;
    }


}

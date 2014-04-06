package com.fvza.rankup;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankupCommand implements CommandExecutor {
    private final Rankup plugin;
    
    public RankupCommand(Rankup plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {
        if( !(sender instanceof Player) ){
            return false;
        }
        
        Player player = (Player) sender;
        
        if( command.getName().equalsIgnoreCase("rankup") ) {
            if(args.length == 0){
                plugin.rankup(player);
            } else if ( args.length == 1 ){
                if( args[0].equalsIgnoreCase("v") || args[0].equalsIgnoreCase("version") ){
                    if( player.hasPermission(Rankup.PERMISSION_VERSION)){
                        player.sendMessage(plugin.translate("version.success", plugin.getDescription().getVersion()));
                    } else {
                        player.sendMessage(plugin.translate("version.no-permission"));
                    }
                } else if ( args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("reload") ){
                    if( player.hasPermission("rankup.reload" )){
                        plugin.reload();
                        player.sendMessage(plugin.translate("reload.success"));
                    } else {
                        player.sendMessage(plugin.translate("reload.no-permission"));
                    }
                }
            }
        }
        return true;
    }
}

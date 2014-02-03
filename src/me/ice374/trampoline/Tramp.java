package me.ice374.trampoline;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Tramp extends JavaPlugin implements Listener {

    private ArrayList<Player> tramp = new ArrayList<Player>();

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.BED_BLOCK) {
            e.getPlayer().setVelocity(e.getPlayer().getVelocity().setX(0));
            e.getPlayer().setVelocity(e.getPlayer().getVelocity().setY(1.0));
            e.getPlayer().setVelocity(e.getPlayer().getVelocity().setZ(0));
            tramp.add(e.getPlayer());
        }
        else if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.HUGE_MUSHROOM_1 || 
                e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.HUGE_MUSHROOM_2 ){
            e.getPlayer().setVelocity(e.getPlayer().getVelocity().setX(0));
            e.getPlayer().setVelocity(e.getPlayer().getVelocity().setY(2.0));
            e.getPlayer().setVelocity(e.getPlayer().getVelocity().setZ(0));
            tramp.add(e.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getCause() == DamageCause.FALL && tramp.contains(p)) {
                e.setDamage(0.0);
                tramp.remove(p);
            }
        }
    }
}
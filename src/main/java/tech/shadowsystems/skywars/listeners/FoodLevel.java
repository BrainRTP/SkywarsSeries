package tech.shadowsystems.skywars.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import tech.shadowsystems.skywars.Skywars;
import tech.shadowsystems.skywars.object.Game;
import tech.shadowsystems.skywars.object.GamePlayer;

/**
 * Copyright © 2016 Jordan Osterberg and Shadow Technical Systems LLC. All rights reserved. Please email jordan.osterberg@shadowsystems.tech for usage rights and other information.
 */
public class FoodLevel implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            for (Game game : Skywars.getInstance().getGames()) {
                if (!(game.isState(Game.GameState.ACTIVE) || game.isState(Game.GameState.DEATHMATCH))) {
                    for (GamePlayer gamePlayer : game.getPlayers()) {
                        if (gamePlayer.isTeamClass()) {
                            if (gamePlayer.getTeam().isPlayer(player)) {
                                event.setFoodLevel(25);
                                event.setCancelled(true);
                            }
                        } else {
                            if (gamePlayer.getPlayer() == player) {
                                event.setFoodLevel(25);
                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }

}

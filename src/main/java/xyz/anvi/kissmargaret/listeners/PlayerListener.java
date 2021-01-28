package xyz.anvi.kissmargaret.listeners;

import me.oczi.bukkit.objects.player.MargaretPlayer;
import me.oczi.bukkit.utils.MargaretPlayers;
import me.oczi.bukkit.utils.Partnerships;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        Player player1 = e.getPlayer();
        Entity entity = e.getRightClicked();
        if (!(entity instanceof Player)) {
            return;
        }

        if (!player1.isSneaking()) {
            return;
        }

        MargaretPlayer margaretPlayer1 = MargaretPlayers.getAsMargaretPlayer(player1);
        if (!margaretPlayer1.havePartner()) {
            return;
        }

        /* TODO: Possible cache cooldown to avoid multiple kisses in a determined amount of time
        Cooldown is a CacheSet<Partnership>
        Import CacheSet from me.oczi.bukkit.objects.collections
        if (cooldown.contains(margaretPlayer.getPartnership()) {
            Show message of cooldown here
            ...
            return;
        }*/

        Player player2 = (Player) entity;
        UUID partnerUuid = Partnerships.foundUuidOfPartner(margaretPlayer1);
        UUID uuid = player2.getUniqueId();
        if (!uuid.equals(partnerUuid)) {
            return;
        }
        Location playerEye1 = player1.getEyeLocation();
        Location playerEye2 = player2.getEyeLocation();
        Location middle = playerEye1
            .clone()
            .add((playerEye2.getX() - playerEye1.getX()) / 2,
                (playerEye2.getY() - playerEye1.getY()) / 2,
                (playerEye2.getZ() - playerEye1.getZ()) / 2);
        int amount = 5;
        World world = checkNotNull(playerEye1.getWorld());

        world.spawnParticle(
            Particle.HEART,
            middle,
            amount,
            0.3f,
            0.3f,
            0.3f,
            1f);
    }
}

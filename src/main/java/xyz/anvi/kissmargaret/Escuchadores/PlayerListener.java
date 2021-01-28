package xyz.anvi.kissmargaret.Escuchadores;

import me.oczi.bukkit.objects.partnership.Partnership;
import me.oczi.bukkit.objects.player.MargaretPlayer;
import me.oczi.bukkit.utils.MargaretPlayers;
import me.oczi.bukkit.utils.Partnerships;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        Player partner1 = e.getPlayer();

        if (!(e.getRightClicked() instanceof Player)) {
            return;
        }

        Player partner2 = (Player) e.getRightClicked();

        if(!partner1.isSneaking() || !partner2.isSneaking()) {
            return;
        }

        MargaretPlayer mpartner1 = MargaretPlayers.getAsMargaretPlayer(partner1);
        MargaretPlayer mpartner2 = MargaretPlayers.getAsMargaretPlayer(partner2);

        if (mpartner1.havePartner() && mpartner2.havePartner()) {
            String partnerof1 = Partnerships.getNameOfPartner(mpartner1, true);
            if(partnerof1.equals(partner2.getName())) {
                Location lpartner1 = partner1.getEyeLocation();
                Location lpartner2 = partner2.getEyeLocation();

                Location middle = lpartner1.clone().add((lpartner2.getX() - lpartner1.getX()) / 2, (lpartner2.getY() - lpartner1.getY()) / 2, (lpartner2.getZ() - lpartner1.getZ()) / 2);
                int amount = 5;

                lpartner1.getWorld().spawnParticle(Particle.HEART, middle, amount, 0.3f, 0.3f, 0.3f, 1f);
            }
        }
    }
}

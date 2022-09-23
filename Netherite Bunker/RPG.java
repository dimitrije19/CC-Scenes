package com.prestonplayz.video.scenes.customs.gadgets;

import com.joemelsha.minecraft.util.*;
import com.prestonplayz.util.*;
import com.prestonplayz.video.scenes.*;
import com.prestonplayz.video.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.potion.*;

import java.util.*;


public class RPG extends Gadget {

    private boolean delayed = false;

    public RPG(BaseScene scene) {
        super(scene, ItemConstants.ROCKET_LAUNCHER.clone());
    }

    @Override
    public void leftClick() {

    }

    @Override
    public boolean rightClick() {
        if (delayed == false) {

            delayed = true;
            sync().delay(15*20).run(() -> {
                delayed = false;
            });
            Duration duration = Duration.seconds(15);
            String template = CF.YELLOW_400.bold() + "You can use RPG again in " + CF.RED_400.bold() + "{time}";
            Countdown countdown = new Countdown(plugin(), duration);
            for(int i = (int) duration.seconds(); i > 0; i--) {
                countdown.addActionbar(template, i);
            }
            countdown.start();

            Location l = plugin().warp("a123");

            player().handle().launchProjectile(Firework.class);
            for(Entity e : player().handle().getNearbyEntities(50.0, 50.0, 50.0)){

                if(e instanceof Creeper || e instanceof Zombie){

                    l = e.getLocation();
                    sync().delay(10).run(() -> {
                        TNTPrimed tntPrimed = e.getWorld().spawn(e.getLocation(), TNTPrimed.class, scene()::bind);
                        tntPrimed.setFuseTicks(1);
                        tntPrimed.setYield(5F);
                    });
                    break;

                }


            }

            List<Location> line = Util.getLine(player().handle().getLocation(), l, .1);
            int i = 0;
            for(Location loc : line) {
                sync().delay(1).run(() -> {
                    ParticleBuilder.of(Particle.SMOKE_LARGE)
                                   .location(loc)
                                   .count(1)
                                   .extra(.5)
                                   .spawn();
                });
            }

        }




return true;
        
    }

    @Override
    public void onStart() {

    }

   /* @EventHandler(ignoreCancelled = true)
    public void onProjectileHit(ProjectileHitEvent event) {

        if(event.getHitBlock() != null && event.getEntityType().equals(EntityType.EGG)){

            Location l = event.getHitBlock().getLocation().clone();

            for(LivingEntity ent : l.getNearbyLivingEntities(7.0)){

                if(ent instanceof Player){

                } else {

                    ent.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 10*10, 1));

                }

            }

        }

    }*/
}

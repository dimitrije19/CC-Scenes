package com.prestonplayz.video.scenes.customs.security;

import com.joemelsha.minecraft.util.ParticleBuilder;
import com.joemelsha.scheduler.common.TaskHolder;
import com.prestonplayz.util.Util;
import com.prestonplayz.video.scenes.BaseScene;
import com.prestonplayz.video.util.ItemConstants;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class LaserTurret extends SecurityUpgrade {

    private List<CraftArmorStand> turrets = new ArrayList<>();
    private Map<CraftArmorStand, Boolean> reloaded = new HashMap<>();

    public LaserTurret(BaseScene scene) {
        super(scene);
    }

    @Override
    public void onStart() {
        sync().interval(1).run(() -> {
            List<Entity> entities = world().getEntities();
            entities = entities.stream().filter(entity -> BaseScene.creeperTypes.contains(entity.getType())).collect(Collectors.toList());
            for (Entity entity: entities) {
                if(!(entity instanceof LivingEntity)) continue;
                for(CraftArmorStand turret: turrets) {
                    if(!reloaded.getOrDefault(turret, true)) continue;
                    if(turret.getEyeLocation().distance(entity.getLocation()) <= 15.0) target(turret, (LivingEntity) entity);
                }
            }
        });

        listen().to(PlayerInteractEvent.class, event -> {
            if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
            if(event.getItem() == null) return;
            if(event.getClickedBlock() == null) return;
            if(!event.getItem().isSimilar(ItemConstants.LASER_TURRET)) return;

            Block block = event.getClickedBlock();
            add(block.getLocation());
            event.getItem().subtract();
        });

        scene().bind(() -> {
            for (int i = 0; i < turrets.size(); i++) {
                remove(turrets.get(0));
            }
        });
    }

    public void add(Location loc) {
        world().spawn(adaptLocation(loc), CraftArmorStand.class, (stand) -> {
            stand.setGravity(false);
            stand.setInvisible(true);
            stand.setInvulnerable(true);
            stand.setHelmet(ItemConstants.LASER_TURRET.clone());
            stand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
            turrets.add(stand);
        });
    }

    public void remove(CraftArmorStand turret) {
        turret.remove();
        turrets.remove(turret);
    }

    public List<CraftArmorStand> getTurrets() {
        return turrets;
    }

    public void target(CraftArmorStand turret, LivingEntity target) {
        reloaded.put(turret, false);
        TaskHolder<Void> holder = new TaskHolder<>();
        sync().holder(holder).delay(3, TimeUnit.SECONDS).run(() -> reloaded.put(turret, true));
        float startYaw = turret.getLocation().getYaw();
        float finalYaw =  Util.locationLookAt(turret.getEyeLocation(), target.getLocation().add(0, 1, 0)).getYaw();
        int interpolate = 20;
        sync().interval(1).lim(interpolate).run(() -> {
            float yawAdd = (finalYaw - startYaw)/interpolate;
            Location nextLoc = turret.getLocation();
            nextLoc.setYaw(nextLoc.getYaw() + yawAdd);
            turret.teleport(nextLoc);
        });
        sync().delay(interpolate).run(() -> {
            Location finalLoc = turret.getLocation();
            finalLoc.setYaw(finalYaw);
            turret.teleport(finalLoc);

            shoot(turret, target, holder);
        });
    }

    public void shoot(CraftArmorStand turret, LivingEntity target, TaskHolder cancelReload) {
        if((!target.isValid()) || target.isDead()) {
            //if shot fails continue aiming
            cancelReload.cancel();
            reloaded.put(turret, true);
            return;
        }
        sync().interval(1).lim(20).run(() -> {
            List<Location> line = Util.getLine(turret.getEyeLocation(), target.getLocation().add(0, target.getHeight()/2, 0), .1);
            for(Location loc: line) {
                ParticleBuilder.of(Particle.REDSTONE)
                        .location(loc)
                        .data(new Particle.DustOptions(Color.RED, 0.5f))
                        .count(1)
                        .extra(.5)
                        .spawn();
            }
        });
        target.setHealth(0);
    }

    public Location adaptLocation(Location loc) {
        return loc.getBlock().getLocation().add(0.5, 0.8, 0.5);

    }


}

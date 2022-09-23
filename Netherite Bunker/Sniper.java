package com.prestonplayz.video.scenes.customs.gadgets;

import com.joemelsha.minecraft.math.AABB;
import com.joemelsha.minecraft.util.CF;
import com.prestonplayz.util.Countdown;
import com.prestonplayz.util.Duration;
import com.prestonplayz.util.Util;
import com.prestonplayz.video.scenes.BaseScene;
import com.prestonplayz.video.util.ItemConstants;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class Sniper extends Gadget {

    AtomicBoolean isScoped = new AtomicBoolean(false);
    AtomicBoolean cooldown = new AtomicBoolean(false);

    public Sniper(BaseScene scene) {
        super(scene, ItemConstants.SNIPER_RIFLE.clone());
    }

    @Override
    public void onStart() {
        sync().interval(1).run(() -> {
            EntityEquipment equipment = handle().getEquipment();
            if(equipment != null) {
                ItemStack mainHand = equipment.getItemInMainHand();
                ItemStack offHand = equipment.getItemInOffHand();
                if(offHand.getType() != ItemConstants.SNIPER_RIFLE.getType() && mainHand.getType() != ItemConstants.SNIPER_RIFLE.getType()) {
                    if(isScoped.compareAndSet(true, false)) {
                        setScopedEffect(false);
                    }
                    return;
                }
            }
            if(handle().isSneaking()) {
                if(isScoped.compareAndSet(false, true)) {
                    setScopedEffect(true);
                }
            } else {
                if(isScoped.compareAndSet(true, false)) {
                    setScopedEffect(false);
                }
            }
        });

    }

    @Override
    public boolean rightClick() {
        if(!isScoped.get()) return true;
        if(cooldown.get()) return true;
        List<Entity> entities = Util.getEntitiesInLine(handle().getEyeLocation(), handle().getLocation().getDirection(), 0, 100, new HashSet<>());
        cooldown.set(true);
        Duration duration = Duration.seconds(2);
        Countdown countdown = new Countdown(plugin(), duration);
        for(int i = (int) duration.seconds(); i > 0; i--) {
            countdown.at(i, itemInHandActionBar("Cooldown: " + CF.GREEN + "{time}", countdown, ItemConstants.SNIPER_RIFLE.getType()));
        }
        countdown.at(0, () -> {
            cooldown.set(false);
        });
        countdown.start();
        player().sound(Sound.BLOCK_ANVIL_BREAK);
        for (Entity entity : entities) {
            if(entity.getType() == EntityType.PLAYER) continue;
            if(entity.getType() == EntityType.PRIMED_TNT) continue;
            if(entity.getType() == EntityType.ARMOR_STAND) continue;
            if(!(entity instanceof LivingEntity)) continue;
            ((LivingEntity) entity).setHealth(0);
        }
        return true;
    }

    @Override
    public void leftClick() {

    }

    public void setScopedEffect(boolean set) {
        if(set) {
            handle().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 2, false, false, false));
            EntityEquipment equipment = handle().getEquipment();
            if(equipment != null) {
                equipment.setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
            }
        } else {
            handle().removePotionEffect(PotionEffectType.SLOW);
            EntityEquipment equipment = handle().getEquipment();
            if(equipment != null) {
                equipment.setHelmet(new ItemStack(Material.AIR));
            }
        }
    }

    public Runnable itemInHandActionBar(@NotNull String message, Countdown countdown, Material ...mat) {
        return () -> {
            EntityEquipment equipment = handle().getEquipment();
            if(equipment == null) return;
            Material item = equipment.getItemInMainHand().getType();
            if(!Arrays.asList(mat).contains(item)) return;
            Util.sendActionBar(handle(), message.replaceAll(Pattern.quote("{time}"), countdown.getRemaining().formatText()));
        };
    }

}

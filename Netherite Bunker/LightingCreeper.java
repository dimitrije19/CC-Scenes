package com.prestonplayz.video.scenes.customs.creepers;

import com.prestonplayz.video.scenes.BaseScene;
import net.minecraft.server.v1_16_R3.EntityCreeper;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftCreeper;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class LightingCreeper extends BaseCreeper {

    public LightingCreeper(BaseScene scene) {
        super(scene, EntityType.CREEPER, "Lighting Creeper", 7, Collections.singletonList(new ItemStack(Material.EMERALD, 20)));
    }

    @Override
    public Consumer<Entity> entityConsumer() {
        return entity -> {
            Creeper creeper = (Creeper) entity;

            sync().delayInterval(7, TimeUnit.SECONDS).run(() -> {
                int count = 0;

                for (Entity nearbyEntity : creeper.getNearbyEntities(7, 7, 7)) {

                    if (count >= 4)
                        return;

                    if (nearbyEntity instanceof Creeper){
                        Creeper c = (Creeper) nearbyEntity;
                        EntityCreeper nmsCreeper = ((CraftCreeper) c).getHandle();

                        if (!nmsCreeper.isPowered()){
                            c.getWorld().strikeLightningEffect(c.getLocation());
                            c.setPowered(true);
                            count++;
                        }
                    }
                }
            });
        };
    }
}

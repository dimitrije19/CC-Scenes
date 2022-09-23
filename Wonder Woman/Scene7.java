package com.prestonplayz.video.scenes;


import com.joemelsha.minecraft.util.*;
import com.joemelsha.scheduler.common.*;
import com.prestonplayz.base.*;
import com.prestonplayz.base.scene.frame.*;
import com.prestonplayz.citizens.*;
import com.prestonplayz.util.*;
import com.prestonplayz.video.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;

import java.util.*;

public final class Scene7 extends BaseScene {

	TaskHolder<Void> holder, holder1;
	List<TaskHolder<Void>> tasks = new ArrayList<>();
	private ServerNPC rescueCiv1 = ServerNPCs.generic_male_8;
	private ServerNPC rescueCiv2 = ServerNPCs.generic_male_7;
	private ServerNPC rescueCiv3 = ServerNPCs.generic_male_6;
	private ServerNPC wizard = new ServerNPC("Weather Wizard", "eyJ0aW1lc3RhbXAiOjE1NzY1Mjg3MTUwMjcsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82YzQ4YzAyZWZlYTdlODlhYzBmNjQyOWZhYzg4ZTZiY2NmNTFlYTBkMDljMTgyMTE5YWE4NDBhZWZiY2ZkZDMzIn19fQ==", "AuhLOxH0aQePZa5YVm66vUkbWH7wseDn5vsilKfPy81M620T+bly8kXWz0IDbv+a3dKfyveHo21Ge6VM1BAXCfYQsusPryuzpywFm4Ry1JgH58gXkFx9j2lsHcxi7q/1C6A5bPigghR5EVdOwrpPtLkmUtNc8SY4Ltx2W3J0zKZ3qPOOkId1ZdrZMuKFtSx3B0vQHuDN6iEex5CdnrG9JAG6u+rNYG94n2ERRYkFc8UjT5nXG8x3e/ZRYGV34uzsIrOAHBMYKt5YZ7t0Ozbs9vvsTshOX53006EoQDjxdTGwNc7cSXOZq9npMgCdl7qa2EmuT2bOKpiJ/pChEBGJp/ExoKL9Wg+LlTZGtsqyfa/5Dnrkx7qejJDbq/2Sp0a29zKrf+6uJ+cbg/AVAwSK0EwVbBBc7VeURwHZ2Jl0vVnqQeWiOS94WZO0/7e5WeYiHr4Rp1mZnd1DYlk9Hje11+8ilzEv8olYY9nDlfY4Fpqu07B1fJUI8DMtg+HCWnds/kETdmGD670M51pTbGomN+JsrNHy5kqvMaeosmHsMb47i/qsrXGD0wgzPO2nuz2Omij1yp3cketPzWsRj86/QjY2w65KeT/airkt9gzNy0NnpHGrxzwnopaGpXvBVf+2tj9EHpKiOmDDQeO/KhDP+EvZXuve0+peL0Z/TTl1L6c=");
	private ServerNPC a = ServerNPCs.generic_female_1;
	private ServerNPC b = ServerNPCs.generic_female_2;
	private ServerNPC c = ServerNPCs.generic_female_3;
	private ServerNPC d = ServerNPCs.generic_female_4;
	private ServerNPC e = ServerNPCs.generic_female_5;
	private ServerNPC f = ServerNPCs.generic_male_1;
	private ServerNPC g = ServerNPCs.generic_male_2;
	private ServerNPC h = ServerNPCs.generic_male_3;
	private ServerNPC i = ServerNPCs.generic_male_4;
	private ServerNPC j = ServerNPCs.generic_male_5;

	float angle = 0f;
	float angle1 = 0f;


	public Scene7(ScenePlayer<?> player, int number, String name) {
		super(player, number, name);



		frame(new SpawnNpcFrame(this, wizard, "wizard"));
		frame(new SpawnNpcFrame(this, a, "s7start"));
		frame(new SpawnNpcFrame(this, b, "s7start"));
		frame(new SpawnNpcFrame(this, c, "s7start"));
		frame(new SpawnNpcFrame(this, d, "s7start"));
		frame(new SpawnNpcFrame(this, e, "s7start"));
		frame(new SpawnNpcFrame(this, f, "s7start"));
		frame(new SpawnNpcFrame(this, g, "s7start"));
		frame(new SpawnNpcFrame(this, h, "s7start"));
		frame(new SpawnNpcFrame(this, i, "s7start"));
		frame(new SpawnNpcFrame(this, j, "s7start"));
		frame(new SpawnNpcFrame(this, rescueCiv1, "resciv1"));
		frame(new SpawnNpcFrame(this, rescueCiv2, "resciv2"));
		frame(new SpawnNpcFrame(this, rescueCiv3, "resciv3"));
		frame(new DelayFrame(this, 5));

		frame(new WarpAllFrame(this, "s7start"));
		frame(new GiveItemFrame(this, flight));
		frame(new GiveItemFrame(this, wwHelmet));
		frame(new GiveItemFrame(this, wwChestplate));
		frame(new GiveItemFrame(this, wwLeggings));
		frame(new GiveItemFrame(this, wwBoots));

		frame(new ReplayFrame(this, "s71", a, true));
		frame(new ReplayFrame(this, "s72", b, true));
		frame(new ReplayFrame(this, "s73", c, true));
		frame(new ReplayFrame(this, "s74", d, true));
		frame(new ReplayFrame(this, "s75", e, true));
		frame(new ReplayFrame(this, "s76", f, true));
		frame(new ReplayFrame(this, "s77", g, true));
		frame(new ReplayFrame(this, "s78", h, true));
		frame(new ReplayFrame(this, "s79", i, true));
		frame(new ReplayFrame(this, "s710", j, true));

		frame(new NearLocationWaitFrame(this, plugin().warp("tornado"), 20.0));
		frame(new TitleAllFrame(this, "", ChatColor.RED + "Civilians are stuck in tornado!!", 0, 30, 0));
		frame(new DelayFrame(this, 30));
		frame(new TitleAllFrame(this, "", ChatColor.AQUA + "You must help them!", 0, 30, 0));

		frame(new NearLocationWaitFrame(this, plugin().warp("wizard"), 10.0));
		frame("Stare", () -> {
			wizard.stare(this, player.handle());
		});
		frame(new ChatMessageFrame(this, "Weather Wizard", "You can't stop the weather, Wonder Woman!!"));
		frame(new DelayFrame(this, 40));
		frame(new ChatMessageFrame(this, "Weather Wizard", "You are dead now!"));
		frame(new DelayFrame(this, 40));
		frame(new ReplayFrame(this, "weather", wizard, true));
		frame(new DelayFrame(this, 60));
		frame("parts1", () -> {

			//Location wizardtop = plugin().warp("wizard").add(0, 1.0, 0);
			ParticleBuilder.of(Particle.FLASH).offset(0.5, 1.5, 0.5).count(100).extra(0.01).location(plugin().warp("wizard").add(0.0, 2.0, 0.0)).spawn();

		});
		frame(new DelayFrame(this, 20));
		frame("parts", () -> {

			Bukkit.getWorld("PlayingMinecraftAsWonderWoman_City").strikeLightning(player.handle().getLocation());

		});
		frame(new DelayFrame(this, 20));
		frame("parts", () -> {

			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit1"));
			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit2"));
			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit3"));
			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit4"));
			plugin().warp("lit1").getWorld().setTime(13500);

		});
		frame(new DelayFrame(this, 20));
		frame("parts", () -> {

			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit5"));
			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit6"));
			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit7"));
			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit8"));
			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit9"));
			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit10"));
			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit11"));
			plugin().warp("lit1").getWorld().strikeLightning(plugin().warp("lit12"));
			plugin().warp("lit1").getWorld().setTime(14000);
			plugin().warp("lit1").getWorld().setStorm(true);


		});

		frame(new TitleAllFrame(this, "", ChatColor.YELLOW + "" + ChatColor.BOLD + "Your Wonder Woman Armor has saved you!", 0, 60, 0));
		frame(new DelayFrame(this, 60));
		frame(new ChatMessageFrame(this, "Weather Wizard", "Wait, how are you alive??"));
		frame(new NearLocationWaitFrame(this, plugin().warp("wizard"), 4.0));
		frame(new ReplayFrame(this, "wizard", wizard, false));
		frame("Stare1", () -> {

			 wizard.stare(this, player.handle());

		});

		frame(new ChatMessageFrame(this, "Weather Wizard", "Wait please!!"));
		frame(new InteractNpcFrame(this, wizard));
		frame(new ChatMessageFrame(this, "Weather Wizard", "Okay, okay..."));
		frame(new DelayFrame(this, 40));
		frame("tornado cancel", () -> {
			for(TaskHolder<Void> h : tasks){
				h.cancel();
			}
			holder1.cancel();
			plugin().warp("lit1").getWorld().setTime(0);
			plugin().warp("lit1").getWorld().setStorm(false);
			broadcastTitle("", ChatColor.GREEN + "" + ChatColor.BOLD + "Wizard has destroyed the tornado!", 0, 40, 0);

		});
		frame(new InteractNpcFrame(this, wizard));
		frame(new ChatMessageFrame(this, "Weather Wizard", "We have a hideout inside of a cave near the city. That’s all I can say..."));



		frame(new StallFrame(this));
	}

	public void circle(double y, float radius){


	}

	@Override
	protected void startImpl() {

		holder = new TaskHolder<>();
		holder1 = new TaskHolder<>();

		sync().interval(1).holder(holder1).run(() -> {

			Location warp1 = plugin().warp("resciv1");
			Random rand = new Random();
			float radius1 = 5;
			double x1 = (radius1 * Math.sin(angle));
			double z1 = (radius1 * Math.cos(angle));
			angle1 += 0.001;
			rescueCiv1.wrapper.handle().teleport(new Location(Bukkit.getWorld("PlayingMinecraftAsWonderWoman_City"), warp1.getX() + x1, warp1.getY(), warp1.getZ() + z1), PlayerTeleportEvent.TeleportCause.UNKNOWN);


		});

		sync().interval(2).holder(holder1).run(() -> {

			Location warp1 = plugin().warp("resciv2");
			Random rand = new Random();
			float radius1 = 10;
			double x1 = (radius1 * Math.sin(angle));
			double z1 = (radius1 * Math.cos(angle));
			rescueCiv2.wrapper.handle().teleport(new Location(Bukkit.getWorld("PlayingMinecraftAsWonderWoman_City"), warp1.getX() + x1, warp1.getY(), warp1.getZ() + z1), PlayerTeleportEvent.TeleportCause.UNKNOWN);


		});

		sync().interval(3).holder(holder1).run(() -> {

			Location warp1 = plugin().warp("resciv3");
			Random rand = new Random();
			float radius1 = 15;
			double x1 = (radius1 * Math.sin(angle));
			double z1 = (radius1 * Math.cos(angle));
			rescueCiv3.wrapper.handle().teleport(new Location(Bukkit.getWorld("PlayingMinecraftAsWonderWoman_City"), warp1.getX() + x1, warp1.getY(), warp1.getZ() + z1), PlayerTeleportEvent.TeleportCause.UNKNOWN);


		});



		for(int i = 0; i < 150; i++){

			double y = i*1.00;

			float radius = 1.5f*(i/3);

			TaskHolder<Void> taskhold = new TaskHolder<>();
			tasks.add(taskhold);

			Location location = plugin().warp("tornado");

			sync().interval(1).holder(taskhold).run(() -> {

				double x = (radius * Math.sin(angle));
				double z = (radius * Math.cos(angle));
				angle += 0.1;
				Random rand = new Random();
				ParticleBuilder.of(Particle.CLOUD).location(new Location(Bukkit.getWorld("PlayingMinecraftAsWonderWoman_City"), location.getX()+x, location.getY() + y, location.getZ()+z))
							   .count(7).extra(0.07).offset(rand.nextInt(3)*rand.nextDouble(), rand.nextInt(3)*rand.nextDouble(), rand.nextInt(3)*rand.nextDouble()).spawn(); });


			circle(i, 1.5f*(i/3));

		}




		Bukkit.getWorld("PlayingMinecraftAsWonderWoman_City").getEntities().stream().filter(e -> !(e instanceof Player)).forEach(Entity::remove);
		super.startImpl();
	}

	@EventHandler
	public void onClick(PlayerInteractEvent e){



		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){


			if(e.getPlayer().getItemInHand().equals(flight)){

				e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(2.50d));


			}


		}

	}

	@EventHandler
	public void onfall(EntityDamageEvent e){

		if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){

			e.setCancelled(true);

		}

	}

	@Override
	protected void finishImpl() {
		plugin().warp("lit1").getWorld().setTime(0);
		plugin().warp("lit1").getWorld().setStorm(false);
		super.finishImpl();

	}





}
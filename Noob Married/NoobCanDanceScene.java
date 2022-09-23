package com.prestonplayz.video.scene;

import com.joemelsha.minecraft.util.*;
import com.prestonplayz.base.*;
import com.prestonplayz.base.scene.frame.*;
import com.prestonplayz.citizens.*;
import com.prestonplayz.util.*;
import com.prestonplayz.util.baby.*;
import com.prestonplayz.util.baby.frame.*;
import com.prestonplayz.video.scene.frames.*;
import com.prestonplayz.video.util.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;

public class NoobCanDanceScene extends BaseScene {

	private Move[] EASY = { Move.JUMP, Move.JUMP, Move.FORWARD, Move.RIGHT, Move.BACK, Move.LEFT, Move.CROUCH, Move.CROUCH, Move.CROUCH, Move.JUMP, Move.CROUCH, Move.JUMP, Move.CROUCH, Move.FORWARD, Move.RIGHT, Move.FORWARD, Move.LEFT, Move.BACK, Move.CROUCH, Move.CROUCH, Move.JUMP, Move.JUMP };
	private Move[] HARD = { Move.RIGHT, Move.LEFT, Move.BACK, Move.FORWARD, Move.CROUCH, Move.CROUCH, Move.JUMP, Move.RIGHT, Move.LEFT, Move.FORWARD, Move.BACK, Move.RIGHT, Move.CROUCH, Move.CROUCH, Move.SPIN, Move.CROUCH, Move.CROUCH, Move.SPIN, Move.LEFT, Move.RIGHT, Move.FORWARD, Move.BACK, Move.JUMP, Move.JUMP, Move.JUMP, Move.CROUCH, Move.CROUCH, Move.CROUCH, Move.RIGHT, Move.LEFT, Move.RIGHT, Move.LEFT, Move.BACK, Move.FORWARD, Move.BACK, Move.FORWARD, Move.CROUCH, Move.SPIN, Move.CROUCH, Move.SPIN, Move.CROUCH, Move.CROUCH, Move.CROUCH, Move.JUMP, Move.JUMP, Move.JUMP, Move.JUMP, Move.RIGHT, Move.LEFT, Move.RIGHT, Move.LEFT, Move.RIGHT, Move.LEFT, Move.FORWARD, Move.BACK, Move.RIGHT, Move.LEFT, Move.RIGHT, Move.LEFT, Move.RIGHT, Move.JUMP, Move.JUMP, Move.JUMP, Move.SPIN, Move.PUNCH, Move.PUNCH, Move.PUNCH, Move.JUMP, Move.JUMP, Move.JUMP, Move.PUNCH, Move.PUNCH, Move.PUNCH, Move.CROUCH, Move.CROUCH, Move.CROUCH, Move.SPIN, Move.LEFT, Move.RIGHT, Move.FORWARD, Move.BACK, Move.RIGHT, Move.LEFT, Move.CROUCH, Move.CROUCH, Move.CROUCH, Move.SPIN, Move.SPIN, Move.SPIN, Move.FORWARD, Move.BACK, Move.RIGHT, Move.LEFT, Move.RIGHT, Move.BACK };

	private ServerNPC DANCE_INSTRUCTOR = new ServerNPC("Dance Instructor", "ewogICJ0aW1lc3RhbXAiIDogMTYxMjk5NjczNDM4MCwKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MTQxNDE0MWgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmY1NTgyOWNjMWM2NTViMzI1ZWQzMzYyMGQ5NTNiOTJlYWNiMWEyMTY2Y2JhMjFiMDc2MmQ3ZDc5MjRjZDEwYSIKICAgIH0KICB9Cn0=", "p0yKnO7ybqad/d8FLzy44vs2D4BLSSaF0zFjHRtdeHnkju4vOVo0CzdnS/Anhr49zpo6dm2ysLKDaYYWueX54WTpUZBJIFvQKRy1sbNkCwXzelblHydW5DVzXcRyhUzXwc71Atnk2OmdlXDbjbgWj5fjuoqNwV3lVjWeyWwbUATXdGtcY0e9ImUEYg1B88Xw+S7cLmwHrLaYZQIYCls4BCvmLoz5CmYfOCC+6O0EY/Krahtzi+SyYhmnNG39xK1/tyHQETM6k/RUUKLhGjcEMVh4JUI19JL0JjrtevFAARCkwhFRddg3FXIVQKIY/05JOfK6NALRLU1NxM+Cf4MTTi5KG2EfFd7kf21VMcpbFaRc4z5ix18NJXaYv4e//xhugJDKIOoM0nsM0v/lbmgu6CkCPIbxjFJg8qNlR/WJBVoC8LlpkgvSGbCMKluyM259Y7ZLSbpRuYX34gfRSnkcW81jXTF28TYygmVOOn4JSTBZASgZlqR/npMJZDL8H8JukfXeL1Q+Fu85ltrW6Mf/sZ9CP8fGfx2ye2VOrRONv+zCm3N4dmur+viq6m0a6Uaiw9QBp95xPjbfob+3IQmt5VpZYCzIL1rigppBMyGnBI1J7gaxAfV9ZzGLhZwqgiq5+azByKFBNSetzJjxXF6NI16CXAFSqRUTfD+VfGE3RK0=");
	private ServerNPC DJ = new ServerNPC("Dance DJ", "ewogICJ0aW1lc3RhbXAiIDogMTYxMzAwMzQ5Njg1NywKICAicHJvZmlsZUlkIiA6ICI5MWYwNGZlOTBmMzY0M2I1OGYyMGUzMzc1Zjg2ZDM5ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdG9ybVN0b3JteSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80Y2RmZTNhYjZhZTI2MGZlMWI5Njk0ZDViOTRjMzBhOGU2ZDU0NGFmZDM0NTI1ZjBlMTljZTg0MTY5MjQ2YTEyIgogICAgfQogIH0KfQ==", "g7LkGDlvVZfJDoBgDtUNoyTMz2vTZevfygZvkWzUO5ueMqBNoT4miOgbIEgea9fy6TlJLhQOPKv2T6SnKpsppLqOXx3k0JHqZPXtEopKFh1k0IGuLrfMWuHqoONHALhlpzK5Xt8ofqdTvI09he+cokbYDu9Cw4rr9k0JSeXA7Yyy7FjONjnVGOIliPVrv5y8p/e/d20y3HZD+pTRWJEt+JAEEJBLCy3JS955cL8kKJOEBLriVV4u38/b9g9T2VqjvONy97GQpR7xiupoyKHHwL6ZdsD4PtFc1HxF5zDi4thsyC+XeDBnaaiSMJu/TJ7I07UgwCHbxoUQZE2V/BqGd2+1pWqHTN1LIGZHHbMLrNF36f5yyz39DgR0IBGi+x+eZRGtERX+Hb70yV9QFSgyAnSug+ZjA24H8NnVLx6E/BuO0C2PAqSdqZqBwXYzvUV4ZxkvyOi7POTp7TOfJo8lp9+Vv2o+GXZoIdOe41n3T5aTRaHrIM3nN5ZYXANb85IAlfX3lvSezu4hs06LdZ8OQdXnd1miMIh8SIMNlOHanVHTblfRtFHmcrkyRD9kUwuAfr58rKwsCfMdpV6OKjfE5VdZvQtB3AkRNzGPjXy+IwmgPiXWEW/BTcJ+ezI6H7tWFDDsXZiiZaeZ82pE1B7kjd/LSUK2VHP9MxD8GFVGnQw=");

	private FlippableBaby babyNoob = new FlippableBaby(this, BabyYoutuberType.NOOB);
	private Baby babyPreston = new Baby(this, BabyYoutuberType.PRESTON);

	public NoobCanDanceScene(ScenePlayer<?> player, int number, String name) {
		super(player, number, name);

		frame(new PasteSchemFrame(this, "noobmarriedscene5", "pastescene5"));
		frame(new WarpFrame(this, "scene5"));
		frame(new SpawnNpcFrame(this, DANCE_INSTRUCTOR, "scene5instructor"));
		frame(new SpawnNpcFrame(this, NOOB, "scene5spawnnoob"));
		frame(new SpawnNpcFrame(this, ServerNPCs.unspeakable, "scene5spawnunspeakable"));
		frame(new SpawnNpcFrame(this, ServerNPCs.ssundee, "scene5spawnssundee"));
		frame(new SpawnNpcFrame(this, ServerNPCs.pro1234, "scene5spawnpro"));
		frame(new SpawnNpcFrame(this, ServerNPCs.jelly, "scene5spawnjelly"));
		frame(new SpawnNpcFrame(this, ServerNPCs.mrbeast, "scene5spawnmrbeast"));
		frame(new SpawnBabyFrame(this, babyNoob, "scene5spawnbabynoob", null, true, true));
		frame(new SpawnBabyFrame(this, babyPreston, "scene5spawnbabypreston"));
		frame(new NpcStareFrame(this, NOOB));
		frame(new NpcStareFrame(this, ServerNPCs.unspeakable));
		frame(new NpcStareFrame(this, ServerNPCs.ssundee));
		frame(new NpcStareFrame(this, ServerNPCs.pro1234));
		frame(new NpcStareFrame(this, ServerNPCs.jelly));
		frame(new NpcStareFrame(this, ServerNPCs.mrbeast));
		frame(new DelayFrame(this, 80));
		frame(new MessageFrame(this, NOOB.say("pweson u guys ned to lern to danc... im a gud dancr, but u guys suck")));
		frame(new DelayFrame(this, 80));
		frame(new MessageFrame(this, NOOB.say("folo me i kno just the place!")));
		frame(new DelayFrame(this, 40));
		frame(new NpcStareFrame(this, NOOB, false));
		frame(new NpcStareFrame(this, ServerNPCs.unspeakable, false));
		frame(new NpcStareFrame(this, ServerNPCs.ssundee, false));
		frame(new NpcStareFrame(this, ServerNPCs.pro1234, false));
		frame(new NpcStareFrame(this, ServerNPCs.jelly, false));
		frame(new NpcStareFrame(this, ServerNPCs.mrbeast, false));
		frame(new ReplayFrame(this, "scene5runnoob", NOOB, true));
		frame(new ReplayFrame(this, "scene5rununspeakable", ServerNPCs.unspeakable, true));
		frame(new ReplayFrame(this, "scene5runssundee", ServerNPCs.ssundee, true));
		frame(new ReplayFrame(this, "scene5runpro", ServerNPCs.pro1234, true));
		frame(new ReplayFrame(this, "scene5runjelly", ServerNPCs.jelly, true));
		frame(new ReplayFrame(this, "scene5runmrbeast", ServerNPCs.mrbeast, true));
		frame(new ReplayFrame(this, "scene5runbabynoob", () -> babyNoob.getEntity(), true));
		frame(new ReplayFrame(this, "scene5runbabypreston", () -> babyPreston.getEntity(), true));
		frame(new DelayFrame(this, 20 * 10));
		frame(new MessageFrame(this, DANCE_INSTRUCTOR.say("Hey there, Noob! Are you and your friends her for some dance lessons?")));
		frame(new DelayFrame(this, 60));
		frame(new MessageFrame(this, NOOB.say("ya, they ned to get redy for my weding!!")));
		frame(new DelayFrame(this, 60));
		frame(new MessageFrame(this, DANCE_INSTRUCTOR.say("Alright, lets get started then! We'll start off easy... lets go!")));
		frame(new DelayFrame(this, 20 * 4));
		frame("start countdown", f -> {
			sync().interval(20).lim(3).run(t -> {
				title(CF.GREEN.bold() + "Dancing in...", CF.YELLOW.bold().toString() + (4 - t.getCycle()), 0, 25, 0);
				sound(Sound.BLOCK_NOTE_BLOCK_HARP);
			}).onDone(() -> f.finish());

			return false;
		});
		frame(new ReplayFrame(this, "instructorbeginner", DANCE_INSTRUCTOR, true));
		frame(new ReplayFrame(this, "scene5noobeasy", NOOB, true));
		frame(new ReplayFrame(this, "scene5unspeakableeasy", ServerNPCs.unspeakable, true));
		frame(new ReplayFrame(this, "scene5ssundeeeasy", ServerNPCs.ssundee, true));
		frame(new ReplayFrame(this, "scene5proeasy", ServerNPCs.pro1234, true));
		frame(new ReplayFrame(this, "scene5jellyeasy", ServerNPCs.jelly, true));
		frame(new ReplayFrame(this, "scene5mrbeasteasy", ServerNPCs.mrbeast, true));
		frame(new ReplayFrame(this, "scene5babynoobeasy", () -> babyNoob.getEntity(), true));
		frame(new ReplayFrame(this, "scene5babyprestoneasy", () -> babyPreston.getEntity(), true));
		frame(new EasyDanceFrame(this));
		frame(new StopSoundFrame(this, Sound.MUSIC_DISC_CAT));
		frame(new MessageFrame(this, NOOB.say("yawwnnnn this is tooo sloooo!!! lets hav a DANC OFF!!")));
		frame(new DelayFrame(this, 80));
		frame(new MessageFrame(this, NOOB.say("HIT IT DJ!!!")));
		frame(new DelayFrame(this, 60));
		frame("strike lighting", () -> {
			player.world().strikeLightningEffect(plugin().warp("dj"));
			for (int i = 1; i <= 10; i++) {
				InstantFirework.play(FireworkEffect.builder().withColor(Util.getRandomEntry(Util.getRainbowColor(Math.random(), Math.random()))).with(FireworkEffect.Type.BALL).flicker(true).build(), plugin().warp("scene5firework" + i));
			}
		});
		frame(new PasteSchemFrame(this, "noobmarriedscene5disco", "pastescene5"));
		frame("disco lights", () -> {
			Material[] TYPES = { Material.RED_STAINED_GLASS, Material.BLUE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.YELLOW_STAINED_GLASS, Material.LIME_STAINED_GLASS, Material.PURPLE_STAINED_GLASS, Material.PINK_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.BLUE_STAINED_GLASS };
			sync().interval(5).run(() -> {
				for (int i = 1; i <= 2; i++) {
					Block block = plugin().warp("scene5beaconglass" + i).getBlock();
					Material type = Util.getRandomEntry(TYPES);

					while (type == block.getType()) {
						type = Util.getRandomEntry(TYPES);
					}

					block.setType(type);
				}
			});
		});
		frame(new SpawnNpcFrame(this, DJ, "dj"));
		frame(new DelayFrame(this, 40));
		frame("start countdown", f -> {
			sync().interval(20).lim(3).run(t -> {
				title(Util.getRainbowString("Dance Off In...", true, CF.RED, CF.DEEP_ORANGE_200, CF.YELLOW, CF.GREEN, CF.AQUA, CF.BLUE, CF.PURPLE_100), CF.YELLOW.bold().toString() + (4 - t.getCycle()), 0, 25, 0);
				sound(Sound.BLOCK_NOTE_BLOCK_HARP);
			}).onDone(() -> f.finish());

			return false;
		});
		frame(new ReplayFrame(this, "instructorpro", DANCE_INSTRUCTOR, true));
		frame(new ReplayFrame(this, "scene5noobpro", NOOB, true));
		frame(new ReplayFrame(this, "scene5unspeakablepro", ServerNPCs.unspeakable, true));
		frame(new ReplayFrame(this, "scene5ssundeepro", ServerNPCs.ssundee, true));
		frame(new ReplayFrame(this, "scene5propro", ServerNPCs.pro1234, true));
		frame(new ReplayFrame(this, "scene5jellypro", ServerNPCs.jelly, true));
		frame(new ReplayFrame(this, "scene5mrbeastpro", ServerNPCs.mrbeast, true));
		frame(new ReplayFrame(this, "scene5babynoobpro", () -> babyNoob.getEntity(), true));
		frame(new ReplayFrame(this, "scene5babyprestonpro", () -> babyPreston.getEntity(), true));

//		frame(new SpawnNpcFrame(this, DANCE_INSTRUCTOR, "scene5instructor"));
//		frame(new ChatMessageCancelFrame(this));
//		frame(new ReplayFrame(this, "instructorpro", DANCE_INSTRUCTOR, true));
//		frame(new DelayFrame(this, 919));
//		frame(new DeleteBlocksFrame(this, player.world(), "scene5pro"));

		frame(new ProDanceFrame(this));
		frame(new ForEachBlocksFrame(this, player.world(), "scene5preston", b -> {
			Util.breakBlockParticle(b);
			b.setType(Material.AIR);
		}));
		frame(new SoundFrame(this, Sound.ENTITY_WITHER_SPAWN));
		frame(new AnimatedTitleFrame(this, CF.RED.bold() + "You Failed!", CF.GOLD.bold() + "noob1234 wins!", 2, 40));
		frame(new DelayFrame(this, 111));
		frame(new MessageFrame(this, NOOB.say("told u guys u stink... but gud enuff!!")));
		frame(new DelayFrame(this, 60));
		frame(new MessageFrame(this, NOOB.say("tim for the bachlor party!!")));
		frame(new StallFrame(this));
	}

	@Override
	protected void finishImpl() {
		super.finishImpl();

		for (Player player : Bukkit.getOnlinePlayers()) {
			player.stopSound(Sound.MUSIC_DISC_PIGSTEP);
			player.stopSound(Sound.MUSIC_DISC_CAT);
		}
	}
}

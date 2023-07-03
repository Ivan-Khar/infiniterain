package com.aqupd.infiniterain;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(InfiniteRain.MODID)
public class InfiniteRain {
    public static final String MODID = "infiniterain";
    private long time = 0L;

    public InfiniteRain() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.START)) {
            GameRules gameRules = event.getServer().getGameRules();
            if((System.currentTimeMillis() - time) >= 30000L && gameRules.getRule(GameRules.RULE_WEATHER_CYCLE).get()) {
                time = System.currentTimeMillis();
                gameRules.getRule(GameRules.RULE_WEATHER_CYCLE).set(false, event.getServer());
            }
        }
    }
}

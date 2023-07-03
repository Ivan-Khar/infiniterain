package com.aqupd.infiniterain;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
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
    public void onServerTick(TickEvent.WorldTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.START) && event.side.isServer()) {
            MinecraftServer server = event.world.getServer();
            if(server != null) {
                GameRules gameRules = server.getGameRules();
                if ((System.currentTimeMillis() - time) >= 30000L && gameRules.getRule(GameRules.RULE_WEATHER_CYCLE).get()) {
                    time = System.currentTimeMillis();
                    gameRules.getRule(GameRules.RULE_WEATHER_CYCLE).set(false, server);
                }
            }
        }
    }
}

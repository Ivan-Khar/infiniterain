package com.aqupd.infiniterain;

import com.mojang.logging.LogUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.Objects;

@Mod(InfiniteRain.MODID)
public class InfiniteRain {
    public static final String MODID = "infiniterain";
    public static final Logger LOGGER = LogUtils.getLogger();

    private long time = 0L;

    public InfiniteRain() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (Objects.equals(event.getPhase(), EventPriority.NORMAL)) {
            MinecraftServer server = event.getServer();
            GameRules gameRules = server.getGameRules();
            if((System.currentTimeMillis() - time) == 30000L && gameRules.getRule(GameRules.RULE_WEATHER_CYCLE).get()) {
                time = System.currentTimeMillis();
                gameRules.getRule(GameRules.RULE_WEATHER_CYCLE).set(false, server);
            }
        }
    }
}

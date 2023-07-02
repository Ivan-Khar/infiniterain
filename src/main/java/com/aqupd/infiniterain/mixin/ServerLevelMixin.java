package com.aqupd.infiniterain.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
  @Shadow @Final private ServerLevelData serverLevelData;

  @Inject(method = "advanceWeatherCycle",
    at = @At(target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z", value = "INVOKE")
  )
  private void test(CallbackInfo ci) {
    ServerLevel level = ((ServerLevel) (Object) this);
    if(!level.isRaining()) {
      this.serverLevelData.setThunderTime(0);
      this.serverLevelData.setThundering(false);
      this.serverLevelData.setRainTime(12000);
      this.serverLevelData.setRaining(true);
    }
  }
}

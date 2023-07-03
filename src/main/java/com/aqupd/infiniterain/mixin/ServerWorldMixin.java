package com.aqupd.infiniterain.mixin;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.IServerWorldInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
  @Shadow @Final private IServerWorldInfo serverLevelData;

  @Inject(method = "tick",
    at = @At(target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$RuleKey;)Z", value = "INVOKE", ordinal = 0)
  )
  private void constantRain(CallbackInfo ci) {
    ServerWorld level = ((ServerWorld) (Object) this);
    if(!level.isRaining() || level.isThundering()) {
      this.serverLevelData.setThunderTime(0);
      this.serverLevelData.setThundering(false);
      this.serverLevelData.setRainTime(12000);
      this.serverLevelData.setRaining(true);
    }
  }
}

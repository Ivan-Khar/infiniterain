package com.aqupd.infiniterain.mixin;

import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRules.class)
public abstract class GameRulesMixin {
  @Inject(method = "getBoolean", at = @At("HEAD"), cancellable = true)
  private void infiniteWeatherCycle(GameRules.Key<GameRules.BooleanValue> gameruleKey, CallbackInfoReturnable<Boolean> cir) {
    if(gameruleKey.equals(GameRules.RULE_WEATHER_CYCLE)) cir.setReturnValue(false);
  }
}

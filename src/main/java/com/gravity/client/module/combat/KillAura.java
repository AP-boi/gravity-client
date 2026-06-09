package com.gravity.client.module.combat;

import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class KillAura extends Module {
    private final NumberSetting range = addSetting(new NumberSetting("Range", "Attack range", 4.0, 3.0, 6.0, 0.1));
    private final NumberSetting cps = addSetting(new NumberSetting("CPS", "Clicks per second", 12, 1, 20, 1));
    private final ModeSetting target = addSetting(new ModeSetting("Target", "Target type", "Players", "Players", "Mobs", "All"));
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Attack mode", "Single", "Single", "Switch", "Multi"));
    private final BooleanSetting rotate = addSetting(new BooleanSetting("Rotate", "Rotate towards target", true));

    private int tickCounter = 0;

    public KillAura() {
        super("KillAura", "Automatically attacks nearby entities within range", Category.COMBAT, GLFW.GLFW_KEY_R);
    }

    @Override
    public void onTick() {
        if (!isInGame()) return;

        int tickDelay = Math.max(1, 20 / cps.getIntValue());
        tickCounter++;
        if (tickCounter < tickDelay) return;
        tickCounter = 0;

        Entity closest = null;
        double closestDist = range.getValue();

        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (!(entity instanceof LivingEntity living)) continue;
            if (living.isDead() || living.getHealth() <= 0) continue;

            double dist = mc.player.distanceTo(entity);
            if (dist > range.getValue()) continue;

            boolean validTarget = switch (target.getValue()) {
                case "Players" -> entity instanceof PlayerEntity;
                case "Mobs" -> entity instanceof MobEntity;
                case "All" -> true;
                default -> false;
            };

            if (!validTarget) continue;

            if ("Multi".equals(mode.getValue())) {
                mc.interactionManager.attackEntity(mc.player, entity);
                mc.player.swingHand(Hand.MAIN_HAND);
            } else if (dist < closestDist) {
                closestDist = dist;
                closest = entity;
            }
        }

        if (closest != null && !"Multi".equals(mode.getValue())) {
            if (rotate.isEnabled()) {
                faceEntity(closest);
            }
            mc.interactionManager.attackEntity(mc.player, closest);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }

    private void faceEntity(Entity entity) {
        double diffX = entity.getX() - mc.player.getX();
        double diffY = (entity.getY() + entity.getHeight() / 2) - (mc.player.getY() + mc.player.getEyeHeight(mc.player.getPose()));
        double diffZ = entity.getZ() - mc.player.getZ();
        double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90f;
        float pitch = (float) -Math.toDegrees(Math.atan2(diffY, dist));
        mc.player.setYaw(yaw);
        mc.player.setPitch(pitch);
    }
}

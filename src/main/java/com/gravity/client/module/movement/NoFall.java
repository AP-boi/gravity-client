package com.gravity.client.module.movement;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class NoFall extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "NoFall method", "Packet", "Packet", "Void", "MLG"));
    public NoFall() { super("NoFall", "Prevents all fall damage", Category.MOVEMENT, -1); }
    @Override public void onTick() {
        if (!isInGame()) return;
        if (mc.player.fallDistance > 2f) {
            mc.player.networkHandler.sendPacket(new net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.OnGroundOnly(true, mc.player.horizontalCollision));
        }
    }
}

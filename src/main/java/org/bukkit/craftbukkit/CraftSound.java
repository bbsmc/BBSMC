package org.bukkit.craftbukkit;

import com.google.common.base.Preconditions;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;

public class CraftSound {

    public static net.minecraft.sounds.SoundEvent getnet.minecraft.sounds.SoundEvent(String s) {
        net.minecraft.sounds.SoundEvent effect = net.minecraft.core.Registry.SOUND_EVENT.get(new net.minecraft.resources.ResourceLocation(s));
        Preconditions.checkArgument(effect != null, "Sound effect %s does not exist", s);

        return effect;
    }

    public static net.minecraft.sounds.SoundEvent getnet.minecraft.sounds.SoundEvent(Sound s) {
        net.minecraft.sounds.SoundEvent effect = net.minecraft.core.Registry.SOUND_EVENT.get(CraftNamespacedKey.toMinecraft(s.getKey()));
        Preconditions.checkArgument(effect != null, "Sound effect %s does not exist", s);

        return effect;
    }

    public static Sound getBukkit(net.minecraft.sounds.SoundEvent soundEffect) {
        return Registry.SOUNDS.get(CraftNamespacedKey.fromMinecraft(net.minecraft.core.Registry.SOUND_EVENT.getKey(soundEffect)));
    }
}
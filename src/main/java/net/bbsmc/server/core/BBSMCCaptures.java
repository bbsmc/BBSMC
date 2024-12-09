package net.bbsmc.server.core;

import org.bukkit.event.entity.CreatureSpawnEvent;

public class BBSMCCaptures {
    private static final ThreadLocal<BBSMCCaptures> threadLocal = new ThreadLocal<>();

    private transient CreatureSpawnEvent.SpawnReason creatureSpawnReason = CreatureSpawnEvent.SpawnReason.DEFAULT;

    public static BBSMCCaptures get() {
        BBSMCCaptures currentThreadObject = threadLocal.get();
        if (currentThreadObject == null) {
            currentThreadObject = new BBSMCCaptures();
            threadLocal.set(currentThreadObject);
        }
        return currentThreadObject;
    }

    public void captureCreatureSpawnReason(CreatureSpawnEvent.SpawnReason spawnReason) {
        this.creatureSpawnReason = spawnReason;
    }

    public CreatureSpawnEvent.SpawnReason getCreatureSpawnReason() {
        var result = this.creatureSpawnReason;
        this.creatureSpawnReason = CreatureSpawnEvent.SpawnReason.DEFAULT;
        return result;
    }
}

package net.bbsmc.server.core;

import com.mojang.serialization.DynamicOps;
import joptsimple.OptionSet;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.DataPackConfig;

public class ServerSetter {
    private OptionSet optionSet;
    private DataPackConfig dataPackConfig;
    private DynamicOps<Tag> dynamicOps;

    public void load(OptionSet optionSet, DataPackConfig dataPackConfig, DynamicOps<Tag> dynamicOps) {
        this.optionSet = optionSet;
        this.dataPackConfig = dataPackConfig;
        this.dynamicOps = dynamicOps;
    }

    public OptionSet getOptionSet() {
        return optionSet;
    }

    public DataPackConfig getDataPackConfig() {
        return dataPackConfig;
    }

    public DynamicOps<Tag> getDynamicOps() {
        return dynamicOps;
    }
}

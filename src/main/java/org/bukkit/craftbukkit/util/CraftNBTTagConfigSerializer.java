package org.bukkit.craftbukkit.util;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CraftNBTTagConfigSerializer {

    private static final Pattern ARRAY = Pattern.compile("^\\[.*]");
    private static final Pattern INTEGER = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)?i", Pattern.CASE_INSENSITIVE);
    private static final Pattern DOUBLE = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", Pattern.CASE_INSENSITIVE);
    private static final net.minecraft.nbt.TagParser MOJANGSON_PARSER = new net.minecraft.nbt.TagParser(new StringReader(""));

    public static Object serialize(net.minecraft.nbt.Tag base) {
        if (base instanceof net.minecraft.nbt.CompoundTag) {
            Map<String, Object> innerMap = new HashMap<>();
            for (String key : ((net.minecraft.nbt.CompoundTag) base).getAllKeys()) {
                innerMap.put(key, serialize(((net.minecraft.nbt.CompoundTag) base).get(key)));
            }

            return innerMap;
        } else if (base instanceof net.minecraft.nbt.ListTag) {
            List<Object> baseList = new ArrayList<>();
            for (int i = 0; i < ((net.minecraft.nbt.CollectionTag) base).size(); i++) {
                baseList.add(serialize((net.minecraft.nbt.Tag) ((net.minecraft.nbt.CollectionTag) base).get(i)));
            }

            return baseList;
        } else if (base instanceof net.minecraft.nbt.StringTag) {
            return base.getAsString();
        } else if (base instanceof net.minecraft.nbt.IntTag) { // No need to check for doubles, those are covered by the double itself
            return base.toString() + "i";
        }

        return base.toString();
    }

    public static net.minecraft.nbt.Tag deserialize(Object object) {
        if (object instanceof Map) {
            net.minecraft.nbt.CompoundTag compound = new net.minecraft.nbt.CompoundTag();
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) object).entrySet()) {
                compound.put(entry.getKey(), deserialize(entry.getValue()));
            }

            return compound;
        } else if (object instanceof List) {
            List<Object> list = (List<Object>) object;
            if (list.isEmpty()) {
                return new net.minecraft.nbt.ListTag(); // Default
            }

            net.minecraft.nbt.ListTag tagList = new net.minecraft.nbt.ListTag();
            for (Object tag : list) {
                tagList.add(deserialize(tag));
            }

            return tagList;
        } else if (object instanceof String) {
            String string = (String) object;

            if (ARRAY.matcher(string).matches()) {
                try {
                    return new net.minecraft.nbt.TagParser(new StringReader(string)).readArrayTag();
                } catch (CommandSyntaxException e) {
                    throw new RuntimeException("Could not deserialize found list ", e);
                }
            } else if (INTEGER.matcher(string).matches()) { //Read integers on our own
                return net.minecraft.nbt.IntTag.valueOf(Integer.parseInt(string.substring(0, string.length() - 1)));
            } else if (DOUBLE.matcher(string).matches()) {
                return net.minecraft.nbt.DoubleTag.valueOf(Double.parseDouble(string.substring(0, string.length() - 1)));
            } else {
                net.minecraft.nbt.Tag nbtBase = MOJANGSON_PARSER.type(string);

                if (nbtBase instanceof net.minecraft.nbt.IntTag) { // If this returns an integer, it did not use our method from above
                    return net.minecraft.nbt.StringTag.valueOf(nbtBase.getAsString()); // It then is a string that was falsely read as an int
                } else if (nbtBase instanceof net.minecraft.nbt.DoubleTag) {
                    return net.minecraft.nbt.StringTag.valueOf(String.valueOf(((net.minecraft.nbt.DoubleTag) nbtBase).getAsDouble())); // Doubles add "d" at the end
                } else {
                    return nbtBase;
                }
            }
        }

        throw new RuntimeException("Could not deserialize net.minecraft.nbt.Tag");
    }
}
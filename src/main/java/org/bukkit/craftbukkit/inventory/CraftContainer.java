package org.bukkit.craftbukkit.inventory;

import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class CraftContainer extends net.minecraft.world.inventory.AbstractContainerMenu {

    private final InventoryView view;
    private InventoryType cachedType;
    private net.minecraft.world.inventory.AbstractContainerMenu delegate;

    public CraftContainer(InventoryView view, net.minecraft.world.entity.player.Player player, int id) {
        super(getNotchInventoryType(view.getTopInventory()), id);
        this.view = view;
        // TODO: Do we need to check that it really is a CraftInventory?
        net.minecraft.world.Container top = ((CraftInventory) view.getTopInventory()).getInventory();
        net.minecraft.world.entity.player.Inventory bottom = (net.minecraft.world.entity.player.Inventory) ((CraftInventory) view.getBottomInventory()).getInventory();
        cachedType = view.getType();
        setupSlots(top, bottom, player);
    }

    public CraftContainer(final Inventory inventory, final net.minecraft.world.entity.player.Player player, int id) {
        this(new InventoryView() {
            @Override
            public Inventory getTopInventory() {
                return inventory;
            }

            @Override
            public Inventory getBottomInventory() {
                return getPlayer().getInventory();
            }

            @Override
            public HumanEntity getPlayer() {
                return player.getBukkitEntity();
            }

            @Override
            public InventoryType getType() {
                return inventory.getType();
            }

            @Override
            public String getTitle() {
                return inventory instanceof CraftInventoryCustom ? ((CraftInventoryCustom.MinecraftInventory) ((CraftInventory) inventory).getInventory()).getTitle() : inventory.getType().getDefaultTitle();
            }
        }, player, id);
    }

    @Override
    public InventoryView getBukkitView() {
        return view;
    }

    public static net.minecraft.world.inventory.MenuType<?> getNotchInventoryType(Inventory inventory) {
        switch (inventory.getType()) {
            case PLAYER:
            case CHEST:
            case ENDER_CHEST:
            case BARREL:
                switch (inventory.getSize()) {
                    case 9:
                        return net.minecraft.world.inventory.MenuType.GENERIC_9x1;
                    case 18:
                        return net.minecraft.world.inventory.MenuType.GENERIC_9x2;
                    case 27:
                        return net.minecraft.world.inventory.MenuType.GENERIC_9x3;
                    case 36:
                    case 41: // PLAYER
                        return net.minecraft.world.inventory.MenuType.GENERIC_9x4;
                    case 45:
                        return net.minecraft.world.inventory.MenuType.GENERIC_9x5;
                    case 54:
                        return net.minecraft.world.inventory.MenuType.GENERIC_9x6;
                    default:
                        throw new IllegalArgumentException("Unsupported custom inventory size " + inventory.getSize());
                }
            case WORKBENCH:
                return net.minecraft.world.inventory.MenuType.CRAFTING;
            case FURNACE:
                return net.minecraft.world.inventory.MenuType.FURNACE;
            case DISPENSER:
                return net.minecraft.world.inventory.MenuType.GENERIC_3x3;
            case ENCHANTING:
                return net.minecraft.world.inventory.MenuType.ENCHANTMENT;
            case BREWING:
                return net.minecraft.world.inventory.MenuType.BREWING_STAND;
            case BEACON:
                return net.minecraft.world.inventory.MenuType.BEACON;
            case ANVIL:
                return net.minecraft.world.inventory.MenuType.ANVIL;
            case SMITHING:
                return net.minecraft.world.inventory.MenuType.SMITHING;
            case HOPPER:
                return net.minecraft.world.inventory.MenuType.HOPPER;
            case DROPPER:
                return net.minecraft.world.inventory.MenuType.GENERIC_3x3;
            case SHULKER_BOX:
                return net.minecraft.world.inventory.MenuType.SHULKER_BOX;
            case BLAST_FURNACE:
                return net.minecraft.world.inventory.MenuType.BLAST_FURNACE;
            case LECTERN:
                return net.minecraft.world.inventory.MenuType.LECTERN;
            case SMOKER:
                return net.minecraft.world.inventory.MenuType.SMOKER;
            case LOOM:
                return net.minecraft.world.inventory.MenuType.LOOM;
            case CARTOGRAPHY:
                return net.minecraft.world.inventory.MenuType.CARTOGRAPHY_TABLE;
            case GRINDSTONE:
                return net.minecraft.world.inventory.MenuType.GRINDSTONE;
            case STONECUTTER:
                return net.minecraft.world.inventory.MenuType.STONECUTTER;
            case CREATIVE:
            case CRAFTING:
            case MERCHANT:
                throw new IllegalArgumentException("Can't open a " + inventory.getType() + " inventory!");
            default:
                // TODO: If it reaches the default case, should we throw an error?
                return net.minecraft.world.inventory.MenuType.GENERIC_9x3;
        }
    }

    private void setupSlots(net.minecraft.world.Container top, net.minecraft.world.entity.player.Inventory bottom, net.minecraft.world.entity.player.Player entityhuman) {
        int windowId = -1;
        switch (cachedType) {
            case CREATIVE:
                break; // TODO: This should be an error?
            case PLAYER:
            case CHEST:
            case ENDER_CHEST:
            case BARREL:
                delegate = new net.minecraft.world.inventory.ChestMenu(net.minecraft.world.inventory.MenuType.GENERIC_9x3, windowId, bottom, top, top.getContainerSize() / 9);
                break;
            case DISPENSER:
            case DROPPER:
                delegate = new net.minecraft.world.inventory.DispenserMenu(windowId, bottom, top);
                break;
            case FURNACE:
                delegate = new net.minecraft.world.inventory.FurnaceMenu(windowId, bottom, top, new SimpleContainerData(4));
                break;
            case CRAFTING: // TODO: This should be an error?
            case WORKBENCH:
                setupWorkbench(top, bottom); // SPIGOT-3812 - manually set up slots so we can use the delegated inventory and not the automatically created one
                break;
            case ENCHANTING:
                delegate = new net.minecraft.world.inventory.EnchantmentMenu(windowId, bottom);
                break;
            case BREWING:
                delegate = new net.minecraft.world.inventory.BrewingStandMenu(windowId, bottom, top, new SimpleContainerData(2));
                break;
            case HOPPER:
                delegate = new net.minecraft.world.inventory.HopperMenu(windowId, bottom, top);
                break;
            case ANVIL:
            case SMITHING:
                setupAnvil(top, bottom); // SPIGOT-6783 - manually set up slots so we can use the delegated inventory and not the automatically created one
                break;
            case BEACON:
                delegate = new net.minecraft.world.inventory.BeaconMenu(windowId, bottom);
                break;
            case SHULKER_BOX:
                delegate = new net.minecraft.world.inventory.ShulkerBoxMenu(windowId, bottom, top);
                break;
            case BLAST_FURNACE:
                delegate = new net.minecraft.world.inventory.BlastFurnaceMenu(windowId, bottom, top, new SimpleContainerData(4));
                break;
            case LECTERN:
                delegate = new net.minecraft.world.inventory.LecternMenu(windowId, top, new SimpleContainerData(1), bottom);
                break;
            case SMOKER:
                delegate = new net.minecraft.world.inventory.SmokerMenu(windowId, bottom, top, new SimpleContainerData(4));
                break;
            case LOOM:
                delegate = new net.minecraft.world.inventory.LoomMenu(windowId, bottom);
                break;
            case CARTOGRAPHY:
                delegate = new net.minecraft.world.inventory.CartographyTableMenu(windowId, bottom);
                break;
            case GRINDSTONE:
                delegate = new net.minecraft.world.inventory.GrindstoneMenu(windowId, bottom);
                break;
            case STONECUTTER:
                delegate = new net.minecraft.world.inventory.StonecutterMenu(windowId, bottom);
                break;
            case MERCHANT:
                delegate = new net.minecraft.world.inventory.MerchantMenu(windowId, bottom);
                break;
        }

        if (delegate != null) {
            this.lastSlots = delegate.lastSlots;
            this.slots = delegate.slots;
            this.remoteSlots = delegate.remoteSlots;
        }

        // SPIGOT-4598 - we should still delegate the shift click handler
        switch (cachedType) {
            case WORKBENCH:
                delegate = new net.minecraft.world.inventory.CraftingMenu(windowId, bottom);
                break;
            case ANVIL:
                delegate = new net.minecraft.world.inventory.AnvilMenu(windowId, bottom);
                break;
        }
    }

    private void setupWorkbench(net.minecraft.world.Container top, net.minecraft.world.Container bottom) {
        // This code copied from net.minecraft.world.inventory.AbstractContainerMenuWorkbench
        this.addSlot(new Slot(top, 0, 124, 35));

        int row;
        int col;

        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 3; ++col) {
                this.addSlot(new Slot(top, 1 + col + row * 3, 30 + col * 18, 17 + row * 18));
            }
        }

        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 9; ++col) {
                this.addSlot(new Slot(bottom, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (col = 0; col < 9; ++col) {
            this.addSlot(new Slot(bottom, col, 8 + col * 18, 142));
        }
        // End copy from net.minecraft.world.inventory.AbstractContainerMenuWorkbench
    }

    private void setupAnvil(net.minecraft.world.Container top, net.minecraft.world.Container bottom) {
        // This code copied from net.minecraft.world.inventory.AbstractContainerMenuAnvilAbstract
        this.addSlot(new Slot(top, 0, 27, 47));
        this.addSlot(new Slot(top, 1, 76, 47));
        this.addSlot(new Slot(top, 2, 134, 47));

        int row;
        int col;

        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 9; ++col) {
                this.addSlot(new Slot(bottom, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (row = 0; row < 9; ++row) {
            this.addSlot(new Slot(bottom, row, 8 + row * 18, 142));
        }
        // End copy from net.minecraft.world.inventory.AbstractContainerMenuAnvilAbstract
    }

    @Override
    public ItemStack quickMoveStack(net.minecraft.world.entity.player.Player entityhuman, int i) {
        return (delegate != null) ? delegate.quickMoveStack(entityhuman, i) : ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(net.minecraft.world.entity.player.Player entity) {
        return true;
    }

    @Override
    public net.minecraft.world.inventory.MenuType<?> getType() {
        return getNotchInventoryType(view.getTopInventory());
    }
}
package team.lodestar.lodestone.systems.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import team.lodestar.lodestone.systems.datagen.itemsmith.ItemModelSmith;

public class ItemModelSmithTypes {

    public static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    public static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

    public static ItemModelSmith HANDHELD_ITEM = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        provider.createGenericModel(item, HANDHELD, provider.getItemTexture(name));
    }));
    public static ItemModelSmith GENERATED_ITEM = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        provider.createGenericModel(item, GENERATED, provider.getItemTexture(name));
    }));

    public static ItemModelSmith UNIQUE_ITEM_MODEL = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        provider.getExistingFile(provider.modLoc(name));
    }));

    public static ItemModelSmith BLOCK_TEXTURE_ITEM = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        provider.createGenericModel(item, GENERATED, provider.getBlockTexture(name));
    }));
    public static ItemModelSmith BLOCK_MODEL_ITEM = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        provider.getBuilder(name).parent(new ModelFile.UncheckedModelFile(provider.modLoc("block/" + name)));
    }));

    public static ItemModelSmith WALL_ITEM = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        provider.wallInventory(name, provider.getBlockTextureFromCache("wall"));
    }));
    public static ItemModelSmith FENCE_ITEM = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        provider.fenceInventory(name, provider.getBlockTextureFromCache("texture"));
    }));

    public static ItemModelSmith TRAPDOOR_ITEM = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        provider.getBuilder(name).parent(new ModelFile.UncheckedModelFile(provider.modLoc("block/" + name+"_bottom")));
    }));

    public static ItemModelSmith PRESSURE_PLATE_ITEM = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        provider.getBuilder(name).parent(new ModelFile.UncheckedModelFile(provider.modLoc("block/" + name+"_up")));
    }));
    public static ItemModelSmith BUTTON_ITEM = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        provider.getBuilder(name).parent(new ModelFile.UncheckedModelFile(provider.modLoc("block/" + name+"_inventory")));
    }));
}

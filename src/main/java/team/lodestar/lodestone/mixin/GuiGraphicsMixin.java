package team.lodestar.lodestone.mixin;


import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lodestar.lodestone.handlers.screenparticle.ScreenParticleHandler;
import team.lodestar.lodestone.systems.particle.screen.TheWorstInterface;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin implements TheWorstInterface {

    @Unique
    boolean lodestone$bl = false;

    //If some previous methods need special case handling, disable this with lodestone$bl
    @Inject(method = {"renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;IIII)V"}, at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
    private void lodestone$renderGuiItem(LivingEntity entity, Level level, ItemStack stack, int x, int y, int p_283260_, int p_281995_, CallbackInfo ci) {
        if (!lodestone$bl) {
            ScreenParticleHandler.renderItemStackEarly(stack, x, y, true);
        }
    }

    //Reset lodestone$bl at TAIL
    @Inject(method = {"renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;IIII)V"}, at = @At(value = "TAIL"))
    private void lodestone$renderGuiItemEnd(LivingEntity entity, Level level, ItemStack stack, int x, int y, int p_283260_, int p_281995_, CallbackInfo ci) {
        lodestone$bl = false;
    }

    //For CreativeModeInventoryScreenMixin and AbstractContainerScreenMixin
    @Inject(method = "renderItem(Lnet/minecraft/world/item/ItemStack;II)V", at = @At("HEAD"))
    private void lodestone$disableDoubleRendering(ItemStack stack, int x, int y, CallbackInfo ci) {
        lodestone$bl = true;
    }

    //Dont remember but needed
    @Inject(method = "renderItem(Lnet/minecraft/world/item/ItemStack;III)V", at = @At("HEAD"))
    private void lodestone$DisableDoubleRendering(ItemStack stack, int x, int y, int seed, CallbackInfo ci) {
        lodestone$bl = true;
    }

    @Inject(at = @At(value = "TAIL"), method = "renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;IIII)V")
    private void lodestone$renderGuiItemLate(LivingEntity entity, Level level, ItemStack stack, int p_281271_, int p_282210_, int p_283260_, int p_281995_, CallbackInfo ci) {
        ScreenParticleHandler.renderItemStackLate();
    }

    @Override
    public boolean lodestone$getB() {
        return lodestone$bl;
    }

    @Override
    public void lodestone$setB(boolean bl) {
        this.lodestone$bl = bl;
    }
}
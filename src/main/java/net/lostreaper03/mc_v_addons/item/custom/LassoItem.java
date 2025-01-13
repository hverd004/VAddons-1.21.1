package net.lostreaper03.mc_v_addons.item.custom;

import net.lostreaper03.mc_v_addons.entity.LassoProjectileEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class LassoItem extends Item {
    public LassoItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (level.isClientSide) {
            // Create and launch the lasso projectile
            LassoProjectileEntity lasso = new LassoProjectileEntity(level, player);
            lasso.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(lasso);
            System.out.println("LassoProjectileEntity added to world");
        }

        player.getCooldowns().addCooldown(this, 20);
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide);
    }
}

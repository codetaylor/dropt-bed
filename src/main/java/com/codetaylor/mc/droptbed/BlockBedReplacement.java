package com.codetaylor.mc.droptbed;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.ArrayList;
import java.util.Map;

public class BlockBedReplacement
    extends BlockBed {

  public BlockBedReplacement() {

    this.setSoundType(SoundType.WOOD);
    this.setHardness(0.2F);
    this.disableStats();
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {

    //
  }

  @Override
  public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity, ItemStack stack) {

    if (state.getValue(PART) == BlockBed.EnumPartType.HEAD && tileEntity instanceof TileEntityBed) {
      this.dropItems(world, player, pos, state, (TileEntityBed) tileEntity);

      EnumFacing facing = state.getValue(FACING);
      BlockPos otherPos = pos.offset(facing.getOpposite());
      IBlockState otherBlockState = world.getBlockState(otherPos);

      if (otherBlockState.getBlock() == this && otherBlockState.getValue(PART) == EnumPartType.FOOT) {
        world.setBlockToAir(otherPos);
      }

    } else if (state.getValue(PART) == EnumPartType.FOOT) {
      EnumFacing facing = state.getValue(FACING);
      BlockPos otherPos = pos.offset(facing);
      IBlockState otherBlockState = world.getBlockState(otherPos);
      TileEntity otherTileEntity = world.getTileEntity(otherPos);

      if (otherBlockState.getBlock() == this && otherBlockState.getValue(PART) == EnumPartType.HEAD && otherTileEntity instanceof TileEntityBed) {
        this.dropItems(world, player, otherPos, otherBlockState, (TileEntityBed) otherTileEntity);
        world.setBlockToAir(otherPos);
      }
    }
  }

  private void dropItems(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntityBed tileEntity) {

    ItemStack itemstack = tileEntity.getItemStack();
    ArrayList<ItemStack> drops = new ArrayList<>();
    drops.add(itemstack);
    ForgeEventFactory.fireBlockHarvesting(drops, world, pos, state, 0, 1, this.isSilkTouching(player.getHeldItemMainhand()), player);

    for (ItemStack drop : drops) {
      Block.spawnAsEntity(world, pos, drop);
    }
  }

  private boolean isSilkTouching(ItemStack heldItem) {

    if (heldItem.isEmpty()) {
      return false;
    }

    Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(heldItem);

    return enchantments.containsKey(Enchantments.SILK_TOUCH);
  }
}

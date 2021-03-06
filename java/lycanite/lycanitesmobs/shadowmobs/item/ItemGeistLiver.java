package lycanite.lycanitesmobs.shadowmobs.item;

import lycanite.lycanitesmobs.AssetManager;
import lycanite.lycanitesmobs.ObjectManager;
import lycanite.lycanitesmobs.core.item.ItemBase;
import lycanite.lycanitesmobs.shadowmobs.ShadowMobs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemGeistLiver extends ItemBase {

	// ==================================================
	//                   Constructor
	// ==================================================
    public ItemGeistLiver() {
        super();
        this.group = ShadowMobs.group;
        this.itemName = "geistliver";
        this.setup();
    }


    // ==================================================
    //                    Item Use
    // ==================================================
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemStack = player.getHeldItem(hand);
        if (world.isRemote) {
            return EnumActionResult.SUCCESS;
        }
        else {
            pos = pos.offset(facing);
            if(player.canPlayerEdit(pos, facing, itemStack)) {
                Block block = world.getBlockState(pos).getBlock();
                if(block == Blocks.AIR) {
                    world.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, AssetManager.getSound("shadowfire"), SoundCategory.PLAYERS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F, false);
                    world.setBlockState(pos, ObjectManager.getBlock("shadowfire").getDefaultState());
                }
                if(!player.capabilities.isCreativeMode)
                    itemStack.func_190920_e(Math.max(0, itemStack.func_190916_E() - 1));
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }
}

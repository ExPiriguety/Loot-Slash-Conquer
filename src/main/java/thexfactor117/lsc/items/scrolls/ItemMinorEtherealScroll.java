package thexfactor117.lsc.items.scrolls;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thexfactor117.lsc.capabilities.cap.CapabilityPlayerStats;
import thexfactor117.lsc.capabilities.implementation.Stats;
import thexfactor117.lsc.init.ModItems;
import thexfactor117.lsc.init.ModTabs;
import thexfactor117.lsc.items.base.ItemBase;
import thexfactor117.lsc.loot.Rarity;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ItemMinorEtherealScroll extends ItemBase
{
	private Rarity rarity;
	
	public ItemMinorEtherealScroll(String name, Rarity rarity)
	{
		super(name, ModTabs.lscTab);
		this.rarity = rarity;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{		
		ItemStack stack = player.inventory.getCurrentItem();
		Stats stats = (Stats) player.getCapability(CapabilityPlayerStats.STATS, null);
		
		if (player.inventory.getCurrentItem().getItem() == ModItems.MINOR_ETHEREAL_SCROLL && stats != null)
		{
			if (!world.isRemote && stats.getMana() >= 10)
			{
				player.heal((float) (player.getMaxHealth() * 0.3));
				stack.shrink(1); // decrease stack size by 1
				stats.decreaseMana(10);
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced)
    {		
		tooltip.add(rarity.getColor() + rarity.getName());
    }
}

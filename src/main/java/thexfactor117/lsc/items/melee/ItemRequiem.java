package thexfactor117.lsc.items.melee;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import thexfactor117.lsc.capabilities.api.IChunkLevel;
import thexfactor117.lsc.capabilities.api.IChunkLevelHolder;
import thexfactor117.lsc.capabilities.cap.CapabilityChunkLevel;
import thexfactor117.lsc.init.ModTabs;
import thexfactor117.lsc.items.base.ISpecial;
import thexfactor117.lsc.items.base.ItemMelee;
import thexfactor117.lsc.loot.Attribute;
import thexfactor117.lsc.loot.Rarity;
import thexfactor117.lsc.loot.generation.ItemGeneratorHelper;

public class ItemRequiem extends ItemMelee implements ISpecial
{
	public ItemRequiem(ToolMaterial material, String name, String type)
	{
		super(material, name, type);
		this.setCreativeTab(ModTabs.lscTab);
	}

	@Override
	public void createSpecial(ItemStack stack, NBTTagCompound nbt, World world, ChunkPos pos) 
	{
		IChunkLevelHolder chunkLevelHolder = world.getCapability(CapabilityChunkLevel.CHUNK_LEVEL, null);
		IChunkLevel chunkLevel = chunkLevelHolder.getChunkLevel(pos);
		int level = chunkLevel.getChunkLevel();
		
		nbt.setBoolean("IsSpecial", true);
		Rarity.setRarity(nbt, Rarity.EPIC);
		nbt.setInteger("Level", level);
		
		// Attributes
		Attribute.AGILITY.addAttribute(nbt, world.rand, 5);
		Attribute.DEXTERITY.addAttribute(nbt, world.rand, 5);
		Attribute.LIFE_STEAL.addAttribute(nbt, world.rand, 0.05);
		Attribute.MANA_STEAL.addAttribute(nbt, world.rand, 0.05);
		
		ItemGeneratorHelper.setAttributeModifiers(nbt, stack);
	}
}

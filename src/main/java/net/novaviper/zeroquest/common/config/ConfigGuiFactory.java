package net.novaviper.zeroquest.common.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.novaviper.zeroquest.common.lib.Constants;

public class ConfigGuiFactory implements IModGuiFactory {
	// Get Examples from ForgeGuiFactory\\
	// Properties are in ConfigHandler\\
	//@formatter:off

	@Override
	public void initialize(Minecraft minecraftInstance) {}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {return ModGuiConfig.class;}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {return null;}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {return null;}

	public static class ModGuiConfig extends GuiConfig{

		public ModGuiConfig(GuiScreen guiScreen){
			super(guiScreen, getConfigElements(), Constants.modid, false, false, I18n.format("gui.config.title"));
		}

		private static List<IConfigElement> getConfigElements()
		{
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			list.add(new DummyCategoryElement("function", "gui.config.function", FunctionEntry.class));
			list.add(new DummyCategoryElement("load", "gui.config.load", LoadEntry.class));
			list.add(new DummyCategoryElement("misc", "gui.config.misc", MiscEntry.class));
			return list;
		}

		/**
		 * This custom list entry provides the General Settings entry on the Minecraft Forge Configuration screen.
		 * It extends the base Category entry class and defines the IConfigElement objects that will be used to build the child screen.
		 */
		public static class FunctionEntry extends CategoryEntry //Function Entry
		{
			public FunctionEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
			{
				super(owningScreen, owningEntryList, prop);
			}

			@Override
			protected GuiScreen buildChildScreen()
			{

				List<IConfigElement> list = new ArrayList<IConfigElement>();
				//list.add(new DummyCategoryElement("terrain", "gui.config.terrain", TerrainEntry.class));
				list.addAll((new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_FUNCTION))).getChildElements());

				return new GuiConfig(this.owningScreen, list, this.owningScreen.modID, ConfigHandler.CATEGORY_FUNCTION,
						this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
						this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
						I18n.format("gui.config.function"),
						I18n.format("gui.config.function.tooltip"));
			}
		}

		public static class LoadEntry extends CategoryEntry //Load Entry
		{
			public LoadEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
			{
				super(owningScreen, owningEntryList, prop);
			}

			@Override
			protected GuiScreen buildChildScreen()
			{
				List<IConfigElement> list = new ArrayList<IConfigElement>();
				//list.add(new DummyCategoryElement("terrain", "gui.config.terrain", TerrainEntry.class));
				list.addAll((new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_LOAD))).getChildElements());

				return new GuiConfig(this.owningScreen, list, this.owningScreen.modID, ConfigHandler.CATEGORY_LOAD,
						this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
						this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
						I18n.format("gui.config.load"),
						I18n.format("gui.config.load.tooltip"));
			}
		}

		public static class MiscEntry extends CategoryEntry //Miscellaneous Entry
		{
			public MiscEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
			{
				super(owningScreen, owningEntryList, prop);
			}

			@Override
			protected GuiScreen buildChildScreen()
			{
				List<IConfigElement> list = new ArrayList<IConfigElement>();
				list.add(new DummyCategoryElement("dimensions", "gui.config.dimensions", TerrainEntry.class));
				list.add(new DummyCategoryElement("biomes", "gui.config.biomes", BiomesEntry.class));
				list.addAll((new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_MISC))).getChildElements());

				return new GuiConfig(this.owningScreen, list, this.owningScreen.modID, ConfigHandler.CATEGORY_MISC,
						this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
						this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
						I18n.format("gui.config.misc"),
						I18n.format("gui.config.misc.tooltip"));
			}
		}

		public static class TerrainEntry extends CategoryEntry //Miscellaneous Entry
		{
			public TerrainEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
			{
				super(owningScreen, owningEntryList, prop);
			}

			@Override
			protected GuiScreen buildChildScreen()
			{
				return new GuiConfig(this.owningScreen,
						(new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_DIMENSION)).getChildElements()),
						this.owningScreen.modID, ConfigHandler.CATEGORY_DIMENSION, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
						this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
						I18n.format("gui.config.dimensions"),
						I18n.format("gui.config.dimensions.tooltip"));
			}
		}
	}

	public static class BiomesEntry extends CategoryEntry //Miscellaneous Entry
	{
		public BiomesEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
		{
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		protected GuiScreen buildChildScreen()
		{

			return new GuiConfig(this.owningScreen,
					(new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_BIOME)).getChildElements()),
					this.owningScreen.modID, ConfigHandler.CATEGORY_BIOME, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
					this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
					I18n.format("gui.config.biomes"),
					I18n.format("gui.config.biomes.tooltip"));
		}
	}
}

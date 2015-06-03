package common.zeroquest.util;

import java.util.Hashtable;

import common.zeroquest.lib.Constants;

import net.minecraft.util.ResourceLocation;

/**
 * @author ProPercivalalb
 */
public class ResourceReference {

	private static Hashtable<String, ResourceLocation> petInventorySkins = new Hashtable<String, ResourceLocation>();
	private static Hashtable<String, ResourceLocation> zertumWildSkins = new Hashtable<String, ResourceLocation>();
	private static Hashtable<String, ResourceLocation> zertumTameSkins = new Hashtable<String, ResourceLocation>();
	private static Hashtable<String, ResourceLocation> zertumAngrySkins = new Hashtable<String, ResourceLocation>();
	private static Hashtable<String, ResourceLocation> zertumEvoWildSkins = new Hashtable<String, ResourceLocation>();
	private static Hashtable<String, ResourceLocation> zertumEvoTameSkins = new Hashtable<String, ResourceLocation>();
	private static Hashtable<String, ResourceLocation> zertumLayers = new Hashtable<String, ResourceLocation>();
	private static Hashtable<String, ResourceLocation> zertumEvoLayers = new Hashtable<String, ResourceLocation>();
	private static Hashtable<String, ResourceLocation> zertumMaleLayers = new Hashtable<String, ResourceLocation>();
	private static Hashtable<String, ResourceLocation> zertumEvoMaleLayers = new Hashtable<String, ResourceLocation>();
	private static Hashtable<String, ResourceLocation> jakanSkins = new Hashtable<String, ResourceLocation>();
	private static Hashtable<String, ResourceLocation> kortorSkins = new Hashtable<String, ResourceLocation>();

	public static final ResourceLocation foodBowlGui = new ResourceLocation(Constants.modid, getGuiTexturePath("foodBowl.png"));
	public static final ResourceLocation nileTableGui = new ResourceLocation(Constants.modid, getGuiTexturePath("nileTable.png"));
	public static final ResourceLocation female = new ResourceLocation(Constants.modid, getGuiTexturePath("female.png"));
	public static final ResourceLocation male = new ResourceLocation(Constants.modid, getGuiTexturePath("male.png"));
	public static final ResourceLocation kurr = new ResourceLocation(Constants.modid, getEntityTexturePath("kurr.png"));
	public static final ResourceLocation riggator = new ResourceLocation(Constants.modid, getEntityTexturePath("riggator.png"));
	public static final ResourceLocation rowaren = new ResourceLocation(Constants.modid, getEntityTexturePath("rowaren.png"));
	public static final ResourceLocation foodBowlModel = new ResourceLocation(Constants.modid, getModelTexturePath("foodBowl.png"));
	public static final ResourceLocation nileTableModel = new ResourceLocation(Constants.modid, getModelTexturePath("nileTable.png"));

	public static ResourceLocation getInventorySkins(String type) {
		if (!petInventorySkins.containsKey(type)) {
			petInventorySkins.put(type, new ResourceLocation(Constants.modid, getGuiTexturePath("petInventory" + type + ".png")));
		}
		return petInventorySkins.get(type);
	}

	//@formatter:off
	/*==============================================ZERTUM SKINS==============================================*/
	//@formatter:on
	public static ResourceLocation getZWildSkins(String type) {
		if (!zertumWildSkins.containsKey(type)) {
			zertumWildSkins.put(type, new ResourceLocation(Constants.modid, getZertumTexturePath(type + "zertum.png")));
		}
		return zertumWildSkins.get(type);
	}

	public static ResourceLocation getZTameSkins(String type) {
		if (!zertumTameSkins.containsKey(type)) {
			zertumTameSkins.put(type, new ResourceLocation(Constants.modid, getZertumTexturePath(type + "zertum_tame.png")));
		}
		return zertumTameSkins.get(type);
	}

	public static ResourceLocation getZAngrySkins(String type) {
		if (!zertumAngrySkins.containsKey(type)) {
			zertumAngrySkins.put(type, new ResourceLocation(Constants.modid, getZertumTexturePath(type + "zertum_angry.png")));
		}
		return zertumAngrySkins.get(type);
	}

	public static ResourceLocation getZEvoWildSkins(String type) {
		if (!zertumEvoWildSkins.containsKey(type)) {
			zertumEvoWildSkins.put(type, new ResourceLocation(Constants.modid, getEvolvedZertumTexturePath(type + "zertum.png")));
		}
		return zertumEvoWildSkins.get(type);
	}

	public static ResourceLocation getZEvoTamekins(String type) {
		if (!zertumEvoTameSkins.containsKey(type)) {
			zertumEvoTameSkins.put(type, new ResourceLocation(Constants.modid, getEvolvedZertumTexturePath(type + "zertum.png")));
		}
		return zertumEvoTameSkins.get(type);
	}

	//@formatter:off
	/*==============================================ZERTUM LAYERS==============================================*/
	//@formatter:on
	public static ResourceLocation getZLayers(String type) {
		if (!zertumLayers.containsKey(type)) {
			zertumLayers.put(type, new ResourceLocation(Constants.modid, getZertumTexturePath("zertum_" + type + ".png")));
		}
		return zertumLayers.get(type);
	}

	public static ResourceLocation getZELayers(String type) {
		if (!zertumEvoLayers.containsKey(type)) {
			zertumEvoLayers.put(type, new ResourceLocation(Constants.modid, getEvolvedZertumTexturePath("zertum_" + type + ".png")));
		}
		return zertumEvoLayers.get(type);
	}

	public static ResourceLocation getZMaleLayers(String type) {
		if (!zertumMaleLayers.containsKey(type)) {
			zertumMaleLayers.put(type, new ResourceLocation(Constants.modid, getZertumGenderTexturePath(type + "zertum_male.png")));
		}
		return zertumMaleLayers.get(type);
	}

	public static ResourceLocation getZMaleTameLayers(String type) {
		if (!zertumMaleLayers.containsKey(type)) {
			zertumMaleLayers.put(type, new ResourceLocation(Constants.modid, getZertumGenderTexturePath(type + "zertum_male_tame.png")));
		}
		return zertumMaleLayers.get(type);
	}

	public static ResourceLocation getZEMaleLayers(String type) {
		if (!zertumEvoMaleLayers.containsKey(type)) {
			zertumEvoMaleLayers.put(type, new ResourceLocation(Constants.modid, getEvolvedZertumGenderTexturePath(type + "zertum_male.png")));
		}
		return zertumEvoMaleLayers.get(type);
	}

	//@formatter:off
	/*==============================================JAKAN SKINS==============================================*/
	//@formatter:on
	public static ResourceLocation getJakanSkins(String type) {
		if (!jakanSkins.containsKey(type)) {
			jakanSkins.put(type, new ResourceLocation(Constants.modid, getJakanTexturePath("jakan" + type + ".png")));
		}
		return jakanSkins.get(type);
	}

	//@formatter:off
	/*==============================================KORTOR SKINS==============================================*/
	//@formatter:on
	public static ResourceLocation getKortorSkins(String type) {
		if (!kortorSkins.containsKey(type)) {
			kortorSkins.put(type, new ResourceLocation(Constants.modid, getKortorTexturePath("kortor" + type + ".png")));
		}
		return kortorSkins.get(type);
	}

	//@formatter:off
	/*==============================================TEXTURE PATHS==============================================*/
	 /**
     * Gets a local GUI texture file path.
     * @param textureFileName The .png file that relates to the texture file. 
     * @return The whole path string including the given parameter.
     */
    private static String getGuiTexturePath(String textureFileName) {
	    return String.format("%s/gui/%s", new Object[] {getOverrideTexturesPath(), textureFileName});
	}
	
    /**
     * Gets a local Zertum texture file path.
     * @param textureFileName The .png file that relates to the texture file. 
     * @return The whole path string including the given parameter.
     */
    private static String getZertumTexturePath(String textureFileName) {
	    return String.format("%s/entity/zertum/%s", new Object[] {getOverrideTexturesPath(), textureFileName});
	}
    
    /**
     * Gets a local Zertum gender texture file path.
     * @param textureFileName The .png file that relates to the texture file. 
     * @return The whole path string including the given parameter.
     */
    private static String getZertumGenderTexturePath(String textureFileName) {
	    return String.format("%s/entity/zertum/gender/%s", new Object[] {getOverrideTexturesPath(), textureFileName});
	}
    
    /**
     * Gets a local Evolved Zertum texture file path.
     * @param textureFileName The .png file that relates to the texture file. 
     * @return The whole path string including the given parameter.
     */
    private static String getEvolvedZertumTexturePath(String textureFileName) {
	    return String.format("%s/entity/zertum/evo/%s", new Object[] {getOverrideTexturesPath(), textureFileName});
	}
    
    /**
     * Gets a local Evolved Zertum gendertexture file path.
     * @param textureFileName The .png file that relates to the texture file. 
     * @return The whole path string including the given parameter.
     */
    private static String getEvolvedZertumGenderTexturePath(String textureFileName) {
	    return String.format("%s/entity/zertum/evo/gender/%s", new Object[] {getOverrideTexturesPath(), textureFileName});
	}
    
    /**
     * Gets a local Jakan texture file path.
     * @param textureFileName The .png file that relates to the texture file. 
     * @return The whole path string including the given parameter.
     */
    private static String getJakanTexturePath(String textureFileName) {
	    return String.format("%s/entity/jakan/%s", new Object[] {getOverrideTexturesPath(), textureFileName});
	}
    
    /**
     * Gets a local Kortor texture file path.
     * @param textureFileName The .png file that relates to the texture file. 
     * @return The whole path string including the given parameter.
     */
    private static String getKortorTexturePath(String textureFileName) {
	    return String.format("%s/entity/kortor/%s", new Object[] {getOverrideTexturesPath(), textureFileName});
	}
    
    /**
     * Gets a local entity texture file path.
     * @param textureFileName The .png file that relates to the texture file. 
     * @return The whole path string including the given parameter.
     */
    private static String getEntityTexturePath(String textureFileName) {
	    return String.format("%s/entity/%s", new Object[] {getOverrideTexturesPath(), textureFileName});
	}
    
    /**
     * Gets a local item/model texture file path.
     * @param textureFileName The .png file that relates to the texture file. 
     * @return The whole path string including the given parameter.
     */
    private static String getModelTexturePath(String textureFileName) {
	    return String.format("%s/models/%s", new Object[] {getOverrideTexturesPath(), textureFileName});
	}
    
    /**
     * Gets the location of the mods textures.
     * @return The default texture local
     */
    private static String getOverrideTexturesPath() {
	    return "textures";
	}
}
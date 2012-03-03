package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import net.minecraft.client.Minecraft;

public class mod_Timber extends BaseMod {
  public String getVersion() { return "1.1.0"; }
  
  @MLProp (name="axeIds", info="List of item ids that are axes.")
    public static String axes = "258, 271, 275, 279, 286, 1052";
  @MLProp (name="woodIds", info="List of item ids that are wood.")
    public static String wood = "17";
  @MLProp (name="toggleKey", info="Sets the key to hold down to toggle Timber! off. (example, 46 = C)") 
    public static int key = 46;
  
  static Block[] tree;
    
  public void load() {
    String[] ids = wood.split(",");
    tree = new Block[ids.length];
    for (int i=0; i<ids.length; i++) {
      Block.blocksList[i] = null;
      tree[i] = new BlockTimberTree(i).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setBlockName("log");
    }
    ModLoader.SetInGameHook(this, true, true);
  }
    
  public boolean OnTickInGame(float f, Minecraft minecraft) {
    if (minecraft.thePlayer.getCurrentEquippedItem() != null && canTimber(minecraft))
      BlockTimberTree.setAxe(Boolean.valueOf(true));
    else
      BlockTimberTree.setAxe(Boolean.valueOf(false));
	
    return true;
  }
  
  private boolean canTimber(Minecraft minecraft) {
  	return isItemAxe(minecraft.thePlayer.getCurrentEquippedItem().itemID, axes);
  }
  
  private boolean isItemAxe(int itemID, String idList) {
    String[] ids = idList.split(",");
    for (int i=0; i<ids.length; i++) {
      int id = Integer.parseInt(ids[i].trim());
      if (itemID == id) return true;
    }
    
    return false;
  }

}
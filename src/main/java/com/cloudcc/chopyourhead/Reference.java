package com.cloudcc.chopyourhead;

/**
 * Created by Tobias on 09.11.2016.
 */
public class Reference {

    public static final String MODID = "chopyourhead";
    public static final String VERSION = "1.0";
    public static final String CLIENT_PROXY_CLASS = "com.cloudcc.chopyourhead.proxy.clientProxy";
    public static final String SERVER_PROXY_CLASS = "com.cloudcc.chopyourhead.proxy.serverProxy";


    public static enum ModBlocks{
        //XXXX("unlocalizedName","registryName//.json")

        GRINDER("grinder", "BlockGrinder"),
        MACHINECASING("machinecasing","BlockMachineCasing");

        private String unlocalizedName;
        private String registryName;

        ModBlocks(String unlocalizedName, String registryName){
            this.registryName = registryName;
            this.unlocalizedName = unlocalizedName;
        }

        public String getUnlocalizedName(){ return unlocalizedName; }

        public String getRegistryName(){ return registryName; }

    }


    public static enum ModItems{


        //XXXX("unlocalizedName","registryName//.json")
        MITHRIL_INGOT("mithril_ingot","ItemMithrilIngot"),
        MITHRIL_DUST("mithril_dust","ItemMithrilDust"),
        ADAMANTIT_DUST("adamantit_dust","ItemAdamantitDust"),
        ADAMANTIT_INGOT("adamantit_ingot","ItemAdamantitIngot"),
        Chicken_Head("chickenhead", "ItemChickenHead"),
        MITHRIL_GEAR("mithrilgear", "ItemMithrilGear");

        private String unlocalizedName;
        private String registryName;

        ModItems(String unlocalizedName, String registryName){
            this.registryName = registryName;
            this.unlocalizedName = unlocalizedName;
        }

        public String getUnlocalizedName(){ return unlocalizedName; }

        public String getRegistryName(){ return registryName; }

    }
    public static enum ModOres{
        //XXXX("unlocalizedName","registryName//.json")

        ADAMANTIT_ORE("adamantitore", "BlockAdamantitOre"),
        MITHRIL_ORE("mithrilore", "BlockMithrilOre");

        private String unlocalizedName;
        private String registryName;

        ModOres(String unlocalizedName, String registryName){
            this.registryName = registryName;
            this.unlocalizedName = unlocalizedName;
        }

        public String getUnlocalizedName(){ return unlocalizedName; }

        public String getRegistryName(){ return registryName; }

    }

    public static enum ModCrops{

        CORN_PLANT("cornplant","BlockCornPlant");

        private String unlocalizedName;
        private String registryName;

        ModCrops(String unlocalizedName, String registryName){
            this.registryName = registryName;
            this.unlocalizedName = unlocalizedName;
        }

        public String getRegistryName() {
            return registryName;
        }

        public String getUnlocalizedName() {
            return unlocalizedName;
        }
    }
}




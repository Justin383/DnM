//package net.wjka.dnm.GUI;
//
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.gui.DrawContext;
//import net.minecraft.client.gui.screen.Screen;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.text.Text;
//import net.minecraft.util.Identifier;
//import net.wjka.dnm.DungeonsandMinecraft;
//import net.wjka.dnm.NetworkingManager;
//
//public class CustomPopupScreen extends Screen {
//    private final long openTime;
//    private final long rollDelay;
//    private static final long DURATION = 500; // in ms
//    private static final long delay = 100;
//    private static final int TEXTURE_WIDTH = 192;
//    private static final int TEXTURE_HEIGHT = 100;
//    private static final Identifier T_POSITIVE = new Identifier(DungeonsandMinecraft.MOD_ID, "textures/item/positive_dice.png");
//    private static final Identifier T_NEUTRAL = new Identifier(DungeonsandMinecraft.MOD_ID, "textures/item/neutral_dice.png");
//    private static final Identifier T_NEGATIV = new Identifier(DungeonsandMinecraft.MOD_ID, "textures/item/negative_dice.png");
//
//    //dice specific
//    private int diceNum;
//    private String diceType;
//
//    public CustomPopupScreen(Text title) {
//        super(title);
//        this.openTime = System.currentTimeMillis(); // captures the time when the screen is opened
//        this.rollDelay = System.currentTimeMillis();
//
//        diceNum = NetworkingManager.getDiceNum();
//        //diceType = NetworkingManager.getDiceType();
//    }
//
//
//    @Override
//    protected void init() {
//        super.init();
//        //init the GUI
//        //this.addCloseButton();
//    }
//
////    public void render(DrawContext context, int mouseX, int mouseY, float delta){
////        renderBackground(context);
////    }
//
//    /*
//    protected void addCloseButton() {
//        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button ->
//                this.close()).dimensions(this.width / 2 - 100, 196, 200, 20).build());
//    }
//    */
//
//    public void updateDiceNumber() {
//        this.diceNum = NetworkingManager.getDiceNum(); //modify as needed but this fetches the dicenum as soon as it arrives. calling render from here is not feasable :c
//        //or i think so. idk im dumb    //no. You´re not dumb
//        DungeonsandMinecraft.LOGGER.info("Updated dice num: " + NetworkingManager.getDiceNum());
//    }
//
//
//    public void updateDiceType() {
//        this.diceType = NetworkingManager.getDiceType();
//        DungeonsandMinecraft.LOGGER.info("Updated dice type: " + NetworkingManager.getDiceType());
//    }
//
//
//    @Override
//    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
//        //please add a delay of about 50-100ms here. idk how to do it
//        if (System.currentTimeMillis() - rollDelay >= delay) {
//            updateDiceNumber();
//        }
//        //after the delay fetch the diceNum stored in the method updateDiceNumber()... right above this one
//        //after fetching it you can execute the rendering of your gwui
//        //sadly the packetsending introduces a delay, which i cant fix 3:
//        //if we wont wait for the fetch of the new dicenum it will deisplay the previous rolled dicenum and we dont want this :c
//
//        this.renderBackground(context); // Render the default background
//
////        assert this.client != null; //it told me to assert it
//
//        this.client.getTextureManager().bindTexture(T_POSITIVE);
//        this.client.getTextureManager().bindTexture(T_NEUTRAL);
//        this.client.getTextureManager().bindTexture(T_NEGATIV);
//        int x = (this.width - TEXTURE_WIDTH) + 90;
//        int y = (this.height - TEXTURE_HEIGHT);
////
//
//
//        if (this.client != null) {
//            this.client.getTextureManager();
//        }
//        switch (diceType){
//            case "dice_negative": context.drawTexture(T_NEGATIV, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, 100, 100); break;
//            case "dice_neutral": context.drawTexture(T_NEUTRAL, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, 100, 100); break;
//            case "dice_positive": context.drawTexture(T_POSITIVE, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, 100, 100); break;
//        }
//
//        super.render(context, mouseX, mouseY, delta); // Render buttons and other elements
//
//        if (System.currentTimeMillis() - openTime > DURATION) {
//            this.client.setScreen(null); //when the time is grater than it should, close gwui
//        }
//
//        // Additional rendering code for displaying diceNum can go here
//    }
//
//    @Override
//    public boolean shouldPause() {
//        return false;               // ---> return true to pause
//    }
//}
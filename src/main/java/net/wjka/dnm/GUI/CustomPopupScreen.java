package net.wjka.dnm.GUI;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.NetworkingManager;

@Environment(value = EnvType.CLIENT) // ------> see BookScreen class for insight
public class CustomPopupScreen extends Screen {
    private final long openTime;
    private final long rollDelay;
    private static final long DURATION = 500; // in ms
    private static final long DELAY = 100;
    private static final Identifier TEXTURE_POS = new Identifier("dungeons_and_minecraft", "textures/item/positive_dice.png");
    private static final Identifier TEXTURE_NEU = new Identifier("dungeons_and_minecraft", "textures/item/neutral_dice.png");
    private static final Identifier TEXTURE_NEG = new Identifier("dungeons_and_minecraft", "textures/item/negative_dice.png");
    private static final int TEXTURE_WIDTH = 192;
    private static final int TEXTURE_HEIGHT = 100;


    private final int diceNum;
    private final String diceType;

    public CustomPopupScreen(Text title) {
        super(title);
        this.openTime = System.currentTimeMillis(); // captures the time when the screen is opened
        this.rollDelay = System.currentTimeMillis();

        diceNum = NetworkingManager.getDiceNum();
        diceType = NetworkingManager.getDiceType();
    }


    @Override
    protected void init() {
        super.init();
        //this.addCloseButton();
    }

    /*
    protected void addCloseButton() {
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button ->
                this.close()).dimensions(this.width / 2 - 100, 196, 200, 20).build());
    }
    */

    public void updateDiceNumber() {
        int fetchedDiceNum = NetworkingManager.getDiceNum(); //modify as needed but this fetches the dicenum as soon as it arrives. calling render from here is not feasable :c
        //or i think so. idk im dumb    //no. You´re not dumb
        DungeonsandMinecraft.LOGGER.info("Updated dice num: " + NetworkingManager.getDiceNum());
    }

    public void updateDiceType() {
        String fetchedDiceType = NetworkingManager.getDiceType();
        DungeonsandMinecraft.LOGGER.info("Updated dice type: " + NetworkingManager.getDiceType());
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        //please add a delay of about 50-100ms here. idk how to do it
        //after the delay fetch the diceNum stored in the method updateDiceNumber()... right above this one
        //after fetching it you can execute the rendering of your gwui
        //sadly the packetsending introduces a delay, which i cant fix 3:
        //if we wont wait for the fetch of the new dicenum it will deisplay the previous rolled dicenum and we dont want this :c

        if (System.currentTimeMillis() - rollDelay >= DELAY) {
            updateDiceNumber();
            updateDiceType();
        }

        //this.renderBackground(context); // Render the default background

        assert this.client != null; //it told me to assert it
        this.client.getTextureManager().bindTexture(TEXTURE_POS);
        this.client.getTextureManager().bindTexture(TEXTURE_NEU);
        this.client.getTextureManager().bindTexture(TEXTURE_NEG);
        int x = (this.width - TEXTURE_WIDTH) + 90;
        int y = (this.height - TEXTURE_HEIGHT);

        switch (diceType){
            case "dice_negative": context.drawTexture(TEXTURE_NEG, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, 100, 100); break;
            case "dice_neutral": context.drawTexture(TEXTURE_NEU, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, 100, 100); break;
            case "dice_positive": context.drawTexture(TEXTURE_POS, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, 100, 100); break;
        }

        super.render(context, mouseX, mouseY, delta); // Render buttons and other elements

        if (System.currentTimeMillis() - openTime > DURATION) {
            this.client.setScreen(null); //when thwe twime is gwatew thwan it shwuld, cwose gwui
        }

        int diceNum = NetworkingManager.getDiceNum(); // Fetch the latest diceNum directly in the render method


        // Additional rendering code for displaying diceNum can go here
    }

    @Override
    public boolean shouldPause() {
        return false;               // ---> return true to pause
    }
}
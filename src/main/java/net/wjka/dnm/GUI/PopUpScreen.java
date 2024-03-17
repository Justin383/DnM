package net.wjka.dnm.GUI;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.NetworkingManager;

@Environment(EnvType.CLIENT)
public class PopUpScreen extends Screen {
    public PopUpScreen() {
        // The parameter is the title of the screen,
        // which will be narrated when you enter the screen.
        super(Text.literal("Pwo pwap :3"));
    }


    //public ButtonWidget button1;
    //public ButtonWidget button2;
    //public IconWidget diceImage;
    private static final Identifier T_POSITIVE = new Identifier(DungeonsandMinecraft.MOD_ID, "textures/item/positive_dice.png");
    private static final Identifier T_NEUTRAL = new Identifier(DungeonsandMinecraft.MOD_ID, "textures/item/neutral_dice.png");
    private static final Identifier T_NEGATIVE = new Identifier(DungeonsandMinecraft.MOD_ID, "textures/item/negative_dice.png");
    private final int dWidth = 192;
    private final int dHeight = 100;
    private final int dPosX = 320;
    private final int dPosY = 130;
    private int diceNum;
    private long openTime;
    private static final long DURATION = 1500; //how long the gui should stay open in ms-
    private long rollDelay;
    private String diceType;
    private static final long delay = 100;

    @Override
    protected void init() {
        this.openTime = System.currentTimeMillis(); // captures the time when the screen is opened
        this.rollDelay = System.currentTimeMillis();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        if (System.currentTimeMillis() - rollDelay >= delay) { //wait for a brief moment then render ->
            UpdateDiceInfos();
            context.drawCenteredTextWithShadow(textRenderer, Text.literal("DiceNum: " + diceNum), (width /2) + 155, height / 2, 0xffffff); //draw text with number of OUR dice
            switch (diceType){
                case "dice_negative": context.drawTexture(T_NEGATIVE, dPosX, dPosY, 0, 0.0f, 0.0f, dWidth, dHeight, 100, 100); break;
                case "dice_neutral": context.drawTexture(T_NEUTRAL, dPosX, dPosY, 0, 0.0f, 0.0f, dWidth, dHeight, 100, 100); break;
                case "dice_positive": context.drawTexture(T_POSITIVE, dPosX, dPosY, 0, 0.0f, 0.0f, dWidth, dHeight, 100, 100); break;
            }

        }

        if (System.currentTimeMillis() - openTime > DURATION && this.client != null) {
            this.client.setScreen(null); //when the time is grater than it should, close gwui
        }
    }

    public void UpdateDiceInfos() {
        NetworkingManager nm = new NetworkingManager();
        this.diceNum = nm.getDiceNum();
        this.diceType = nm.getDiceType();
//        DungeonsandMinecraft.LOGGER.info("Updated dice type: " + NetworkingManager.getDiceType());
//        DungeonsandMinecraft.LOGGER.info("Updated dice num: " + diceNum);
//        DungeonsandMinecraft.LOGGER.info("Updated dice type: " + diceType);
    }

    @Override
    public boolean shouldPause() {
        return false;               // ---> return true to pause
    }
}
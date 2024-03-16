package net.wjka.dnm.GUI;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.IconButtonWidget;
import net.minecraft.client.gui.widget.IconWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.NetworkingManager;

import javax.swing.*;
import java.awt.*;

@Environment(EnvType.CLIENT)
public class PopUpScreen extends Screen {
    public PopUpScreen() {
        // The parameter is the title of the screen,
        // which will be narrated when you enter the screen.
        super(Text.literal("My tutorial screen"));
    }


    public ButtonWidget button1;
    public ButtonWidget button2;
    public IconWidget diceImage;
    private static final Identifier T_POSITIVE = new Identifier(DungeonsandMinecraft.MOD_ID, "textures/item/positive_dice.png");
    private final int dWidth = 150;
    private final int dHeight = 150;
    private final int dPosX = 50;
    private final int dPosY = 50;
    private int diceNum;
    private long openTime;
    private long rollDelay;
    private String diceType;
    private static final long delay = 100;

    @Override
    protected void init() {
        this.openTime = System.currentTimeMillis(); // captures the time when the screen is opened
        this.rollDelay = System.currentTimeMillis();

        //diceNum = NetworkingManager.getDiceNum();
//        diceType = NetworkingManager.getDiceType();
        button1 = ButtonWidget.builder(Text.literal("Close"), button -> {
                    DungeonsandMinecraft.LOGGER.info("closed the popup");
                    close();
                })
                .dimensions(width / 2 - 205, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Close the Interface")))
                .build();

        addDrawableChild(button1);
    }


    // For versions 1.20 and after
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        if (System.currentTimeMillis() - rollDelay >= delay) { //wait for a brief moment then render ->
            UpdateDiceInfos();
            context.drawCenteredTextWithShadow(textRenderer, Text.literal("DiceNum: " + diceNum), width / 2, height / 2, 0xffffff);
            context.drawCenteredTextWithShadow(textRenderer, Text.literal("DiceType: " + diceType), width / 2, height / 2 - 20, 0xffffff);
            context.drawTexture(T_POSITIVE, dPosX, dPosY, 0, 0.0f, 0.0f, dWidth, dHeight, dWidth, dHeight);
        }
    }

    public void UpdateDiceInfos() {
        NetworkingManager nm = new NetworkingManager();
        this.diceNum = nm.getDiceNum();
        this.diceType = nm.getDiceType();

        //this.diceType = NetworkingManager.getDiceType();
        //DungeonsandMinecraft.LOGGER.info("Updated dice type: " + NetworkingManager.getDiceType());
        DungeonsandMinecraft.LOGGER.info("Updated dice num: " + diceNum);
        DungeonsandMinecraft.LOGGER.info("Updated dice type: " + diceType);
    }

}
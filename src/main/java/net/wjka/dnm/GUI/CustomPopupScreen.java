package net.wjka.dnm.GUI;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.NetworkingManager;

@Environment(value = EnvType.CLIENT) // ------> siehe BookScreen class
public class CustomPopupScreen extends Screen {
    private static final int TEXTURE_WIDTH = 192;
    private static final int TEXTURE_HEIGHT = 192;

    private int diceNum;

    public CustomPopupScreen(Text title) {
        super(title);
        diceNum = NetworkingManager.getDiceNum();
    }

    /*
    @Override
    protected void init() {
        super.init();
        int x = (this.width - TEXTURE_WIDTH) / 2;
        int y = (this.height - TEXTURE_HEIGHT) / 2;

        // Add buttons or other interactive elements here
        // Example: Add a close button
        this.addDrawableChild(new ButtonWidget(x + 75, y + 140, 40, 20, Text.literal("Close"), button -> {
            if (this.client != null) {
                this.client.setScreen(null); // Close the screen
            }
        }));
    }
    */

    @Override
    protected void init() {
        this.addCloseButton();
    }

    protected void addCloseButton() {
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button ->
                this.close()).dimensions(this.width / 2 - 100, 196, 200, 20).build());
    }

    public void updateDiceNumber() {
        int fetchedDiceNum = NetworkingManager.getDiceNum(); //modify as needed but this fetches the dicenum as soon as it arrives. calling render from here is not feasable :c
        //or i think so. idk im dumb
        DungeonsandMinecraft.LOGGER.info("Updated dice num: " + NetworkingManager.getDiceNum());
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        //please add a delay of about 50-100ms here. idk how to do it
        //after the delay fetch the diceNum stored in the method updateDiceNumber()... right above this one
        //after fetching it you can execute the rendering of your gwui
        //sadly the packetsending introduces a delay, which i cant fix 3:
        //if we wont wait for the fetch of the new dicenum it will deisplay the previous rolled dicenum and we dont want this :c
        this.renderBackground(context); // Render the default background
        super.render(context, mouseX, mouseY, delta); // Render buttons and other elements

        int diceNum = NetworkingManager.getDiceNum(); // Fetch the latest diceNum directly in the render method


        // Additional rendering code for displaying diceNum can go here
    }
}
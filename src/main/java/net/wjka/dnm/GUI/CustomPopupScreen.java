package net.wjka.dnm.GUI;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

@Environment(value = EnvType.CLIENT) // ------> siehe BookScreen class
public class CustomPopupScreen extends Screen {
    private static final int TEXTURE_WIDTH = 192;
    private static final int TEXTURE_HEIGHT = 192;

    public CustomPopupScreen(Text title) {
        super(title);
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

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context); // Render the default background

        // Draw the custom texture
        /*
        this.client.getTextureManager().bindTexture(TEXTURE);
        int x = (this.width - TEXTURE_WIDTH) / 2;
        int y = (this.height - TEXTURE_HEIGHT) / 2;
        drawTexture(context, x, y, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        */

        super.render(context, mouseX, mouseY, delta); // Render buttons and other elements
    }
}
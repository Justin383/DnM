package net.wjka.dnm.GUI;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.NetworkingManager;

public class DiceToast implements Toast {
    private static long startTime;
    private final double endTime = 2000.0;
    private static int diceNum;
    private static String diceType;

    private static final Identifier TEXTURE = new Identifier("textures/gui/toasts.png");
    private static final Identifier T_POSITIVE = new Identifier(DungeonsandMinecraft.MOD_ID, "textures/item/positive_dice.png");
    private static final Identifier T_NEUTRAL = new Identifier(DungeonsandMinecraft.MOD_ID, "textures/item/neutral_dice.png");
    private static final Identifier T_NEGATIVE = new Identifier(DungeonsandMinecraft.MOD_ID, "textures/item/negative_dice.png");
    public static boolean isExistent = false;

    @Override
    public Toast.Visibility draw(DrawContext context, ToastManager manager, long startTime) {
            UpdateDiceInfos();
            this.startTime = startTime;
            context.drawTexture(TEXTURE, 0, 0, 0, 32, this.getWidth(), this.getHeight());
            MutableText text = Text.literal("DiceNum: " + diceNum);
        switch (diceType) {
            case "dice_negative": text.setStyle(text.getStyle().withBold(true).withColor(Formatting.DARK_RED)); break;
            case "dice_neutral": text.setStyle(text.getStyle().withBold(true).withColor(Formatting.DARK_BLUE)); break;
            case "dice_positive": text.setStyle(text.getStyle().withBold(true).withColor(Formatting.DARK_GREEN)); break;
        }
            context.drawText(manager.getClient().textRenderer, text, 30, 12, -16777216, false);
        //dice PNG pos values:
        int dWidth = 20;
        int dHeight = 20;
        int dPosX = 5;
        int dPosY = 6;
        switch (diceType) {
                case "dice_negative": context.drawTexture(T_NEGATIVE, dPosX, dPosY, 0, 0.0f, 0.0f, dWidth, dHeight, dWidth, dHeight); break;
                case "dice_neutral": context.drawTexture(T_NEUTRAL, dPosX, dPosY, 0, 0.0f, 0.0f, dWidth, dHeight, dWidth, dHeight); break;
                case "dice_positive": context.drawTexture(T_POSITIVE, dPosX, dPosY, 0, 0.0f, 0.0f, dWidth, dHeight, dWidth, dHeight); break;
            }
//            DungeonsandMinecraft.LOGGER.info("time: " + startTime);


        if(startTime < endTime){
            isExistent = true;
            return Toast.Visibility.SHOW;
        } else {
            isExistent = false;
            return Toast.Visibility.HIDE;
        }
    }


    public void UpdateDiceInfos() {
        NetworkingManager nm = new NetworkingManager();
        diceNum = nm.getDiceNum();
        diceType = nm.getDiceType();

//        DungeonsandMinecraft.LOGGER.info("Updated dice num: " + diceNum);
//        DungeonsandMinecraft.LOGGER.info("Updated dice type: " + diceType);
    }

}

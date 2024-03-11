package net.wjka.dnm;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.wjka.dnm.GUI.CustomPopupScreen;


public class NetworkingManager{
    private static int diceNum;
    public static final Identifier DICE_NUM_PACKET_ID = new Identifier(DungeonsandMinecraft.MOD_ID, "highlight_block");
    public static void sendDiceNumPacket(ServerPlayerEntity player, int diceNum) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(diceNum); //stores the value in the buffer

        ServerPlayNetworking.send(player, DICE_NUM_PACKET_ID, buf); //sends the packet(buffer)
    }

    public static void registerPacketHandlers() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            // This code only runs on the client, safe from ClassNotFoundException on a server
            ClientPlayNetworking.registerGlobalReceiver(DICE_NUM_PACKET_ID, (client, handler, buf, responseSender) -> {
                int packetNum = buf.readInt();
                client.execute(() -> {
                    diceNum = packetNum; // Update the static dice number

                    // Check if the current screen is CustomPopupScreen and update it
                    if (client.currentScreen instanceof CustomPopupScreen) {
                        ((CustomPopupScreen) client.currentScreen).updateDiceNumber();
                    }
                });
            });
        }
    }

    public static int getDiceNum(){
        return diceNum;
    }
}
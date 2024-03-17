package net.wjka.dnm;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.wjka.dnm.GUI.DiceToast;
import net.wjka.dnm.GUI.PopUpScreen;


public class NetworkingManager{

    /*
    The NetworkingManager is a class containing all important code which is used to send data between the client and server in our mod
    Minecraft is Built with a Server and Client Architecture, which means it splits (simplified) its tasks between a server running in the background [the world]
    and the client running as everything visible and the player. Those two run on independent threads [Server thread], [Render thread]
    meaning if one is overloaded and needs more time to calculate thus being unresponsive, the other one can still run fine.
    \\NOTE, if one thread is unresponsive for too long, they close the connection between them.
    This brings many benefits, for example if the server is overloaded, for example a large ammount of data needs to be handled.
    -> can be visualized by changing the int radius in the ModifyTerrain class to a ridiciolous large number (150) and seeing how the world is frozen and no entity or updates in
    the world are made while the server calculates this task in the BG, but the player is still able to move freely
    [may be teleported back once the server is responsive again due to discrepancy in position data].

    We use the NetworkingManager to send Data between client side applications and server side applications, since our gui can only run on client-side and the rest of the code runs on the server-side
     */
    private static int diceNum;
    private static String diceType;

    public static final Identifier DICE_NUM_PACKET_ID = new Identifier(DungeonsandMinecraft.MOD_ID, "highlight_num");
    public static final Identifier DICE_TYPE_PACKET_ID = new Identifier(DungeonsandMinecraft.MOD_ID, "highlight_type");
    public static void sendDiceNumPacket(ServerPlayerEntity player, int diceNum) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(diceNum); //stores the value in the buffer

        ServerPlayNetworking.send(player, DICE_NUM_PACKET_ID, buf); //sends the packet(buffer)
    }

    public static void sendDiceTypePacket(ServerPlayerEntity player, String diceType) {
//        DungeonsandMinecraft.LOGGER.info("DICETYPE at beginning of NM: " + diceType); //PASS
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(diceType); //stores the value in the buffer

        ServerPlayNetworking.send(player, DICE_TYPE_PACKET_ID, buf); //sends the packet(buffer)
    }

    public static void registerNumPacketHandlers(){
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            // This code only runs on the client, safe from ClassNotFoundException on a server
            ClientPlayNetworking.registerGlobalReceiver(DICE_NUM_PACKET_ID, (client, handler, buf, responseSender) -> {
                int packetNum = buf.readInt();

                client.execute(() -> {
                    diceNum = packetNum; // Update the static dice number

                    // Check if the current screen is CustomPopupScreen and update it
                    if (client.currentScreen instanceof PopUpScreen) {
                        ((PopUpScreen) client.currentScreen).UpdateDiceInfos();
                        DiceToast dT = new DiceToast();
                        dT.UpdateDiceInfos();
                    }
//                    DungeonsandMinecraft.LOGGER.info("NM diceNum: " + diceNum);
                });
            });
        }
    }

    public static void registerTypePacketHandlers() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            // This code only runs on the client, safe from ClassNotFoundException on a server
            ClientPlayNetworking.registerGlobalReceiver(DICE_TYPE_PACKET_ID, (client, handler, buf, responseSender) -> {
                String packetType = buf.readString();

                client.execute(() -> {
                    diceType = packetType; // Update the static dice number

                    // Check if the current screen is CustomPopupScreen and update it
                    if (client.currentScreen instanceof PopUpScreen) {
                        ((PopUpScreen) client.currentScreen).UpdateDiceInfos();
                        DiceToast dT = new DiceToast();
                        dT.UpdateDiceInfos();
                    }
//                    DungeonsandMinecraft.LOGGER.info("NM diceType: " + diceType);
                });
            });
        }
    }



    public int getDiceNum(){
        return diceNum;
    }
    public String getDiceType(){
        return diceType;
    }
}
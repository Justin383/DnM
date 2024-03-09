package net.wjka.dnm;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.wjka.dnm.EventGen.DiceEventGen;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandClass {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("Cheatsheet") //create command with the command name
                .then(argument("randomNum", IntegerArgumentType.integer()) //first arg
                        .then(argument("diceNum", IntegerArgumentType.integer()) //second arg
                                .then(argument("type", StringArgumentType.word()) // third arg
                                        .executes(context -> { //execute our code
                                            ServerCommandSource source = context.getSource(); //get src from cmd
                                            ServerWorld world = source.getWorld(); //get serverworld from src
                                            if (!(source.getEntity() instanceof PlayerEntity)) { //check if code is being run by a player
                                                source.sendError(Text.literal("This command can only be executed by a Player!")); //throw error
                                                return 0; //cancel this command if it is not from a player
                                            }
                                            PlayerEntity player = (PlayerEntity) source.getEntity(); //get player from src
                                            int randomNum = IntegerArgumentType.getInteger(context, "randomNum"); //store first value
                                            int diceNum = IntegerArgumentType.getInteger(context, "diceNum"); //store second value
                                            String type = StringArgumentType.getString(context, "type"); //store third value
                                            DiceEventGen de = new DiceEventGen();

                                            if ("negative".equals(type)) {
                                                de.NegativeDiceEvent(world, player, randomNum, diceNum); //calls negativeDiceEvent From DecideEventGen, bypassing all random number generators
                                            } else if ("neutral".equals(type)) {
                                                //code
                                            } else if ("positive".equals(type)) {
                                                //code
                                            } else {
                                                //idk
                                                source.sendError(Text.literal("Invalid type specified."));
                                                return 0;
                                            }

                                            context.getSource().sendFeedback(() -> Text.literal("Received diceNum: " + diceNum + " and randomNum: " + randomNum + " with type: " + type), false);


                                            return 1;
                                        })))));
    }
}
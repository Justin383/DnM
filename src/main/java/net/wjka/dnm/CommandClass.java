package net.wjka.dnm;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.wjka.dnm.EventGen.DiceEventGen;
import net.wjka.dnm.EventGen.NegativeEffects;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandClass {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("Cheatsheet") //create command with the command name
                .then(argument("randomNum", IntegerArgumentType.integer()) //first arg
                        .then(argument("diceNum", IntegerArgumentType.integer()) //second arg
                                .then(argument("type", StringArgumentType.word())// third arg
                                        .suggests((context, builder) -> {
                                            // adds autocomplete and suggestions to type
                                            Stream.of("negative", "neutral", "positive").forEach(builder::suggest); //builder is the imported class we need to use the built in autocomplete and suggestions!
                                            return builder.buildFuture();
                                        })
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
                                            DiceEventGen de = new DiceEventGen(world, player, type, diceNum);
                                            if(diceNum >= 0 && diceNum <= 20 && randomNum >= 0 && randomNum <= 20) { //check if numbers are in range
                                                if ("negative".equals(type)) {
                                                    de.NegativeDiceEvent(randomNum); //calls negativeDiceEvent From DecideEventGen, bypassing all random number generators
                                                } else if ("neutral".equals(type)) {
                                                    de.NeutralDiceEvent(randomNum);
                                                } else if ("positive".equals(type)) {
                                                    de.PositiveDiceEvent(randomNum);
                                                } //DEBUG ONLY
                                                  else if("time".equals(type)){
                                                    NegativeEffects ne = new NegativeEffects(diceNum, world, player);
                                                    ne.ChangeTime();
                                                } else {
                                                    //throw error of type
                                                    source.sendError(Text.literal("Type must be one of the following: [negative] [neutral] [positive]"));
                                                    return 0;
                                                }
                                                context.getSource().sendFeedback(() -> Text.literal("§l§9RandomNum: §l§f" + diceNum + "§l§8 | §l§4DiceNum: §l§f" + randomNum + "§l§8 | §l§6Type: §l§f" + type), false);

                                            }


                                            else {
                                                    source.sendError(Text.literal("diceNum and randNum must be in range: 0-20!"));
                                            }
                                            return 1;
                                        })))));
    }
}
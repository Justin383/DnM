package net.wjka.dnm;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.wjka.dnm.EventGen.DiceEventGen;
import net.wjka.dnm.EventGen.Effects.NegativeEffects;
import net.wjka.dnm.EventGen.Effects.NeutralEffects;
import net.wjka.dnm.item.Dice.NeutralDice;

import java.awt.*;
import java.util.stream.Stream;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandClass {
    public static void registerCheatSheet(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("cheatsheet") //create command with the command name
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
                                            DiceEventGen de = new DiceEventGen(world, player, type, diceNum); //create obj of Diceeventgen
                                            if(diceNum >= 0 && diceNum <= 50 && randomNum >= 0 && randomNum <= 20) { //check if numbers are in range
                                                if ("negative".equals(type)) {
                                                    de.NegativeDiceEvent(randomNum); //calls negativeDiceEvent From DecideEventGen, bypassing all random number generators
                                                } else if ("neutral".equals(type)) {
                                                    de.NeutralDiceEvent(randomNum); // same but neutral
                                                } else if ("positive".equals(type)) {
                                                    de.PositiveDiceEvent(randomNum); //same but positive
                                                } //DEBUG ONLY
                                                  else if("time".equals(type)){ //TEMPORARY
                                                    NegativeEffects ne = new NegativeEffects(diceNum, world, player);
                                                    ne.ChangeTime();
                                                } else if("w".equals(type)){
                                                    NeutralEffects neutral = new NeutralEffects(diceNum, world, player);
                                                    neutral.ChangeWeather();
                                                } else {
                                                    //throw error of type
                                                    source.sendError(Text.literal("Type must be one of the following: [negative] [neutral] [positive]")); //throwws error if dicetype isnt correct
                                                    return 0;
                                                }
                                                context.getSource().sendFeedback(() -> Text.literal("§l§9RandomNum: §l§f" + diceNum + "§l§8 | §l§4DiceNum: §l§f" + randomNum + "§l§8 | §l§6Type: §l§f" + type), false);
                                                  //gives feedback on what was chosen
                                            }
                                            else {
                                                    source.sendError(Text.literal("diceNum and randNum must be in range: 0-20!"));
                                            }
                                            return 1;
                                        })))));
    }

    public static void registerGUIToggle(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("dicegui") //create command with the command name
                .then(argument("toggle", StringArgumentType.word())// third arg
                        .suggests((context, builder) -> {
                            // adds autocomplete and suggestions to type
                            Stream.of("small", "large").forEach(builder::suggest); //builder is the imported class we need to use the built in autocomplete and suggestions!
                            return builder.buildFuture();
                        })
                            .executes(context -> { //execute our code
                                ServerCommandSource source = context.getSource();
                                PlayerEntity player = source.getPlayer();
                                if (!(source.getEntity() instanceof PlayerEntity)) { //check if code is being run by a player
                                    source.sendError(Text.literal("This command can only be executed by a Player!")); //throw error
                                    return 0; //cancel this command if it is not from a player
                                }
                                String toggle = StringArgumentType.getString(context, "toggle"); //store the cmd val
                                PlayerActions playerActions = new PlayerActions(player);
                                if("small".equals(toggle)){
                                    playerActions.changeGuiType(true);
                                    context.getSource().sendFeedback(() -> Text.literal("The GUI will now be displayed as the" + toggle + "variant"), false);
                                } else if ("large".equals(toggle)){
                                    playerActions.changeGuiType(false);
                                    context.getSource().sendFeedback(() -> Text.literal("The GUI will now be displayed as the" + toggle + "variant"), false);
                                } else {
                                    source.sendError(Text.literal("only small or large are allowed types!"));
                                }
                                            return 1; //PASS
                            })));
    }
}


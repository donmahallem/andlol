/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public enum Queue {
    /**
     * Custom games
     */
    CUSTOM,
    /**
     * Normal 5v5 Blind Pick games
     */
    NORMAL_5x5_BLIND,
    /**
     * Historical Summoner's Rift Coop vs AI games
     */
    BOT_5x5,
    /**
     * Summoner's Rift Coop vs AI Intro Bot games
     */
    BOT_5x5_INTRO,
    /**
     * Summoner's Rift Coop vs AI Beginner Bot games
     */
    BOT_5x5_BEGINNER,
    /**
     * Historical Summoner's Rift Coop vs AI Intermediate Bot games
     */
    BOT_5x5_INTERMEDIATE,
    /**
     * Normal 3v3 games
     */
    NORMAL_3x3,
    /**
     * Normal 5v5 Draft Pick games
     */
    NORMAL_5x5_DRAFT,
    /**
     * Dominion 5v5 Blind Pick games
     */
    ODIN_5x5_BLIND,
    /**
     * Dominion 5v5 Draft Pick games
     */
    ODIN_5x5_DRAFT,
    /**
     * Dominion Coop vs AI games
     */
    BOT_ODIN_5x5,
    /**
     * Ranked Solo 5v5 games
     */
    RANKED_SOLO_5x5,
    /**
     * Ranked Premade 3v3 games
     */
    RANKED_PREMADE_3x3,
    /**
     * Ranked Premade 5v5 games
     */
    RANKED_PREMADE_5x5,
    /**
     * Ranked Team 3v3 games
     */
    RANKED_TEAM_3x3,
    /**
     * Ranked Team 5v5 games
     */
    RANKED_TEAM_5x5,
    /**
     * Twisted Treeline Coop vs AI games
     */
    BOT_TT_3x3,
    /**
     * Team Builder games
     */
    GROUP_FINDER_5x5,
    /**
     * ARAM games
     */
    ARAM_5x5,
    /**
     * One for All games
     */
    ONEFORALL_5x5,
    /**
     * Snowdown Showdown 1v1 games
     */
    FIRSTBLOOD_1x1,
    /**
     * Snowdown Showdown 2v2 games
     */
    FIRSTBLOOD_2x2,
    /**
     * Hexakill games
     */
    SR_6x6,
    /**
     * Ultra Rapid Fire games
     */
    URF_5x5,
    /**
     * Ultra Rapid Fire games played against AI games
     */
    BOT_URF_5x5,
    /**
     * Doom Bots Rank 1 games
     */
    NIGHTMARE_BOT_5x5_RANK1,
    /**
     * Doom Bots Rank 2 games
     */
    NIGHTMARE_BOT_5x5_RANK2,
    /**
     * Doom Bots Rank 5 games
     */
    NIGHTMARE_BOT_5x5_RANK5,
    /**
     * Ascension games
     */
    ASCENSION_5x5,
    /*
    * KING PORO
     */
    KING_PORO;

    /**
     * TypeAdapter required for GSON
     */
    public static class TypeAdapter implements JsonDeserializer<Queue>, JsonSerializer<Queue> {

        @Override
        public Queue deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Queue.valueOf(json.getAsJsonPrimitive().getAsString());
        }

        @Override
        public JsonElement serialize(Queue src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.name());
        }
    }
}

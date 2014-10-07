/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

public enum GameSubType {
    /**
     * Custom Game
     */
    NONE,
    /**
     * Summoners Rift unranked Game
     */
    NORMAL,
    /**
     * Twisted Treeline unranked Game
     */
    NORMAL_3x3,
    /**
     * Dominon/Crystal Scar Games
     */
    ODIN_UNRANKED,
    /**
     * ARAM/Howling Abyss Game
     */
    ARAM_UNRANKED_5x5,
    /**
     * Summoner's Rift and Crystal Scar games played against Intro, Beginner, or Intermediate AI
     */
    BOT,
    /**
     * Twisted Treeline games played against AI
     */
    BOT_3x3,
    /**
     * Summoner's Rift ranked solo queue games
     */
    RANKED_SOLO_5x5,
    /**
     * Twisted Treeline ranked team games
     */
    RANKED_TEAM_3x3,
    /**
     * Summoner's Rift ranked team games
     */
    RANKED_TEAM_5x5,
    /**
     * One for All games
     */
    ONEFORALL_5x5,
    /**
     * Snowdown Showdown 1x1 games
     */
    FIRSTBLOOD_1x1,
    /**
     * Snowdown Showdown 2x2 games
     */
    FIRSTBLOOD_2x2,
    /**
     * Hexakill games
     */
    SR_6x6,
    /**
     * Team Builder games
     */
    CAP_5x5,
    /**
     * Ultra Rapid Fire games
     */
    URF,
    /**
     * Ultra Rapid Fire games played against AI
     */
    URF_BOT,
    /**
     * Summoner's Rift games played against Nightmare AI
     */
    NIGHTMARE_BOT,
    /**
     * Ascension
     */
    ASCENSION;
}

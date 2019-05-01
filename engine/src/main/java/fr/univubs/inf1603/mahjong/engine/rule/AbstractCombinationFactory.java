package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTileInterface;

public interface AbstractCombinationFactory {

    /**
     * @param tiles Tiles with which we want to make a combination
     * @return The correct Combination made with the given tiles
     * @throws fr.univubs.inf1603.mahjong.engine.rule.RulesException the tiles cannot be made into a valid combination
     */
    Combination newCombination(GameTileInterface... tiles) throws RulesException;
}

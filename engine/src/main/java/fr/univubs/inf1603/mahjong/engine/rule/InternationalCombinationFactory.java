package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import fr.univubs.inf1603.mahjong.engine.game.GameTileInterface;

import java.util.Arrays;

/**
 * 
 */
public class InternationalCombinationFactory implements AbstractCombinationFactory {

    @Override
    public Combination newCombination(GameTileInterface... tiles) throws RulesException{
        if (Arrays.stream(tiles).anyMatch(gameTileInterface -> !(gameTileInterface instanceof GameTile)))
            throw new RulesException("GameTileInterfaces not instances of GameTile");
        else {
            GameTile[] gameTiles =  Arrays.copyOf(tiles,tiles.length,GameTile[].class);
            for (int i = 0; i < gameTiles.length; i++)
                for (int j = 0; j < tiles.length; j++)
                    if (i != j && tiles[i].getGameID() == tiles[j].getGameID())
                        throw new RulesException("Same gameID found on the tiles");

            try {
                return new Pung(gameTiles);
            } catch (RulesException e) {
                //This combination is not a Pung
            }
            try {
                return new Kong(gameTiles);
            } catch (RulesException e) {
                //This combination is not a Kong
            }
            try {
                return new Chow(gameTiles);
            } catch (RulesException e) {
                //This combination is not a Chow
            }
            try {
                return new Pair(gameTiles);
            } catch (RulesException e) {
                //This combination is not a Pair
            }
            throw new RulesException("This is not a valid combination");
        }
    }
}

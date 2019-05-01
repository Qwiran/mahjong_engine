package fr.univubs.inf1603.mahjong.engine.rule;
import fr.univubs.inf1603.mahjong.Wind;

import fr.univubs.inf1603.mahjong.engine.game.GameException;
import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import fr.univubs.inf1603.mahjong.engine.game.GameTileInterface;
import fr.univubs.inf1603.mahjong.engine.game.MahjongBoard;
import fr.univubs.inf1603.mahjong.engine.game.Move;
import fr.univubs.inf1603.mahjong.engine.game.MoveException;
import fr.univubs.inf1603.mahjong.engine.game.TileZoneIdentifier;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class SillyBoardRule implements BoardRule {

    @Override
    public Wind[] getPlayerOrder() {
        return Wind.values();
    }

    @Override
    public StartingWall buildWall() {
        return new StartingWall(Wind.EAST, 0, new ArrayDeque<>());
    }

    @Override
    public MahjongBoard distributeTiles(StartingWall startingWall) {
        MahjongBoard board = new MahjongBoard(Wind.WEST);
        ArrayList<GameTileInterface> allTile = new ArrayList();
        int gameIndex = 0;
        for (InternationalTiles a : InternationalTiles.values()) {
            if (a.getTile() instanceof CommonTile) {
                for(int i = 0;i<6;i++,gameIndex++){ // yes silly rules have 6 of every common tile
                    allTile.add(new GameTile(gameIndex, a.getTile()));
                }
            }            
        }
        try {
            board.getTileZone(TileZoneIdentifier.Wall).getTiles().addAll(allTile);
        } catch (GameException ex) {
            Logger.getLogger(SillyBoardRule.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0;i<4;i++){//This is the draw moves they draw 13 tiles for each player in one swipe
            HashMap<Integer,TileZoneIdentifier> path = new HashMap<>();
            for(int j = 0;j<13;j++){
                try {
                    path.put(i*j, TileZoneIdentifier.getIdentifierFromNormalizedName("hand"+Wind.values()[i].getName()));
                    Move drawMove=null;
                    drawMove = new Move(Wind.values()[i], 0, path, new HashMap<>());
                    board.applyMove(drawMove);
                } catch (GameException ex) {
                    Logger.getLogger(SillyBoardRule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return board;
    }

    @Override
    public boolean isMoveValid(MahjongBoard board, Move lastMove, Move move) {
        return findValidMoves(board, lastMove).get(move.getWind()).contains(move);
    }

    @Override
    public EnumMap<Wind, Collection<Move>> findValidMoves(MahjongBoard board, Move lastMove) {
        EnumMap<Wind, Collection<Move>> moves = new EnumMap<>(Wind.class);

        Wind nextWindToPlay = Wind.values()[(lastMove.getWind().ordinal() + 1) % Wind.values().length];
        moves.put(nextWindToPlay, new ArrayList<>());
        HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
        try {
            path.put(board.getTileZone("hand" + nextWindToPlay.getName()).getTiles().get(0).getGameID(), //Spaghetti code
                    board.getTileZone("discard" + nextWindToPlay.getName()).getIdentifier());            //Spaghetti code
        } catch (GameException ex) {
            Logger.getLogger(SillyBoardRule.class.getName()).log(Level.SEVERE, null, ex);
        }
        Move discard = new Move(nextWindToPlay, 0, path,new HashMap<>());
        moves.get(nextWindToPlay).add(discard);
        return moves;
    }

    @Override
    public boolean isGameFinished(MahjongBoard board, Move lastMove) {
        return false;
    }

}

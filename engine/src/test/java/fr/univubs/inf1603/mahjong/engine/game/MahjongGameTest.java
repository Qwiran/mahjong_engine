/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;
import fr.univubs.inf1603.mahjong.Wind;

import fr.univubs.inf1603.mahjong.engine.rule.GameRule;
import fr.univubs.inf1603.mahjong.engine.rule.GameRuleFactory;
import fr.univubs.inf1603.mahjong.engine.rule.RulesException;
import fr.univubs.inf1603.mahjong.Wind;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class MahjongGameTest {
    
    public MahjongGameTest() {
    }

    

    /**
     * Test of getBoard method, of class MahjongGame.
     */
    @Test
    public void testGetBoard_0args() {
        
        try {
            System.out.println("getBoard");
            GameRule rule = new GameRuleFactory().create("INTERNATIONAL");
            MahjongBoard expResult = new MahjongBoard(Wind.WEST);
            MahjongGame instance = new MahjongGame(rule, expResult, (Move)null, Duration.ofSeconds(5), Duration.ofSeconds(5), new int[4], UUID.randomUUID(), Wind.values());
            Board result = instance.getBoard();
            assertEquals(expResult, result);
        }   
        catch (GameException | RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }

    /**
     * Test of getPossibleMoves method, of class MahjongGame.
     */
    @Test
    public void testGetPossibleMoves_0args() {
        try {
            System.out.println("getPossibleMoves");
            GameRule rule =  new GameRuleFactory().create("SILLY");
            MahjongBoard board = new MahjongBoard(Wind.WEST);
            HashMap path = new HashMap<>();
            path.put(0, TileZoneIdentifier.Wall);
            HashMap publiclyVisible = new HashMap<>();
            publiclyVisible.put(0, true);
            Move move = new Move(Wind.WEST, 0, path, publiclyVisible);
            MahjongGame instance = new MahjongGame(rule, board, move, Duration.ofSeconds(5), Duration.ofSeconds(5), new int[4], UUID.randomUUID(), Wind.values());
            instance.launchGame();
            ArrayList<Move> result = instance.getPossibleMoves();
            assertNotNull(result);
        } catch (GameException | RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }  


    /**
     * Test of getStealingTime method, of class MahjongGame.
     */
    @Test
    public void testGetStealingTime()  {
        try {
            System.out.println("getStealingTime");
            GameRule rule = new GameRuleFactory().create("INTERNATIONAL");
            Duration expResult = Duration.ofDays(5);
            MahjongGame instance = new MahjongGame(rule, expResult, Duration.ZERO);
            Duration result = instance.getStealingTime();
            assertEquals(expResult, result);
        } catch (RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }

    /**
     * Test of getPlayingTime method, of class MahjongGame.
     */
    @Test
    public void testGetPlayingTime() {
        try {
            System.out.println("getPlayingTime");
            GameRule rule = new GameRuleFactory().create("INTERNATIONAL");
            Duration expResult = Duration.ofDays(5);
            MahjongGame instance = new MahjongGame(rule, Duration.ZERO, expResult);
            Duration result = instance.getPlayingTime();
            assertEquals(expResult, result);
        } catch (RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }

    /**
     * Test of getLastPlayedMove method, of class MahjongGame.
     */
    @Test
    public void testGetLastPlayedMove(){
        try {
            System.out.println("getLastPlayedMove");
            GameRule rule =  new GameRuleFactory().create("INTERNATIONAL");
            MahjongBoard board = new MahjongBoard(Wind.WEST);
            HashMap path = new HashMap<>();
            path.put(0, TileZoneIdentifier.Wall);
            HashMap publiclyVisible = new HashMap<>();
            publiclyVisible.put(0, true);
            Move expResult = new Move(Wind.WEST, 0, path, publiclyVisible);
            MahjongGame instance = new MahjongGame(rule, board, expResult, Duration.ofSeconds(5), Duration.ofSeconds(5), new int[4], UUID.randomUUID(), Wind.values());
            
            Move result = instance.getLastPlayedMove();
            assertEquals(expResult, result);
        } catch (GameException | RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }

    /**
     * Test of getUUID method, of class MahjongGame.
     */
    @Test
    public void testGetUUID() {
        try {
            System.out.println("getUUID");
            GameRule rule = new GameRuleFactory().create("INTERNATIONAL");
            UUID expResult = UUID.randomUUID();
            MahjongBoard board = new MahjongBoard(Wind.WEST);
            MahjongGame instance = new MahjongGame(rule, board, (Move)null, Duration.ofSeconds(5), Duration.ofSeconds(5), new int[4], expResult, Wind.values());
            UUID result = instance.getUUID();
            assertEquals(expResult, result);
        } catch (GameException | RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }

    
    /**
     * Test of getRule method, of class MahjongGame.
     */
    @Test
    public void testGetRule() {
        try {
            System.out.println("getRule");
            GameRule expResult = new GameRuleFactory().create("INTERNATIONAL");
            MahjongGame instance = new MahjongGame(expResult, Duration.ZERO, Duration.ZERO);
            GameRule result = instance.getRule();
            assertEquals(expResult, result);
        } catch (RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }

    /**
     * Test of getCurrentwind method, of class MahjongGame.
     */
    @Test
    public void testGetCurrentwind(){
        try {
            System.out.println("getCurrentwind");
            GameRule rule = new GameRuleFactory().create("INTERNATIONAL");
            MahjongBoard board = new MahjongBoard(Wind.WEST);
            MahjongGame instance = new MahjongGame(rule, board, (Move)null, Duration.ofSeconds(5), Duration.ofSeconds(5), new int[4], UUID.randomUUID(), Wind.values());
            Wind expResult = board.getCurrentWind();
            Wind result = instance.getCurrentwind();
            assertEquals(expResult, result);
        } catch (GameException | RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }
    /**
     * Test of getPlayerPoints method, of class MahjongGame.
     */
    @Test
    public void testGetPlayerPoints_int(){
        try {
            System.out.println("getPlayerPoints");
            int player = 1;
            int[] pointsPlayer = new int[4];
            pointsPlayer[0]=10;
            pointsPlayer[1]=20;
            pointsPlayer[2]=30;
            pointsPlayer[3]=40;
            GameRule rule = new GameRuleFactory().create("INTERNATIONAL");
            MahjongBoard board = new MahjongBoard(Wind.WEST);
            MahjongGame instance = new MahjongGame(rule, board, (Move)null, Duration.ofSeconds(5), Duration.ofSeconds(5), pointsPlayer, UUID.randomUUID(), Wind.values());
            int expResult = pointsPlayer[1];
            int result = instance.getPlayerPoints(player);
            assertEquals(expResult, result);
        } catch (GameException | RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }
    /**
     * Test of getPlayerPoints method, of class MahjongGame.
     */
    @Test
    public void testGetPlayerPoints_Wind() {
        try {
            System.out.println("getPlayerPoints");
            Wind wind = Wind.EAST;
            int[] pointsPlayer = new int[4];
            pointsPlayer[0]=10;
            pointsPlayer[1]=20;
            pointsPlayer[2]=30;
            pointsPlayer[3]=40;
            Wind[] windPlayer = new Wind[4];
            windPlayer[0]=Wind.NORTH;
            windPlayer[1]=Wind.EAST;
            windPlayer[2]=Wind.SOUTH;
            windPlayer[3]=Wind.WEST;
            GameRule rule = new GameRuleFactory().create("INTERNATIONAL");
            MahjongBoard board = new MahjongBoard(Wind.WEST);
            MahjongGame instance = new MahjongGame(rule, board, (Move)null, Duration.ofSeconds(5), Duration.ofSeconds(5), pointsPlayer, UUID.randomUUID(), windPlayer);
            int expResult = pointsPlayer[1];
            int result = instance.getPlayerPoints(wind);
            assertEquals(expResult, result);
        } catch (GameException | RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }

    /**
     * Test of getAllPlayerPoints method, of class MahjongGame.
     */
    @Test
    public void testGetAllPlayerPoints() {
        try {
            System.out.println("getAllPlayerPoints");
            int[] expResult = new int[4];
            expResult[0]=10;
            expResult[1]=20;
            expResult[2]=30;
            expResult[3]=40;
            GameRule rule = new GameRuleFactory().create("INTERNATIONAL");
            MahjongBoard board = new MahjongBoard(Wind.WEST);
            MahjongGame instance = new MahjongGame(rule, board, (Move)null, Duration.ofSeconds(5), Duration.ofSeconds(5), expResult, UUID.randomUUID(), Wind.values());
            int[] result = instance.getAllPlayerPoints();
            Assert.assertArrayEquals(expResult, result);
        } catch (GameException | RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }

    /**
     * Test of getPlayerWind method, of class MahjongGame.
     */
    @Test
    public void testGetPlayerWind(){
        try {
            System.out.println("getPlayerWind");
            int player = 1;
            int[] pointsPlayer = new int[4];
            pointsPlayer[0]=10;
            pointsPlayer[1]=20;
            pointsPlayer[2]=30;
            pointsPlayer[3]=40;
            Wind[] windPlayer = new Wind[4];
            windPlayer[0]=Wind.NORTH;
            windPlayer[1]=Wind.EAST;
            windPlayer[2]=Wind.SOUTH;
            windPlayer[3]=Wind.WEST;
            GameRule rule = new GameRuleFactory().create("INTERNATIONAL");
            MahjongBoard board = new MahjongBoard(Wind.WEST);
            MahjongGame instance = new MahjongGame(rule, board, (Move)null, Duration.ofSeconds(5), Duration.ofSeconds(5), pointsPlayer, UUID.randomUUID(), windPlayer);
            Wind expResult = windPlayer[1];
            Wind result = instance.getPlayerWind(player);
            assertEquals(expResult, result);
        } catch (GameException | RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }

    /**
     * Test of getPlayerFromWind method, of class MahjongGame.
     */
    @Test
    public void testGetPlayerFromWind(){
        try {
            System.out.println("getPlayerFromWind");
            Wind wind = Wind.EAST;
            int[] pointsPlayer = new int[4];
            pointsPlayer[0]=10;
            pointsPlayer[1]=20;
            pointsPlayer[2]=30;
            pointsPlayer[3]=40;
            Wind[] windPlayer = new Wind[4];
            windPlayer[0]=Wind.NORTH;
            windPlayer[1]=Wind.EAST;
            windPlayer[2]=Wind.SOUTH;
            windPlayer[3]=Wind.WEST;
            GameRule rule = new GameRuleFactory().create("INTERNATIONAL");
            MahjongBoard board = new MahjongBoard(Wind.WEST);
            MahjongGame instance = new MahjongGame(rule, board, (Move)null, Duration.ofSeconds(5), Duration.ofSeconds(5), pointsPlayer, UUID.randomUUID(), windPlayer);
            int expResult = 1;
            int result = instance.getPlayerFromWind(wind);
            assertEquals(expResult, result);
        } catch (GameException | RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }

    /**
     * Test of getPlayerWinds method, of class MahjongGame.
     */
    @Test
    public void testGetPlayerWinds() {
        try {
            System.out.println("getPlayerWinds");
            int[] pointsPlayer = new int[4];
            pointsPlayer[0]=10;
            pointsPlayer[1]=20;
            pointsPlayer[2]=30;
            pointsPlayer[3]=40;
            Wind[] windPlayer = new Wind[4];
            windPlayer[0]=Wind.NORTH;
            windPlayer[1]=Wind.EAST;
            windPlayer[2]=Wind.SOUTH;
            windPlayer[3]=Wind.WEST;
            GameRule rule = new GameRuleFactory().create("INTERNATIONAL");
            MahjongBoard board = new MahjongBoard(Wind.WEST);
            MahjongGame instance = new MahjongGame(rule, board, (Move)null, Duration.ofSeconds(5), Duration.ofSeconds(5), pointsPlayer, UUID.randomUUID(), windPlayer);
            Wind[] expResult = windPlayer;
            Wind[] result = instance.getPlayerWinds();
            assertArrayEquals(expResult, result);
        } catch (GameException | RulesException ex) {
            fail("MahjongGame threw an exception :"+ex);
        }
    }

 
    
}

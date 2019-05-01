/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.Wind;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class MoveTest{
    /**
     * Test of getSide method, of class Move.
     */
    @Test
    public void testGetSide() {
        System.out.println("getSide");
        Wind expResult = Wind.EAST;
        HashMap path = new HashMap<>();
        path.put(0, TileZoneIdentifier.Wall);
        HashMap publiclyVisible = new HashMap<>();
        publiclyVisible.put(0, true);
        Move instance=null;
        instance = new Move(expResult, 0, path,publiclyVisible);
        Wind result = instance.getWind();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPriority method, of class Move.
     */
    @Test
    public void testGetPriority() {
        System.out.println("getPriority");
        int expResult = 5;
        HashMap path = new HashMap<>();
        path.put(0, TileZoneIdentifier.Wall);
        HashMap publiclyVisible = new HashMap<>();
        publiclyVisible.put(0, true);
        Move instance=null;
        instance = new Move(Wind.EAST, expResult, path,publiclyVisible);
        int result = instance.getPriority();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class Move.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        HashMap<Integer, TileZoneIdentifier> expResult = new HashMap<>();
        expResult.put(0, TileZoneIdentifier.Wall);
        HashMap publiclyVisible = new HashMap<>();
        publiclyVisible.put(0, true);
        Move instance=null;
        instance = new Move(Wind.WEST, 0, expResult,publiclyVisible);
        HashMap<Integer, TileZoneIdentifier> result = instance.getPath();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPubliclyVisible method, of class Move.
     */
    @Test
    public void testGetPubliclyVisible() {
        System.out.println("getPubliclyVisibl");
        HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
        path.put(0, TileZoneIdentifier.Wall);
        HashMap expResult = new HashMap<>();
        expResult.put(0, true);
        Move instance=null;
        instance = new Move(Wind.WEST, 0, path,expResult);
        HashMap<Integer, Boolean> result = instance.getPubliclyVisible();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isEqual method, of class Move (expected result is true).
     */
    @Test
    public void testIsEqualTrue() {
        System.out.println("isEqualsTrue");
        int priority = 5;
        HashMap path = new HashMap<>();
        path.put(0, TileZoneIdentifier.Wall);
        HashMap publiclyVisible = new HashMap<>();
        publiclyVisible.put(0, true);
        Move instance1=null;
        Move instance2=null;
        instance1 = new Move(Wind.EAST, priority, path,publiclyVisible);
        instance2 = new Move(Wind.EAST, priority, path,publiclyVisible);
        assertTrue(instance1.isEqual(instance2));
    }
    
    /**
     * Test of isEqual method, of class Move (expected result is false).
     */
    @Test
    public void testIsEqualFalse() {
        System.out.println("isEqualsFalse");
        int priority = 5;
        HashMap path = new HashMap<>();
        path.put(0, TileZoneIdentifier.Wall);
        HashMap publiclyVisible = new HashMap<>();
        publiclyVisible.put(0, true);
        HashMap publiclyVisible2 = new HashMap<>();
        publiclyVisible2.put(0, false);
        Move instance1=null;
        Move instance2=null;
        instance1 = new Move(Wind.EAST, priority, path,publiclyVisible);
        instance2 = new Move(Wind.EAST, priority, path,publiclyVisible2);
        assertFalse(instance1.isEqual(instance2));
    }
    
}

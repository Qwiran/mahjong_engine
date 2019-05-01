package fr.univubs.inf1603.mahjong.engine.rule;
import fr.univubs.inf1603.mahjong.Wind;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InternationalPatternsTest {

    public InternationalPatternsTest(){}
    
    @Test
    public void mixedTripleChows() {
        System.out.println("mixedTripleChows");
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        
        Collection<Combination> hand = new ArrayList<>();
        GameTile dot1 = new GameTile(0, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(1, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(2, InternationalTiles.DOT_3);
        GameTile car1 = new GameTile(3, InternationalTiles.CHARACTER_1);
        GameTile car2 = new GameTile(4, InternationalTiles.CHARACTER_2);
        GameTile car3 = new GameTile(5, InternationalTiles.CHARACTER_3);
        
        Combination combiDots = null;
        Combination combiChars = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot2, dot3);
            combiChars = newCombiFact.newCombination(car1, car2, car3);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hand.add(combiDots);
        hand.add(combiChars);
        
        Collection<Combination> melds = new ArrayList<>();
        GameTile bam1 = new GameTile(8, InternationalTiles.BAMBOO_1);
        GameTile bam2 = new GameTile(9, InternationalTiles.BAMBOO_2);
        GameTile bam3 = new GameTile(10, InternationalTiles.BAMBOO_3);
        GameTile bam4 = new GameTile(8, InternationalTiles.BAMBOO_4);
        GameTile bam5 = new GameTile(9, InternationalTiles.BAMBOO_5);
        GameTile bam6 = new GameTile(10, InternationalTiles.BAMBOO_6);
        
        try {
            Combination combiBams = newCombiFact.newCombination(bam1, bam2, bam3);
            Combination combiBamsBis = newCombiFact.newCombination(bam4,bam5,bam6);
            melds.add(combiBams);
            melds.add(combiBamsBis);
            
            Collection<Combination> concealed = new ArrayList<>();
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.MIXED_TRIPLE_CHOWS;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("mixedTripleChows pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(9, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.MIXED_TRIPLE_CHOWS);
                assertEquals(iP.getPattern().getValue(), 8);
            }
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void mixedStraight() {
        System.out.println("mixedStraight");
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        
        Collection<Combination> hand = new ArrayList<>();
        GameTile dot1 = new GameTile(0, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(1, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(2, InternationalTiles.DOT_3);
        GameTile car4 = new GameTile(3, InternationalTiles.CHARACTER_4);
        GameTile car5 = new GameTile(4, InternationalTiles.CHARACTER_5);
        GameTile car6 = new GameTile(5, InternationalTiles.CHARACTER_6);
        
        Combination combiDots = null;
        Combination combiChars = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot2, dot3);
            combiChars = newCombiFact.newCombination(car4, car5, car6);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hand.add(combiDots);
        hand.add(combiChars);
        
        Collection<Combination> melds = new ArrayList<>();
        GameTile bam7 = new GameTile(8, InternationalTiles.BAMBOO_7);
        GameTile bam8 = new GameTile(9, InternationalTiles.BAMBOO_8);
        GameTile bam9 = new GameTile(10, InternationalTiles.BAMBOO_9);
        GameTile bam1 = new GameTile(11, InternationalTiles.BAMBOO_1);
        GameTile bam1b = new GameTile(12, InternationalTiles.BAMBOO_1);
        GameTile bam1t = new GameTile(13, InternationalTiles.BAMBOO_1);
        
        try {
            Combination combiBams = newCombiFact.newCombination(bam7, bam8, bam9);
            Combination combiBamsBis = newCombiFact.newCombination(bam1, bam1b, bam1t);
            melds.add(combiBamsBis);
            melds.add(combiBams);
            
            Collection<Combination> concealed = new ArrayList<>();
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.MIXED_STRAIGHT;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("mixedStraight pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(9, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.MIXED_STRAIGHT);
                assertEquals(iP.getPattern().getValue(), 8);
            }
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void mixedShiftedPungs() {
        System.out.println("mixedShiftedPungs");
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        
        Collection<Combination> hand = new ArrayList<>();
        GameTile dot4 = new GameTile(0, InternationalTiles.DOT_4);
        GameTile dot4b = new GameTile(1, InternationalTiles.DOT_4);
        GameTile dot4t = new GameTile(2, InternationalTiles.DOT_4);
        GameTile car5 = new GameTile(3, InternationalTiles.CHARACTER_5);
        GameTile car5b = new GameTile(4, InternationalTiles.CHARACTER_5);
        GameTile car5t = new GameTile(5, InternationalTiles.CHARACTER_5);
        
        Combination combiDots = null;
        Combination combiChars = null;
        try {
            combiDots = newCombiFact.newCombination(dot4, dot4b, dot4t);
            combiChars = newCombiFact.newCombination(car5, car5b, car5t);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hand.add(combiDots);
        hand.add(combiChars);
        
        Collection<Combination> melds = new ArrayList<>();
        GameTile bam6 = new GameTile(8, InternationalTiles.BAMBOO_6);
        GameTile bam6b = new GameTile(9, InternationalTiles.BAMBOO_6);
        GameTile bam6t = new GameTile(10, InternationalTiles.BAMBOO_6);
        GameTile bam1 = new GameTile(11, InternationalTiles.BAMBOO_1);
        GameTile bam1b = new GameTile(12, InternationalTiles.BAMBOO_1);
        GameTile bam1t = new GameTile(13, InternationalTiles.BAMBOO_1);
        
        try {
            Combination combiBams = newCombiFact.newCombination(bam6, bam6b, bam6t);
            Combination combiBamsBis = newCombiFact.newCombination(bam1, bam1b, bam1t);
            melds.add(combiBamsBis);
            melds.add(combiBams);
            
            Collection<Combination> concealed = new ArrayList<>();
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.MIXED_SHIFTED_PUNGS;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("mixedShiftedPungs pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(9, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.MIXED_SHIFTED_PUNGS);
                assertEquals(iP.getPattern().getValue(), 8);
            }
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void twoConcealedKongs() {
        System.out.println("twoConcealedKongs");
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        
        Collection<Combination> concealed = new ArrayList<>();
        GameTile dot4 = new GameTile(0, InternationalTiles.DOT_4);
        GameTile dot4b = new GameTile(1, InternationalTiles.DOT_4);
        GameTile dot4t = new GameTile(2, InternationalTiles.DOT_4);
        GameTile dot4q = new GameTile(11, InternationalTiles.DOT_4);
        GameTile car5 = new GameTile(3, InternationalTiles.CHARACTER_5);
        GameTile car5b = new GameTile(4, InternationalTiles.CHARACTER_5);
        GameTile car5t = new GameTile(5, InternationalTiles.CHARACTER_5);
        GameTile car5q = new GameTile(6, InternationalTiles.CHARACTER_5);
        GameTile bam6 = new GameTile(8, InternationalTiles.BAMBOO_6);
        GameTile bam6b = new GameTile(9, InternationalTiles.BAMBOO_6);
        GameTile bam6t = new GameTile(10, InternationalTiles.BAMBOO_6);
        
        Combination combiDots = null;
        Combination combiChars = null;
        Combination combiBams = null;
        try {
            combiDots = newCombiFact.newCombination(dot4, dot4b, dot4t, dot4q);
            combiChars = newCombiFact.newCombination(car5, car5b, car5t, car5q);
            combiBams = newCombiFact.newCombination(bam6, bam6b, bam6t);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        concealed.add(combiDots);
        concealed.add(combiChars);
        concealed.add(combiBams);
        
        
        Collection<Combination> melds = new ArrayList<>();
        
        
        try {
                        
            Collection<Combination> hand = new ArrayList<>();
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.TWO_CONCEALED_KONGS;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("twoConcealedKongs pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(8, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.TWO_CONCEALED_KONGS);
                assertEquals(iP.getPattern().getValue(), 8);
            }
        } catch (Exception ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void reversibleTiles(){
        System.out.println("reversibleTiles");
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        Collection<Combination> hand = new ArrayList<>();
        Collection<Combination> melds = new ArrayList<>();
        Collection<Combination> concealed = new ArrayList<>();
        
        GameTile dot1 = new GameTile(0, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(1, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(2, InternationalTiles.DOT_3);
        GameTile dot4 = new GameTile(11, InternationalTiles.DOT_4);
        GameTile dot4b = new GameTile(3, InternationalTiles.DOT_4);
        GameTile dot4t = new GameTile(4, InternationalTiles.DOT_4);
        GameTile drag = new GameTile(5, InternationalTiles.DRAGON_WHITE);
        GameTile dragb = new GameTile(6, InternationalTiles.DRAGON_WHITE);
        GameTile dragt= new GameTile(8, InternationalTiles.DRAGON_WHITE);
        GameTile bamb2 = new GameTile(9, InternationalTiles.BAMBOO_2);
        GameTile bamb2b = new GameTile(10, InternationalTiles.BAMBOO_2);
        
        Combination combiDots = null;
        Combination combiDotsb = null;
        Combination combiDrag = null;
        Combination combibamb = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot2, dot3);
            combiDotsb = newCombiFact.newCombination(dot4, dot4b, dot4t);
            combiDrag = newCombiFact.newCombination(drag, dragb, dragt);
            combibamb = newCombiFact.newCombination(bamb2, bamb2b);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hand.add(combiDots);
        hand.add(combiDotsb);
        concealed.add(combiDrag);
        melds.add(combibamb);
        
        try {
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.REVERSIBLE_TILES;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("reversibleTiles pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(11, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.REVERSIBLE_TILES);
                assertEquals(iP.getPattern().getValue(), 8);
            }
        } catch (Exception ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void twoDragonPungs(){        
        System.out.println("twoDragonPungs");
        
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        Collection<Combination> hand = new ArrayList<>();
        Collection<Combination> melds = new ArrayList<>();
        Collection<Combination> concealed = new ArrayList<>();
        
        GameTile dot1 = new GameTile(0, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(1, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(2, InternationalTiles.DOT_3);
        GameTile dragr = new GameTile(11, InternationalTiles.DRAGON_RED);
        GameTile dragrb = new GameTile(3, InternationalTiles.DRAGON_RED);
        GameTile dragrt = new GameTile(4, InternationalTiles.DRAGON_RED);
        GameTile dragrq = new GameTile(1, InternationalTiles.DRAGON_RED);
        GameTile drag = new GameTile(5, InternationalTiles.DRAGON_WHITE);
        GameTile dragb = new GameTile(6, InternationalTiles.DRAGON_WHITE);
        GameTile dragt= new GameTile(8, InternationalTiles.DRAGON_WHITE);
        GameTile bamb2 = new GameTile(9, InternationalTiles.BAMBOO_2);
        GameTile bamb2b = new GameTile(10, InternationalTiles.BAMBOO_2);
        
        Combination combiDots = null;
        Combination combiDragBis = null;
        Combination combiDrag = null;
        Combination combibamb = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot2, dot3);
            combiDragBis = newCombiFact.newCombination(dragr, dragrb, dragrt, dragrq);
            combiDrag = newCombiFact.newCombination(drag, dragb, dragt);
            combibamb = newCombiFact.newCombination(bamb2, bamb2b);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hand.add(combiDots);
        hand.add(combiDragBis);
        concealed.add(combiDrag);
        melds.add(combibamb);
        
        try {
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.TWO_DRAGON_PUNGS;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("twoDragonsPungs pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(7, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.TWO_DRAGON_PUNGS);
                assertEquals(iP.getPattern().getValue(), 6);
            }
        } catch (Exception ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void mixedShiftedChows(){        
        System.out.println("mixedShiftedChows");
        
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        Collection<Combination> hand = new ArrayList<>();
        Collection<Combination> melds = new ArrayList<>();
        Collection<Combination> concealed = new ArrayList<>();
        
        GameTile dot1 = new GameTile(1, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(2, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(3, InternationalTiles.DOT_3);
        GameTile bam2 = new GameTile(4, InternationalTiles.BAMBOO_2);
        GameTile bam3 = new GameTile(5, InternationalTiles.BAMBOO_3);
        GameTile bam4 = new GameTile(6, InternationalTiles.BAMBOO_4);
        GameTile car3 = new GameTile(7, InternationalTiles.CHARACTER_3);
        GameTile car4 = new GameTile(8, InternationalTiles.CHARACTER_4);
        GameTile car5 = new GameTile(9, InternationalTiles.CHARACTER_5);
        GameTile bamb2b = new GameTile(10, InternationalTiles.BAMBOO_2);
        GameTile bamb2t = new GameTile(11, InternationalTiles.BAMBOO_2);
        
        Combination combiDots = null;
        Combination combiBamb = null;
        Combination combiCar = null;
        Combination combiBamBis = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot2, dot3);
            combiBamb = newCombiFact.newCombination(bam2, bam3, bam4);
            combiBamBis = newCombiFact.newCombination(bamb2b, bamb2t);
            combiCar = newCombiFact.newCombination(car3, car4, car5);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hand.add(combiDots);
        hand.add(combiBamb);
        melds.add(combiCar);
        melds.add(combiBamBis);
        
        try {
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.MIXED_SHIFTED_CHOWS;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("mixedShiftedChows pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(9, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.MIXED_SHIFTED_CHOWS);
                assertEquals(iP.getPattern().getValue(), 6);
            }
        } catch (Exception ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void allPungs(){        
        System.out.println("allPungs");
        
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        Collection<Combination> hand = new ArrayList<>();
        Collection<Combination> melds = new ArrayList<>();
        Collection<Combination> concealed = new ArrayList<>();
        
        GameTile dot1 = new GameTile(1, InternationalTiles.DOT_1);
        GameTile dot1b = new GameTile(2, InternationalTiles.DOT_1);
        GameTile dot1t = new GameTile(3, InternationalTiles.DOT_1);
        GameTile bam2 = new GameTile(4, InternationalTiles.BAMBOO_2);
        GameTile bam2b = new GameTile(5, InternationalTiles.BAMBOO_2);
        GameTile bam2t = new GameTile(6, InternationalTiles.BAMBOO_2);
        GameTile car3 = new GameTile(7, InternationalTiles.CHARACTER_3);
        GameTile car3b = new GameTile(8, InternationalTiles.CHARACTER_3);
        GameTile car3t = new GameTile(9, InternationalTiles.CHARACTER_3);
        GameTile car5 = new GameTile(10, InternationalTiles.CHARACTER_5);
        GameTile car5b = new GameTile(11, InternationalTiles.CHARACTER_5);
        GameTile car5t = new GameTile(12, InternationalTiles.CHARACTER_5);
        GameTile car5q = new GameTile(13, InternationalTiles.CHARACTER_5);
        GameTile bamb9 = new GameTile(14, InternationalTiles.BAMBOO_9);
        GameTile bamb9b = new GameTile(15, InternationalTiles.BAMBOO_9);
        
        Combination combiDots = null;
        Combination combiBamb = null;
        Combination combiCar = null;
        Combination combiCarBis = null;
        Combination combiBamBis = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot1b, dot1t);
            combiBamb = newCombiFact.newCombination(bam2, bam2b, bam2t);
            combiCarBis = newCombiFact.newCombination(car5, car5b, car5t, car5q);
            combiBamBis = newCombiFact.newCombination(bamb9, bamb9b);
            combiCar = newCombiFact.newCombination(car3, car3b, car3t);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hand.add(combiDots);
        hand.add(combiBamb);
        hand.add(combiCar);
        hand.add(combiCarBis);
        hand.add(combiBamBis);
        
        try {
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.ALL_PUNGS;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("allPungs pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(15, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.ALL_PUNGS);
                assertEquals(iP.getPattern().getValue(), 6);
            }
        } catch (Exception ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void halfFlush(){        
        System.out.println("halfFlush");
        
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        Collection<Combination> hand = new ArrayList<>();
        Collection<Combination> melds = new ArrayList<>();
        Collection<Combination> concealed = new ArrayList<>();
        
        GameTile dot1 = new GameTile(1, InternationalTiles.DOT_1);
        GameTile dot1b = new GameTile(2, InternationalTiles.DOT_1);
        GameTile dot1t = new GameTile(3, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(4, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(5, InternationalTiles.DOT_3);
        GameTile dot4 = new GameTile(6, InternationalTiles.DOT_4);
        GameTile car5 = new GameTile(10, InternationalTiles.DOT_5);
        GameTile car5b = new GameTile(11, InternationalTiles.DOT_5);
        GameTile car5t = new GameTile(12, InternationalTiles.DOT_5);
        GameTile car5q = new GameTile(13, InternationalTiles.DOT_5);
        GameTile wind = new GameTile(14, InternationalTiles.WIND_EAST);
        GameTile windb = new GameTile(15, InternationalTiles.WIND_EAST);
        
        Combination combiDots = null;
        Combination combiDotsB = null;
        Combination combiDotsT = null;
        Combination combiWind = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot1b, dot1t);
            combiDotsB = newCombiFact.newCombination(dot2, dot3, dot4);
            combiDotsT = newCombiFact.newCombination(car5, car5b, car5t, car5q);
            combiWind = newCombiFact.newCombination(wind, windb);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hand.add(combiDots);
        hand.add(combiDotsB);
        hand.add(combiDotsT);
        hand.add(combiWind);
        
        try {
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.HALF_FLUSH;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("halfFlush pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(12, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.HALF_FLUSH);
                assertEquals(iP.getPattern().getValue(), 6);
            }
        } catch (Exception ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void allTypes(){
        System.out.println("allTypes");
        
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        Collection<Combination> hand = new ArrayList<>();
        Collection<Combination> melds = new ArrayList<>();
        Collection<Combination> concealed = new ArrayList<>();
        
        GameTile dot1 = new GameTile(1, InternationalTiles.DOT_1);
        GameTile dot1b = new GameTile(2, InternationalTiles.DOT_1);
        GameTile dot1t = new GameTile(3, InternationalTiles.DOT_1);
        
        GameTile bamboo4 = new GameTile(4, InternationalTiles.BAMBOO_4);
        GameTile bamboo4b = new GameTile(5, InternationalTiles.BAMBOO_4);
        GameTile bamboo4t = new GameTile(6, InternationalTiles.BAMBOO_4);
        
        GameTile car1 = new GameTile(10, InternationalTiles.CHARACTER_1);
        GameTile car2 = new GameTile(11, InternationalTiles.CHARACTER_2);
        GameTile car3 = new GameTile(12, InternationalTiles.CHARACTER_3);
        
        
        GameTile wind = new GameTile(14, InternationalTiles.WIND_EAST);
        GameTile windb = new GameTile(15, InternationalTiles.WIND_EAST);
        GameTile windt = new GameTile(13, InternationalTiles.WIND_EAST);
        
        GameTile dragon = new GameTile(9, InternationalTiles.DRAGON_GREEN);
        GameTile dragonb = new GameTile(8, InternationalTiles.DRAGON_GREEN);
        
        Combination combiDots = null;
        Combination combiDotsB = null;
        Combination combiDotsT = null;
        Combination combiWind = null;
        Combination combiDragon = null;
        
        try {
            combiDots = newCombiFact.newCombination(dot1, dot1b, dot1t);
            combiDotsB = newCombiFact.newCombination(bamboo4, bamboo4b, bamboo4t);
            combiDotsT = newCombiFact.newCombination(car1, car2, car3);
            combiWind = newCombiFact.newCombination(wind, windb, windt);
            combiDragon = newCombiFact.newCombination(dragon, dragonb);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hand.add(combiDots);
        hand.add(combiDotsB);
        hand.add(combiDotsT);
        hand.add(combiWind);
        hand.add(combiDragon);
        
        try {
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.ALL_TYPES;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("allTypes pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(14, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.ALL_TYPES);
                assertEquals(iP.getPattern().getValue(), 6);
            }
        } catch (Exception ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void meldedHand(){        
        System.out.println("meldedHand");
        
        GameTile winningTile = new GameTile(1, InternationalTiles.WIND_SOUTH);
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        Collection<Combination> hand = new ArrayList<>();
        Collection<Combination> melds = new ArrayList<>();
        Collection<Combination> concealed = new ArrayList<>();
        
        GameTile dot1 = new GameTile(2, InternationalTiles.DOT_1);
        GameTile dot1b = new GameTile(3, InternationalTiles.DOT_1);
        GameTile dot1t = new GameTile(4, InternationalTiles.DOT_1);
        GameTile car2 = new GameTile(5, InternationalTiles.CHARACTER_2);
        GameTile car3 = new GameTile(6, InternationalTiles.CHARACTER_3);
        GameTile car4 = new GameTile(7, InternationalTiles.CHARACTER_4);
        GameTile bam5 = new GameTile(8, InternationalTiles.BAMBOO_5);
        GameTile bam5b = new GameTile(9, InternationalTiles.BAMBOO_5);
        GameTile bam5t = new GameTile(10, InternationalTiles.BAMBOO_5);
        GameTile bam5q = new GameTile(11, InternationalTiles.BAMBOO_5);
        GameTile wind = new GameTile(12, InternationalTiles.WIND_SOUTH);
        GameTile drag = new GameTile(13, InternationalTiles.DRAGON_GREEN);
        GameTile dragb = new GameTile(14, InternationalTiles.DRAGON_GREEN);
        
        Combination combiDots = null;
        Combination combiCars = null;
        Combination combiBams = null;
        Combination combiWind = null;
        Combination combiDrags = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot1b, dot1t);
            combiCars = newCombiFact.newCombination(car2, car3, car4);
            combiBams = newCombiFact.newCombination(bam5, bam5b, bam5t, bam5q);
            combiWind = newCombiFact.newCombination(wind, winningTile);
            combiDrags = newCombiFact.newCombination(drag, dragb);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        melds.add(combiDots);
        melds.add(combiCars);
        melds.add(combiBams);
        melds.add(combiDrags);
        melds.add(combiWind);
                
        try {
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, true, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.MELDED_HAND;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);
            
            for (IdentifiedPattern iP : pattern) {
                System.out.println("meldedHand pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(14, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.MELDED_HAND);
                assertEquals(iP.getPattern().getValue(), 6);
            }
        } catch (Exception ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void twoMeldedKongs(){        
        System.out.println("twoMeldedKongs");
        
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        Collection<Combination> hand = new ArrayList<>();
        Collection<Combination> melds = new ArrayList<>();
        Collection<Combination> concealed = new ArrayList<>();
        
        GameTile dot1 = new GameTile(1, InternationalTiles.DOT_1);
        GameTile dot1b = new GameTile(2, InternationalTiles.DOT_1);
        GameTile dot1t = new GameTile(3, InternationalTiles.DOT_1);
        GameTile dot1q = new GameTile(4, InternationalTiles.DOT_1);
        GameTile car5 = new GameTile(10, InternationalTiles.CHARACTER_5);
        GameTile car5b = new GameTile(11, InternationalTiles.CHARACTER_5);
        GameTile car5t = new GameTile(12, InternationalTiles.CHARACTER_5);
        GameTile car5q = new GameTile(13, InternationalTiles.CHARACTER_5);
        GameTile wind = new GameTile(5, InternationalTiles.WIND_EAST);
        GameTile windb = new GameTile(6, InternationalTiles.WIND_EAST);
        
        Combination combiDots = null;
        Combination combiCars = null;
        Combination combiWinds = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot1b, dot1t, dot1q);
            combiCars = newCombiFact.newCombination(car5, car5b, car5t, car5q);
            combiWinds = newCombiFact.newCombination(wind, windb);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        melds.add(combiDots);
        melds.add(combiCars);
        melds.add(combiWinds);
        
        try {
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.TWO_MELDED_KONGS;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("twoMeldedKongs pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(8, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.TWO_MELDED_KONGS);
                assertEquals(iP.getPattern().getValue(), 4);
            }
        } catch (Exception ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void outsideHand(){        
        System.out.println("outsideHand");
        
        GameTile winningTile = null;
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        Collection<Combination> hand = new ArrayList<>();
        Collection<Combination> melds = new ArrayList<>();
        Collection<Combination> concealed = new ArrayList<>();
        
        GameTile dot = new GameTile(7, InternationalTiles.DOT_7);
        GameTile dotb = new GameTile(8, InternationalTiles.DOT_8);
        GameTile dott = new GameTile(9, InternationalTiles.DOT_9);
        GameTile drag = new GameTile(1, InternationalTiles.DRAGON_WHITE);
        GameTile dragb = new GameTile(2, InternationalTiles.DRAGON_WHITE);
        GameTile dragt = new GameTile(3, InternationalTiles.DRAGON_WHITE);
        GameTile dragq = new GameTile(4, InternationalTiles.DRAGON_WHITE);
        GameTile wind = new GameTile(5, InternationalTiles.WIND_EAST);
        GameTile windb = new GameTile(6, InternationalTiles.WIND_EAST);
        
        Combination combiDots = null;
        Combination combiDrags = null;
        Combination combiWinds = null;
        try {
            combiDots = newCombiFact.newCombination(dot, dotb, dott);
            combiDrags = newCombiFact.newCombination(drag, dragb, dragt, dragq);
            combiWinds = newCombiFact.newCombination(wind, windb);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hand.add(combiDots);
        concealed.add(combiDrags);
        melds.add(combiWinds);
        
        try {
            Collection<GameTile> supremeHonors = new ArrayList<>();
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.OUTSIDE_HAND;
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);

            for (IdentifiedPattern iP : pattern) {
                System.out.println("outsideHand pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(9, tiles.size());
                assertEquals(iP.getPattern(), InternationalPatterns.OUTSIDE_HAND);
                assertEquals(iP.getPattern().getValue(), 4);
            }
        } catch (Exception ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void pungOfTerminalsOrHonors() {
        System.out.println("pungOfTerminalsOrHonors");
        //Création de la winningTile
        GameTile winningTile = new GameTile(14, InternationalTiles.BAMBOO_1);
        //Création de la factory
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        //Création de la main
        Collection<Combination> hand = new ArrayList<>();
        GameTile dot1 = new GameTile(0, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(1, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(2, InternationalTiles.DOT_3);
        GameTile dot6 = new GameTile(3, InternationalTiles.DOT_6);
        GameTile dot7 = new GameTile(4, InternationalTiles.DOT_7);
        GameTile dot8 = new GameTile(5, InternationalTiles.DOT_8);
        GameTile bamboo2 = new GameTile(6, InternationalTiles.BAMBOO_2);
        GameTile bamboo2bis = new GameTile(7, InternationalTiles.BAMBOO_2);

        Combination combiDots = null;
        Combination combiDotsBis = null;
        Combination combiBamboos = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot2, dot3);
            combiDotsBis = newCombiFact.newCombination(dot6, dot7, dot8);
            combiBamboos = newCombiFact.newCombination(bamboo2, bamboo2bis);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }

        hand.add(combiDots);
        hand.add(combiDotsBis);
        hand.add(combiBamboos);
        //Création des melds
        Collection<Combination> melds = new ArrayList<>();
        GameTile carac1 = new GameTile(8, InternationalTiles.CHARACTER_1);
        GameTile carac1bis = new GameTile(9, InternationalTiles.CHARACTER_1);
        GameTile carac1ter = new GameTile(10, InternationalTiles.CHARACTER_1);
        GameTile dragonG = new GameTile(11, InternationalTiles.DRAGON_RED);
        GameTile dragonW = new GameTile(12, InternationalTiles.DRAGON_RED);
        GameTile dragonR = new GameTile(13, InternationalTiles.DRAGON_RED);

        try {
            Combination combiCars = newCombiFact.newCombination(carac1, carac1bis, carac1ter);
            Combination combiDragons = newCombiFact.newCombination(dragonG, dragonR, dragonW);

            melds.add(combiCars);
            melds.add(combiDragons);
            //Création des cachés et des honneurs
            Collection<Combination> concealed = new ArrayList<>();
            Collection<GameTile> supremeHonors = new ArrayList<>();
            //Création d'un set
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.PUNG_OF_TERMINALS_OR_HONORS;

            //Appel de la méthode d'identification par rapport au set créé
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);
            //ArrayList de retour pour transformer un IdentifiedPattern en GameTile

            for (IdentifiedPattern iP : pattern) {
                //Le but est le suivant : chaque tuiles du patterns identifié doivent être comparées à la combinaison créée
                //Soit : Combination combiCars = newCombiFact.newCombination(carac1,carac1bis,carac1ter);
                //Doit être retrouvé dans l'IdentifiedPattern pour le assertEquals
                //Donc que le contenu de toTest soit égale à combiCars de notre test
                //toTest.addAll(iP.getTiles());
                System.out.println("pungOfTerminalsOrHonors pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                Collection<GameTile> combiTiles = new HashSet<>(Arrays.asList(combiCars.getTiles()));
                assertEquals(combiTiles, tiles);
            }
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void fullyConcealedHand(){
        System.out.println("fullyConcealedHand");
        //Création de la winningTile
        GameTile winningTile = new GameTile(14, InternationalTiles.BAMBOO_1);
        //Création de la factory
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        //Création de la main
        Collection<Combination> hand = new ArrayList<>();
        GameTile dot1 = new GameTile(0, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(1, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(2, InternationalTiles.DOT_3);
        GameTile dot6 = new GameTile(3, InternationalTiles.DOT_6);
        GameTile dot7 = new GameTile(4, InternationalTiles.DOT_7);
        GameTile dot8 = new GameTile(5, InternationalTiles.DOT_8);
        GameTile bamboo2 = new GameTile(6, InternationalTiles.BAMBOO_2);
        GameTile bamboo2bis = new GameTile(7, InternationalTiles.BAMBOO_2);

        Combination combiDots = null;
        Combination combiDotsBis = null;
        Combination combiBamboos = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot2, dot3);
            combiDotsBis = newCombiFact.newCombination(dot6, dot7, dot8);
            combiBamboos = newCombiFact.newCombination(bamboo2, bamboo2bis);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collection<Combination> concealed = new ArrayList<>();
        concealed.add(combiDots);
        concealed.add(combiDotsBis);
        concealed.add(combiBamboos);
        //Création des melds
        Collection<Combination> melds = new ArrayList<>();
        GameTile carac1 = new GameTile(8, InternationalTiles.CHARACTER_1);
        GameTile carac1bis = new GameTile(9, InternationalTiles.CHARACTER_1);
        GameTile carac1ter = new GameTile(10, InternationalTiles.CHARACTER_1);
        GameTile dragonG = new GameTile(11, InternationalTiles.DRAGON_RED);
        GameTile dragonW = new GameTile(12, InternationalTiles.DRAGON_RED);
        GameTile dragonR = new GameTile(13, InternationalTiles.DRAGON_RED);

        try {
            Combination combiCars = newCombiFact.newCombination(carac1, carac1bis, carac1ter);
            Combination combiDragons = newCombiFact.newCombination(dragonG, dragonR, dragonW);

            concealed.add(combiCars);
            concealed.add(combiDragons);
            //Création des cachés et des honneurs
            
            Collection<GameTile> supremeHonors = new ArrayList<>();
            //Création d'un set
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, true, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.FULLY_CONCEALED_HAND;

            //Appel de la méthode d'identification par rapport au set créé
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);
            //ArrayList de retour pour transformer un IdentifiedPattern en GameTile

            for (IdentifiedPattern iP : pattern) {
                //Le but est le suivant : chaque tuiles du patterns identifié doivent être comparées à la combinaison créée
                //Soit : Combination combiCars = newCombiFact.newCombination(carac1,carac1bis,carac1ter);
                //Doit être retrouvé dans l'IdentifiedPattern pour le assertEquals
                //Donc que le contenu de toTest soit égale à combiCars de notre test
                //toTest.addAll(iP.getTiles());
                System.out.println("fully_concealed pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(iP.getPattern(), InternationalPatterns.FULLY_CONCEALED_HAND);
                assertEquals(14, tiles.size());
                assertEquals(iP.getPattern().getValue(), 4);
            }
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void dragonPung(){
        System.out.println("dragonPung");
        //Création de la winningTile
        GameTile winningTile = new GameTile(14, InternationalTiles.BAMBOO_1);
        //Création de la factory
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        //Création de la main
        Collection<Combination> hand = new ArrayList<>();
        GameTile dot1 = new GameTile(0, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(1, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(2, InternationalTiles.DOT_3);
        GameTile dot6 = new GameTile(3, InternationalTiles.DOT_6);
        GameTile dot7 = new GameTile(4, InternationalTiles.DOT_7);
        GameTile dot8 = new GameTile(5, InternationalTiles.DOT_8);
        GameTile bamboo2 = new GameTile(6, InternationalTiles.BAMBOO_2);
        GameTile bamboo2bis = new GameTile(7, InternationalTiles.BAMBOO_2);

        Combination combiDots = null;
        Combination combiDotsBis = null;
        Combination combiBamboos = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot2, dot3);
            combiDotsBis = newCombiFact.newCombination(dot6, dot7, dot8);
            combiBamboos = newCombiFact.newCombination(bamboo2, bamboo2bis);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collection<Combination> concealed = new ArrayList<>();
        concealed.add(combiDots);
        concealed.add(combiDotsBis);
        concealed.add(combiBamboos);
        //Création des melds
        Collection<Combination> melds = new ArrayList<>();
        GameTile carac1 = new GameTile(8, InternationalTiles.CHARACTER_1);
        GameTile carac1bis = new GameTile(9, InternationalTiles.CHARACTER_1);
        GameTile carac1ter = new GameTile(10, InternationalTiles.CHARACTER_1);
        GameTile dragonG = new GameTile(11, InternationalTiles.DRAGON_RED);
        GameTile dragonW = new GameTile(12, InternationalTiles.DRAGON_RED);
        GameTile dragonR = new GameTile(13, InternationalTiles.DRAGON_RED);

        try {
            Combination combiCars = newCombiFact.newCombination(carac1, carac1bis, carac1ter);
            Combination combiDragons = newCombiFact.newCombination(dragonG, dragonR, dragonW);

            concealed.add(combiCars);
            concealed.add(combiDragons);
            //Création des cachés et des honneurs
            
            Collection<GameTile> supremeHonors = new ArrayList<>();
            //Création d'un set
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, true, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.DRAGON_PUNG;

            //Appel de la méthode d'identification par rapport au set créé
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);
            //ArrayList de retour pour transformer un IdentifiedPattern en GameTile

            for (IdentifiedPattern iP : pattern) {
                //Le but est le suivant : chaque tuiles du patterns identifié doivent être comparées à la combinaison créée
                //Soit : Combination combiCars = newCombiFact.newCombination(carac1,carac1bis,carac1ter);
                //Doit être retrouvé dans l'IdentifiedPattern pour le assertEquals
                //Donc que le contenu de toTest soit égale à combiCars de notre test
                //toTest.addAll(iP.getTiles());
                System.out.println("dragon_pung pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(iP.getPattern(), InternationalPatterns.DRAGON_PUNG);
                assertEquals(3, tiles.size());
                assertEquals(iP.getPattern().getValue(), 2);
            }
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void prevalentWind(){
        System.out.println("prevalentWind");
        //Création de la winningTile
        GameTile winningTile = new GameTile(14, InternationalTiles.BAMBOO_1);
        //Création de la factory
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        //Création de la main
        Collection<Combination> hand = new ArrayList<>();
        GameTile dot1 = new GameTile(0, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(1, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(2, InternationalTiles.DOT_3);
        

        Combination combiDots = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot2, dot3);
            
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collection<Combination> concealed = new ArrayList<>();
        concealed.add(combiDots);
        //Création des melds
        Collection<Combination> melds = new ArrayList<>();
        GameTile carac1 = new GameTile(8, InternationalTiles.CHARACTER_1);
        GameTile carac1bis = new GameTile(9, InternationalTiles.CHARACTER_1);
        GameTile carac1ter = new GameTile(10, InternationalTiles.CHARACTER_1);
        GameTile windD = new GameTile(11, InternationalTiles.WIND_SOUTH);
        GameTile windDb = new GameTile(12, InternationalTiles.WIND_SOUTH);
        GameTile windDt = new GameTile(13, InternationalTiles.WIND_SOUTH);

        try {
            Combination combiCars = newCombiFact.newCombination(carac1, carac1bis, carac1ter);
            Combination combiDragons = newCombiFact.newCombination(windD, windDb, windDt);

            concealed.add(combiCars);
            hand.add(combiDragons);
            //Création des cachés et des honneurs
            
            Collection<GameTile> supremeHonors = new ArrayList<>();
            //Création d'un set
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, true, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.PREVALENT_WIND;

            //Appel de la méthode d'identification par rapport au set créé
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);
            //ArrayList de retour pour transformer un IdentifiedPattern en GameTile

            for (IdentifiedPattern iP : pattern) {
                //Le but est le suivant : chaque tuiles du patterns identifié doivent être comparées à la combinaison créée
                //Soit : Combination combiCars = newCombiFact.newCombination(carac1,carac1bis,carac1ter);
                //Doit être retrouvé dans l'IdentifiedPattern pour le assertEquals
                //Donc que le contenu de toTest soit égale à combiCars de notre test
                //toTest.addAll(iP.getTiles());
                System.out.println("prevalent_wind pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(iP.getPattern(), InternationalPatterns.PREVALENT_WIND);
                assertEquals(3, tiles.size());
                assertEquals(iP.getPattern().getValue(), 2);
            }
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void seatWind(){
        System.out.println("seatWind");
        //Création de la winningTile
        GameTile winningTile = new GameTile(14, InternationalTiles.BAMBOO_1);
        //Création de la factory
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        //Création de la main
        Collection<Combination> hand = new ArrayList<>();
        GameTile dot1 = new GameTile(0, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(1, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(2, InternationalTiles.DOT_3);
        

        Combination combiDots = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot2, dot3);
            
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collection<Combination> concealed = new ArrayList<>();
        concealed.add(combiDots);
        //Création des melds
        Collection<Combination> melds = new ArrayList<>();
        GameTile carac1 = new GameTile(8, InternationalTiles.CHARACTER_1);
        GameTile carac1bis = new GameTile(9, InternationalTiles.CHARACTER_1);
        GameTile carac1ter = new GameTile(10, InternationalTiles.CHARACTER_1);
        GameTile windD = new GameTile(11, InternationalTiles.WIND_EAST);
        GameTile windDb = new GameTile(12, InternationalTiles.WIND_EAST);
        GameTile windDt = new GameTile(13, InternationalTiles.WIND_EAST);

        try {
            Combination combiCars = newCombiFact.newCombination(carac1, carac1bis, carac1ter);
            Combination combiDragons = newCombiFact.newCombination(windD, windDb, windDt);

            concealed.add(combiCars);
            hand.add(combiDragons);
            //Création des cachés et des honneurs
            
            Collection<GameTile> supremeHonors = new ArrayList<>();
            //Création d'un set
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, true, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.SEAT_WIND;

            //Appel de la méthode d'identification par rapport au set créé
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);
            //ArrayList de retour pour transformer un IdentifiedPattern en GameTile

            for (IdentifiedPattern iP : pattern) {
                //Le but est le suivant : chaque tuiles du patterns identifié doivent être comparées à la combinaison créée
                //Soit : Combination combiCars = newCombiFact.newCombination(carac1,carac1bis,carac1ter);
                //Doit être retrouvé dans l'IdentifiedPattern pour le assertEquals
                //Donc que le contenu de toTest soit égale à combiCars de notre test
                //toTest.addAll(iP.getTiles());
                System.out.println("seat_wind pattern : "+iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                assertEquals(iP.getPattern(), InternationalPatterns.SEAT_WIND);
                assertEquals(3, tiles.size());
                assertEquals(iP.getPattern().getValue(), 2);
            }
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
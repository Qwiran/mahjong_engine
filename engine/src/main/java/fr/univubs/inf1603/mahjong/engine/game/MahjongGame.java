package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.*;
import fr.univubs.inf1603.mahjong.Wind;

import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.util.*;
import org.apache.log4j.Logger;


/**
 * Cette classe permet de représenter une partie de Mahjong
 *
 */
public class MahjongGame implements Game {

    private Wind[] playerWind;
    private GameRule rule;
    private MahjongBoard board;
    private Move lastPlayedMove;

    private Duration stealingTime;
    private Duration playingTime;
    private Timer playingTimer;

    private ArrayList<Move> registeredMoves;
    private ArrayList<Move> possiblesMoves;
    private UUID uuid;
    private boolean ableToRegisterMoves;

    private int[] playerPoints;
  
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private static final Logger LOGGER = Logger.getLogger(GameTile.class.getName());

    
    /**
     * This is the full constructor of MahjongGame, allowing to initialize all
     * of its fields
     *
     * @param rule Rules of this game
     * @param board Current state of the board
     * @param lastPlayedMove The last played move of this game
     * @param stealingTime The time players have to decide if they can steal a
     * discarded tile
     * @param playingTime This players have to decide what to discard
     * @param playerPoints The points's number of the player
     * @param uuid This game's UUID
     * @param playerWind The wind according to the player
     * @throws GameException
     */
    public MahjongGame(GameRule rule, MahjongBoard board, Move lastPlayedMove, Duration stealingTime, Duration playingTime, int[] playerPoints, UUID uuid, Wind[] playerWind) throws GameException {
        LOGGER.info("MahjongGame constructor/8 entry");
        this.rule = rule;
        this.board = board;
        this.lastPlayedMove = lastPlayedMove;
        this.stealingTime = stealingTime;
        this.playingTime = playingTime;
        this.uuid = uuid;
        this.playerPoints = playerPoints;
        this.playerWind = playerWind;

    }

    /**
     * This is a constructor of MahjongGame
     *  @param rule Rules of this game
     * @param stealingTime The time players have to decide if they can steal a
     * discarded tile
 * @param playingTime This players have to decide what to discard
     */
    public MahjongGame(GameRule rule, Duration stealingTime, Duration playingTime) {
        this(UUID.randomUUID(), rule, stealingTime, playingTime);
    }

    /**
     * This is a constructor of MahjongGame
     *
     * @param uuid {@link UUID} of the game
     * @param rule Rules of this game
     * @param stealingTime The time players have to decide if they can steal a
     * discarded tile
     * @param playingTime This players have to decide what to discard
     */
    public MahjongGame(UUID uuid, GameRule rule, Duration stealingTime, Duration playingTime) {
        LOGGER.info("MahjongGame constructor/4 entry");

        this.rule = rule;
        this.stealingTime = stealingTime;

        this.playingTime = playingTime;

        this.lastPlayedMove = null;
        this.board = null;
        this.uuid = uuid;
        this.playerPoints = new int[4];
        this.playerWind = this.rule.getBoardRule().getPlayerOrder();
        
    }

    @Deprecated
    public MahjongGame(GameRule rule) throws GameException {
        this.rule = rule;
        this.board = null;
        this.uuid = UUID.randomUUID();
        this.playingTime = Duration.ofSeconds(5);
    }

    @Override
    public Board getBoard(Wind wind) throws GameException {
        if (wind == null) {
            return this.board;
        }
        else if(this.board==null){
            return null;
        }
        return this.board.getViewFromWind(wind);
    }

    public Board getBoard() {
        return this.board;
    }

    @Override
    public ArrayList<Move> getPossibleMoves() {
        if(this.possiblesMoves==null){
            return new ArrayList<>();
        }
        return this.possiblesMoves;
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Wind wind) {
        Map<Wind, Collection<Move>> moves = new HashMap<>(this.rule.getBoardRule().findValidMoves(this.board, this.lastPlayedMove));
        if (moves.containsKey(wind))
            return new ArrayList<>(moves.get(wind));
        else
            return new ArrayList<>();
    }

    @Override
    public ArrayList<Move> getPossibleMoves(int player) throws GameException {
        return getPossibleMoves(playerWind[player]);
    }

    @Override
    public MahjongGame clone() {
        throw new UnsupportedOperationException("not implemented yet");
//        return new MahjongGameGame();
    }

    @Override
    public Duration getStealingTime() {
        return this.stealingTime;
    }

    @Override
    public Duration getPlayingTime() {
        return this.playingTime;
    }

    @Override
    public Move getLastPlayedMove() {
        return this.lastPlayedMove;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    @Override
    public GameRule getRule() {
        return this.rule;
    }

    @Override
    public Wind getCurrentwind() throws GameException {
        if(this.board==null){
            return null;
        }
        return this.board.getCurrentWind();
    }

    @Override
    public int getPlayerPoints(int player) {
        if(player<0 || player>3){
            throw new IllegalArgumentException("Player's index should be 0,1,2 or 3, was: "+player);
        }
        return this.playerPoints[player];
    }

    @Override
    public int getPlayerPoints(Wind wind){
        int indexOfWind = 0;
        while (this.playerWind[indexOfWind] != wind) {
            indexOfWind++;
        }
        return this.playerPoints[indexOfWind];
    }

    @Override
    public int[] getAllPlayerPoints() {
        return this.playerPoints;
    }

    @Override
    public Wind getPlayerWind(int player) throws GameException {
        if (player < 0 || player > 3) {
            throw new GameException("The player you are asking the wind from doesn't exist. There is only 4 player describs by 0 1 2 3 .");
        }
        Wind wind = this.playerWind[player];
        if (wind == null) {
            throw new GameException("The wind of a player cannot be null.");
        }
        return wind;
    }

    @Override
    public int getPlayerFromWind(Wind wind) throws GameException {
        int player = -1;
        for (int i = 0; i < 4; i++) {
            if (this.playerWind[i] == wind) {
                player = i;
            }
        }
        if (player == -1) {
            throw new GameException("The wind has to be in the playerWind.");
        }
        return player;
    }

    @Override
    public Wind[] getPlayerWinds() throws GameException {
        return this.playerWind;
    }

    /**
     * Permet d'effectuer le move sur le Board
     *
     * @param move Le move à effectuer
     * @throws GameException quand throw GameException dans board.applyMove
     */
    private synchronized void applyMove(Move move) throws GameException {
        try {
            System.out.println();
            if(!move.getPath().isEmpty()){
                this.board.applyMove(move);
                this.lastPlayedMove = move;
                this.propertyChangeSupport.firePropertyChange(LAST_PLAYED_MOVE_PROPERTY, null, this.lastPlayedMove);
            }else{
                this.applyMahjong(move.getWind());
            }                
        } catch (GameException ge) {
            throw new GameException(ge.getMessage());
        }
    }

    @Override
    public void launchGame() {
        LOGGER.info("MahjongGame launchGame() entry");
        if (this.playerWind == null) {
            this.playerWind = this.rule.getBoardRule().getPlayerOrder();
            //This shoudl not happen, peristance maybe missed this field
        }
        StartingWall wall = this.rule.getBoardRule().buildWall();

        if (this.board == null) {
            if (wall != null) {
                this.board = this.rule.getBoardRule().distributeTiles(wall);
            } else {
                LOGGER.fatal("Wall filled by gamerule : " + rule.getName() + " is null");
            }
        }
        this.registeredMoves = new ArrayList<>();
        getAndFirePossibleMoves();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                LOGGER.debug("MahjongGame timer performing task");
//                ableToRegisterMoves = false;
                try {
                    chooseMoveToApply();
                } catch (GameException ex) {
                    LOGGER.fatal("Unable to choose move to play : " + ex);
                }
                getAndFirePossibleMoves();
                //playingTimer.schedule(this, playingTime.toMillis());
            }
        };
        playingTimer = new Timer();
        playingTimer.schedule(task, this.playingTime.toMillis(), this.playingTime.toMillis());

    }

    @Override
    public synchronized void registerMove(Move move) throws GameException {
        //      if(this.ableToRegisterMoves){
        boolean matchAlreadyPlayed = false;
        for (Move p : this.registeredMoves) {
            if (p.equals(move)) {
                matchAlreadyPlayed = true;
                break;
            }
        }
        if(!matchAlreadyPlayed){
            boolean match = false;
            for (Move p : this.possiblesMoves) {
                if (p.equals(move)) {
                    match = true;
                    break;
                }
            }
            if (match) {
                LOGGER.debug("Registered move is : " + move);
                this.registeredMoves.add(move);
            } else {
                throw new MoveException("Move " + move + " apprears to be forged");
            }
        }else{
            throw new GameException("Trying to register an already registered move");
        }
        //    }
        /*  else{
            throw new GameException("Impossible de register le move hors du temps imparti.");
        }*/
    }

    /**
     * Permet de récupérer les moves possibles sur un Board via Rule et de les
     * notifier
     */
    private synchronized void getAndFirePossibleMoves() {
        this.possiblesMoves = new ArrayList<>();
        this.registeredMoves = new ArrayList<>();
        EnumMap<Wind, Collection<Move>> validMoves = this.rule.getBoardRule().findValidMoves(this.board, this.lastPlayedMove);
        for (Wind w : validMoves.keySet()) {
            for (Move m : validMoves.get(w)) {
                this.possiblesMoves.add(m);
            }
        }
        if (this.rule.getBoardRule().isGameFinished(this.board, this.lastPlayedMove)) {
            this.exitGame(0, "Fin de la partie.");
        } else {
            this.propertyChangeSupport.firePropertyChange(POSSIBLE_MOVES_PROPERTY, null, this.possiblesMoves);
        }
    }

    /**
     * Permet de choisir le move à effectuer par rapport à ceux qui ont été
     * register et appel la méthode applyMove
     *
     * @throws GameException quand applyMove throw une GameException
     */
    private synchronized void chooseMoveToApply() throws GameException {
        if (this.possiblesMoves == null) {
            LOGGER.fatal("MahjongGame cannot provide a structure of possible move");
        }

        if (this.possiblesMoves.isEmpty()) {
            LOGGER.fatal("MahjongGame "+this.getUUID()+" does not have any possible move");
        }

        Move moveToApply;//Default applied move in the event of no registered move
        if (this.registeredMoves == null) {
            LOGGER.fatal("MahjongGame's registeredMoves is null");
        }
        if (this.registeredMoves.isEmpty()) {
            LOGGER.info("Move was choosen by MahjongGame, not by a player");
            moveToApply = this.possiblesMoves.get(0);//Default applied move in the event of no registered move
            for (Move move : this.possiblesMoves) {
                if (move.getPriority() < moveToApply.getPriority()) {
                    moveToApply = move;
                }
            }
        } else {
            moveToApply = this.registeredMoves.get(0);
            for (Move move : this.registeredMoves) {
                if (move.getPriority() < moveToApply.getPriority()) {
                    moveToApply = move;
                }
            }
        }
        try {
            this.applyMove(moveToApply);            
        } catch (GameException ge) {
            throw new GameException(ge.getMessage());
        }
    }

    /**
     * Les actions à effectuer lors d'une interruption de partie
     *
     * @param state L'état de sortie (0 = sortie sans erreur, 1 = sortie avec
     * erreur)
     * @param msg Le message à afficher
     */
    private void exitGame(int state, String msg) {
        LOGGER.info("Exiting MahjongGame with code "+state+" and message :"+msg);
        this.playingTimer.cancel();
        switch (state) {
            case 0:
                this.propertyChangeSupport.firePropertyChange(GAME_OVER_PROPERTY, null, msg);
                break;
            case 1:
                LOGGER.warn(msg);
                break;
            default:
                LOGGER.warn("L'état envoyé n'est pas supporté.");
        }
}

    /**
     * Si le dernier move n'est pas changer il n'y a pas mahjong
     * @param wind
     * @param set
     * @return 
     */
    private void applyMahjong(Wind wind){
        AbstractCombinationFactory factory = new InternationalCombinationFactory();
        try {
            //WINNINGTILE
            Integer idLastTile = 0;
            for(Integer inte : lastPlayedMove.getPath().keySet()) idLastTile = inte;
            GameTileInterface winningTileInterface = board.getTile(idLastTile);
            GameTile winningTile = null;
            if(winningTileInterface instanceof GameTile) winningTile = (GameTile) winningTileInterface;

            //HAND
            ArrayList<GameTile> hand = new ArrayList<GameTile>();
            int j=0;
            for(GameTileInterface gti : board.getTileZone("Hand"+wind.getName()).getTiles()){
                GameTile gt = null;
                if(gti instanceof GameTile) gt = (GameTile) gti;
                if(gt != null) hand.add(gt);
            }
            ArrayList<Combination> concealed = new ArrayList<Combination>();

            //MELDS
            ArrayList<Combination> melds = new ArrayList<Combination>();
            GameTile[] tab = null;
            for(int i=0; i<4; i++){
                tab = new GameTile[board.getTileZone("Meld"+wind.getName()+String.valueOf(i)).getTiles().size()];
                int k = 0;
                for(GameTileInterface gti : board.getTileZone("Meld"+wind.getName()+String.valueOf(i)).getTiles()){
                    GameTile gt = null;
                    if(gti instanceof GameTile) gt = (GameTile) gti;
                    if(gt != null) tab[k] = gt;
                    k++;
                }
                try{
                    melds.add(factory.newCombination(tab));
                }catch(RulesException ex){

                }
            }

            //SUPREMEHONORS
            ArrayList<GameTileInterface> supremeHonorsInterface = board.getTileZone("Supreme"+wind.getName()).getTiles();
            ArrayList<GameTile> supremeHonors = new ArrayList<GameTile>();
            for(GameTileInterface gti : supremeHonorsInterface){
                GameTile gt = null;
                if(gti instanceof GameTile) gt = (GameTile) gti;
                if(gt != null) supremeHonors.add(gt);
            }
            boolean drawnFromWall = false;
            boolean takenFromDiscard = false;

            PlayerSituation situation = new PlayerSituation(winningTile, hand, concealed, melds, supremeHonors,
                    drawnFromWall, takenFromDiscard, board.getCurrentWind(), wind);
          
            int max = 0;
            ScoringSystem scoring = this.rule.getScoringSystem();
            for(PlayerSet setPlayer :  scoring.createSetsFromSituation(situation) ){
                
                Collection<IdentifiedPattern> allPatterns = scoring.identifyPatterns(setPlayer);
                
                for ( Collection<IdentifiedPattern> split : scoring.splitIncompatiblePatterns(allPatterns)){
                    int nb = scoring.computeScore(split);
                    if(nb > max) max = nb;
                }
                
            }
            if(max !=0){
                this.lastPlayedMove = new Move(wind,-1,new HashMap<Integer,TileZoneIdentifier>(),new HashMap<Integer,Boolean>());               
                this.propertyChangeSupport.firePropertyChange(LAST_PLAYED_MOVE_PROPERTY, null, this.lastPlayedMove);
            }
        }catch (GameException ex) {
            LOGGER.fatal(ex);
        }

    } 
}

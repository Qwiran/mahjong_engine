package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.Wind;
import fr.univubs.inf1603.mahjong.engine.rule.AbstractTile;
import java.beans.PropertyChangeSupport;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.apache.log4j.Logger;


/**
 * MahjongBoard represents the mahjong board during the game.
 *
 */
public class MahjongBoard implements Board, Cloneable {

    /**
     * Le vent courrant du board
     */
    private Wind currentWind;
    /**
     * L identificateur unique du board
     */
    private final UUID uuid;
    /**
     * La liste des zones contenu dans le board
     */
    private final EnumMap<TileZoneIdentifier, TileZone> zones;
    /**
     * Un tableau qui donne pour chaque tuile un numéro unique a chaque board
     */
    private HashMap<Integer,GameTileInterface> indexToTile;
    /**
     * Un tableau contenant la tuile lié à une zone
     */
    private HashMap<GameTileInterface,TileZone> tileToZone;

    private static final Logger LOGGER = Logger.getLogger(GameTile.class.getName());

    
    /**
     * This constructor allows initialization of all fields and should only be
     * used by a persistence framework, factories, or other constructors of this
     * class
     *
     * @param wind Le vent courrant du board
     * @param uuid L identifiant unique
     * @param zones La liste des zones
     */
    public MahjongBoard(Wind wind, UUID uuid, EnumMap<TileZoneIdentifier, TileZone> zones) {
        LOGGER.info("MahjongBoard constructor/4 entry");
        this.currentWind = wind;
        this.uuid = uuid;
        this.zones = zones;
        indexToTile=null;
        tileToZone=null;
    }

    /**
     * Constructeur du board initialisant tout en ne prenant que le vent courant
     * @param wind Le vent courrant du board
     */
    public MahjongBoard(Wind wind) {
        LOGGER.info("MahjongBoard constructor/1 entry");
        this.currentWind = wind;
        this.uuid = UUID.randomUUID();
        this.zones = new EnumMap<>(TileZoneIdentifier.class);
        for( TileZoneIdentifier tzi : TileZoneIdentifier.values()){
            this.zones.put(tzi,new MahjongTileZone(tzi));
        }
        indexToTile=null;
        tileToZone=null;
    }

    @Override
    public Wind getCurrentWind() throws GameException {
        return this.currentWind;
    }
    
    /**
     * Modifie la valeur currentTile
     * @param newWind Le nouveau vent 
     */
    public void setWind(Wind newWind) {
        this.currentWind = newWind;
        this.propertyChangeSupport.firePropertyChange(CURRENTWIND, null, this.currentWind);
    }
    
    /**
     * Retourne la vision du board du point de vue d'un joueur
     * @param wind Le cote du joueur
     * @return Un board du point de vue du joueur
     * @throws GameException
     */
    Board getViewFromWind(Wind wind) throws GameException{
        // If null is passed we just return this board, another option would be to clone this board.
        if(wind==null){
            return this;
        }
        
        // We create a new empty board
        MahjongBoard retBoard = new MahjongBoard(this.getCurrentWind());
        
        // This computes what tzi correspond to the requested player's hand
        TileZoneIdentifier windHand = TileZoneIdentifier.getIdentifierFromNormalizedName("Hand"+wind.getName());
 
        
        for(Map.Entry<GameTileInterface,TileZone> e : tileToZone.entrySet()){
            GameTile key = (GameTile)e.getKey();
            MahjongTileZone value = (MahjongTileZone)e.getValue();
        
            //Determines the destination in the new board
            MahjongTileZone destination = (MahjongTileZone)retBoard.getTileZone(value.getIdentifier());

            boolean publiclyVisible = key.isPubliclyVisible();

            //If the tile is not publicly visible or in the player's hand we hide it 
            AbstractTile visibleFace =  publiclyVisible ||
                                        value.getIdentifier()==windHand ?
                                        key.getTile() : HiddenTile.HIDDENTILE;
            //Create the copy of the tile to be added into the new board
            GameTile copy = new GameTile(key.getGameID(),
                                        visibleFace,
                                        key.getUUID(),
                                        publiclyVisible, 
                                        key.getOrientation());
            destination.addTile(copy);
        }
        return retBoard;
    }

    @Override
    public UUID getUUID(){
        return this.uuid;
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return this.propertyChangeSupport;
    }

    @Override
    public GameTileInterface getTile(int gameIndex) throws GameException {
        if(indexToTile == null){
            buildReverseSearch();
        }
        GameTileInterface result = indexToTile.get(gameIndex);
        if(result==null){
            throw new GameException("Unable to find tile of gameIndex "+gameIndex);
        }
        return result;
    }

    @Override
    public TileZone getTileZoneOfTile(int gameIndex) throws GameException {
        return this.getTileZoneOfTile(getTile(gameIndex));        
    }

    @Override
    public TileZone getTileZoneOfTile(GameTileInterface tile) throws GameException {
        if(tileToZone == null){
            buildReverseSearch();
        }
        TileZone result = this.tileToZone.get(tile);
        if(result.getTiles().contains(tile)){
            return result;
        }
        //At this point the tile that was asked for is either not present on the Board or the reverse search is broken
        throw new GameException("Tile does not exist within this Board");
    }

    @Override
    public TileZone getTileZone(TileZoneIdentifier identifier) throws GameException{
        TileZone tz = this.zones.get(identifier);
        if(tz == null) throw new GameException("The identifier isn't in the Board");
        return tz;
    }

    @Override
    public TileZone getTileZone(String normalizedName) throws GameException {
        return getTileZone(TileZoneIdentifier.getIdentifierFromNormalizedName(normalizedName));
    }
    
    private void buildReverseSearch() throws GameException{
        this.indexToTile = new HashMap<>();
        this.tileToZone = new HashMap<>();
        for(TileZone t : this.zones.values()){
            for(GameTileInterface tile : t.getTiles()){
                this.indexToTile.put(tile.getGameID(), tile);
                this.tileToZone.put(tile, t);
            }
        } 
    }
    
    
    /**
     * This applies given Move to the Board moving tiles from zones to other ones
     * @param move Move to be applied
     * @throws GameException If the move is incoherent or appears to be forged
     */
    public void applyMove(Move move) throws GameException{
        isMoveCoherent(move);
        for(Entry<Integer, TileZoneIdentifier> t : move.getPath().entrySet()){
            GameTile buf = (GameTile)getTile(t.getKey());
            ((MahjongTileZone)getTileZoneOfTile(buf)).removeTile(buf);
            ((MahjongTileZone)getTileZone(t.getValue())).addTile(buf);
            this.tileToZone.put(buf,getTileZone(t.getValue()));//Updating the reverse search
        }
    }
    
    /**
     * Verifie si le Move passer en paramètre est correct
     * @param move Le move dont on veut vérifier la validité
     * @throws GameException
     */
    private void isMoveCoherent(Move move) throws GameException{
        for(Entry<Integer, TileZoneIdentifier> t : move.getPath().entrySet()){
            if(getTileZoneOfTile(t.getKey())==null){
                throw new GameException("Move "+move.getUUID()+" is trying to move a tile that does not exist");
            }
            if(getTileZoneOfTile(t.getKey()) == getTileZone(t.getValue())){
                throw new GameException("Move "+move.toString()+"\n\tis trying to move a tile back into the same zone");
            }
        }
    }
    

    /**
    * @return L'enummap de tilezones
    */
    public EnumMap<TileZoneIdentifier, TileZone> getZones(){
        return this.zones;
    }
    
}

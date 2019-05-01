package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.Wind;
import fr.univubs.inf1603.mahjong.engine.persistence.Persistable;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 * Cette classe permet de représenter le déplacement d'une ou plusieurs tuiles
 * d'un joueur
 */
public class Move implements Persistable, Serializable, Cloneable {

    private final Wind wind;
    private final int priority;
    private final HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
    private final HashMap<Integer, Boolean> publiclyVisible = new HashMap<>();
    private final UUID uuid;

    /**
     * @param wind
     * @param priority        La priorité du Move par rapport à d'autre Move (-1 est la
     *                        plus grosse priorité (Mahjong), n est la moins grosse priorité)
     * @param path            Une Map qui contient l'information de déplacement de la ou
     *                        des tuile(s). Le numéro reprente l'ID de la tuile, la MahjongTileZone est la
     *                        zone de jeu dans laquelle la tuile va être ajoutée
     * @param publiclyVisible Une map qui contient la visibilité de la ou des
     *                        tuile(s) concernés par le Move. Le numéro représente l'ID de la tuile, le boolean représente
     *                        la visibilité de la tuile : true = tuile visible
     * @param uuid
     */
    public Move(Wind wind, int priority, HashMap<Integer, TileZoneIdentifier> path, HashMap<Integer, Boolean> publiclyVisible, UUID uuid) {
        this.wind = wind;

        if (priority < 0)
            this.priority = -1;
        else
            this.priority = priority;


        if (path != null)
            this.path.putAll(path);


        if (publiclyVisible != null)
            this.publiclyVisible.putAll(publiclyVisible);


        this.uuid = uuid;
    }

    public Move(Wind wind, int priority, HashMap<Integer, TileZoneIdentifier> path, HashMap<Integer, Boolean> publiclyVisible) {
        this(wind, priority, path, publiclyVisible, UUID.randomUUID());
    }

    public Move(Move move) throws MoveException {
        this(move.wind, move.priority, move.path, move.publiclyVisible, move.uuid);
    }

    /**
     * Retourne le coté du joueur associé au Move
     *
     * @return side
     */
    public Wind getWind() {
        return this.wind;
    }

    /**
     * Retourne la priorité du Move
     *
     * @return priority
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Retourne le map symbolisant le déplacement
     *
     * @return path
     */
    public HashMap<Integer, TileZoneIdentifier> getPath() {
        return this.path;
    }

    /**
     * Retourne la map qui represente la visibilité des tuiles
     *
     * @return publiclyVisible
     */
    public HashMap<Integer, Boolean> getPubliclyVisible() {
        return this.publiclyVisible;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }


    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return this.propertyChangeSupport;
    }

    /**
     * Retourne vrai si le move passé en argument est égal à ce move
     *
     * @param move le move à tester
     * @return true si les deux moves sont égaux false sinon
     */
    public boolean isEqual(Move move) {
        boolean ret = false;
        if (move.getWind() == this.wind) {
            if (move.getPriority() == this.priority) {
                if (moveMapEqual(this.path, move.path)) {
                    if (moveMapEqual(this.publiclyVisible, move.publiclyVisible)) {
                        ret = true;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Retourne vrai si les deux map passé en arguments son égaux faux sinon
     *
     * @param map1 la premièere map
     * @param map2 la deuxieme map
     * @return true si les moves sont égaux, false sinon
     */
    private boolean moveMapEqual(HashMap map1, HashMap map2) {

        for (Object k : map2.keySet()) {
            if (!map1.get(k).equals(map2.get(k))) {
                return false;
            }
        }
        for (Object y : map1.keySet()) {
            if (!map2.containsKey(y)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String description = new String();
        for (Integer tile : path.keySet()) {
            description += tile + ":" + path.get(tile);
            description += " ";
        }
        return description;
    }
}

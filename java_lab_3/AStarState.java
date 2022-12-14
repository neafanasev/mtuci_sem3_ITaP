package java_lab_3;

import java.util.HashMap;

public class AStarState
{
    private Map2D map;
    public HashMap<Location, Waypoint> openWaypoints = new HashMap<>();
    public HashMap<Location, Waypoint> closedWaypoints = new HashMap<>();

    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    public Map2D getMap() {
        return map;
    }
    

    public Waypoint getMinOpenWaypoint() {
    	float minCost = Float.MAX_VALUE;
    	Waypoint minWaypoint = null;
    	for (Location key : openWaypoints.keySet()) {
    		Waypoint point = openWaypoints.get(key);
    		float cost = point.getTotalCost();
    		if (cost < minCost) {
    			minCost = cost;
    			minWaypoint = point;
    		}
    	}
        return minWaypoint;
    }
    

    public boolean addOpenWaypoint(Waypoint newWP) {
    	Waypoint oldWP = openWaypoints.get(newWP.loc);
        if (oldWP == null || oldWP.getPreviousCost() > newWP.getPreviousCost()) {
        	openWaypoints.put(newWP.loc, newWP);
        	return true;
        } 
        return false;
    }

    public int numOpenWaypoints() {
        return openWaypoints.size();
    }

    public void closeWaypoint(Location loc){
        closedWaypoints.put(loc, openWaypoints.remove(loc));
    }

    public boolean isLocationClosed(Location loc) {   
        return closedWaypoints.containsKey(loc);
    }
}

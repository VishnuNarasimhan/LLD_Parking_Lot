package factories;

import models.ParkingSpotStrategyType;
import strategies.CheapestSpotAssignmentStrategy;
import strategies.NearestSpotAssignmentStrategy;
import strategies.ParkingSpotAssignmentStrategy;

public class ParkingSpotAssignmentStrategyFactory {
    public static ParkingSpotAssignmentStrategy
    getParkingLotStrategy(ParkingSpotStrategyType parkingSpotStrategyType){
        if(parkingSpotStrategyType.equals(ParkingSpotStrategyType.NEAREST)){
            return new NearestSpotAssignmentStrategy();
        }
        if(parkingSpotStrategyType.equals(ParkingSpotStrategyType.CHEAPEST)){
            return new CheapestSpotAssignmentStrategy();
        }
        return null;
    }
}

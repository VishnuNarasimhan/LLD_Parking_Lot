package strategies;

import models.ParkingLot;
import models.ParkingSpot;
import models.Vehicle;

public class NearestSpotAssignmentStrategy implements ParkingSpotAssignmentStrategy{
    @Override
    public ParkingSpot assignmentParkingSpot(ParkingLot parkingLot, Vehicle vehicle) {
        return new ParkingSpot();
    }
}

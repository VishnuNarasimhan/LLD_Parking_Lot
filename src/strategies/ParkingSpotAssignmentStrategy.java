package strategies;

import models.ParkingLot;
import models.ParkingSpot;
import models.Vehicle;

public interface ParkingSpotAssignmentStrategy {
    ParkingSpot assignmentParkingSpot(ParkingLot parkingLot, Vehicle vehicle);
}

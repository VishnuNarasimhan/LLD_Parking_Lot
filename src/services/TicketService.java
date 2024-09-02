package services;

import exceptions.GateNotFoundException;
import models.Gate;
import models.ParkingLot;
import models.Ticket;
import models.Vehicle;
import repositories.GateRepository;
import repositories.VehicleRepository;
import strategies.ParkingSpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

/*
Service classes should be as general as possible
 */

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy;

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingSpotAssignmentStrategy = parkingSpotAssignmentStrategy;
    }

    public Ticket issueTicket(String vehicleNumber,
                              String ownerName,
                              Long gateId,
                              Long operatorId) throws GateNotFoundException {

        Ticket ticket = new Ticket();

        // For Date
        ticket.setEntryTime(new Date());

        // For Gate
        Optional<Gate> optionalGate = gateRepository.findByGateId(gateId);

        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException("Invalid gate id passed");
        }

        Gate gate = optionalGate.get();
        ticket.setGate(gate);

        // For Operator
        ticket.setOperator(gate.getOperator());

        // For Vehicle
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        Vehicle vehicle = null;
        if (optionalVehicle.isEmpty()) {
            // If Vehicle is not present in the DB.
            // Create a new Vehicle and save in DB.
            vehicle = new Vehicle();
            vehicle.setNumber(vehicleNumber);
            vehicle.setOwnerName(ownerName);
            vehicle = vehicleRepository.save(vehicle);
        } else {
            vehicle = optionalVehicle.get();
        }
        ticket.setVehicle(vehicle);

        // Parking Spot
        ParkingLot parkingLot = gate.getParkingLot();
        ticket.setParkingSpot(parkingSpotAssignmentStrategy.assignmentParkingSpot(parkingLot, vehicle));

        return ticket;
    }
}

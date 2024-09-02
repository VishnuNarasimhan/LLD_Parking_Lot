import controllers.TicketController;
import dtos.IssueTicketRequestDto;
import dtos.IssueTicketResponseDto;
import exceptions.TicketNotGeneratedException;
import factories.ParkingSpotAssignmentStrategyFactory;
import models.ParkingSpotStrategyType;
import models.Ticket;
import repositories.GateRepository;
import repositories.VehicleRepository;
import services.TicketService;
import strategies.ParkingSpotAssignmentStrategy;

public class ParkingLotApplication {
    public static void main(String[] args) throws TicketNotGeneratedException {
        GateRepository gateRepository = new GateRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy =
                ParkingSpotAssignmentStrategyFactory.getParkingLotStrategy(ParkingSpotStrategyType.NEAREST);
        TicketService ticketService = new TicketService(gateRepository,
                                                        vehicleRepository,
                                                        parkingSpotAssignmentStrategy);
        TicketController ticketController = new TicketController(ticketService);

        IssueTicketRequestDto requestDto = new IssueTicketRequestDto();
        requestDto.setGateNumber(123L);
        requestDto.setOperatorId(234L);
        requestDto.setOwnerName("Vishnu");
        requestDto.setVehicleNumber("TN01V0001");

        IssueTicketResponseDto responseDto = ticketController.issueTicket(requestDto);

        Ticket ticket = responseDto.getTicket();

    }
}
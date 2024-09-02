package controllers;

import dtos.IssueTicketRequestDto;
import dtos.IssueTicketResponseDto;
import dtos.ResponseStatus;
import exceptions.TicketNotGeneratedException;
import models.Ticket;
import services.TicketService;

/*
The task of controller is to give the input parameter to the service class.
 */

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto requestDto) throws TicketNotGeneratedException {
        IssueTicketResponseDto responseDto = new IssueTicketResponseDto();

        try {
            Ticket ticket = ticketService.issueTicket(
                    requestDto.getVehicleNumber(),
                    requestDto.getOwnerName(),
                    requestDto.getGateNumber(),
                    requestDto.getOperatorId()
            );
            responseDto.setTicket(ticket);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            throw new TicketNotGeneratedException("Ticket cannot be Generated");
        }
        return responseDto;
    }
}

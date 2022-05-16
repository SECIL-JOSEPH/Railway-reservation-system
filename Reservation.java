package RailwayPackage;
import java.util.*;

import javax.swing.plaf.basic.BasicLookAndFeel;

import RailwayPackage.Passenger.Gender;

public class Reservation 
{     
    static int id = 1;
    HashMap<Integer, Passenger> reservedTicketDetails;
    Queue<Integer> racTicketQueue;
    Queue<Integer> waitingListTicketQueue;
    List<Integer> bookedTicketList;

    public Reservation() 
    {
        reservedTicketDetails = new HashMap<Integer, Passenger>();
        racTicketQueue = new LinkedList<Integer>();
        waitingListTicketQueue = new LinkedList<Integer>();    
        bookedTicketList = new ArrayList<Integer>();   
    }
    
    public void bookTicket(Passenger passenger,Passenger childpassenger,Seat seat)
    {        
        if (Seat.wl == 0)
        {
            System.out.println("No tickets available");
        }
        else
        {                          
            passenger.getTicket().setpnrId(id++);            
            if(!passenger.getIsChild())
            {
                seat.alloteSeat(passenger, childpassenger);
                passenger.getTicket().setseat(seat);                     
            }
            if (seat.getSeatStatus() == Seat.seatStatus.RAC)
            {
                racTicketQueue.add(passenger.getTicket().getpnrId());
            }
            else if (seat.getSeatStatus() == Seat.seatStatus.WaitingList)
            {
                waitingListTicketQueue.add(passenger.getTicket().getpnrId());
            }
            else if (seat.getSeatStatus() == Seat.seatStatus.Lower || seat.getSeatStatus() == Seat.seatStatus.Upper || seat.getSeatStatus() == Seat.seatStatus.Middle)
            {
                bookedTicketList.add(passenger.getTicket().getpnrId());
            }            
            reservedTicketDetails.put(passenger.getTicket().getpnrId(), passenger);
            if(childpassenger.getIsChild())
            {
                childpassenger.getTicket().setpnrId(id++);
                reservedTicketDetails.put(childpassenger.getTicket().getpnrId(), childpassenger);
            }
        }
    }
    
    public void cancelTicket(int Cancel_Id ,Seat seat)
    {       
        Passenger passenger;
        Passenger childPassenger;

        if (bookedTicketList.isEmpty())
        {
            System.out.println("Tickets not yet Booked");
        }
        else if (bookedTicketList.contains(Cancel_Id))
        {
            passenger = reservedTicketDetails.get(Cancel_Id);                        
            if (passenger.getTicket().getseat().getSeatStatus() == Seat.seatStatus.Lower)
            {    
                Seat.lowerBerth++;                
            }
            else if (passenger.getTicket().getseat().getSeatStatus() == Seat.seatStatus.Middle)
            {                
                Seat.middleBerth++;
            }
            else if (passenger.getTicket().getseat().getSeatStatus() == Seat.seatStatus.Upper)
            {                
                Seat.upperberth++;
            }
            System.out.println("Ticket Cancelled Sucessfully");
            if(passenger.getGender()==Gender.Femmale)
            {
                if(reservedTicketDetails.containsKey(Cancel_Id+1))
                {
                    reservedTicketDetails.remove(Cancel_Id+1);            
                }
            }
            reservedTicketDetails.remove(Cancel_Id);            
            bookedTicketList.remove(Cancel_Id-1);
            if (racTicketQueue.size() > 0)
            {
                Cancel_Id = racTicketQueue.remove();                    
                passenger = reservedTicketDetails.get(Cancel_Id);
                childPassenger = reservedTicketDetails.get(Cancel_Id+1);
                bookedTicketList.add(Cancel_Id-1);                
                Seat.rac++;
                seat.alloteSeat(passenger,childPassenger);
                reservedTicketDetails.replace(Cancel_Id, passenger);                
                if (waitingListTicketQueue.size() > 0)
                {
                    Cancel_Id = waitingListTicketQueue.remove();
                    passenger = reservedTicketDetails.get(Cancel_Id);
                    racTicketQueue.add(Cancel_Id);                    
                    Seat.wl++;
                    seat.alloteSeat(passenger,childPassenger);
                    reservedTicketDetails.replace(Cancel_Id, passenger);                    
                }
            }
        }
        else
        {
            System.out.println("Invalid Passenger Id");
        }
    }
    
    public void getBookedTickets()
    {
        if (bookedTicketList.isEmpty())
        {
            
            System.out.println("\n...........Tickets not yet booked");
        }
        else
        {            
            for (Map.Entry<Integer,Passenger> x : reservedTicketDetails.entrySet())
            {                
                Passenger passenger = x.getValue();
                if (passenger.getIsChild())
                {
                    System.out.println("\nChild Name : "+ passenger.getName() +"\nChild Age : "+ passenger.getAge() +"\nChild Gender : "+ passenger.getGender());
                }
                else
                {                    
                    System.out.println("..................");
                    System.out.println("\nPNR Id : "+ x.getKey() +"\nName : "+ passenger.getName() +"\nAge : "+ passenger.getAge() +"\nGender : "+ passenger.getGender() +"\nStatus : "+ (x.getKey()+" - "+passenger.getTicket().getseat().getSeatStatus().toString()));
                }                
            }
            System.out.println("Total number of tickets occupied : "+ (bookedTicketList.size()+racTicketQueue.size()+waitingListTicketQueue.size()));
        }
    }    
}

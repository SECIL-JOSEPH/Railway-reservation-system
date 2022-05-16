package RailwayPackage;
import java.util.*;

public class Seat
{    
    static int lowerBerth = 1;
    static int middleBerth = 0;
    static int upperberth = 0;
    static int rac = 1;
    static int wl = 1;    
    Scanner sc;

    private Berthpreference berthpreference;  
    private seatStatus status;

    public Berthpreference getBerthpreference()
    {
        return berthpreference;
    }
    public void setBerthpreference(char value)
    {        
        if (value == '1')
        {
            this.berthpreference = Seat.Berthpreference.Lower;
        }
        else if( value == '2')
        {
            this.berthpreference = Seat.Berthpreference.Middle;
        }
        else if( value == '3')
        {
            this.berthpreference = Seat.Berthpreference.Upper;
        }
        else
        {
            System.out.println("Please Enter Valid Berth Perference :");
            System.out.println("Lower Berth - L\nMiddle Berth - M\nUpper Berth - U");
            value = sc.next().charAt(0);
            setBerthpreference(value);
        }        
    }
    public seatStatus getSeatStatus()
    {
        return status;
    }    
    public enum Berthpreference
    {               
        Lower,
        Middle,
        Upper;                 
    }
    public void assignSeats(int totalSeats)
    {
        this.lowerBerth = totalSeats/3;
        this.middleBerth = totalSeats/3;
        this.upperberth = totalSeats/3;
        if(totalSeats<4)
        {
            this.rac = 1;
            this.wl = 1;            
        }        
        else if(totalSeats<10)
        {
            this.rac = 1;            
            this.wl = (totalSeats*25)/100;
        }
        else
        {
            this.rac = (totalSeats*10)/100;
            this.wl = (totalSeats*25)/100;
        }
    }

    public void getAvailableSeats()
    {
        
        System.out.println("\n...........................");
        System.out.println("Available Lower berth : " + lowerBerth);
        System.out.println("Available MIddle berth : " + middleBerth);
        System.out.println("Available Upper berth : " + upperberth);
        System.out.println("Available RAC : " + rac);
        System.out.println("Available Waiting list : " + wl);            
        System.out.println("Total number of tickets unoccupied : " + (lowerBerth+ middleBerth + upperberth + rac + wl));
        System.out.println("...........................");
    }

    public void alloteSeat(Passenger passenger,Passenger childpassenger)
    {
        if ((passenger.getAge() >= 60 || childpassenger.getIsChild()) && passenger.getTicket().getseat().getBerthpreference()!= Berthpreference.Lower && lowerBerth > 0)
        {
            lowerBerth--;
            this.status = seatStatus.Lower;
            if (passenger.getAge() >= 60)
            {                
                System.out.println("Your are senior citizen so Lower berth given for your convienence");
            }
            else
            {
                System.out.println("You have child so Lower berth given for your convienence");
            }
            System.out.println(".......Lower berth booked successfully.......");            
        }
        else if ((this.berthpreference == Berthpreference.Lower && lowerBerth > 0) || (this.berthpreference == Berthpreference.Middle && middleBerth > 0) || (this.berthpreference == Berthpreference.Upper && upperberth > 0))
        {
            if (this.berthpreference == Berthpreference.Lower)
            {
                lowerBerth--;
                this.status = seatStatus.Lower;
                System.out.println("Prefered berth given");                
                System.out.println(".......Lower berth booked for "+passenger.getName()+".......");                
            }
            else if (this.berthpreference == Berthpreference.Middle)
            {
                middleBerth--;
                this.status = seatStatus.Middle;
                System.out.println("Prefered berth given");
                System.out.println(".......Middle berth booked for "+passenger.getName()+".......");
            }
            else if (this.berthpreference == Berthpreference.Upper)
            {
                upperberth--;
                this.status = seatStatus.Upper;
                System.out.println("Prefered berth given");
                System.out.println(".......Upper berth booked for "+passenger.getName()+".......");
            }
        }
        else if (lowerBerth > 0)
        {
            lowerBerth--;
            this.status = seatStatus.Lower;
            System.out.println("Prefered berth is not available");
            System.out.println(".......Lower berth booked for "+passenger.getName()+".......");
        }
        else if (middleBerth > 0)
        {
            middleBerth--;
            this.status = seatStatus.Middle;
            System.out.println("Prefered berth is not available");
            System.out.println(".......Middle berth booked for "+passenger.getName()+".......");
        }
        else if (upperberth > 0)
        {
            upperberth--;
            this.status = seatStatus.Upper;
            System.out.println("Prefered berth is not available");
            System.out.println(".......Upper berth booked for "+passenger.getName()+".......");
        }
        else if (rac > 0)
        {
            rac--;
            this.status = seatStatus.RAC;
            System.out.println("......."+passenger.getName()+" Added to RAC successfully.......");
        }
        else if (wl > 0)
        {
            wl--;
            this.status = seatStatus.WaitingList;
            System.out.println("......."+passenger.getName()+" Added to Waiting list successfully.......");
        }
    }

    public enum seatStatus
    {               
        Lower,
        Middle,
        Upper,
        RAC,
        WaitingList;        
    }

}
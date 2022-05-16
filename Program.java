import java.util.*;
import RailwayPackage.*;

public class Program
{
    public static void main(String[] args) {
                    
            Reservation reservation = new Reservation();
            Admin adm = new Admin();
            Seat seat =new Seat();
            Scanner sc = new Scanner(System.in);
            System.out.println("\nWelcome to Indian Railways Reservation System");
            boolean exit = false;
            while(exit == false)
            {                
                System.out.println("\n1. Admin\n2. Passenger\n");
                int choice = Getinteger("Enter Your Option", 1, 2);
                switch(choice)
                {
                    case 1:
                    {                        
                        System.out.println("Username :");
                        String username = sc.next();
                        System.out.println("Password :");
                        int  password = sc.nextInt();
                        if(adm.isAdmin(username, password))
                        {
                            System.out.println("Enter number of total tickets");
                            seat.assignSeats(sc.nextInt());
                        }
                        else
                        {
                            System.out.println("Invalid Username and Password");
                        }
                        break;
                    }
                    case 2:
                    {
                        exit = true;
                    }   
                }
            }
            exit = false;
            while (exit == false)
            {
                System.out.println("\n1. Book Ticket\n2. Cancel Ticket\n3. Available Tickets\n4. Booked Tickets\n5. Exit\n");
                int choice = Getinteger("Enter Your Option", 1, 5);                
                Passenger passenger = new Passenger();
                Passenger childPassenger = new Passenger();
                Ticket ticket  = new Ticket();
                switch (choice)
                {
                    case 1:
                        {                            
                            System.out.println("Enter Your Name :");
                            passenger.setName(sc.next());
                            System.out.println("Enter your Age :");
                            passenger.setAge(sc.nextInt());
                            System.out.println("Enter Valid Gender :");
                            System.out.println("Male - 1\nFemMale - 2");
                            passenger.setGender(sc.next().charAt(0));
                            if (passenger.getGender()== RailwayPackage.Passenger.Gender.Femmale)
                            {
                                System.out.println("If you have child below age 5 (Press 1) :");
                                System.out.println("If you are unmarried or you dont have child below age 5 (Press 2) :");
                                choice = sc.nextInt();                                
                                if (choice == 1)
                                {
                                    Ticket childTicket  = new Ticket();
                                    childPassenger.setIsChild(true);
                                    System.out.println("Enter Your Child Name :");
                                    childPassenger.setName(sc.next());
                                    System.out.println("Enter your Child Age :");
                                    childPassenger.setAge(sc.nextInt());                                    
                                    System.out.println("Enter Valid Gender :");
                                    System.out.println("Male - 1\nFemMale - 2");
                                    childPassenger.setGender(sc.next().charAt(0));                                    
                                    childPassenger.setTicket(childTicket);
                                }
                            }
                            seat = new Seat();
                            System.out.println("Enter You Berth Perfernce :");
                            System.out.println("Lower Berth - 1\nMiddle Berth - 2\nUpper Berth - 3");
                            seat.setBerthpreference(sc.next().charAt(0));
                            passenger.setTicket(ticket);
                            passenger.getTicket().setseat(seat);
                            reservation.bookTicket(passenger,childPassenger,seat);
                            break;
                        }
                    case 2:
                        {
                            System.out.println("Enter your PNR ID");
                            int Cancel_Id = sc.nextInt();
                            reservation.cancelTicket(Cancel_Id, seat);                            
                            break;
                        }
                    case 3:
                        {
                            seat.getAvailableSeats();
                            break;
                        }
                    case 4:
                        {
                            reservation.getBookedTickets();                            
                            break;
                        }
                    case 5:
                        {
                            exit = true;
                            sc.close();
                            break;
                        }
                }
            }
        }                
        public static int Getinteger(String message, int minimum, int maximum)
        {
            System.out.println(message + " : (Please Enter a number between "+ minimum +" and " + maximum);
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            if (i >= minimum && i <= maximum)
            {                
                return i;
            }
            else
            {
                System.out.println("Enter the valid number between "+ minimum +" and " + maximum);
                Getinteger(message, minimum, maximum);
            }            
            return i;            
        }        
}
package RailwayPackage;
import java.util.*;

public class Passenger 
{    
    private String name;
    private int age;
    private Gender gender;    
    private boolean ischild;
    private Ticket ticket;

    Scanner sc;
    
    public Passenger() 
    {
        this.sc = new Scanner(System.in);
    }                      

    public String getName()
    {
        return name;
    }

    public void setName(String value)
    {
        if (isName(value))
        {            
            this.name = value;                
        }
        else
        {
            System.out.println("Please Enter Your Name");
            value = sc.next();
            setName(value);
        }        
    }

    public int getAge()
    {        
        return age;
    }   

    public void setAge(int value)
    {
        if(ischild)
        {
            if (value >= 1 && value <= 5)
            {
                this.age = value;                
            }
            else
            {
                System.out.println("Please Enter Valid Child Age : (Age Must Between 1 to 5)");
                value = sc.nextInt();
                setAge(value);
            }
        }
        else
        {
            if (value >= 1 && value <= 100)
            {
                this.age = value;                
            }
            else
            {
                System.out.println("Please Enter Valid Age : (Age Must Between 1 to 100)");
                value = sc.nextInt();
                setAge(value);
            }
        }
    }             

    public Gender getGender()
    {        
        return gender;
    }

    public void setGender(char value)
    {
        
        if (value == '1')
        {            
            this.gender = Gender.Male;
        }
        else if( value == '2') 
        {
            this.gender = Gender.Femmale;
        }
        else
        {
            System.out.println("Please Enter Valid Gender :");
            System.out.println("Male - M\nFemMale - F");
            value = sc.next().charAt(0);
            setGender(value);
        }        
    }

    public boolean getIsChild()
    {        
        return ischild;
    }

    public void setIsChild(Boolean value)
    {        
        this.ischild = value;        
    }

    public Ticket getTicket()
    {        
        return ticket;
    }

    public void setTicket(Ticket value)
    {
        this.ticket=value;
    }
        
    private boolean isName(String value)
    {
        boolean name =false;
        for(int i=0;i<value.length();i++)
        {
            if((value.charAt(i)>=65 &&  value.charAt(i)<=90) || (value.charAt(i)>=97 &&  value.charAt(i)<=122))
            {
                name=true;
            }
            else
            {
                name=false;
                break;
            }
        }
        return name;
    }
    
    public enum Gender
    {               
        Male,
        Femmale; 
    }
}
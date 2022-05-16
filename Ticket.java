package RailwayPackage;

public class Ticket {
        
    private int pnrId;
    private Seat seat;

    public int getpnrId()
    {
        return pnrId;
    }
    public void setpnrId(int value)
    {
        this.pnrId = value;
    }
    public Seat getseat()
    {
        return seat;
    }
    public void setseat(Seat value)
    {
        this.seat = value;
    }        
}

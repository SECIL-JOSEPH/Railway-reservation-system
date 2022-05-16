package RailwayPackage;
public class Admin 
{ 
    String admin = "secil";
    int password = 123;
    
    public boolean isAdmin(String username, int password)
    {
        boolean verify = false;
        if(this.admin.equals(username))
        {            
            if(this.password == password)
            {
                verify = true;
            }
        }
        return verify;
    }
}

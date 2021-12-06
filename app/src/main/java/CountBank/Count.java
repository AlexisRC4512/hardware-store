package CountBank;

public class Count  {
    private String user,fullName;
    private String pass;
    private double monto=0;
    public Count(){};
    public Count(String user,String pass){
        this.user=user;
        this.pass=pass;
    }
    public Count(String user,String pass,String fullName){
        this.user=user;
        this.pass=pass;
        this.fullName=fullName;

    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }
}



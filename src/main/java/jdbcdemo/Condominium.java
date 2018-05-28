
package jdbcdemo;

import java.sql.Date;


public class Condominium {
    
    private int Aptno;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private Date purchaseDate;
    private Date sellDate;

         
    public int getAptno() {
        return Aptno;
    }

    public void setAptno(int Aptno) {
        this.Aptno = Aptno;
    }

    public String getAddress() {
        return address;
    }

   public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

   public void setCity(String city) {
        this.city = city;
    }

   public String getState() {
        return state;
    }

   public void setState(String state) {
        this.state = state;
    }

   public String getZipcode() {
        return zipcode;
    }

   public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

   public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }
    
    
    public Condominium () {};
    
    public Condominium (int Aptno, String address, String city, String state)
    
    {
    setAptno(Aptno);
    setAddress(address);
    setCity(city);
    setState(state);    
    }
    
}

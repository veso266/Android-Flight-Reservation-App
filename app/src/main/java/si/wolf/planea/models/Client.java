/*************************************************************************************************
 * JANUARY 8, 2018
 * Mentesnot Aboset
  ************************************************************************************************/
package si.wolf.planea.models;


import android.graphics.Bitmap;

import java.io.Serializable;

public class Client implements Serializable {

    private String clientID;
    private String firstName;
    private String lastName;
    private String phone;
    private String creditCard;
    private String birthday;
    private Bitmap image;

    public Client(){

    }

    public Client(String firstName, String lastName, String phone, String creditCard, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.creditCard = creditCard;
        this.birthday = birthday;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getBDay() {
        return birthday;
    }

    public void setBDay(String BDay) {
        this.creditCard = BDay;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
        //return getFirstName() + " " + getLastName() + " | " + getBDay();
    }

}

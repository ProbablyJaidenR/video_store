public class Customer implements Comparable<Customer>
{
    String firstName;
    String lastName;
    String phoneNumber;
    Video[] videoList;
    public Customer left;
    public Customer right;

    public Customer(String firstName, String lastName, String phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.videoList = new Video[3];
    }
    public int compareTo(Customer other)
    {
        if(this.phoneNumber.compareTo(other.phoneNumber) < 0) return -1;
        else return 1;
    }

    public String toString()
    {
        return firstName + " " + lastName + " |Phone Number: " + phoneNumber + "|";
    }
    
}
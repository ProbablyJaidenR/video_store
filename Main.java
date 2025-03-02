/*
Your Name: Jaiden Roman
Pace Email: JR79854N@pace.edu
*/
import java.util.Scanner;
public class Main 
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        VideoList inventory = new VideoList();
        RentList rented = new RentList();
        CustomerList customers = new CustomerList();
        
        inventory.insert("The Okay Movie", "675937584637");
        inventory.insert("The Amazing Movie", "123456789012");
        inventory.insert("The Worst Movie in Existence", "210987654321");
        inventory.insert("A Movie", "268467895371");

        customers.insert("Bob", "Whilikers", "109209309");
        customers.insert("Phil", "Bill", "205205205");
        customers.insert("Alexa", "AI", "923456789");
        customers.insert("Luigi", "Mario", "92955MARIO");

        System.out.println("Welcome! What do you wish to do? Enter q to quit. Enter c for commands: ");
        String input = scan.nextLine();

        while(!input.equalsIgnoreCase("q"))
        {
            if(input.equalsIgnoreCase("displayrented"))
            {
                rented.printInOrder();
            }
            if(input.equalsIgnoreCase("displayinventory"))
            {
                inventory.printInOrder();
            }
            if(input.equalsIgnoreCase("displaycustomers"))
            { 
                customers.printInOrder();
            }
            if(input.equalsIgnoreCase("c"))
            {
                System.out.println("To add a customer, type newcustomer. To add a video, type newvideo. To check the status of a rented video. type rentstatus. To make a rent, type rent. To make a return, type return. To display: displayrented, displayinventory, displaycustomers");
            }

            //O(n)
            if(input.equalsIgnoreCase("rentstatus"))
            {
                System.out.println("Enter the name of the video you would like to check the status of: ");
                String name = scan.nextLine();
                Video m = rented.findByName(name);
                if(m == null) System.out.println("This video is not currently rented");
                else
                {
                    System.out.println(m.renter.firstName + " " +m.renter.lastName+" has " + m.name + " rented. Their phone number is: " + m.renter.phoneNumber);
                }
            }

            //O(log(n))
            if(input.equalsIgnoreCase("return"))
            {
                System.out.println("Please enter the phone number of the customer: ");
                String phoneNumber = scan.nextLine();
                Customer c = customers.find(phoneNumber);
                if(c == null) System.out.println("The customer cannot be found.");
                else
                {
                    System.out.println("Please enter the barcode of the movie to be returned: ");
                    String barcode = scan.nextLine();
                    Video m = rented.find(barcode);
                    if(m == null) System.out.println("The video cannot be found.");
                    else
                    {
                        boolean there = false;

                        for (int i = 0; i <3; i++) 
                        {
                            if(c.videoList[i] == null);
                            else if(c.videoList[i].equals(m))
                            {
                                c.videoList[i] = null;
                                there = true;
                            }
                        }    
                        
                        
                        if(there == true)
                        {
                            inventory.insert(m.name, m.barcode);
                            rented.remove(m.name, m.barcode);
                            System.out.println(m.name + " successfully returned.");
                        } 
                        else
                        {
                            System.out.println("The customer does not have this movie rented");
                        }
                    }
                }
            }

            //O(log(n))
            if(input.equalsIgnoreCase("newvideo"))
            {
                System.out.println("Please enter the video's name: ");
                String name = scan.nextLine();
                String barcode = "placeholder";
                System.out.println("Please enter the video's barcode");
                barcode = scan.nextLine();
                while(barcode.length() != 12)
                {
                    System.out.println("Invalid barcode (must be 12 characters long)");
                    barcode = scan.nextLine();
                }
                
                inventory.insert(name, barcode);
                System.out.println(name + " successfully added.");
            }

            //O(log(n))
            if(input.equalsIgnoreCase("newcustomer"))
            {
                System.out.println("Please enter the customer's first name: ");
                String firstName = scan.nextLine();
                System.out.println("Please enter the customer's last name: ");
                String lastName = scan.nextLine();
                System.out.println("Please enter the customer's phone number: ");
                String phoneNumber = scan.nextLine();
                customers.insert(firstName, lastName, phoneNumber);
                System.out.println(firstName + " successfully added.");
            }
            //O(log(n))
            if(input.equalsIgnoreCase("rent"))
            {
                System.out.println("Please enter the barcode of the movie you wish to rent: ");
                input = scan.nextLine();
                Video m = inventory.find(input);
                
                if(m == null) System.out.println("This barcode cannot be found");
                else
                {
                    System.out.println("Which customer wishes to rent " + m.name + "? Please enter their phone number.");
                    input = scan.nextLine();
                    Customer c = customers.find(input);
                    if(c==null) System.out.println("This customer cannot be found");
                    else
                    {
                        if(c.videoList[2] != null && c.videoList[1] != null && c.videoList[0] !=null) System.out.println(c.firstName + " has too many videos rented");
                        else
                        {
                            rented.insert(m.name, m.barcode, c);
                            if(c.videoList[0] == null) c.videoList[0] = m;
                            else if(c.videoList[1] == null) c.videoList[1] = m;
                            else if(c.videoList[2] == null) c.videoList[2] = m;
                            inventory.remove(m.name, m.barcode);
                            System.out.println(m.name + " successfully rented.");
                        }
                    }
                }

            }
            System.out.println("Is there anything else you would like to do? Enter q to quit. Enter c for commands: ");
            input = scan.nextLine();
        }



        
    }
    

    
    
}

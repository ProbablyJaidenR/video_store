public class CustomerList 
{
    public Customer root;

    public CustomerList()
    {
        root = null;
    }

    //O(1)
    public boolean empty()
    {
        return(root == null);
    }

    //O(log(n))
    public void insert(String name, String lastName, String phoneNumber)
    {
        Customer n = new Customer(name, lastName, phoneNumber);

        if(root==null)
        {
            root = n;
            return;
        }

        Customer current = root;

        while(true)
        {
            if(n.compareTo(current) < 0)
            {
                if(current.left ==null)
                {
                    current.left = n;
                    break;
                }
                else
                {
                    current = current.left;
                }
            }
            else 
            {
                if(current.right == null)
                {
                    current.right = n;
                    break;
                }
                else
                {
                    current = current.right;
                }
            }

        }
    }
    
    //O(log(n))
    public Customer find(String phone)
    {
        if(root == null) return null;

        Customer current = root;

        while(current != null)
        {
            if(current.phoneNumber.equalsIgnoreCase(phone)) return current;

            if(current.phoneNumber.compareTo(phone) > 0)
            {
                current = current.left;
            }
            else
            {
                current = current.right;
            }
        }
        return null;
    }
    
    //findMin O(log(n))
    public Customer findMin()
    {
        if(root == null) return null;
        return findMin(root);
    }

    public Customer findMin(Customer n)
    {
        if(n.left == null) return n;
        return findMin(n.left);
    }

    //printInOrder O(n)
    public void printInOrder()
    {
        printInOrder(root);
    }

    void printInOrder(Customer n)
    {
        if(n == null) return;

        printInOrder(n.left);
        System.out.println(n);
        printInOrder(n.right);

    }

    //remove O(log(n))
    public void remove(String firstName, String lastName, String phoneNumber)
    {
        Customer n = new Customer(firstName,lastName,phoneNumber);

        this.root = remove(this.root, n);
    }

    public Customer remove(Customer current, Customer n)
    {
        if(current == null) return null;

        if(n.phoneNumber.compareTo(current.phoneNumber) < 0)
        {
            current.left = remove(current.left, n);
        }
        else if(n.phoneNumber.compareTo(current.phoneNumber) > 0)
        {
            current.right = remove(current.right, n);
        }
        else
        {
            if(current.left == null && current.right == null)
            {
                current = null;
            }
            else if(current.left != null && current.right ==null)
            {
                current = current.left;
            }
            else if(current.left == null && current.right != null)
            {
                current = current.right;
            }
            else
            {
                Customer min = findMin(current.right);

                current.firstName = min.firstName;
                current.lastName = min.lastName;
                current.phoneNumber = min.phoneNumber;
                current.videoList = min.videoList;

                current.right = remove(current.right, min);
            }
        }
        return current;
    }
}

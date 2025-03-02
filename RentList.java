public class RentList 
{
    public Video root;

    public RentList()
    {
        root = null;
    }

    //O(1)
    public boolean empty()
    {
        return(root == null);
    }

    //O(log(n))
    public void insert(String name, String barcode, Customer renter)
    {
        Video n = new Video(name, barcode, renter);

        if(root==null)
        {
            root = n;
            return;
        }

        Video current = root;

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

    //O(n)
    public Video findByName(String name)
    {
        if(root == null) return null;
        if(root.name.equals(name)) return root;

        Queue q = new Queue();

        q.enqueue(root);

        while(!q.empty())
        {
            Video n = q.dequeue();
            if(n.name.equals(name)) return n;

            if(n.left != null) q.enqueue(n.left);
            if(n.right != null) q.enqueue(n.right);
        }
        return null;

    }

    //O(log(n))
    public Video find(String barcode)
    {
        if(root == null) return null;

        Video current = root;

        while(current != null)
        {
            if(current.barcode.equalsIgnoreCase(barcode)) return current;

            if(current.barcode.compareTo(barcode) > 0)
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
    public Video findMin()
    {
        if(root == null) return null;
        return findMin(root);
    }

    public Video findMin(Video n)
    {
        if(n.left == null) return n;
        return findMin(n.left);
    }

    //printInOrder O(n)
    public void printInOrder()
    {
        printInOrder(root);
    }

    void printInOrder(Video n)
    {
        if(n == null) return;

        printInOrder(n.left);
        System.out.println(n);
        printInOrder(n.right);
    }

    //remove O(log(n))
    public void remove(String name, String barcode)
    {
        Video n = new Video(name,barcode);

        this.root = remove(this.root, n);
    }

    public Video remove(Video current, Video n)
    {
        if(current == null) return null;

        if(n.barcode.compareTo(current.barcode) < 0)
        {
            current.left = remove(current.left, n);
        }
        else if(n.barcode.compareTo(current.barcode) > 0)
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
                Video min = findMin(current.right);

                current.name = min.name;
                current.barcode = min.barcode;
                current.renter = min.renter;

                current.right = remove(current.right, min);
            }
        }
        return current;
    }
}

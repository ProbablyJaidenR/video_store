public class Video implements Comparable<Video>
{
    String name;
    String barcode;
    Customer renter;
    public Video left;
    public Video right;
    public Video next;

    public Video(String name, String barcode)
    {
        this.name = name;
        this.barcode = barcode;
        this.renter = null;
        left = null;
        right = null;
    }
    public Video(String name, String barcode, Customer renter)
    {
        this.name = name;
        this.barcode = barcode;
        this.renter = renter;
        left = null;
        right = null;
    }

    public int compareTo(Video other)
    {
        if(this.barcode.compareTo(other.barcode) < 0) return -1;
        else return 1;
    }

    public boolean equals(Video other)
    {
        boolean equal = false;

        if(name.equals(other.name))
        {
            if(barcode.equals(other.barcode))
            {
                equal = true;
            }
        }
        return equal;
    }
    
    public String toString()
    {
        return name + " |Barcode: " + barcode + "|";
    }
    
}

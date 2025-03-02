
public class Queue 
{
    Video head;
    
    Video tail;

    public Queue()
    {
        head = null;
        tail = null;
    }
    //O(1)
    public boolean empty()
    {
        return (head==null);
    }
    //O(1)
    public void enqueue(Video n)
    {

        if(head==null)
        {
            head = n;
            tail = n;
            return;
        }

        tail.next = n;
        tail = n;
    }
    //O(1)
    public Video dequeue()
    {
        if(head==null) return null;

        Video n = head;
        head = head.next;

        if(head==null) tail = null;
        return n;
    }
}

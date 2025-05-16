import java.util.ArrayList;
public class BinaryHeap
{
    private Message[] a;
    private int n;

    private void swim(int k) 
    {
        while (k > 1 && a[k/2].compareTo(a[k])<0) 
        {
            Message temp = a[k];
            a[k] = a[k/2];
            a[k/2] = temp;
            k = k/2;
        }
    }

    public void resize(int newLength)
    {
        Message[] temp = new Message[newLength];
        for (int i = 1; i < n; i++)
        {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void insert(Message x) 
    {
        if (n >= a.length - 1)
        {
            this.resize(2 * a.length);
        }
        a[++n] = x;
        swim(n);
    }

    private void sink(int k) 
    {
        while (2*k <= n) 
        {
            int j = 2*k;
            if (j < n && a[j].compareTo(a[j+1])<0)
            {
                j++;
            }
            
            if (a[k].compareTo(a[j])>=0)
            {
                break;
            }

            Message temp = a[k];
            a[k] = a[j];
            a[j] = temp;
            k = j;
        }
    }

    public Message deleteMax() 
    {
        Message max = a[1];
        a[1] = a[n--];
        sink(1);
        return max;
    }

    public ArrayList<Message> getMostRecent(int num)
    {
        ArrayList<Message> recents = new ArrayList<Message>();
        if (!(num < 1) || a.length == 0)
        {
            if (num >= a.length)
            {
                num = a.length - 1;
            }
            for (int i = 0; i < num; i++)
            {
                recents.add(a[i]);
            }
        }
        return recents;
    }

    public static void main(String[] args) 
    {
       InstagramDMLoader test = new InstagramDMLoader(("/Users/caoilainnchristensen/Documents/CS62 Final Project Data"));
       BinaryHeap testHeap = new BinaryHeap();

       for(Message message : test.loadMessagesFromParticipant("leobutdumber"))
       {
            testHeap.insert(message);
       }

    }
}

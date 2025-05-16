import java.util.ArrayList;
public class BinaryHeapDate
{
    private Message[] a;
    private int n;

    public BinaryHeapDate()
    {
        a = new Message[10];
        n = 0;
    }

    private void swim(int k) 
    {
        while (k > 1 && a[k/2].compareToDate(a[k])<0) 
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
        for (int i = 1; i <= n; i++)
        {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void insert(Message x) 
    {
        if (n == a.length - 1)
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
            if (j < n && a[j].compareToDate(a[j+1])<0)
            {
                j++;
            }
            
            if (a[k].compareToDate(a[j])>=0)
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
        if ( !(num < 1 || n == 0))
        {
            if (num >= n)
            {
                num = n - 1;
            }
            for (int i = 1; i < num; i++)
            {
                recents.add(a[i]);
            }
        }
        return recents;
    }
}

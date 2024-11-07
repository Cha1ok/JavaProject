package ind4;

import java.util.ArrayList;
import java.util.Comparator;

public class StuList
{
    private ArrayList<Student> s;

    public ArrayList<Student> getS() { return s; }
    public void setS(ArrayList<Student> s) { this.s = s; }

    public void add(Student a)
    {
        if (s == null) s = new ArrayList<Student>();
        s.add(a);
    }

    public void del(String fio)
    {
        for(int i=0;i<s.size();++i)
            if(fio.equalsIgnoreCase(s.get(i).getFio())) s.remove(s.get(i));
    }

    public String toString()
    {
        String st = "";
        for(Student i : s)
            st += i.toString();
        return st;
    }

    void sortFio()
    {
        Comparator<Student> c = new Comparator<Student>()
        {
            @Override
            public int compare(Student o1, Student o2) { return o1.getFio().compareTo(o2.getFio()); }
        };
        s.sort(c);
    }
}

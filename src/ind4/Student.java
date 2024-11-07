package ind4;

public class Student
{
    private String fio;
    private int kurs;
    private int[] marks;

    public Student(String fio, int kurs, int[] marks)
    {
        this.fio = fio;
        this.kurs = kurs;
        this.marks = marks;
    }

    public String getFio() { return fio; }
    public int getKurs() { return kurs; }
    public int[] getMarks() { return marks; }

    public void setFio(String fio) { this.fio = fio; }
    public void setKurs(int kurs) { this.kurs = kurs; }
    public void setMarks(int[] marks) { this.marks = marks; }

    @Override
    public String toString()
    {
        String s = "ФИО: " + fio + ", Курс: " + kurs + ", Оценки: ";
        int n = marks.length;
        for(int i=0;i<n-1;++i)
            s += marks[i] + " ";
        s += marks[n-1] + "\n";
        return s;
    }
}

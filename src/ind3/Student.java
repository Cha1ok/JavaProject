package ind3;

import java.util.Comparator;
public class Student  {
    private String fio;
    private int kurs;

    Student(String fio, int kurs) {
        this.fio = fio;
        this.kurs = kurs;
    }

    String getFio() {
        return fio;
    }

    int getKurs() {
        return kurs;
    }
public static Comparator<Student> NameComparator=new Comparator<Student>() {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getFio().compareTo(o2.getFio());
    }
};
    public static Comparator<Student> KursComparator=new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getKurs()-(o2.getKurs());
        }
    };

    public String toString(){
        return "Студент "+fio+"\n Класс "+kurs;
    }
}


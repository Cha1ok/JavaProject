package ind3;

import java.util.ArrayList;

public class School {
    ArrayList<Student> a;

    School() {
        a = new ArrayList<Student>();
    }

    void addStudent(Student s) {
        a.add(s);
    }

    void dell(String fio) {
        int p = -1;
        for (int i = 0; i < a.size(); i++) {
            if (fio.equalsIgnoreCase(a.get(i).getFio()))
                p = i;
        }
        if (p > -1) {
            a.remove(p);
            System.out.println("Удалили студента" + fio);
        } else {
            System.out.println("Такого студента нет");
        }
    }

    void print() {
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i).toString());
        }
    }

    void sortByKurs() {
        a.sort(Student.KursComparator);
    }

    void sortByFio() {
        a.sort(Student.NameComparator);
    }

    void searchByName(String fio) {
        for (int i = 0; i < a.size(); i++) {
            if (fio.equalsIgnoreCase(a.get(i).getFio())) {
                System.out.println(a.get(i).toString());
            }
        }
    }
}

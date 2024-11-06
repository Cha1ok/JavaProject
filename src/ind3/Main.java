package ind3;
import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Scanner ss=new Scanner(System.in);
        School school=new School();
        System.out.println("Введите кол-во студентов");
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            System.out.println("Введите имя студента");
            String fio=ss.nextLine();
            System.out.println("Введите класс студента");
            int kurs=sc.nextInt();
            Student student=new Student(fio,kurs);
            school.addStudent(student);
        }
        System.out.println("Добавить нового студента? 1-да 2-нет");
        int p1=sc.nextInt();
        while(p1!=2){
            System.out.println("Введите имя студента");
            String fio=ss.nextLine();
            System.out.println("Введите класс студента");
            int kurs=sc.nextInt();
            Student student=new Student(fio,kurs);
            school.addStudent(student);
            System.out.println("Добавить ешё одного студента? 1-да 2-нет");
            p1=sc.nextInt();
        }
        System.out.println("Какая сортировка? 1-по крусу 2 по имени");
        int p=sc.nextInt();
        if(p==1){school.sortByKurs();}
        if(p==2){school.sortByFio();}
        System.out.println("Найти студента по имени");
        String f1=ss.nextLine();
        school.searchByName(f1);
        System.out.println("Введите имя кого удалить?");
        String f=ss.nextLine();
        school.dell(f);
        school.print();
    }
}

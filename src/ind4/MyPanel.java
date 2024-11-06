package ind4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel
{
    private JLabel l1, l2, l3, l4;
    private JTextField t1, t2, t3, t4;
    private JButton b1, b2, b3;
    private JTextArea ta;
    private StuList sl;

    MyPanel()
    {
        l1 = new JLabel("ФИО");
        l2 = new JLabel("Курс");
        l3 = new JLabel("Оценки");
        l4 = new JLabel("ФИО");
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t1.setColumns(15);
        t2.setColumns(7);
        t3.setColumns(7);
        t4.setColumns(15);
        b1 = new JButton("Добавить");
        b2 = new JButton("Удалить");
        b3 = new JButton("Сортировать");
        ta = new JTextArea();
        ta.setColumns(30);
        ta.setRows(15);

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);

        add(b1);
        add(ta);

        add(l4);
        add(t4);
        add(b2);

        add(b3);

        sl = new StuList();
        MyListener1 m1 = new MyListener1();
        b1.addActionListener(m1);
        MyListener2 m2 = new MyListener2();
        b2.addActionListener(m2);
        MyListener3 m3 = new MyListener3();
        b3.addActionListener(m3);
    }

    class MyListener1 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String fio = t1.getText();
            int kurs = Integer.parseInt(t2.getText());
            String s = t3.getText();
            String[] ocenki = s.split(" ");
            int n = ocenki.length;
            int[] marks = new int[n];
            for(int i=0;i<n;++i)
                marks[i] = Integer.parseInt(ocenki[i]);
            Student st = new Student(fio, kurs, marks);
            sl.add(st);
            t1.setText("");
            t2.setText("");
            t3.setText("");
            ta.setText(sl.toString());
        }
    }

    class MyListener2 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String fio = t4.getText();
            sl.del(fio);
            ta.setText(sl.toString());
            t4.setText("");
        }
    }

    class MyListener3 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            sl.sortFio();
            ta.setText(sl.toString());
        }
    }
}

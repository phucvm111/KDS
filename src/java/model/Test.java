/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Admin
 */
public class Test {

    static PrintColor pc = new PrintColor();

    private static String firstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance(new Locale("us"));
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String d = sdf.format(calendar.getTime());
        return d;
    }

    public String getDateTimeNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String output = dtf.format(now);
        return output;
    }

    public void printLog(String s, double time) {
        if (time < 50) {
            pc.show(pc.green(s));
        } else if (time < 100) {
            pc.show(pc.blue(s));
        } else if (time < 200) {
            pc.show(pc.cyan(s));
        } else if (time < 300) {
            pc.show(pc.magenta(s));
        } else {
            pc.show(pc.red(s));
        }
    }

    public void writeFile(String location, String content) throws FileNotFoundException, IOException {
//        PrintStream console = System.out;
        System.out.println("Run");
        File file = new File(location);
        if (!file.exists()) {
            System.out.println("no");
        }
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
        System.out.println(content);

    }

    public void savetoFile(String file, String content) {
        PrintWriter out;
        try {
            out = new PrintWriter(file);
            out.write("Hello" + "\n");
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.out.println("Not exist");
        }
        System.out.println("Exist");
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        LocalDate local = LocalDate.now();
        Test t = new Test();
////        String date = local.toString();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        String date = sdf.format(new Date());
//        System.out.println(date);
//        System.out.println(t.getDateTimeNow());
//        pc.show(pc.blue("as"));
//        t.writeFile("test.txt", "OK");
        t.printLog("blue",49);
        t.savetoFile("test.txt", "OK");
    }
}

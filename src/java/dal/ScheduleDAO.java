/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import model.Activity;
import model.Schedule;
import model.ScheduleDetails;
import model.Class;
import model.Slot;
import model.Test;

/**
 *
 * @author Windows 10 TIMT
 */
public class ScheduleDAO extends DBContext {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;
    private final Test test = new Test();

//    ScheduleDAO sd = new ScheduleDAO();
    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    private static Date findNextDay(Date date) {
        return new Date(date.getTime() + MILLIS_IN_A_DAY);
    }

    private static Date findPrevDay(Date date) {
        return new Date(date.getTime() - MILLIS_IN_A_DAY);
    }

    public String convertDate(String date) {
        //22/06/20022
        double t1, t2;
        t1 = System.currentTimeMillis();
        String result = "";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date d = sdf1.parse(date);
            result = sdf2.format(d);
        } catch (ParseException e) {

        }
        t2 = System.currentTimeMillis();
        test.printLog(test.getDateTimeNow() + " - convertDate: " + (t2 - t1) + "ms", (t2 - t1));

        return result;
    }

    public List<Date> getWeekDay(String date) {
        double t1, t2;
        t1 = System.currentTimeMillis();
        List<Date> listDate = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = sdf.parse(date);
            for (int i = 0; i < 7; i++) {
                listDate.add(d);
                d = findNextDay(d);
            }
        } catch (ParseException e) {

        }
        t2 = System.currentTimeMillis();
        test.printLog(test.getDateTimeNow() + " - getWeekDay: " + (t2 - t1) + "ms", (t2 - t1));
        return listDate;
    }

    public String firstDayOfWeek(Date date) {
        double t1, t2;
        t1 = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String d = sdf.format(calendar.getTime());
        t2 = System.currentTimeMillis();
        test.printLog(test.getDateTimeNow() + " - firstDayOfWeek: " + (t2 - t1) + "ms", (t2 - t1));
        return d;
    }

    //get first Monday of month and year
    public LocalDate getFirstMondayOfMonthYear(int month, int year) {
        double t1, t2;
        t1 = System.currentTimeMillis();
        LocalDate now = LocalDate.of(year, month, 1);
        LocalDate firstMonday = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        t2 = System.currentTimeMillis();
        test.printLog(test.getDateTimeNow() + " - getFirstMondayOfMonthYear: " + (t2 - t1) + "ms", (t2 - t1));
        return firstMonday;
    }

    public LinkedHashMap<LocalDate, String> getAllWeeksInYear(int year) {
        double t1, t2;
        t1 = System.currentTimeMillis();
        ScheduleDAO sd = new ScheduleDAO();
        LinkedHashMap map = new LinkedHashMap<>();
        String t = "";
        LocalDate firstMonday = sd.getFirstMondayOfMonthYear(1, year);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            LocalDate lastSunday = firstMonday.plusDays(6);
            t = firstMonday.toString() + " To " + lastSunday.toString();
            t = fmt.format(firstMonday) + " To " + fmt.format(lastSunday);
            map.put(firstMonday, t);
            firstMonday = lastSunday.plusDays(1);
            if (firstMonday.getYear() > year) {
                break;
            }
        }
        t2 = System.currentTimeMillis();
        test.printLog(test.getDateTimeNow() + " - getAllWeeksInYear: " + (t2 - t1) + "ms", (t2 - t1));
        return map;
    }

    public Schedule getScheduleByClassDateSlot(int class_id, String date, int slot_id) {
        double t1, t2;
        t1 = System.currentTimeMillis();
        ClassDAO cd = new ClassDAO();
        ActivityDAO ad = new ActivityDAO();
        SlotDAO sd = new SlotDAO();

        try {
            String sql = "select * from Schedule\n"
                    + " where class_id = ? and schedule_date = ? and slot_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, class_id);
            ps.setString(2, date);
            ps.setInt(3, slot_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setSchedule_id(rs.getInt(1));
                Class c = cd.getClassByID(rs.getInt(2));
                s.setClasss(c);
                Activity a = ad.getAllActivityByID(rs.getInt(3));
                s.setActivity(a);
                Slot sl = sd.getSlotByID(rs.getInt(4));
                s.setSlot(sl);
                s.setSchedule_date(rs.getString(5));
                t2 = System.currentTimeMillis();
                System.out.println(test.getDateTimeNow() + " - getScheduleByClassDateSlot: " + (t2 - t1) + "ms");
                return s;
            }
        } catch (Exception e) {

        }
        t2 = System.currentTimeMillis();
        test.printLog(test.getDateTimeNow() + " - getScheduleByClassDateSlot: " + (t2 - t1) + "ms", (t2 - t1));
        return null;
    }

    public List<Schedule> getAllSchedulesByClassDate(int classID, String date) {
        double t1, t2;
        t1 = System.currentTimeMillis();
        List<Schedule> list = new ArrayList<>();
        String newDate = convertDate(date);
        ScheduleDAO sd = new ScheduleDAO();

        for (int i = 1; i <= 7; i++) {
            Schedule s = sd.getScheduleByClassDateSlot(classID, newDate, i);
            list.add(s);
        }
        t2 = System.currentTimeMillis();
        test.printLog(test.getDateTimeNow() + " - getAllSchedulesByClassDate: " + (t2 - t1) + "ms", (t2 - t1));

        return list;
    }

    public ScheduleDetails getScheduleDetailsByClassDate(int classID, String date) {
        double t1, t2;
        t1 = System.currentTimeMillis();
        ScheduleDetails sde = new ScheduleDetails();
        LinkedHashMap map = new LinkedHashMap<Date, List<Schedule>>();
        sde.setClassID(classID);
        try {
            List<Date> listDate = getWeekDay(date);
            listDate.forEach(d -> {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dd = sdf.format(d);
                List<Schedule> list = getAllSchedulesByClassDate(classID, dd);
                map.put(d, list);
            });
            sde.setScheduleMap(map);
        } catch (Exception e) {

        }
        t2 = System.currentTimeMillis();
        test.printLog(test.getDateTimeNow() + " - getScheduleDetailsByClassDate: " + (t2 - t1) + "ms", (t2 - t1));

        return sde;
    }

    public void deleteSchedule(int id) {
        double t1, t2;
        t1 = System.currentTimeMillis();
        try {
            String sql = "delete schedule where schedule_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {

        }
        t2 = System.currentTimeMillis();
        test.printLog(test.getDateTimeNow() + " - deleteSchedule: " + (t2 - t1) + "ms", (t2 - t1));

    }

    public void addSchedule(int class_id, int act_id, int slot, String date) {
        double t1, t2;
        t1 = System.currentTimeMillis();
        try {
            String sql = "insert into Schedule values\n"
                    + "(?, ?, ?, ?)";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, class_id);
            ps.setInt(2, act_id);
            ps.setInt(3, slot);
            ps.setString(4, date);
            ps.executeUpdate();
        } catch (Exception e) {

        }
        t2 = System.currentTimeMillis();
        test.printLog(test.getDateTimeNow() + " - addSchedule: " + (t2 - t1) + "ms", (t2 - t1));

    }

    public void updateSchedule(int schedule_id, int actitivity_id) {
        double t1, t2;
        t1 = System.currentTimeMillis();
        try {
            String sql = "update Schedule set activity_id = ? "
                    + "where schedule_id = ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, actitivity_id);
            ps.setInt(2, schedule_id);
            ps.executeUpdate();
        } catch (Exception e) {

        }
        t2 = System.currentTimeMillis();
        test.printLog(test.getDateTimeNow() + " - updateSchedule: " + (t2 - t1) + "ms", (t2 - t1));

    }

    public static void main(String[] args) {
        ScheduleDAO sd = new ScheduleDAO();
        LinkedHashMap<LocalDate, String> map = new LinkedHashMap<>();
        map = sd.getAllWeeksInYear(2022);
        System.out.println(map);
    }
}

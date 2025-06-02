package dal;

import model.Event;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventDAO extends DBContext { // DBContext là lớp base để lấy Connection

    // Lấy tất cả các sự kiện
    public List<Event> getAllEvents() {
        List<Event> list = new ArrayList<>();
        String sql = "SELECT event_id, event_name, event_description, event_date, location FROM Event ORDER BY event_date DESC";
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = new DBContext().getConnection(); 
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Event ev = new Event();
                ev.setEventId(rs.getInt("event_id"));
                ev.setEventName(rs.getString("event_name"));
                ev.setEventDescription(rs.getString("event_description"));
                ev.setEventDate(rs.getDate("event_date"));
                ev.setLocation(rs.getString("location"));
                list.add(ev);
            }
            Logger.getLogger(EventDAO.class.getName()).log(Level.INFO, "Retrieved {0} events from DB.", list.size());
        } catch (SQLException e) { 
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "SQL Error when getting all events", e);
        } catch (Exception e) { 
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error when getting all events", e);
        } finally {
           
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error closing resources in getAllEvents", ex);
            }
        }
        return list;
    }

    // Lấy sự kiện theo ID
    public Event getEventById(int id) {
        String sql = "SELECT event_id, event_name, event_description, event_date, location FROM Event WHERE event_id = ?";
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Event ev = null; 

        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                ev = new Event();
                ev.setEventId(rs.getInt("event_id"));
                ev.setEventName(rs.getString("event_name"));
                ev.setEventDescription(rs.getString("event_description"));
                ev.setEventDate(rs.getDate("event_date"));
                ev.setLocation(rs.getString("location"));
            }
        } catch (SQLException e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "SQL Error when getting event by ID: " + id, e);
        } catch (Exception e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error when getting event by ID: " + id, e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error closing resources in getEventById", ex);
            }
        }
        return ev;
    }

    // Thêm sự kiện mới
    public void addEvent(Event ev) throws SQLException { 
        String sql = "INSERT INTO Event (event_name, event_description, event_date, location) VALUES (?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, ev.getEventName());
            ps.setString(2, ev.getEventDescription());
            ps.setDate(3, ev.getEventDate());
            ps.setString(4, ev.getLocation());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                Logger.getLogger(EventDAO.class.getName()).log(Level.INFO, "Event added successfully to DB: {0}", ev.getEventName());
            } else {
                Logger.getLogger(EventDAO.class.getName()).log(Level.WARNING, "Failed to add event to DB: {0}. No rows affected.", ev.getEventName());
            }
        } catch (SQLException e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "SQL Error when adding event: " + ev.getEventName(), e);
            throw e; 
        } catch (Exception e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error when adding event: " + ev.getEventName(), e);
            throw new RuntimeException("Unexpected error when adding event.", e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error closing resources in addEvent", ex);
            }
        }
    }

    // Cập nhật sự kiện
    public boolean updateEvent(Event event) throws SQLException { 
        
        String sql = "UPDATE Event SET event_name = ?, event_description = ?, event_date = ?, location = ? WHERE event_id = ?";
        
        Connection connection = null;
        PreparedStatement ps = null;

        try { 
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, event.getEventName());
            ps.setString(2, event.getEventDescription());
            ps.setDate(3, event.getEventDate());
            ps.setString(4, event.getLocation());
            ps.setInt(5, event.getEventId());
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                Logger.getLogger(EventDAO.class.getName()).log(Level.INFO, "Event updated successfully: {0}", event.getEventId());
            } else {
                 Logger.getLogger(EventDAO.class.getName()).log(Level.WARNING, "No event found or updated for ID: {0}", event.getEventId());
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "SQL Error when updating event: " + event.getEventId(), e);
            throw e; 
        } catch (Exception e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error when updating event: " + event.getEventId(), e);
            throw new RuntimeException("Unexpected error when updating event.", e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error closing resources in updateEvent", ex);
            }
        }
    }

    // Xóa sự kiện
    public void deleteEvent(int id) throws SQLException { 
        
        String sql = "DELETE FROM Event WHERE event_id = ?";
        
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                Logger.getLogger(EventDAO.class.getName()).log(Level.INFO, "Event deleted successfully: {0}", id);
            } else {
                 Logger.getLogger(EventDAO.class.getName()).log(Level.WARNING, "No event found or deleted for ID: {0}", id);
            }
        } catch (SQLException e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "SQL Error when deleting event: " + id, e);
            throw e; 
        } catch (Exception e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error when deleting event: " + id, e);
            throw new RuntimeException("Unexpected error when deleting event.", e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error closing resources in deleteEvent", ex);
            }
        }
    }

    public static void main(String[] args) {
        EventDAO dao = new EventDAO();
        List<Event> events = dao.getAllEvents();
        System.out.println("--- All Events ---");
        for (Event e : events) {
            System.out.println("ID: " + e.getEventId() + ", Name: " + e.getEventName() + " - Date: " + e.getEventDate());
        }

        // Test addEvent (thêm một sự kiện mẫu)
        System.out.println("\n--- Testing addEvent ---");
        Event newEvent = new Event();
        newEvent.setEventName("Test Event " + System.currentTimeMillis()); // Unique name
        newEvent.setEventDescription("This is a test event description.");
        newEvent.setEventDate(java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(7))); // 7 ngày sau
        newEvent.setLocation("Test Location");
        try {
            dao.addEvent(newEvent);
            System.out.println("Test event added successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to add test event: " + e.getMessage());
            e.printStackTrace();
        }

        // Test getAllEvents again to see the new event
        System.out.println("\n--- All Events (after add) ---");
        events = dao.getAllEvents();
        for (Event e : events) {
            System.out.println("ID: " + e.getEventId() + ", Name: " + e.getEventName() + " - Date: " + e.getEventDate());
        }

        // Test getEventById (lấy event đầu tiên nếu có)
        if (!events.isEmpty()) {
            Event firstEvent = events.get(0);
            System.out.println("\n--- Testing getEventById for ID: " + firstEvent.getEventId() + " ---");
            Event retrievedEvent = dao.getEventById(firstEvent.getEventId());
            if (retrievedEvent != null) {
                System.out.println("Retrieved Event: " + retrievedEvent.getEventName());
            } else {
                System.out.println("Event not found for ID: " + firstEvent.getEventId());
            }
        }
        
        // Test updateEvent (cập nhật event đầu tiên nếu có)
        if (!events.isEmpty()) {
            Event eventToUpdate = events.get(0);
            String oldName = eventToUpdate.getEventName();
            eventToUpdate.setEventName("Updated " + oldName);
            eventToUpdate.setLocation("Updated Location");
            System.out.println("\n--- Testing updateEvent for ID: " + eventToUpdate.getEventId() + " ---");
            try {
                if (dao.updateEvent(eventToUpdate)) {
                    System.out.println("Event updated successfully to: " + eventToUpdate.getEventName());
                } else {
                    System.out.println("Failed to update event.");
                }
            } catch (SQLException e) {
                System.err.println("Failed to update test event: " + e.getMessage());
                e.printStackTrace();
            }
            // Check again
            System.out.println("\n--- All Events (after update) ---");
            events = dao.getAllEvents();
            for (Event e : events) {
                System.out.println("ID: " + e.getEventId() + ", Name: " + e.getEventName() + " - Date: " + e.getEventDate());
            }
        }

        // Test deleteEvent (xóa event vừa thêm, hoặc event đầu tiên nếu không có test add)
        // Cần biết ID của event mới thêm để xóa chính xác
        if (events.size() > 0) {
             Event eventToDelete = events.get(0); // Lấy event đầu tiên
             System.out.println("\n--- Testing deleteEvent for ID: " + eventToDelete.getEventId() + " ---");
            try {
                dao.deleteEvent(eventToDelete.getEventId());
                System.out.println("Event deleted successfully.");
            } catch (SQLException e) {
                System.err.println("Failed to delete test event: " + e.getMessage());
                e.printStackTrace();
            }
             System.out.println("\n--- All Events (after delete) ---");
             events = dao.getAllEvents();
             for (Event e : events) {
                 System.out.println("ID: " + e.getEventId() + ", Name: " + e.getEventName() + " - Date: " + e.getEventDate());
             }
        }
    }
}
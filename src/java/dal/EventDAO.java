package dal;

import model.Event;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventDAO extends DBContext { 

        public List<Event> getAllEvent() {
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
        
    // Lấy tất cả các sự kiện
    public List<Event> getAllEvents(String searchTitle, int page, int recordsPerPage) {
        List<Event> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = new DBContext().getConnection();
            StringBuilder sql = new StringBuilder("SELECT event_id, event_name, event_description, event_date, location FROM Event WHERE 1=1");

            // Thêm điều kiện tìm kiếm nếu có searchTitle
            if (searchTitle != null && !searchTitle.trim().isEmpty()) {
                sql.append(" AND event_name LIKE ?");
            }
            
            sql.append(" ORDER BY event_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

            ps = connection.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (searchTitle != null && !searchTitle.trim().isEmpty()) {//Gán tham số tìm kiếm(nếu có)
                ps.setString(paramIndex++, "%" + searchTitle.trim() + "%");
            }
            
            // Tham số cho phân trang
            ps.setInt(paramIndex++, (page - 1) * recordsPerPage);
            ps.setInt(paramIndex++, recordsPerPage);

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
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "SQL Error when getting all events with search/pagination", e);
        } catch (Exception e) { 
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error when getting all events with search/pagination", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error closing resources in getAllEvents (search/pagination)", ex);
            }
        }
        return list;
    }
    
    //lấy tổng số bản ghi sự kiện phù hợp với điều kiện tìm kiếm
    public int getNoOfRecords(String searchTitle) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int noOfRecords = 0;

        try {
            connection = new DBContext().getConnection();
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Event WHERE 1=1");
            if (searchTitle != null && !searchTitle.trim().isEmpty()) {
                sql.append(" AND event_name LIKE ?");
            }
            ps = connection.prepareStatement(sql.toString());
            
            if (searchTitle != null && !searchTitle.trim().isEmpty()) {
                ps.setString(1, "%" + searchTitle.trim() + "%");
            }
            
            rs = ps.executeQuery();
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "SQL Error when getting total records with search", e);
        } catch (Exception e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error when getting total records with search", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error closing resources in getNoOfRecords", ex);
            }
        }
        return noOfRecords;
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
    
    
     /*
     * Lấy một số lượng sự kiện giới hạn, sắp xếp theo ngày diễn ra sắp tới.
     * Chỉ lấy các sự kiện có ngày diễn ra từ ngày hiện tại trở đi.
     * Thích hợp cho các trang chủ hoặc widget hiển thị sự kiện sắp tới.
     * @param limit Số lượng sự kiện tối đa muốn lấy.
     * @return Danh sách các đối tượng Event sắp diễn ra.
     */
    public List<Event> getUpcomingEvents(int limit) { 
        List<Event> list = new ArrayList<>();
        String sql = "SELECT event_id, event_name, event_description, event_date, location " +
                     "FROM Event " +
                     "WHERE event_date >= GETDATE() " +
                     "ORDER BY event_date ASC " +     
                     "OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection connection = new DBContext().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, limit);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Event ev = new Event();
                    ev.setEventId(rs.getInt("event_id"));
                    ev.setEventName(rs.getString("event_name"));
                    ev.setEventDescription(rs.getString("event_description"));
                    ev.setEventDate(rs.getDate("event_date"));
                    ev.setLocation(rs.getString("location"));
                    list.add(ev);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "SQL Error when getting upcoming events with limit: " + limit, e);
        } catch (Exception e) {
            Logger.getLogger(EventDAO.class.getName()).log(Level.SEVERE, "Error when getting upcoming events with limit: " + limit, e);
        }
        return list;
    }

}
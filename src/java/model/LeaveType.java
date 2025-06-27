package model;

public class LeaveType {
    private int leaveTypeId;
    private String typeName;
    private String description;
    private boolean hasEntitlement; // Corresponds to has_entitlement BIT
    private boolean isAccumulative; // Corresponds to is_accumulative BIT
    private boolean allowHalfDay;   // Corresponds to allow_half_day BIT

    public LeaveType() {
    }

    public LeaveType(int leaveTypeId, String typeName, String description,
                     boolean hasEntitlement, boolean isAccumulative, boolean allowHalfDay) {
        this.leaveTypeId = leaveTypeId;
        this.typeName = typeName;
        this.description = description;
        this.hasEntitlement = hasEntitlement;
        this.isAccumulative = isAccumulative;
        this.allowHalfDay = allowHalfDay;
    }

    // --- Getters and Setters ---
    public int getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(int leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasEntitlement() { // Getter cho boolean thường dùng isXxx
        return hasEntitlement;
    }

    public void setHasEntitlement(boolean hasEntitlement) {
        this.hasEntitlement = hasEntitlement;
    }

    public boolean isIsAccumulative() { // Getter cho boolean thường dùng isXxx
        return isAccumulative;
    }

    public void setIsAccumulative(boolean isAccumulative) {
        this.isAccumulative = isAccumulative;
    }

    public boolean isAllowHalfDay() { // Getter cho boolean thường dùng isXxx
        return allowHalfDay;
    }

    public void setAllowHalfDay(boolean allowHalfDay) {
        this.allowHalfDay = allowHalfDay;
    }

    @Override
    public String toString() {
        return "LeaveType{" +
                "leaveTypeId=" + leaveTypeId +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                ", hasEntitlement=" + hasEntitlement +
                ", isAccumulative=" + isAccumulative +
                ", allowHalfDay=" + allowHalfDay +
                '}';
    }
}

package model;

public class Menu {
  private int menu_id;  
  private String menudate;
  private String menutype;
  private String dish;
  private float calories;
  private String notes;
  private Class calssid;

    public Menu() {
    }

    public Menu(int menu_id, String menudate, String menutype, String dish, float calories, String notes, Class calssid) {
        this.menu_id = menu_id;
        this.menudate = menudate;
        this.menutype = menutype;
        this.dish = dish;
        this.calories = calories;
        this.notes = notes;
        this.calssid = calssid;
    }

    @Override
    public String toString() {
        return "Menu{" + "menu_id=" + menu_id + ", menudate=" + menudate + ", menutype=" + menutype + ", dish=" + dish + ", calories=" + calories + ", notes=" + notes + ", calssid=" + calssid + '}';
    }
    
    public Menu(String menudate, String menutype, String dish, float calories, String notes, Class calssid) {
        this.menudate = menudate;
        this.menutype = menutype;
        this.dish = dish;
        this.calories = calories;
        this.notes = notes;
        this.calssid = calssid;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
 
    public String getMenudate() {
        return menudate;
    }

    public void setMenudate(String menudate) {
        this.menudate = menudate;
    }

    public String getMenutype() {
        return menutype;
    }

    public void setMenutype(String menutype) {
        this.menutype = menutype;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Class getCalssid() {
        return calssid;
    }

    public void setCalssid(Class calssid) {
        this.calssid = calssid;
    }

 



 
  
}

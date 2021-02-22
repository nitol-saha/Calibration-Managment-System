package CMS;

public class StandardScheduleModel {

    private String sd_id,month,week;

    public StandardScheduleModel(String sd_id, String month, String week) {
        this.sd_id = sd_id;
        this.month = month;
        this.week = week;
    }

    public String getSd_id() {
        return sd_id;
    }

    public void setSd_id(String sd_id) {
        this.sd_id = sd_id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}

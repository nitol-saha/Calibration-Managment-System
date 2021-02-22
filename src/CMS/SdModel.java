package CMS;

public class SdModel {
    private String sd_id,sd_name,sd_model, sd_serial_no;

    public SdModel(String sd_id, String sd_name, String sd_model, String sd_serial_no) {
        this.sd_id = sd_id;
        this.sd_name = sd_name;
        this.sd_model = sd_model;
        this.sd_serial_no = sd_serial_no;
    }

    public String getSd_id() {
        return sd_id;
    }

    public void setSd_id(String sd_id) {
        this.sd_id = sd_id;
    }

    public String getSd_name() {
        return sd_name;
    }

    public void setSd_name(String sd_name) {
        this.sd_name = sd_name;
    }

    public String getSd_model() {
        return sd_model;
    }

    public void setSd_model(String sd_model) {
        this.sd_model = sd_model;
    }

    public String getSd_serial_no() {
        return sd_serial_no;
    }

    public void setSd_serial_no(String sd_serial_no) {
        this.sd_serial_no = sd_serial_no;
    }
}

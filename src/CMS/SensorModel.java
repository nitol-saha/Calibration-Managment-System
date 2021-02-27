package CMS;

public class SensorModel {
    private String sen_id,sen_name,sen_model, sen_serial_no, eq_id;

    public SensorModel(String sen_id, String sen_name, String sen_model, String sen_serial_no, String eq_id) {
        this.sen_id = sen_id;
        this.sen_name = sen_name;
        this.sen_model = sen_model;
        this.sen_serial_no = sen_serial_no;
        this.eq_id=eq_id;
    }

    public String getEq_id() {
        return eq_id;
    }

    public void setEq_id(String eq_id) {
        this.eq_id = eq_id;
    }

    public String getSen_id() {
        return sen_id;
    }

    public void setSen_id(String sen_id) {
        this.sen_id = sen_id;
    }

    public String getSen_name() {
        return sen_name;
    }

    public void setSen_name(String sen_name) {
        this.sen_name = sen_name;
    }

    public String getSen_model() {
        return sen_model;
    }

    public void setSen_model(String sen_model) {
        this.sen_model = sen_model;
    }

    public String getSen_serial_no() {
        return sen_serial_no;
    }

    public void setSen_serial_no(String sen_serial_no) {
        this.sen_serial_no = sen_serial_no;
    }
}

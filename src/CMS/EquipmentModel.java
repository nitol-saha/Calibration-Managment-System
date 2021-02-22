package CMS;

public class EquipmentModel {
    private String eq_id,eq_name,facility_name;
    private int id;

    public EquipmentModel(int id , String eq_id, String eq_name, String facility_name) {
        this.id=id;
        this.eq_id = eq_id;
        this.eq_name = eq_name;
        this.facility_name = facility_name;
    }



    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getEq_id() {
        return eq_id;
    }

    public void setEq_id(String eq_id) {
        this.eq_id = eq_id;
    }

    public String getEq_name() {
        return eq_name;
    }

    public void setEq_name(String eq_name) {
        this.eq_name = eq_name;
    }

    public String getFacility_name() {
        return facility_name;
    }

    public void setFacility_name(String facility_name) {
        this.facility_name = facility_name;
    }
}
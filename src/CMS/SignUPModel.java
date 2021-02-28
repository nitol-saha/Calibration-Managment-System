package CMS;

public class SignUPModel {
    private String f_name, u_name, psw, gend;

    public SignUPModel(String f_name, String u_name, String psw, String gend) {
        this.f_name = f_name;
        this.u_name = u_name;
        this.psw = psw;
        this.gend = gend;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getGend() {
        return gend;
    }

    public void setGend(String gend) {
        this.gend = gend;
    }
}

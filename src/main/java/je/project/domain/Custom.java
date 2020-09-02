package je.project.domain;

import java.sql.Timestamp;

public class Custom {
    private Integer id;
    private String idcard;
    private Timestamp stime;
    private Integer type;
    private String company;
    private String ftel;
    private String tel;
    private String address;
    private String postcode;
    private String name;
    private String email;

    public Custom() {
        super();
    }

    public Custom(Integer id1, String idcard1, Timestamp stime1, Integer type1, String company1, String ftel1,
            String tel1, String address1, String postcode1, String name1, String email1) {
        super();
        this.id = id1;
        this.idcard = idcard1;
        this.stime = stime1;
        this.type = type1;
        this.company = company1;
        this.ftel = ftel1;
        this.tel = tel1;
        this.address = address1;
        this.postcode = postcode1;
        this.name = name1;
        this.email = email1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Timestamp getStime() {
        return stime;
    }

    public void setStime(Timestamp stime) {
        this.stime = stime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFtel() {
        return ftel;
    }

    public void setFtel(String ftel) {
        this.ftel = ftel;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Custom[id=" + id + ",idcard=" + idcard + ",stime=" + stime + ",type=" + type + ",company=" + company
                + ",ftel=" + ftel + ",tel=" + tel + ",address=" + address + ",postcode=" + postcode + ",name=" + name
                + ",email=" + email + "+]";
    }
}

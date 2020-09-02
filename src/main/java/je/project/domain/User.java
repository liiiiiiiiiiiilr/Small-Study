package je.project.domain;

import java.sql.Timestamp;

public class User {
    private Integer id;
    private String idcard;
    private Timestamp stime;
    private Integer type;
    private String company;
    private String ftel;
    private String tel;
    private String address;
    private String postcode;

    public User(Integer id, String idcard, Timestamp stime, Integer type, String company, String ftel, String tel, String address, String postcode, String name, String email) {
        this.id = id;
        this.idcard = idcard;
        this.stime = stime;
        this.type = type;
        this.company = company;
        this.ftel = ftel;
        this.tel = tel;
        this.address = address;
        this.postcode = postcode;
        this.name = name;
        this.email = email;
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

    public User() {
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

    private String name;
    private String email;

}

package com.example.dream.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestListInfo_copy {
    @SerializedName("request_id")
    @Expose
    private Integer request_id;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("blood_type")
    @Expose
    private String blood_type;
    @SerializedName("blood_kind")
    @Expose
    private String blood_kind;
    @SerializedName("need")
    @Expose
    private Integer need;
    @SerializedName("deadline")
    @Expose
    private String deadline;
    @SerializedName("patient_name")
    @Expose
    private String patient_name;
    @SerializedName("patient_id")
    @Expose
    private String patient_id;
    @SerializedName("patient_loc")
    @Expose
    private String patient_loc;
    @SerializedName("patient_sex")
    @Expose
    private String patient_sex;
    @SerializedName("patient_age")
    @Expose
    private Integer patient_age;
    @SerializedName("patient_birth")
    @Expose
    private String patient_birth;
    @SerializedName("hospital_name")
    @Expose
    private String hospital_name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("request_detail")
    @Expose
    private String request_detail;



    public Integer getNeed() {
        return need;
    }

    public void setNeed(Integer need) {
        this.need = need;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getBlood_kind() {
        return blood_kind;
    }

    public void setBlood_kind(String blood_kind) {
        this.blood_kind = blood_kind;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_loc() {
        return patient_loc;
    }

    public void setPatient_loc(String patient_loc) {
        this.patient_loc = patient_loc;
    }

    public String getPatient_sex() {
        return patient_sex;
    }

    public void setPatient_sex(String patient_sex) {
        this.patient_sex = patient_sex;
    }

    public Integer getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(Integer patient_age) {
        this.patient_age = patient_age;
    }

    public String getPatient_birth() {
        return patient_birth;
    }

    public void setPatient_birth(String patient_birth) {
        this.patient_birth = patient_birth;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRequest_detail() {
        return request_detail;
    }

    public void setRequest_detail(String request_detail) {
        this.request_detail = request_detail;
    }
}

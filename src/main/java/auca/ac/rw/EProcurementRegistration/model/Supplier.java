package auca.ac.rw.EProcurementRegistration.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Section 1: General Info
    @Column(nullable = false)
    private String supplierName;

    @Column(nullable = false, unique = true)
    private String tinNo;

    @Column(nullable = false)
    private String bizCert; // Can be a path or filename after upload

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String rssbNo;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private LocalDate regDate;

    @Column(nullable = false)
    private String bizType;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private Double capital;

    @Column(nullable = false)
    private Integer employees;

    private String poBox;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String tel1;

    private String tel2;

    private String fax;

    @Column(nullable = false, unique = true)
    private String email;

    private String website;

    // Section 2: Bank Details
    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String accountNo;

    @Column(nullable = false)
    private String accountHolder;

    // Section 3: Representative
    @Column(nullable = false)
    private String repName;

    @Column(nullable = false)
    private String repId;

    @Column(nullable = false)
    private String repPhone;

    @Column(nullable = false)
    private String repEmail;

    private String repPoBox;

    // Metadata
    @Column(nullable = false)
    private String status = "Pending Verification";

    @Column(nullable = false, updatable = false)
    private LocalDateTime registeredAt = LocalDateTime.now();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public String getBizCert() {
        return bizCert;
    }

    public void setBizCert(String bizCert) {
        this.bizCert = bizCert;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRssbNo() {
        return rssbNo;
    }

    public void setRssbNo(String rssbNo) {
        this.rssbNo = rssbNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Integer getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }

    public String getRepPhone() {
        return repPhone;
    }

    public void setRepPhone(String repPhone) {
        this.repPhone = repPhone;
    }

    public String getRepEmail() {
        return repEmail;
    }

    public void setRepEmail(String repEmail) {
        this.repEmail = repEmail;
    }

    public String getRepPoBox() {
        return repPoBox;
    }

    public void setRepPoBox(String repPoBox) {
        this.repPoBox = repPoBox;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
}

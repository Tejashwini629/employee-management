package com.wecodee.EmployeeManagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import com.sun.istack.NotNull;
import org.hibernate.internal.CoreLogging;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
//        @NotNull(message = "First name should not be null")
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Column(name = "salary")
    private long salary;
    @Min(18)
    @Max(60)
    @Column(name = "age")
    private int age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    @NotBlank //it won't accept null and empty
    @Column(name = "department")
    private String department;
//    @Email(message = "Invalid Email")
//    private String email;
////    @NotNull
//    @Pattern(regexp = "^\\d{10}$",message = "Invalid mobile number")
//    private String mobileNumber;

    public Employee() {
        super();
        // TODO Auto-generated constructor stub
    }

//	public Employee( String firstname, String lastname, long salary) {
//		super();
//	
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.salary = salary;
//	}


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public long getSalary() {
        return salary;
    }


    public void setSalary(long salary) {
        this.salary = salary;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public Date getDob() {
        return dob;
    }


    public void setDob(Date dob) {
        this.dob = dob;
    }


    public String getDepartment() {
        return department;
    }


    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary
                + ", age=" + age + ", dob=" + dob + ", department=" + department + "]";
    }

}

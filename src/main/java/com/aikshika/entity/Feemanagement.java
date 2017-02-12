package com.aikshika.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint; 

import java.io.Serializable;

@Entity
@Table(name = "Feemanagements",uniqueConstraints = @UniqueConstraint(columnNames = "classname"))
public class Feemanagement implements Serializable {
	
	private static final long serialVersionUID = -7988799579036225137L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
   @Column
    private String classname;
    
    @Column
    private float feeamount;

    public Feemanagement() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public float getFeeamount() {
		return feeamount;
	}
	public void setFeeamount(float feeamount) {
		this.feeamount = feeamount;
	}
    
    @Override
    public String toString() {
        return "Feemanagement{" +
                "id=" + id +
                ", classname='" + classname + '\'' +
                ", feeamount=" + feeamount +
                '}';
    }
}
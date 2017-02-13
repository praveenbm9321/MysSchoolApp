
package com.aikshika.admin.dao;

import java.util.List;

import com.aikshika.entity.Feemanagement;


public interface FeemanagementDao {
	public long createFee(Feemanagement fee);
    public Feemanagement updateFee(Feemanagement fee);
    public void deleteFee(long id);
    public void sortFee();
    public List<Feemanagement> getAllFees();
    public Feemanagement getFee(long id);	
	public List<Feemanagement> getAllFees(String className);
}

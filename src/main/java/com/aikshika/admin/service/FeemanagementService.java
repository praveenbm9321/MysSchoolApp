
package com.aikshika.admin.service;
import java.util.List;

import com.aikshika.entity.Feemanagement;

public interface FeemanagementService {
	public long createFee(Feemanagement fee);
    public Feemanagement updateFee(Feemanagement fee);
    public void sortFee();
    public void deleteFee(long id);
    public List<Feemanagement> getAllFees();
    public Feemanagement getFee(long id);	
	public List<Feemanagement> getAllFees(String className);
}

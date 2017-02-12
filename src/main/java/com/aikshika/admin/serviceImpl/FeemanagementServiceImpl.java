package com.aikshika.admin.serviceImpl;
import com.aikshika.admin.dao.FeemanagementDao;
import com.aikshika.admin.service.FeemanagementService;
import com.aikshika.entity.Feemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FeemanagementServiceImpl implements FeemanagementService {
	
	public FeemanagementServiceImpl() {
		System.out.println("EmployeeServiceImpl()");
	}
	
    @Autowired
    private FeemanagementDao employeeDAO;

    @Override
    public long createFee(Feemanagement fee) {
        return employeeDAO.createFee(fee);
    }
    @Override
    public Feemanagement updateFee(Feemanagement fee) {
        return employeeDAO.updateFee(fee);
    }
    @Override
    public void deleteFee(long id) {
        employeeDAO.deleteFee(id);
    }
    @Override
    public void sortFee() {
        employeeDAO.sortFee();
    }
    @Override
    public List<Feemanagement> getAllFees() {
        return employeeDAO.getAllFees();
    }
    @Override
    public Feemanagement getFee(long id) {
        return employeeDAO.getFee(id);
    }    
    @Override
    public List<Feemanagement> getAllFees(String className) {
    	return employeeDAO.getAllFees(className);
    }
}

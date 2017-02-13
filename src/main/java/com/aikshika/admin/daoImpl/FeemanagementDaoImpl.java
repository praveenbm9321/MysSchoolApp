/**
 * 
 */
package com.aikshika.admin.daoImpl;

import com.aikshika.admin.dao.FeemanagementDao;
import com.aikshika.entity.Feemanagement;
import com.aikshika.util.HibernateUtil;

import org.hibernate.engine.spi.ExecuteUpdateResultCheckStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Repository
public class FeemanagementDaoImpl implements FeemanagementDao {
    
	public FeemanagementDaoImpl() {
    	System.out.println("FeemanagementDAOImpl");
    }
	
	@Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public long createFee(Feemanagement fee) {        
        return (Long) hibernateUtil.create(fee);
    }
    
    @Override
    public Feemanagement updateFee(Feemanagement fee) {        
        return hibernateUtil.update(fee);
    }
    
    @Override
    public void deleteFee(long id) {
        Feemanagement fee = new Feemanagement();
        fee.setId(id);
        hibernateUtil.delete(fee);
    }
    @Override
    public void sortFee() {
        hibernateUtil.sortFee();
    }
    
    @Override
    public List<Feemanagement> getAllFees() {        
        return hibernateUtil.fetchAll(Feemanagement.class);
    }
    
    @Override
    public Feemanagement getFee(long id) {
        return hibernateUtil.fetchById(id, Feemanagement.class);
    }

	@Override
	public List<Feemanagement> getAllFees(String feeName) { 
		//String query="select classname from feemanagements ORDER BY classname DESC";  
		String query = "SELECT e.* FROM Feemanagements e WHERE e.classname like '%"+ feeName +"%'";
		List<Object[]> feeObjects = hibernateUtil.fetchAll(query);
		List<Feemanagement> fees = new ArrayList<Feemanagement>();
		for(Object[] feeObject: feeObjects) {
			Feemanagement fee = new Feemanagement();
			long id = ((BigInteger) feeObject[0]).longValue();			
			String classname = (String) feeObject[1];
			float feeamount = (float) feeObject[2];
			fee.setId(id);
			fee.setClassname(classname);
			fee.setFeeamount(feeamount);
			fees.add(fee);
		}
		System.out.println(fees);
		return fees;
	}
}
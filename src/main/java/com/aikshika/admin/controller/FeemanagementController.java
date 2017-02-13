package com.aikshika.admin.controller;
import com.aikshika.admin.service.FeemanagementService;
import com.aikshika.entity.Feemanagement;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FeemanagementController {
	
	private static final Logger logger = Logger.getLogger(FeemanagementController.class);
	
	public FeemanagementController() {
		System.out.println("FeemanagenemtController()");
	}

    @Autowired
    private FeemanagementService feeService;

    @RequestMapping("createFee")
    public ModelAndView createFee(@ModelAttribute Feemanagement fee) {
    	logger.info("Creating Feemanagement. Data: "+fee);
        return new ModelAndView("feeDetailForm");
    }
    
    @RequestMapping("editFee")
    public ModelAndView editFee(@RequestParam long id, @ModelAttribute Feemanagement fee) {
    	logger.info("Updating the Feemanagement for the Id "+id);
        fee = feeService.getFee(id);
        return new ModelAndView("feeDetailEditList", "feeObject", fee);
    }
    
    @RequestMapping("saveFee")
    public ModelAndView saveFee(@ModelAttribute Feemanagement fee) {
    	logger.info("Saving the Feemanagement. Data : "+fee);
    	 if(fee.getId() == 0){ // if employee id is 0 then creating the employee other updating the employee
            feeService.createFee(fee);
            feeService.sortFee();
        } else {
            feeService.updateFee(fee);
            feeService.sortFee();
        }
        return new ModelAndView("redirect:getAllFees");
    }
    
    @RequestMapping("deleteFee")
    public ModelAndView deleteFee(@RequestParam long id) {
    	logger.info("Deleting the Feemanagement. Id : "+id);
        feeService.deleteFee(id);
        return new ModelAndView("redirect:getAllFees");
    }
    
    @RequestMapping(value = {"getAllFees", "/"})
    public ModelAndView getAllFees() {
    	logger.info("Getting the all Feemanagements.");
        List<Feemanagement> feeList = feeService.getAllFees();
        return new ModelAndView("feeDetailList", "feeList", feeList);
    }
    
    @RequestMapping("searchFee")
    public ModelAndView searchFee(@RequestParam("searchName") String searchName) {  
    	logger.info("Searching the Feemanagement. Feemanagement Names: "+searchName);
    	List<Feemanagement> feeList = feeService.getAllFees(searchName);
        return new ModelAndView("feeDetailList", "feeList", feeList);    	
    }
}
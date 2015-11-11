/*
 * Created on 13-Jun-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnquireForm extends ActionForm {
	
	private long enquireFormId;
	private String name = null;
	private String email = null;
	private String comment = null;
	private int carId = 0;
	
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public long getEnquireFormId() {
		return enquireFormId;
	}
	public void setEnquireFormId(long enquireFormId) {
		this.enquireFormId = enquireFormId;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) { 
		this.name = null;
	    this.email = null;
	    this.comment = null;
	  } 

}

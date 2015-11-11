 /*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import supercars.form.EnquireForm;
import supercars.dataloader.EnquiryDataLoader;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionEnquire extends Action {

	// Perform Action
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
	
		String query = request.getParameter("query");
		String forward = "success";
		
		if(query == null){}
		else if (query.equals("load")) {
			getEnquireForm(Integer.parseInt(request.getParameter("enquireId")));
		}
		else if(query.equals("save")) {
			saveEnquireForm((EnquireForm)form);
			forward = "thanks";
		}
		form.reset(mapping, request);
		return(mapping.findForward(forward));
	}
		
	
	public void saveEnquireForm(EnquireForm enquireForm) {
		new EnquiryDataLoader().saveEnquireForm(enquireForm);
	}
	
	public void getEnquireForm(int enquireFormId){
		EnquireForm enquireForm = new EnquiryDataLoader().getEnquiry(enquireFormId);
	}
}

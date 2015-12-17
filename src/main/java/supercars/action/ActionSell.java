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

import supercars.dataloader.CarDataLoader;
import supercars.dataloader.ManufacturerDataLoader;
import supercars.form.CarForm;
import supercars.form.FormHelper;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionSell extends Action {

	// Perform Action
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		String query = request.getParameter("query");
		String forward = "success";
		
		if(query == null){}
		else if (query.equals("load")) {
			//getEnquireForm(Integer.parseInt(request.getParameter("enquireId")));
		}
		else if(query.equals("save")) {
			if (FormHelper.isCarValid((CarForm)form)) {
				saveCarForm((CarForm)form);
			}
			forward = "thanks";
		}
		request.setAttribute("manufacturers", new ManufacturerDataLoader().getManufacturers());
		form.reset(mapping, request);
		return(mapping.findForward(forward));
	}
	
	public void saveCarForm(CarForm carForm) {
		new CarDataLoader().saveCar(carForm);
	}
}

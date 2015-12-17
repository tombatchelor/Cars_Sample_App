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

import supercars.Car;
import supercars.dataloader.CarDataLoader;
import supercars.dataloader.EnquiryDataLoader;
import supercars.dataloader.ManufacturerDataLoader;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionCar extends Action {

	// Perform Action
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		String query = request.getParameter("query");
		int carId = Integer.parseInt(request.getParameter("cid"));
		
		Car car = new CarDataLoader().getCar(carId);
		request.setAttribute("car", car);
		request.setAttribute("manufacturer", new ManufacturerDataLoader().getManufacturer(car.getManufacturer()));
		request.setAttribute("enquiries", null);
		
		if(query.equals("carEnquiries")) {
			request.setAttribute("enquiries", new EnquiryDataLoader().getEnquirysForCar(carId));
		}
		
		return(mapping.findForward("success"));
	}
}

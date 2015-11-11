 /*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import supercars.Car;
import supercars.Manufacturer;
import supercars.dataloader.CarDataLoader;
import supercars.dataloader.ManufacturerDataLoader;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionCars extends Action {

	// Perform Action
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		String query = request.getParameter("query");
		
		if(query == null){}
		else if (query.equals("manu")) {
			getCarsByManufacturer(request);
		}
		else if(query.equals("search")) {
			getCarsBySearch(request);
		}
		return(mapping.findForward("success"));
	}
	
	public void getCarsByManufacturer(HttpServletRequest request){
		String manuId = request.getParameter("mid");
		/*try {
			Thread.sleep(2500);
			}
			catch(Exception e){
				e.printStackTrace();
			}*/
		request.setAttribute("manufacturer", new ManufacturerDataLoader().getManufacturer(manuId));
		request.setAttribute("cars", new CarDataLoader().getCarsByManufacturer(manuId));
	}
	
	public void getCarsBySearch(HttpServletRequest request) {
		
		Map manufacturers = new HashMap();
		Manufacturer manufacturer = null;
		
		//Get criteria for user request
		String criteria = request.getParameter("criteria");
		
		// Get cars that match criteria
		ArrayList cars = (ArrayList)new CarDataLoader().getCarsBySearch(criteria);
		
		// Get Manufacturer details for all cars
		Iterator iterator = cars.iterator();
		while(iterator.hasNext()){
			Car car = (Car)iterator.next();
			manufacturer = new Manufacturer();
			manufacturer = new ManufacturerDataLoader().getManufacturer(car.getManufacturer());
			manufacturers.put(car.getManufacturer(), manufacturer);
		}
		// Put cars in request
		request.setAttribute("cars", cars);
		
		// Put manufacturers in request
		request.setAttribute("manufacturers", manufacturers);
		
	}
	
}

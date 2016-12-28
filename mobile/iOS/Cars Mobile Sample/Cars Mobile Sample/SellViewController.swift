//
//  SellViewController.swift
//  Cars Mobile Sample
//
//  Created by Tom Batchelor on 12/27/16.
//  Copyright © 2016 Tom Batchelor. All rights reserved.
//

import UIKit

class SellViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {

    var manufacturers = [Manufacturer]()
    
    @IBOutlet weak var manufacturerPicker: UIPickerView!
    @IBOutlet weak var carModel: UITextField!
    @IBOutlet weak var carName: UITextField!
    @IBOutlet weak var carColour: UITextField!
    @IBOutlet weak var carYear: UITextField!
    @IBOutlet weak var carPrice: UITextField!
    @IBOutlet weak var carSummary: UITextField!
    @IBOutlet weak var carDescription: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        self.manufacturerPicker.dataSource = self
        self.manufacturerPicker.delegate = self
        
        RestApiManager.sharedInstance.getManufacturers({manufacturers in
            self.manufacturers = manufacturers
            dispatch_async(dispatch_get_main_queue(), {
                self.manufacturerPicker.reloadAllComponents()
                return
            })
        })
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // Mark: Picker
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return manufacturers.count
    }
    
    func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return manufacturers[row].name
    }
    
    // ACTIONS
    
    @IBAction func sellCar(sender: UIButton) {
        let car = Car(carId: 0, name: carName.text!, model: carModel.text!)
        car?.manufacturer = manufacturers[manufacturerPicker.selectedRowInComponent(0)]
        car?.colour = carColour.text!
        car?.year = Int(carYear.text!)
        car?.price = Float64(carPrice.text!)
        car?.summary = carSummary.text!
        car?.description = carDescription.text!
        RestApiManager.sharedInstance.saveCar(car!, onCompletion:  { car in
            
        })
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}

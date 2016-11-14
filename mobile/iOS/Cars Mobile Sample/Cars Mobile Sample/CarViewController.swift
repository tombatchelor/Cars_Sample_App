//
//  CarViewController.swift
//  Cars Mobile Sample
//
//  Created by Tom Batchelor on 11/14/16.
//  Copyright © 2016 Tom Batchelor. All rights reserved.
//

import UIKit

class CarViewController: UIViewController {

    @IBOutlet weak var carImage: UIImageView!
    @IBOutlet weak var carName: UILabel!
    @IBOutlet weak var carModel: UILabel!
    @IBOutlet weak var carColour: UILabel!
    @IBOutlet weak var carYear: UILabel!
    @IBOutlet weak var carPrice: UILabel!
    @IBOutlet weak var carSummary: UILabel!
    @IBOutlet weak var carDescription: UILabel!
    
    
    var car:Car?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        carImage.image = car?.picture
        carName.text = car?.name
        carModel.text = car?.model
        carColour.text = car?.colour
        if let year = car?.year {
            carYear.text = String(year)
        }
        if let price = car?.price {
            carPrice.text = String(price)
        }
        carSummary.text = car?.summary
        carDescription.text = car?.description
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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

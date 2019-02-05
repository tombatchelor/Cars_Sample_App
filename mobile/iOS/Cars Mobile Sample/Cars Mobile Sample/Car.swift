//
//  Car.swift
//  Cars Mobile Sample
//
//  Created by Tom Batchelor on 11/13/16.
//  Copyright © 2016 Tom Batchelor. All rights reserved.
//

import Foundation
import UIKit

class Car {
    
    var carId: Int
    var name: String
    var model: String
    var manufacturer: Manufacturer?
    var colour: String?
    var year: Int?
    var price: Float64?
    var summary: String?
    var description: String?
    var picture: UIImage?
    
    init?(carId: Int, name: String, model: String) {
        self.carId = carId
        self.name = name
        self.model = model
        
        if carId < 0 || name.isEmpty || model.isEmpty {
            return nil
        }
    }
    
    func getCarAsDict() -> [String: AnyObject] {
        var dict = [String: AnyObject]()
        dict["name"] = self.name as AnyObject;
        dict["model"] = self.model as AnyObject;
        dict["manufacturerId"] = self.manufacturer!.manufacturerId as AnyObject;
        dict["colour"] = self.colour as AnyObject;
        dict["year"] = self.year as AnyObject;
        dict["price"] = self.price as AnyObject;
        dict["summary"] = self.summary as AnyObject;
        dict["description"] = self.description as AnyObject;
        dict["wheelSize"] = 0 as AnyObject;
        dict["tyreSize"] = 0 as AnyObject;
        dict["photo"] = "0" as AnyObject;
        dict["manual"] = false as AnyObject;
        return dict
    }
}

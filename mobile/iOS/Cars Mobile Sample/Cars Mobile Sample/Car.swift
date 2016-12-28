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
        dict["name"] = self.name;
        dict["model"] = self.model;
        dict["manufacturerId"] = self.manufacturer!.manufacturerId;
        dict["colour"] = self.colour;
        dict["year"] = self.year;
        dict["price"] = self.price;
        dict["summary"] = self.summary;
        dict["description"] = self.description;
        dict["wheelSize"] = 0;
        dict["tyreSize"] = 0;
        dict["photo"] = "0";
        dict["manual"] = false;
        return dict
    }
}

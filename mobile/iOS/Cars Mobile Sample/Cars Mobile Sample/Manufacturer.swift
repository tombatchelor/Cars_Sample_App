//
//  Manufacturer.swift
//  Cars Mobile Sample
//
//  Created by Tom Batchelor on 11/7/16.
//  Copyright © 2016 Tom Batchelor. All rights reserved.
//

import Foundation
import UIKit

class Manufacturer {
    
    var manufacturerId:Int
    var name: String
    var country: String?
    var web: String?
    var email: String?
    var logo: UIImage?
    var engineId: Int?
    
    init?(manufacturerId: Int, name: String, web: String, email: String) {
        self.manufacturerId = manufacturerId
        self.name = name
        self.web = web
        self.email = email
        
        if manufacturerId < 0 || name.isEmpty {  // || logo.images?.count < 0 {
            return nil
        }
    }
    
    init?(manufacturerId: Int, name: String, country: String, web: String, email: String, logo: UIImage, engineId: Int?) {
        self.manufacturerId = manufacturerId
        self.name = name
        self.country = country
        self.web = web
        self.email = email
        self.logo = logo
        self.engineId = engineId
        
        if manufacturerId < 0 || name.isEmpty {  // || logo.images?.count < 0 {
            return nil
        }
    }
}

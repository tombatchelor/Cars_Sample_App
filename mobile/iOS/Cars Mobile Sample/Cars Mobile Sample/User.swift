//
//  User.swift
//  Cars Mobile Sample
//
//  Created by Tom Batchelor on 12/27/16.
//  Copyright © 2016 Tom Batchelor. All rights reserved.
//

import Foundation

class User {
    var username: String
    var password: String
    
    init(username: String) {
        self.username = username
        password = ""
    }
    
    func getUserAsDict() -> [String: AnyObject] {
        var dict = [String: AnyObject]()
        dict["username"] = self.username
        dict["password"] = self.password
        
        return dict
    }
}
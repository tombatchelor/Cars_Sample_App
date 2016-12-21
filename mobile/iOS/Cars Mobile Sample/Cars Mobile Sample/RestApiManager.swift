//
//  RestAPIManager.swift
//  Cars Mobile Sample
//
//  Created by Tom Batchelor on 11/7/16.
//  Copyright © 2016 Tom Batchelor. All rights reserved.
//

import Foundation
import UIKit

class RestApiManager: NSObject {
    static let sharedInstance = RestApiManager()
    
    let baseURL = "http://localhost:8080/Cars_Sample_App/"
    let restURL = "http://localhost:8080/Cars_Sample_App/public/"
    let angularURL = "http://localhost:8080/Cars_Sample_App/angular/"
    //MARK: Type getters
    
    func getManufacturers(onCompletion: ([Manufacturer]) -> Void) {
        let route = restURL + "manufacturer"
        getJSON(route, onCompletion: {json in
            var manufacturers = [Manufacturer]()
            for item in json {
                if let member = item as? [String: AnyObject] {
                    let name = member["name"] as! String
                    let id = member["manufacturerId"] as! Int
                    let web = member["web"] as! String
                    let email = member["email"] as! String
                    let manufacturer = Manufacturer(manufacturerId: id, name: name, web: web, email: email)!
                    let logoName = member["logo"] as! String
                    let imageURL = self.angularURL + "images/manufacturers/" + logoName
                    self.getImage(imageURL, onCompletion: { image in
                        manufacturer.logo = image
                    })
                    manufacturers.append(manufacturer)
                    
                }
            }

            onCompletion(manufacturers)
        })
    }
    
    func getManufacturer(manufacturerId: Int, onCompletion: (Manufacturer) -> Void) {
        let route = restURL + "manufacturer/" + String(manufacturerId)
        getJSON(route, onCompletion:  {json in
            var manufacturer:Manufacturer?
            if let member = json[0] as? [String: AnyObject] {
                let name = member["name"] as! String
                let id = member["manufacturerId"] as! Int
                let web = member["web"] as! String
                let email = member["email"] as! String
                manufacturer = Manufacturer(manufacturerId: id, name: name, web: web, email: email)!
                let logoName = member["logo"] as! String
                let imageURL = self.angularURL + "images/manufacturers/" + logoName
                self.getImage(imageURL, onCompletion: { image in
                    manufacturer!.logo = image
                })
            }
            onCompletion(manufacturer!)
        })
    }
    
    func getCarsByManufacturer(manufactureId: Int, onCompletion: ([Car]) -> Void) {
        let route = restURL + "car/manufacturer/" + String(manufactureId)
        getJSON(route, onCompletion: {json in
            var cars = [Car]()
            for item in json {
                if let member = item as? [String: AnyObject] {
                    let carId = member["carId"] as! Int
                    let name = member["name"] as! String
                    let model = member["model"] as! String
                    let car = Car(carId: carId, name: name, model: model)!
                    self.getManufacturer(manufactureId, onCompletion: {manufacturer in
                        car.manufacturer = manufacturer
                    })
                    car.picture = UIImage(named: "sample")
                    car.colour = (member["colour"] as? String)
                    car.price = Float64(member["price"] as! NSNumber)
                    car.year = Int(member["year"] as! NSNumber)
                    car.description = (member["description"] as? String)
                    car.summary = (member["summary"] as? String)
                    cars.append(car)
                }
            }
            onCompletion(cars)
        })
    }
    
    func getCarsByManufacturer(manufacture: Manufacturer, onCompletion: ([Car]) -> Void) {
        let route = restURL + "car/manufacturer/" + String(manufacture.manufacturerId)
        getJSON(route, onCompletion: {json in
            var cars = [Car]()
            for item in json {
                if let member = item as? [String: AnyObject] {
                    let carId = member["carId"] as! Int
                    let name = member["name"] as! String
                    let model = member["model"] as! String
                    let car = Car(carId: carId, name: name, model: model)!
                    car.manufacturer = manufacture
                    car.picture = UIImage(named: "sample")
                    car.colour = (member["colour"] as? String)
                    car.price = Float64(member["price"] as! NSNumber)
                    car.year = Int(member["year"] as! NSNumber)
                    car.description = (member["description"] as? String)
                    car.summary = (member["summary"] as? String)
                    cars.append(car)
                }
            }
            onCompletion(cars)
        })
    }
    
    func getCarsBySearch(searchTerm: String, onCompletion: ([Car]) -> Void) {
        let route = restURL + "car/" + searchTerm
        postJSON(route, body: ["":""], onCompletion: {json in
            var cars = [Car]()
            print(json)
            for item in json {
                if let member = item as? [String: AnyObject] {
                    let carId = member["carId"] as! Int
                    let name = member["name"] as! String
                    let model = member["model"] as! String
                    let car = Car(carId: carId, name: name, model: model)!
                    // TODO: Change to create manu from embeded manu object
                    car.manufacturer = self.getManufacturerFromDict(member["manufacturer"] as! [String: AnyObject])
                    car.picture = UIImage(named: "sample")
                    car.colour = (member["colour"] as? String)
                    car.price = Float64(member["price"] as! NSNumber)
                    car.year = Int(member["year"] as! NSNumber)
                    car.description = (member["description"] as? String)
                    car.summary = (member["summary"] as? String)
                    cars.append(car)
                }
            }
            onCompletion(cars)
        })
    }
    
    // MARK: Parsing utilities
    
    func getManufacturerFromDict(member: [String: AnyObject]) -> Manufacturer {
        var manufacturer:Manufacturer?
            let name = member["name"] as! String
            let id = member["manufacturerId"] as! Int
            let web = member["web"] as! String
            let email = member["email"] as! String
            manufacturer = Manufacturer(manufacturerId: id, name: name, web: web, email: email)!
            let logoName = member["logo"] as! String
            let imageURL = self.angularURL + "images/manufacturers/" + logoName
            self.getImage(imageURL, onCompletion: { image in
                manufacturer!.logo = image
            })
        return manufacturer!
    }
    
    // MARK: Type specific helpers
    
    func getJSON(path: String, onCompletion: (Array<NSObject>) -> Void) {
        makeHTTPGetRequest(path, onCompletion: { data in
            var json: Array<NSObject> = []
            do {
                json = try NSJSONSerialization.JSONObjectWithData(data, options: NSJSONReadingOptions()) as! Array<NSObject>
            } catch {
                print(error)
            }
            onCompletion(json)
        })
    }
    
    func postJSON(path: String, body: [String: AnyObject], onCompletion: (Array<NSObject>) -> Void) {
        makeHTTPPostRequest(path, body: body, onCompletion: { data in
            var json: Array<NSObject> = []
            do {
                json = try NSJSONSerialization.JSONObjectWithData(data, options: NSJSONReadingOptions()) as! Array<NSObject>
            } catch {
                print(error)
            }
            onCompletion(json)
        })
    }
    
    func getImage(path: String, onCompletion: (UIImage) -> Void) {
        makeHTTPGetRequest(path, onCompletion: {data in
            let image:UIImage = UIImage(data: data)!
            onCompletion(image)
        })
    }
    
    // MARK: Perform a GET Request
    private func makeHTTPGetRequest(path: String, onCompletion: (NSData) -> Void) {
        let request = NSMutableURLRequest(URL: NSURL(string: path)!)
        
        let session = NSURLSession.sharedSession()
        
        let task = session.dataTaskWithRequest(request, completionHandler: {data, response, error -> Void in
            if let jsonData = data {
                onCompletion(jsonData)
            }
        })
        task.resume()
    }
    
    // MARK: Perform a POST Request
    private func makeHTTPPostRequest(path: String, body: [String: AnyObject], onCompletion: (NSData) -> Void) {
        let request = NSMutableURLRequest(URL: NSURL(string: path)!)
        
        // Set the method to POST
        request.HTTPMethod = "POST"
        
        do {
            // Set the POST body for the request
            let jsonBody = try NSJSONSerialization.dataWithJSONObject(body, options: .PrettyPrinted)
            request.HTTPBody = jsonBody
            let session = NSURLSession.sharedSession()
            
            let task = session.dataTaskWithRequest(request, completionHandler: {data, response, error -> Void in
                if let jsonData = data {
                    onCompletion(jsonData)
                }
            })
            task.resume()
        } catch {
            // Create your personal error
            onCompletion(NSData())
        }
    }
}
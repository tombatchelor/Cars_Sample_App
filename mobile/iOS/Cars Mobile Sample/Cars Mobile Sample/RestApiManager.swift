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
    
    func getManufacturers(_ onCompletion: @escaping ([Manufacturer]) -> Void) {
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
    
    func getManufacturer(_ manufacturerId: Int, onCompletion: @escaping (Manufacturer) -> Void) {
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
    
    func getCarsByManufacturer(_ manufactureId: Int, onCompletion: @escaping ([Car]) -> Void) {
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
    
    func getCarsByManufacturer(_ manufacture: Manufacturer, onCompletion: @escaping ([Car]) -> Void) {
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
    
    func getCarsBySearch(_ searchTerm: String, onCompletion: @escaping ([Car]) -> Void) {
        let route = restURL + "car/" + searchTerm
        postJSON(route, body: ["":"" as AnyObject], onCompletion: {json in
            var cars = [Car]()
            print(json)
            for item in json {
                if let member = item as? [String: AnyObject] {
                    let carId = member["carId"] as! Int
                    let name = member["name"] as! String
                    let model = member["model"] as! String
                    let car = Car(carId: carId, name: name, model: model)!
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
    
    func saveCar(_ car: Car, onCompletion: (Car) -> Void) {
        let route = restURL + "car"
        putJSON(route, body: car.getCarAsDict())
        onCompletion(car)
    }
    
    func loginUser(_ user: User, onCompletion: (User) -> Void) {
        let route = restURL + "user/login"
        makeHTTPPostRequest(route, body: user.getUserAsDict(), onCompletion: {data in
            
        })
    }
    
    func getUser(_ onCompletion: @escaping (User?) -> Void) {
        let route = restURL + "user"
        getJSON(route, onCompletion: {json in
            var user:User?
            if let member = json[0] as? [String: AnyObject] {
                let username = member["username"] as? String
                if (username != nil) {
                    user = User(username: username!)
                }
            }
            onCompletion(user)
        })
        
    }
    
    func logoutUser() {
        let route = restURL + "user/logout"
        makeHTTPGetRequest(route, onCompletion: {data in
        })
    }
    
    // MARK: Parsing utilities
    
    func getManufacturerFromDict(_ member: [String: AnyObject]) -> Manufacturer {
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
    
    func getJSON(_ path: String, onCompletion: @escaping (Array<NSObject>) -> Void) {
        makeHTTPGetRequest(path, onCompletion: { data in
            var json: Array<NSObject> = []
            
            var cleanedData = Data()
            var dataString = NSString(data: data, encoding: String.Encoding.utf8.rawValue)!
            if dataString.character(at: 0) != ("[" as NSString).character(at: 0)  {
                dataString = "[" + (dataString as String) + "]" as NSString
                print(dataString)
                cleanedData = dataString.data(using: String.Encoding.utf8.rawValue)!
            } else {
                cleanedData = data
            }
            
            do {
                json = try JSONSerialization.jsonObject(with: cleanedData, options: JSONSerialization.ReadingOptions()) as! Array<NSObject>
            } catch {
                print(error)
            }
            onCompletion(json)
        })
    }
    
    func postJSON(_ path: String, body: [String: AnyObject], onCompletion: @escaping (Array<NSObject>) -> Void) {
        makeHTTPPostRequest(path, body: body, onCompletion: { data in
            var json: Array<NSObject> = []
            do {
                json = try JSONSerialization.jsonObject(with: data, options: JSONSerialization.ReadingOptions()) as! Array<NSObject>
            } catch {
                print(error)
            }
            onCompletion(json)
        })
    }
    
    func putJSON(_ path: String, body: [String: AnyObject]) {
        makeHTTPPutRequest(path, body: body, onCompletion: { data in
        })
    }
    
    func getImage(_ path: String, onCompletion: @escaping (UIImage) -> Void) {
        makeHTTPGetRequest(path, onCompletion: {data in
            let image:UIImage = UIImage(data: data)!
            onCompletion(image)
        })
    }
    
    // MARK: Perform a GET Request
    fileprivate func makeHTTPGetRequest(_ path: String, onCompletion: @escaping (Data) -> Void) {
        let url = path.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)!
        let request = NSMutableURLRequest(url: URL(string: url)!)
        
        let session = URLSession.shared
        
        let task = session.dataTask(with: request as URLRequest, completionHandler: {data, response, error -> Void in
            if let jsonData = data {
                onCompletion(jsonData)
            }
        })
        task.resume()
    }
    
    // MARK: Perform a POST Request
    fileprivate func makeHTTPPostRequest(_ path: String, body: [String: AnyObject], onCompletion: @escaping (Data) -> Void) {
        let url = path.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)!
        let request = NSMutableURLRequest(url: URL(string: url)!)
    
        // Set the method to POST
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        do {
            // Set the POST body for the request
            let jsonBody = try JSONSerialization.data(withJSONObject: body, options: .prettyPrinted)
            request.httpBody = jsonBody
            let session = URLSession.shared
            
            let task = session.dataTask(with: request as URLRequest, completionHandler: {data, response, error -> Void in
                if let jsonData = data {
                    onCompletion(jsonData)
                }
            })
            task.resume()
        } catch {
            // Create your personal error
            onCompletion(Data())
        }
    }
    
    // MARK: Perform a PUT Request
    fileprivate func makeHTTPPutRequest(_ path: String, body: [String: AnyObject], onCompletion: @escaping (Data) -> Void) {
        let url = path.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)!
        let request = NSMutableURLRequest(url: URL(string: url)!)
        
        // Set the method to POST
        request.httpMethod = "PUT"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        do {
            // Set the POST body for the request
            let jsonBody = try JSONSerialization.data(withJSONObject: body, options: .prettyPrinted)
            request.httpBody = jsonBody
            let session = URLSession.shared
            
            let task = session.dataTask(with: request as URLRequest, completionHandler: {data, response, error -> Void in
                if let jsonData = data {
                    onCompletion(jsonData)
                }
            })
            task.resume()
        } catch {
            // Create your personal error
            onCompletion(Data())
        }
    }
}

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
    
    let baseURL = "http://localhost:8080/Cars_Sample_App/public/"
    
    func getManufacturers(onCompletion: ([Manufacturer]) -> Void) {
        let route = baseURL + "manufacturer"
        makeHTTPGetRequest(route, onCompletion: { data in
            //let manufacturers:Array<Manufacturer> = [Manufacturer(manufacturerId: 1, name: "Test", country: "UK", web: "www.uk.com", email: "email@mail.com", logo: UIImage(named: "sample")!, engineId: 0)!]
            var json: Array<NSObject> = []
            do {
                json = try NSJSONSerialization.JSONObjectWithData(data, options: NSJSONReadingOptions()) as! Array<NSObject>
            } catch {
                print(error)
            }
            
            var manufacturers = [Manufacturer]()
            for item in json {
                if let member = item as? [String: AnyObject] {
                    let name = member["name"] as! String
                    let id = member["manufacturerId"] as! Int
                    let web = member["web"] as! String
                    let email = member["email"] as! String
                    let logoName = member["logo"] as! String
                    manufacturers.append(Manufacturer(manufacturerId: id, name: name, web: web, email: email, logoName: logoName)!)
                }
            }

            onCompletion(manufacturers)
        })
    }
    
    func getRandomUser(onCompletion: (NSObject) -> Void) {
        let route = baseURL
        makeHTTPGetRequest(route, onCompletion: { data in
            var json: Array<NSObject> = []
            do {
                json = try NSJSONSerialization.JSONObjectWithData(data, options: NSJSONReadingOptions()) as! Array<NSObject>
            } catch {
                print(error)
            }
            onCompletion(json)
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
//
//  SearchViewController.swift
//  Cars Mobile Sample
//
//  Created by Tom Batchelor on 12/13/16.
//  Copyright © 2016 Tom Batchelor. All rights reserved.
//

import UIKit

class SearchViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    var cars = [Car]();
    @IBOutlet weak var tableView: UITableView!
    let carDetailSegue  = "CarDetailSegue"
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        //self.tableView.registerClass(UITableViewCell.self, forCellReuseIdentifier: "CarTableViewCell")
        
        tableView.delegate = self
        tableView.dataSource = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    // MARK: Actions
    
    @IBAction func searchField(sender: UITextField) {
        print(sender.text!)
        RestApiManager.sharedInstance.getCarsBySearch(sender.text!, onCompletion: {cars in
            self.cars = cars
            dispatch_async(dispatch_get_main_queue(), {
                self.tableView.reloadData()
                return
            })
        })
    }
    // MARK - TableView Stuff

    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {X
        return cars.count
    }
    

    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cellIdentifier = "CarTableViewCell"
        let cell = tableView.dequeueReusableCellWithIdentifier(cellIdentifier, forIndexPath: indexPath) as! CarTableViewCell
        print(indexPath.row)
        let car = cars[indexPath.row]
        
        // Configure the cell
        cell.carShortDescription.text = car.name + " - " + car.model
        cell.manufacturerLogo.image = car.manufacturer!.logo
        cell.carPicture.image = car.picture!
        
        return cell
    }
    /*
    // MARK: - Navigation

    */
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if  segue.identifier == carDetailSegue,
            let destination = segue.destinationViewController as? CarViewController,
            carIndex = tableView.indexPathForSelectedRow?.row {
                destination.car = cars[carIndex]
        }
    }

}

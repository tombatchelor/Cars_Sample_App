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
    
    @IBAction func searchField(_ sender: UITextField) {
        print(sender.text!)
        RestApiManager.sharedInstance.getCarsBySearch(sender.text!, onCompletion: {cars in
            self.cars = cars
            DispatchQueue.main.async(execute: {
                self.tableView.reloadData()
                return
            })
        })
    }
    // MARK - TableView Stuff

    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return cars.count
    }
    

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cellIdentifier = "CarTableViewCell"
        let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as! CarTableViewCell
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
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if  segue.identifier == carDetailSegue,
            let destination = segue.destination as? CarViewController,
            let carIndex = tableView.indexPathForSelectedRow?.row {
                destination.car = cars[carIndex]
        }
    }

}

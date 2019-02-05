//
//  ViewController.swift
//  Cars Mobile Sample
//
//  Created by Tom Batchelor on 11/6/16.
//  Copyright © 2016 Tom Batchelor. All rights reserved.
//

import UIKit

class HomeViewController: UIViewController {

    @IBOutlet weak var userLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func viewWillAppear(_ animated: Bool) {
        RestApiManager.sharedInstance.getUser({user in
            if (user != nil) {
                self.userLabel.text = user!.username
            } else {
                self.userLabel.text = "Anonymous"
            }
        })
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: Actions
    
    @IBAction func logout(_ sender: AnyObject) {
        RestApiManager.sharedInstance.logoutUser()
    }

}


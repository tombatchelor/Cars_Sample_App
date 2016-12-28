//
//  CarTableViewCell.swift
//  Cars Mobile Sample
//
//  Created by Tom Batchelor on 11/13/16.
//  Copyright © 2016 Tom Batchelor. All rights reserved.
//

import UIKit

class CarTableViewCell: UITableViewCell {
    
    //MARK - Properties
    @IBOutlet weak var carShortDescription: UILabel!
    @IBOutlet weak var manufacturerLogo: UIImageView!
    @IBOutlet weak var carPicture: UIImageView!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}

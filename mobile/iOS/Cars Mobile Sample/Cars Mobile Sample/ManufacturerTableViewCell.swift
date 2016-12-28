//
//  ManufacturerTableViewCell.swift
//  Cars Mobile Sample
//
//  Created by Tom Batchelor on 11/7/16.
//  Copyright © 2016 Tom Batchelor. All rights reserved.
//

import UIKit

class ManufacturerTableViewCell: UITableViewCell {

    // MARK: Properties
    @IBOutlet weak var manuName: UILabel!
    @IBOutlet weak var manuLogo: UIImageView!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}

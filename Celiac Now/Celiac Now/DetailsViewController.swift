//
//  DetailsViewController.swift
//  
//
//  Created by Austin Bouley on 3/11/19.
//

import UIKit
import SwiftSoup

class DetailsViewController: UIViewController {

    var scannedCode:String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        view.backgroundColor = .white
        determineGluten()
        

        // Setup label and button layout
        view.addSubview(codeLabel)
        codeLabel.centerYAnchor.constraint(equalTo: view.centerYAnchor, constant: -100).isActive = true
        codeLabel.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16).isActive = true
        codeLabel.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16).isActive = true
        codeLabel.heightAnchor.constraint(equalToConstant: 50).isActive = true
        if let scannedCode = scannedCode {
            codeLabel.text = scannedCode
        }
        
        view.addSubview(scanButton)
        scanButton.widthAnchor.constraint(equalTo: view.widthAnchor, multiplier: 0.5).isActive = true
        scanButton.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        scanButton.heightAnchor.constraint(equalToConstant: 50).isActive = true
        scanButton.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: -50).isActive = true
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    let codeLabel:UILabel = {
        let codeLabel = UILabel()
        codeLabel.textAlignment = .center
        codeLabel.backgroundColor = .white
        codeLabel.translatesAutoresizingMaskIntoConstraints = false
        return codeLabel
    }()
    
    lazy var scanButton:UIButton = {
        let scanButton = UIButton(type: .system)
        scanButton.setTitle("Scan Again", for: .normal)
        scanButton.setTitleColor(.white, for: .normal)
        scanButton.titleLabel?.font = UIFont.boldSystemFont(ofSize: 16)
        scanButton.backgroundColor = .orange
        scanButton.layer.cornerRadius = 25
        scanButton.addTarget(self, action: #selector(displayBarcodeViewController), for: .touchUpInside)
        scanButton.translatesAutoresizingMaskIntoConstraints = false
        
        return scanButton
    }()
    
    @objc func displayBarcodeViewController() {
        let scanViewController = BarcodeViewController()
        //navigationController?.pushViewController(scanViewController, animated: true)
        //navigationController?.present(scanViewController, animated: true, completion: nil)
        present(scanViewController, animated: true, completion: nil)
    }
    
    func determineGluten()
    {
        let code: String = self.scannedCode!
        var html = ""
        
        guard let url = URL(string: "https://www.barcodelookup.com/\(code)") else {
            print("Error: doesn't seem to be a valid URL")
            return }
        
        do{
            html = try String(contentsOf: url, encoding: String.Encoding.utf8)
            print(html)
        } catch let error {
            print("Error 1: \(error)")
        }
        
        if html.lowercased().contains("wheat") == true{
            self.scannedCode = "Contains Wheat"
        }
        else{
            self.scannedCode = "Gluten Free"
        }
        
        
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}

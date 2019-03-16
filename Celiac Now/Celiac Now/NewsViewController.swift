//
//  NewsViewController.swift
//  Celiac Now
//
//  Created by Austin Bouley on 3/7/19.
//  Copyright © 2019 Austin Bouley. All rights reserved.
//

import UIKit
import SwiftSoup

class NewsViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    @IBOutlet weak var tableView: UITableView!
    
    
    var titles: [String] = [""]
    var dates: [String] = [""]
    var summaries: [String] = [""]
    var links: [String] = [""]
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        getBeyondCeliac()
        
        //table
        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
        self.tableView.dataSource = self
        self.tableView.delegate = self
        
        
        // Do any additional setup after loading the view.
    }
    
    //get html code from website
    func getBeyondCeliac()
    {
        
        var html = ""
        
        guard let url = URL(string: "https://www.beyondceliac.org/research-news/") else {
            print("Error: doesn't seem to be a valid URL")
            return
        }
        
        do {
            html = try String(contentsOf: url, encoding: .ascii)
        } catch let error {
            print("Error 1: \(error)")
        }
        
        do {
            let doc: Document = try SwiftSoup.parse(html)
        
            //titles
            let blogTitles: Elements = try doc.getElementsByClass("blog-post__title")
            let titlesNoSpan: String = try blogTitles.toString().replacingOccurrences(of: "</span>", with: "#", options: .literal, range: nil)
            let titlesTemp: Document = try SwiftSoup.parse(titlesNoSpan)
            let titles: [String] = try titlesTemp.text().components(separatedBy: "#")
            self.titles = titles
            
            //references
            let blogLink: Elements = try doc.getElementsByClass("blog-post__title")
            let linkWithA: Elements = try blogLink.select("a")
            var linkTemp: [String] = try linkWithA.toString().components(separatedBy: "\n")
            
            for i in 0..<linkTemp.count{
                let temp = linkTemp[i]
                let pattern = "\"([^\"]+)\""
                if let range = temp.range(of: pattern, options: .regularExpression){
                    linkTemp[i] = String(temp[range])
                }
            }
    
            self.links = linkTemp
            
            
            //dates
            let blogDates: Elements = try doc.getElementsByClass("blog-post__date")
            let dates: [String] = try blogDates.text().components(separatedBy: " ")
            self.dates = dates
            
            //summaries
            let blogSummaries: Elements = try doc.getElementsByClass("blog-post__summary")
            let summariesNoP: String = try blogSummaries.toString().replacingOccurrences(of: "</p>", with: "#", options: .literal, range: nil)
            let summariesTemp: Document = try SwiftSoup.parse(summariesNoP)
            let summaries: [String] = try summariesTemp.text().components(separatedBy: "#")
            self.summaries = summaries
            
        
            
        } catch is Exception {
            print("error 2")
        } catch {
            print("error 3")
        }
        
        
    }
    
    
    //from UITableDataSource
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        //number of articles in array
        return 25
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell")! as UITableViewCell
        let item = self.titles[indexPath.item] + "\n------------\n" + self.dates[indexPath.item] + "\n------------\n" + self.summaries[indexPath.item]
        cell.textLabel?.numberOfLines = 10
        cell.textLabel?.lineBreakMode = .byWordWrapping
        cell.textLabel?.text = item
        return cell
    }
    
    
    //from UITableDelegate
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath)
    {
        let ac = UIAlertController(title: "This is the link", message: links[indexPath.row], preferredStyle: .alert)
        ac.addAction(UIAlertAction(title: "OK", style: .default))
        present(ac, animated: true)
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

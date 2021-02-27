#include <iostream>
#include <fstream>
#include <vector>
#include <sstream>

using namespace std;

struct item 
{
	string id;
	string name;
	float price;
};

struct customer
{
	int id;
	string name;
};

int main()	
{
	ifstream finMenu;
	
	finMenu.open("MenuMap.csv");
	
	vector<item> menu;
	
	string line;
	string id;
	string name;
	string price;
	item temp;
	
	while (getline(finMenu, line))
	{
		stringstream s(line);
		
		getline(s, id, ',');
		getline(s, name, ',');
		getline(s, price, '\n');
		
		temp.id = id;
		temp.name = name;
		temp.price = stod(price);
		
		menu.push_back(temp);
	}
	
	ifstream finCustomers;
	
	finCustomers.open("customer.csv");
	
	vector<customer> customers;
	
	string customerId;
	string firstName;
	string lastName;
	customer temp2;
	
	while (getline(finCustomers, line))
	{
		stringstream s(line);
		
		getline(s, lastName, ',');
		getline(s, firstName, ',');
		getline(s, customerId, ',');
		
		temp2.id = stoi(customerId);
		temp2.name = firstName + lastName;
		
		customers.push_back(temp2);
	}
	
	ifstream fin;
	fstream fout;
	
	fin.open("allNamesDatesOrders.csv");
	fout.open("orders.csv", ios::out);
	
	int orderId = 0;
	int n_items = 0;
	int n_customers = customers.size();
	cout << n_customers;
	string entreeList = "";
	string sideList = "";
	string beverageList = "";
	string dessertList = "";
	string fullOrder = "";
	string customerName = "";
	string date = "";
	string word;
	double cost;
	
	while (getline(fin, line))
	{
		fout << orderId << ",";

		stringstream s(line);
		
		getline(s, lastName, ',');
		
		getline(s, firstName, ',');
		
		firstName = firstName.substr(1, firstName.length());
		
		customerName = firstName + lastName;
		
		for (int i = 0; i < n_customers; ++i)
		{
			//cout << customers[i].name << " " << customerName << endl;
			if (customers[i].name == customerName)
			{
				fout << customers[i].id << ",";
				break;
			}
		}
		
		getline(s, date, ',');
		date = date.substr(1, date.length());
		
		fout << date << ",";
		
		cost = 0;
		n_items = 0;
		entreeList = "";
		sideList = "";
		beverageList = "";
		dessertList = "";
		fullOrder = "";
		
		while (getline(s, word, ','))
		{
			word = word.substr(1, word.length());
			for (int i = 0; i < 18; ++i) 
			{
				if (word == menu[i].id) 
				{
					if (word[0] == 'E') 
					{
						if (entreeList != "") 
						{
							entreeList += ":";
						}
						entreeList += menu[i].name;
					}
					else if (word[0] == 'S') 
					{
						if (sideList != "") 
						{
							sideList += ":";
						}
						sideList += menu[i].name;
					}
					else if (word[0] == 'B') 
					{
						if (beverageList != "") 
						{
							beverageList += ":";
						}
						beverageList += menu[i].name;
					}
					else
					{
						if (dessertList != "") 
						{
							dessertList += ":";
						}
						dessertList += menu[i].name;
					}
					cost += menu[i].price;
					n_items++;
					break;
				}
			}		
		}
		
		fout << cost << "," << n_items << ",";
		
		fout << entreeList << "," << sideList << ",";
		fout << beverageList << "," << dessertList << "\n";
		
		orderId++;
	}
}
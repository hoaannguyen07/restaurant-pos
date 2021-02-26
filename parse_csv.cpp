#include <iostream>
#include <vector>

#include <string.h>
#include <fstream>
#include <sstream>
#include <iomanip> // use setfill('0') to put preceding 0's when necessary

#include <cctype> // ::isspace used
#include <algorithm>

#include <set>
#include <map>

#include <stdlib.h> // rand()

struct cust_info
{
    std::string lastname;
    std::string firstname;
    int id;
    int password;
    int payment;
};

std::vector<std::vector<std::string>> read_csv_to_vector (std::string filename)
{
    // 2D vector to put all my results in. Each row will contain vector
    // of the words on that line in the csv file minus the deliminater 
    std::vector<std::vector<std::string>> result;

    // open csv file using the input stream & make sure that there are no errors w/ opening it
    std::ifstream input_csv_file (filename);
    if (!input_csv_file.is_open())
    {
        std::cerr << "Could not open file '" << filename << "'";
        exit(1);
    }

    // initialize var to hold a whole line of text at a time.
    std::string line;

    // go through file, inputting one line at a time, then parsing that line into individual components
    while(getline(input_csv_file, line))
    {
        std::vector<std::string> info_csv_line; // vector to keep info of indiv. components of the csv line
        std::string info; // used to hold the individual info of each column

        // make the line into a string stream so that it could further be broken down into its individual components
        std::stringstream line_ss (line);

        // read the columns of data using the stringstream and push it into the info_csv_line vector
        while(getline(line_ss, info, ','))
        {
            info.erase(std::remove_if(info.begin(), info.end(), ::isspace), info.end()); // trim white spaces
            info_csv_line.push_back(info);
        }

        // append info-csv_line vector to result vector because it represents all info of that specific line
        result.push_back(info_csv_line);
    }

    return result;
}

void create_csv_only_names(std::string out_csv_name, std::vector<std::vector<std::string>> &csv_data)
{
    // create a stream for the output file and make sure that it is created
    std::ofstream output_csv_file(out_csv_name);
    if (!output_csv_file.is_open())
    {
        std::cerr << "Could not open file '" << out_csv_name << "'";
        exit(1);
    }
    
    /*line example: 
    lastname, firstname
    lastname, firstname
    */
   // go through 2D vector and for each row, only take out the first 2 elements (b/c they are lastname & firstname) to output to csv
   // csv_data[i][0] = last name
   // csv_data[i][1] = firstname
   for(int i = 0; i < csv_data.size(); i++)
   {
       for(int j = 0; j < 2; j++)
       {
           output_csv_file << csv_data[i][0] << ", " << csv_data[i][1] << std::endl;
       }
   }

   output_csv_file.close();
}

std::vector<cust_info> create_customer_vector(std::vector<std::vector<std::string>> &csv_data)
{
    // lastname, firstname, ID, Password, Payment Method = 0
    std::vector<cust_info> customer_table;

    std::set<std::string> cust_names;

    for(int i = 0; i < csv_data.size(); i++)
    {
        std::string cur_cust_name = csv_data[i][0] + " " + csv_data[i][1];
        cust_names.insert(cur_cust_name);
    }

    int id_password = 1;
    for(std::set<std::string>::iterator it = cust_names.begin(); it != cust_names.end(); it++)
    {
        std::stringstream name(*it);

        // get last & first name of customer from set & trim strings before creating a cust_info with it
        std::string last_name, first_name;
        name >> last_name >> first_name;

        last_name.erase(std::remove_if(last_name.begin(), last_name.end(), ::isspace), last_name.end()); // trim white spaces
        first_name.erase(std::remove_if(first_name.begin(), first_name.end(), ::isspace), first_name.end()); // trim white spaces

        cust_info cur_customer;
        cur_customer.lastname = last_name;
        cur_customer.firstname = first_name;
        cur_customer.id = id_password;
        cur_customer.password = id_password;
        cur_customer.payment = 0;

        customer_table.push_back(cur_customer);

        id_password++;
    }

    return customer_table;
}

void create_customer_csv(std::string customer_csv_name, std::vector<cust_info> &customer_data)
{
    // create a stream for the output file and make sure that it is created
    std::ofstream output_csv_file(customer_csv_name);
    if (!output_csv_file.is_open())
    {
        std::cerr << "Could not open file '" << customer_csv_name << "'";
        exit(1);
    }
    
    for(cust_info cur_cust : customer_data)
    {
        output_csv_file << cur_cust.lastname << ", " << cur_cust.firstname << ", " << cur_cust.id << ", " << cur_cust.password << ", " << cur_cust.payment << std::endl;
    }
    
    output_csv_file.close();
}


std::map<std::string, int> create_visitation_map(std::vector<std::vector<std::string>> &csv_data)
{
    std::map<std::string, int> visitation_map;

    for(int i = 0; i < csv_data.size(); i++)
    {
        std::string cur_cust_name = csv_data[i][0] + " " + csv_data[i][1];
        // if cur_cust_name is already in the map, increment the amount of visits the person has by 1.
        // else, that person visits for the first time
        if (visitation_map.find(cur_cust_name) != visitation_map.end())
        {
            visitation_map[cur_cust_name]++; // increment by 1 after the visit
        }
        else 
        {
            visitation_map[cur_cust_name] = 1;
        }
    }

    int total_visits = 0;
    for(std::map<std::string, int>::iterator i = visitation_map.begin(); i != visitation_map.end(); i++)
    {
        total_visits += i->second;
    }

    std::cout << "The restaurant was visited " << total_visits << " times\n";

    return visitation_map;
}


void create_visitation_csv (std::string filename, std::map<std::string, int> &visitation_map)
{
    // create a stream for the output file and make sure that it is created
    std::ofstream output_csv_file(filename);
    if (!output_csv_file.is_open())
    {
        std::cerr << "Could not open file '" << filename << "'";
        exit(1);
    }


    int cust_id = 1; // keep track of customer id affiliated with visitation (map is sorted like the set)
    for(auto i = visitation_map.begin(); i != visitation_map.end(); i++)
    {
        output_csv_file << cust_id << ", " << i->second << std::endl;

        cust_id++;
    }

    output_csv_file.close();
}

int main()
{
    std::vector<std::vector<std::string>> csv_data = read_csv_to_vector("allNamesDatesOrders.csv");

    std::cout << "There are " << csv_data.size() << " lines in this csv file" << std::endl;
    
    // std::cout << std::endl;

    // std::cout << "This is a sample of the data:" << std::endl;
    // for(int i = 0; i < 10; i++)
    // {
    //     for(std::string s : csv_data[i])
    //     {
    //         std::cout << s << ' ';
    //     }

    //     std::cout << std::endl;
    // }

    // std::string only_names_csv = "onlyNames.csv";
    // create_csv_only_names(only_names_csv, csv_data);

    // create a customer info csv
    std::vector<cust_info> customer_data_table = create_customer_vector(csv_data);
    create_customer_csv("customer.csv", customer_data_table);

    std::map<std::string, int> cust_visitation_map = create_visitation_map(csv_data);
    create_visitation_csv("visitation.csv", cust_visitation_map);

    return 0;
}
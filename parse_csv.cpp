#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include <vector>

#include <cctype> // ::isspace used
#include <algorithm>

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
}

std::vector<cust_info> create_customer_vector(std::vector<std::vector<std::string>> &csv_data)
{
    // lastname, firstname, ID, Password, Payment Method = 0
    std::vector<cust_info> customer_table;

    for(int i = 0; i < csv_data.size(); i++)
    {
        cust_info cur_customer;
        cur_customer.lastname = csv_data[i][0];
        cur_customer.firstname = csv_data[i][1];
        cur_customer.id = i;
        cur_customer.password = i;
        cur_customer.payment = 0;

        customer_table.push_back(cur_customer);
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
   
}

int main()
{
    std::vector<std::vector<std::string>> csv_data = read_csv_to_vector("allNamesDatesOrders.csv");

    std::cout << "There are " << csv_data.size() << " lines in this csv file" << std::endl;
    
    std::cout << std::endl;

    std::cout << "This is a sample of the data:" << std::endl;
    for(int i = 0; i < 10; i++)
    {
        for(std::string s : csv_data[i])
        {
            std::cout << s << ' ';
        }

        std::cout << std::endl;
    }

    // std::string only_names_csv = "onlyNames.csv";
    // create_csv_only_names(only_names_csv, csv_data);

    // create a customer info csv
    std::vector<cust_info> customer_data_table = create_customer_vector(csv_data);
    create_customer_csv("customer.csv", customer_data_table);


    return 0;
}
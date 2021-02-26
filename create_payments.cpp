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
#include <chrono>
#include <windows.h>

const int VISA = 0;
const int MASTERCARD = 1;

struct card_info
{
    bool payment_type;
    std::string card_num;
    std::string card_comp;
    std::string exp_date;
    std::string ccv;
    int cust_id;
};

int create_boolean()
{

    srand(time(0));
    return rand() % 2;
}

std::string create_card_num()
{
    std::string card_number;
    
    srand(time(0));
    for(int i = 0; i < 2; i++)
    {
        
        // create a random 1-digit integer and add it to the card_number string
        int rand_num = rand() % (int)(1e8); // random numbers from 0 to 9

        card_number += std::to_string(rand_num);
    }

    return card_number;
}

// create a random month string with appropriate preceding 0's
std::string create_month()
{
    srand(time(0));
    // get random month from 1 to 12
    int rand_month = (rand() % 12) + 1;

    // put appropriate preceding 0's
    std::stringstream month_stream;
    month_stream << std::setfill('0') << std::setw(2) << rand_month;

    // return what's put into the stream in string form
    return month_stream.str();
}

std::vector<std::string> create_card_num_vec()
{
    std::vector<std::string> card_num_table;

    for(int i = 0; i < 50; i++)
    {
        card_num_table.push_back(create_card_num());
        Sleep(1000);
    }

    return card_num_table;
}

void create_card_num_csv(std::string filename, std::vector<std::string> &card_num_table)
{
    // create a stream for the output file and make sure that it is created
    std::ofstream output_csv_file(filename);
    if (!output_csv_file.is_open())
    {
        std::cerr << "Could not open file '" << filename << "'";
        exit(1);
    }

    // for(std::string cur_card_num : card_num_table)
    // {
    //     output_csv_file << cur_card_num << std::endl;
    // }

    for(int i = 0; i < 159978; i++)
    {
        output_csv_file << create_card_num() << std::endl;
        Sleep(1000);
    }
}

// create random year from 2022 to 2030
std::string create_year()
{
    srand(time(0));
    int rand_year = (rand() % 9) + 2022; // random number from 2022 to 2030

    return std::to_string(rand_year);
}

std::string create_exp_date()
{
    return create_month() + "-" + create_year();
}

// create 3-digit security code for card
std::string create_ccv()
{
    srand(time(0));
    int rand_num = rand() % 1000;

    std::stringstream ccv_stream;
    ccv_stream << std::setfill('0') << std::setw(3) << rand_num;

    return ccv_stream.str();
}

// create a unique card for each of the 159977 unique customers
std::vector<card_info> create_card_info_table()
{
    std::vector<card_info> card_info_table;

    // 159978
    for(int i = 0; i < 159977; i++)
    {
        card_info cur_payment;
        cur_payment.payment_type = create_boolean();
        std::stringstream card_num_ss;
        card_num_ss << std::setfill('0') << std::setw(16) << i;
        // cur_payment.card_num = create_card_num();

        cur_payment.card_num = card_num_ss.str();

        int card_type_choice = create_boolean();
        if (card_type_choice == VISA)
        {
            cur_payment.card_comp = "VISA";
        }
        else
        {
            cur_payment.card_comp = "MASTERCARD";
        }

        cur_payment.exp_date = create_exp_date();
        cur_payment.ccv = create_ccv();
        cur_payment.cust_id = i;

        card_info_table.push_back(cur_payment);

        // Sleep(1000); // sleep for 0.1s
    }

    return card_info_table;
}

card_info create_card(int customer_id)
{
    card_info cur_payment;
    cur_payment.payment_type = create_boolean();
    cur_payment.card_num = create_card_num();

    int card_type_choice = create_boolean();
    if (card_type_choice == VISA)
    {
        cur_payment.card_comp = "VISA";
    }
    else
    {
        cur_payment.card_comp = "MASTERCARD";
    }

    cur_payment.exp_date = create_exp_date();
    cur_payment.ccv = create_ccv();
    cur_payment.cust_id = customer_id;

    return cur_payment;
}

void create_card_csv(std::string filename, std::vector<card_info> &card_info_table)
{
    // create a stream for the output file and make sure that it is created
    std::ofstream output_csv_file(filename);
    if (!output_csv_file.is_open())
    {
        std::cerr << "Could not open file '" << filename << "'";
        exit(1);
    }

    for(card_info cur_card : card_info_table)
    {
        output_csv_file << cur_card.payment_type << ", " << cur_card.card_num << ", " 
            << cur_card.card_comp << ", " << cur_card.exp_date << ", " 
            << cur_card.ccv << ", " << cur_card.cust_id << std::endl;
    }

    output_csv_file.close();
}

int main()
{
    // std::cout << "Sample Boolean: " << create_boolean() << std::endl;
    // std::cout << "Sample Card Number: " << create_card_num() << std::endl;
    // std::cout << "Sample Month: " << create_month() << std::endl;
    // std::cout << "Sample Year: " << create_year() << std::endl;
    // std::cout << "Sample Expiration Date: " << create_exp_date() << std::endl;
    // std::cout << "Sample CCV: " << create_ccv() << std::endl;

    std::vector<card_info> card_info_table = create_card_info_table();
    create_card_csv("card_info.csv", card_info_table);

    // std::vector<std::string> card_num_table = create_card_num_vec();
    // create_card_num_csv("card_num.csv", card_num_table);

    return 0;
}
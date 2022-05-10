# Individual Project - Sai Santhosh Yamsani - 015386683

## Description:
The main idea of the project is to create a java application with design patterns and a static database. For every re-run, the changes in the data should not persist.
This application has to permit the customer to buy items, where the customer will be limited on the maximum limit for the particular category. Whenever the customer input prerequisite is fulfilled, a bill sum is calculated and the order is processed else a message incorporating the incorrect quantities is to be displayed.

### Tables in Static Database
Table 1: Dataset - This table contains all the items in the inventory
Table 2: Cards - This table contains the list of credit/debit cards that are being used to make the order.

The input file will contain an order including Items, Quantity needed, and the credit/debit card number.

## Requirements: Java Runtime, Eclipse IDE

## Instructions to run the application:
1. Clone the repository (https://github.com/gopinathsjsu/individual-project-santhoshsai3.git)
2. Open the cloned repository in eclipse IDE
3. Set the arguments for the project to the relevant input, dataset, and cards file,"Dataset.csv" "Cards.csv" "Input.csv". (Note: these files must be present in the root directory of the project)
4. Run the application


## Design Patterns
Singleton Data Pattern: The Singleton pattern ensures that a class has only one instance and provides a global point of access to that instance. In my application, the static database needs exactly only one instance. Hence Singleton Data Pattern is used here. 

Adapter Design Pattern: Adapter pattern works as a bridge between two incompatible interfaces. This type of design pattern comes under structural pattern as this pattern combines the capability of two independent interfaces. The FileUtility class reads input files into Inventory and Order.

Composite Design Pattern: Composite pattern is a partitioning design pattern and describes a group of objects that is treated the same way as a single instance of the same type of object. In my application, the order class contains array of order items where each order item is treated like entire order. 
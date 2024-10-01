package assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingUtilities;

public class Assignment {
    // for user registration and login
    private static HashMap<String, String> userDatabase = new HashMap<String, String>();
    public static String userIDLogin;
    public static boolean loggedIn = checkIfLoggedIn();
    // store employee records in arraylist
    private static List<String> employeeIDE = new ArrayList<>();
    private static List<String> employeeNameE = new ArrayList<>();
    private static List<String> employeeStatusE = new ArrayList<>();
    private static List<String> passwordE = new ArrayList<>();
    // store customer records in arraylist
    private static List<String> customerIDC = new ArrayList<>();
    private static List<String> customerNameC = new ArrayList<>();
    private static List<String> phoneNumberC = new ArrayList<>();
    private static List<String> postcodeC = new ArrayList<>();
    //// store sales records in arraylist
    private static List<String> salesIDS = new ArrayList<>();
    private static List<String> dateTimeS = new ArrayList<>();
    private static List<String> carPlateS = new ArrayList<>();
    private static List<String> customerIDS = new ArrayList<>();
    private static List<String> employeeIDS = new ArrayList<>();
    // store vehicle records in arraylist
    private static List<String> carPlateV = new ArrayList<>();
    private static List<String> carModelV = new ArrayList<>();
    private static List<String> acquiredPriceV = new ArrayList<>();
    private static List<String> carStatusV = new ArrayList<>();
    private static List<String> salesPriceV = new ArrayList<>();

    public static String[] month = { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };
    public static int[] cases = new int[12];
    // format of date and time
    public static LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // read from employee.csv and add the records into the list respectively
        try {
            BufferedReader in = new BufferedReader(new FileReader("employee.csv"));
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                String[] column = currentLine.split(",", -1);
                employeeIDE.add(column[0]);
                employeeNameE.add(column[1]);
                employeeStatusE.add(column[2]);
                passwordE.add(column[3]);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: employee.csv");
        } catch (IOException e) {
            System.out.println("Problem with file input: employee.csv");
        }
        // read from customer.csv
        try {
            BufferedReader in = new BufferedReader(new FileReader("customer.csv"));
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                String[] column = currentLine.split(",", -1);
                customerIDC.add(column[0]);
                customerNameC.add(column[1]);
                phoneNumberC.add(column[2]);
                postcodeC.add(column[3]);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: customer.csv");
        } catch (IOException e) {
            System.out.println("Problem with file input: customer.csv");
        }
        // read from sales.csv
        try {
            BufferedReader in = new BufferedReader(new FileReader("sales.csv"));
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                String[] column = currentLine.split(",", -1);
                if (column.length >= 5) {
                    salesIDS.add(column[0]);
                    dateTimeS.add(column[1]);
                    carPlateS.add(column[2]);
                    customerIDS.add(column[3]);
                    employeeIDS.add(column[4]);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found:sales.csv");
        } catch (IOException e) {
            System.out.println("Problem with file input: sales.csv");
        }
        // read from vehicle.csv
        try {
            BufferedReader in = new BufferedReader(new FileReader("vehicle.csv"));
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                String[] column = currentLine.split(",", -1);
                if (column.length >= 5) {
                    carPlateV.add(column[0]);
                    carModelV.add(column[1]);
                    acquiredPriceV.add(column[2]);
                    carStatusV.add(column[3]);
                    salesPriceV.add(column[4]);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: vehicle.csv");
        } catch (IOException e) {
            System.out.println("Problem with file input: vehicle.csv");
        }

        // LOGIN AND REGISTRATION
        loadUserData();
        System.out.println("USER REGISTRATION AND LOGIN");
        if (loggedIn) {
            System.out.println(userIDLogin + ", welcome back");
            System.out.println("1. Log out");
            System.out.println("2. Exit");
            System.out.println("3. Skip");
        } else {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
        }
        System.out.print("Enter choice: ");
        int choiceLogin = sc.nextInt();
        sc.nextLine();
        if (loggedIn == true) {
            switch (choiceLogin) {
                // log out
                case 1: {
                    loggedIn = false;
                    System.out.println("Logged out.");
                    saveLoginStatus("");
                    System.out.println("Want to login again?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("Enter choice: ");
                    int loginAgain = sc.nextInt();
                    sc.nextLine();
                    switch (loginAgain) {
                        case 1: {
                            System.out.println("\nUSER LOGIN");
                            System.out.print("Enter employee ID: ");
                            String employeeID = sc.nextLine();
                            userIDLogin = employeeID;
                            System.out.print("Enter password: ");
                            String password = sc.nextLine();
                            userLogin(employeeID, password);
                            saveLoginStatus(employeeID);
                        }
                            break;
                        case 2: {
                            System.exit(0);
                        }
                        default: {
                            System.out.println("Invalid choice.");
                            System.exit(0);
                        }
                    }
                }
                    break;
                // exit
                case 2: {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                    break;
                // skip
                case 3: {

                }
                    break;
                default: {
                    System.out.println("Invalid choice. Please select again.");
                    System.exit(0);
                }
                    break;
            }
        } else {
            switch (choiceLogin) {
                // register
                case 1: {
                    userRegister();
                    System.out.println("\nUSER LOGIN");
                    System.out.print("Enter employee ID: ");
                    String employeeID = sc.nextLine();
                    userIDLogin = employeeID;
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();
                    userLogin(employeeID, password);
                    saveLoginStatus(employeeID);
                }
                    break;
                // login
                case 2: {
                    System.out.println("\nUSER LOGIN");
                    System.out.print("Enter employee ID: ");
                    String employeeID = sc.nextLine();
                    userIDLogin = employeeID;
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();
                    userLogin(employeeID, password);
                    saveLoginStatus(employeeID);
                }
                    break;
                // exit
                case 3: {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                    break;
                default: {
                    System.out.println("Invalid choice. Please select again.");
                    System.exit(0);
                }
                    break;
            }
        }

        // add management level employee (this is only for management lvl employees)
        if (checkManagement(userIDLogin) == true) {
            System.out.println("\n1. Add new management level employee(s)");
            System.out.println("2. Skip");
            System.out.print("Enter choice: ");
            int addManagementEmployee = sc.nextInt();
            switch (addManagementEmployee) {
                // to add new management lvl employees
                case 1: {
                    System.out.print("Number of new management employees: ");
                    int addNum = sc.nextInt();
                    for (int i = 0; i < addNum; i++) {
                        addManagement();
                    }
                }
                    break;
                // skip
                case 2:
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }
        }
        // add new customer data (for both lvl employees)
        System.out.println("\n1. Add customer data");
        System.out.println("2. Skip");
        System.out.print("Enter choice: ");
        int addCustomer = sc.nextInt();
        switch (addCustomer) {
            // add new customer records
            case 1: {
                System.out.print("Number of new customers: ");
                int addNum = sc.nextInt();
                for (int i = 0; i < addNum; i++) {
                    addCustomer();
                }
            }
                break;
            // skip
            case 2:
                break;
            default: {
                System.out.println("Invalid choice.");
                System.exit(0);
            }
                break;
        }
        // add new sales data (for both lvl employees)
        System.out.println("\n1. Add sales data");
        System.out.println("2. Skip");
        System.out.print("Enter choice: ");
        int addSales = sc.nextInt();
        switch (addSales) {
            // add sales records
            case 1: {
                System.out.print("Number of new sales: ");
                int addNum = sc.nextInt();
                for (int i = 0; i < addNum; i++) {
                    addSales();
                }
            }
                break;
            // skip
            case 2:
                break;
            default: {
                System.out.println("Invalid choice.");
                System.exit(0);
            }
                break;
        }
        // add new vehicle data
        System.out.println("\n1. Add vehicle data");
        System.out.println("2. Skip");
        System.out.print("Enter choice: ");
        int addVehicle = sc.nextInt();
        switch (addVehicle) {
            // add new vehicle records
            case 1: {
                System.out.print("Number of new vehicle: ");
                int addNum = sc.nextInt();
                for (int i = 0; i < addNum; i++) {
                    addVehicle();
                }
            }
                break;
            // skip
            case 2:
                break;
            default: {
                System.out.println("Invalid choice.");
                System.exit(0);
            }
                break;
        }

        // VIEW INFO
        // sales lvl employees can only view own records
        List<String> filteredCustomerIDByEmployeeID = filterCustomerIDByEmployeeID(userIDLogin);
        List<String> filteredCustomerNameByCustomerID = filterCustomerNameByCustomerID(filteredCustomerIDByEmployeeID);
        List<String> filteredPhoneNumberByCustomerID = filterPhoneNumberByCustomerID(filteredCustomerIDByEmployeeID);
        List<String> filteredCustomerPostcodeByCustomerID = filterCustomerPostcodeByCustomerID(
                filteredCustomerIDByEmployeeID);
        if (checkManagement(userIDLogin) == false) {
            System.out.println("\n1. View own customer data");
            System.out.println("2. View own sales record");
            System.out.println("3. View all vehicle data");
            System.out.println("4. Skip");
            System.out.print("Enter choice: ");
            int view = sc.nextInt();
            sc.nextLine();
            switch (view) {
                case 1: {
                    System.out.println("\nCustomer Data of " + userIDLogin);
                    System.out.printf("%-20S", "Customer ID");
                    System.out.printf("%-35S", "Customer Name");
                    System.out.printf("%-15S", "Phone Number");
                    System.out.printf("%-10S", "Postcode");
                    System.out.println();
                    for (int i = 0; i < filteredCustomerIDByEmployeeID.size(); i++) {
                        System.out.printf("%-20S", filteredCustomerIDByEmployeeID.get(i));
                        System.out.printf("%-35S", filteredCustomerNameByCustomerID.get(i));
                        System.out.printf("%-15S", filteredPhoneNumberByCustomerID.get(i));
                        System.out.printf("%-10S", filteredCustomerPostcodeByCustomerID.get(i));
                        System.out.println();
                    }
                }
                    break;
                case 2: {
                    System.out.println("\nSales Record of " + userIDLogin);
                    System.out.printf("%-12S", "Sales ID");
                    System.out.printf("%-25S", "Date and Time");
                    System.out.printf("%-15S", "Car Plate");
                    System.out.printf("%-15S", "Customer ID");
                    System.out.printf("%-20S", "Employee ID");
                    System.out.println();
                    for (int i = 1; i < salesIDS.size(); i++) {
                        if (userIDLogin.equals(employeeIDS.get(i))) {
                            System.out.printf("%-12S", salesIDS.get(i));
                            System.out.printf("%-25S", dateTimeS.get(i));
                            System.out.printf("%-15S", carPlateS.get(i));
                            System.out.printf("%-15S", customerIDS.get(i));
                            System.out.printf("%-20S", employeeIDS.get(i));
                            System.out.println();
                        }
                    }
                }
                    break;
                case 3: {
                    viewAllVehicle();
                }
                    break;
                // skip
                case 4: {

                }
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }
        }
        // management lvl employee can view all records
        else {
            System.out.println("\n1. View all employee data");
            System.out.println("2. View all customer record");
            System.out.println("3. View all sales data");
            System.out.println("4. View all vehicle data");
            System.out.println("5. Skip");
            System.out.print("Enter choice: ");
            int view = sc.nextInt();
            sc.nextLine();
            switch (view) {
                case 1: {
                    viewAllEmployee();
                }
                    break;
                case 2: {
                    viewAllCustomer();
                }
                    break;
                case 3: {
                    viewAllSales();
                }
                    break;
                case 4: {
                    viewAllVehicle();
                }
                    break;
                case 5: {

                }
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }
        }

        // to calculate employee salary and bonus (only for management lvl employees)
        System.out.println("\nEMPLOYEE SALARY AND BONUS ");
        if (checkManagement(userIDLogin) == true) {
            System.out.print("Enter Employee ID: ");
            String employeeID = sc.nextLine();
            if (checkExistingUserByID(employeeID) == true) {
                System.out.print("Enter year in number: ");
                int year = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter month in number: ");
                int month = sc.nextInt();
                sc.nextLine();
                List<String> filteredDateTimeByYear = filterDateTimeByYear(dateTimeS, year);
                List<String> filteredDateTimeByMonth = filterDateTimeByMonth(filteredDateTimeByYear, month);
                List<String> filteredEmployeeDate = filterEmployeeDateTimeByMonth(filteredDateTimeByMonth, employeeID);
                List<String> filteredEmployeeCarPlate = filterEmployeeCarPlateByMonth(filteredEmployeeDate);
                List<Double> filteredEmployeeCarPrice = filterEmployeeCarPriceByMonth(filteredEmployeeCarPlate);
                double basicSalary;
                double allowance;
                double commission;
                double bonus = 0;
                int totalRecord = calSalesRecord(employeeID);
                double totalAmountInMonth = calSalesAmountInMonth(filteredEmployeeCarPrice);
                // calculate salary
                if (checkManagement(employeeID) == false) {
                    basicSalary = 1200;
                    allowance = 250;
                    commission = 0.01 * totalAmountInMonth;
                    // bonus for sales
                    if (totalRecord > 15 || totalAmountInMonth > 1000000)
                        bonus = 500;
                } else {
                    basicSalary = 2200;
                    allowance = 350;
                    commission = 0.01 * totalAmountInMonth;
                    // bonus for management
                    if (totalAmountInMonth <= 800000 && totalAmountInMonth >= 0)
                        bonus = 0.01 * totalAmountInMonth;
                    else if (totalAmountInMonth <= 1600000 && totalAmountInMonth > 800000)
                        bonus = 0.0115 * totalAmountInMonth;
                    else if (totalAmountInMonth <= 2500000 && totalAmountInMonth > 1600000)
                        bonus = 0.0125 * totalAmountInMonth;
                    else
                        bonus = 0.0135 * totalAmountInMonth;
                }
                double salary = basicSalary + allowance + commission;
                System.out.printf("Employee Salary: RM %.2f", salary);
                System.out.printf("\nEmployee Bonus: RM %.2f", bonus);
                System.out.println("\n");
            } else {
                System.out.println("The employee doesn't exist. Please try again.");
                System.exit(0);
            }
        } else {
            System.out.println("You have no access to view employee salary and bonus.");
            System.out.println();
        }

        // SEARCH AND FILTER
        // management lvl can search for all records
        if (checkManagement(userIDLogin) == true) {
            System.out.println("SEARCH AND FILTER");
            System.out.println("Search for employee records");
            System.out.println("1. By employee ID");
            System.out.println("2. By employee name");
            System.out.println("3. By employee status");
            System.out.println("4. Skip");
            System.out.print("Enter choice: ");
            int searchEmployee = sc.nextInt();
            sc.nextLine();
            switch (searchEmployee) {
                // search by employee id
                case 1: {
                    System.out.print("Enter employee ID: ");
                    String employeeID = sc.nextLine();
                    managementSearchEmployeeByEmployeeID(employeeID);
                }
                    break;
                // search by employee name
                case 2: {
                    System.out.print("Enter employee name: ");
                    String employeeName = sc.nextLine();
                    managementSearchEmployeeByEmployeeName(employeeName);
                }
                    break;
                // search by employee status
                case 3: {
                    System.out.print("Enter employee status: ");
                    String employeeStatus = sc.nextLine();
                    managementSearchEmployeeByEmployeeStatus(employeeStatus);
                }
                    break;
                // skip
                case 4: {

                }
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }
            // search for customer records
            System.out.println("\nSearch for customer records");
            System.out.println("1. By customer ID");
            System.out.println("2. By customer name");
            System.out.println("3. By phone number");
            System.out.println("4. By postcode");
            System.out.println("5. Skip");
            System.out.print("Enter choice: ");
            int searchCustomer = sc.nextInt();
            sc.nextLine();
            switch (searchCustomer) {
                // search by customer id
                case 1: {
                    System.out.print("Enter customer ID: ");
                    String customerID = sc.nextLine();
                    managementSearchCustomerByCustomerID(customerID);
                }
                    break;
                // search by customer name
                case 2: {
                    System.out.print("Enter customer name: ");
                    String customerName = sc.nextLine();
                    managementSearchCustomerByCustomerName(customerName);
                }
                    break;
                // search by phone number
                case 3: {
                    System.out.print("Enter phone number(without \"-\"): ");
                    String phoneNumber = sc.nextLine();
                    managementSearchCustomerByPhoneNumber(phoneNumber);
                }
                    break;
                // skip
                case 4: {
                    System.out.print("Enter postcode: ");
                    String postcode = sc.nextLine();
                    managementSearchCustomerByPostcode(postcode);
                }
                    break;
                case 5: {

                }
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }
            // search for sales records
            System.out.println("\nSearch for sales records");
            System.out.println("1. By sales ID");
            System.out.println("2. By month");
            System.out.println("3. By car plate");
            System.out.println("4. By customer ID");
            System.out.println("5. By employee ID");
            System.out.println("6. Skip");
            System.out.print("Enter choice: ");
            int searchSales = sc.nextInt();
            sc.nextLine();
            switch (searchSales) {
                // search by sales id
                case 1: {
                    System.out.print("Enter sales ID: ");
                    String salesID = sc.nextLine();
                    managementSearchSalesBySalesID(salesID);
                }
                    break;
                // search by month
                case 2: {
                    System.out.print("Enter month: ");
                    int month = sc.nextInt();
                    sc.nextLine();
                    managementSearchSalesByMonth(month);
                }
                    break;
                // search by car plate
                case 3: {
                    System.out.print("Enter car plate: ");
                    String carPlate = sc.nextLine();
                    managementSearchSalesByCarPlate(carPlate);
                }
                    break;
                // search by customer id
                case 4: {
                    System.out.print("Enter customer ID: ");
                    String customerID = sc.nextLine();
                    managementSearchSalesByCustomerID(customerID);
                }
                    break;
                // search by employee id
                case 5: {
                    System.out.print("Enter employee ID: ");
                    String employeeID = sc.nextLine();
                    managementSearchSalesByEmployeeID(employeeID);
                }
                    break;
                // skip
                case 6: {

                }
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }
            // search for vehicle records
            System.out.println("\nSearch for vehicle records");
            System.out.println("1. By car plate");
            System.out.println("2. By car model");
            System.out.println("3. By acquired price");
            System.out.println("4. By car status");
            System.out.println("5. By sales price");
            System.out.println("6. Skip");
            System.out.print("Enter choice: ");
            int searchVehicle = sc.nextInt();
            sc.nextLine();
            switch (searchVehicle) {
                // search by car plate
                case 1: {
                    System.out.print("Enter car plate: ");
                    String carPlate = sc.nextLine();
                    managementSearchVehicleByCarPlate(carPlate);
                }
                    break;
                // search by car model
                case 2: {
                    System.out.print("Enter car model: ");
                    String carModel = sc.nextLine();
                    managementSearchVehicleByCarModel(carModel);
                }
                    break;
                // search by acquired price
                case 3: {
                    System.out.print("Enter the minimum acquired price: ");
                    double minimumAcquiredPrice = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter the maximum acquired price: ");
                    double maximumAcquiredPrice = sc.nextDouble();
                    sc.nextLine();
                    managementSearchVehicleByAcquiredPrice(minimumAcquiredPrice, maximumAcquiredPrice);
                }
                    break;
                // search by car status
                case 4: {
                    System.out.print("Enter car status: ");
                    String carStatus = sc.nextLine();
                    managementSearchVehicleByCarStatus(carStatus);
                }
                    break;
                // search by sales price
                case 5: {
                    System.out.print("Enter the minimum sales price: ");
                    double minimumSalesPrice = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter the maximum sales price: ");
                    double maximumSalesPrice = sc.nextDouble();
                    sc.nextLine();
                    managementSearchVehicleBySalesPrice(minimumSalesPrice, maximumSalesPrice);
                }
                    break;
                // skip
                case 6: {

                }
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }

        }
        // sales can only search for own records
        else {
            System.out.println("SEARCH AND FILTER");
            System.out.println("Search for employee records");
            System.out.println("1. View your own employee record");
            System.out.println("2. Skip");
            System.out.print("Enter choice: ");
            int searchEmployee = sc.nextInt();
            sc.nextLine();
            switch (searchEmployee) {
                // view own employee records
                case 1: {
                    managementSearchEmployeeByEmployeeID(userIDLogin);
                }
                    break;
                // skip
                case 2: {

                }
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }
            // search for customer records
            List<String> ownCustomerIDFromSales = filterOwnCustomerIDFromSales();
            List<String> ownCustomerName = filterOwnCustomerName(ownCustomerIDFromSales);
            List<String> ownPhoneNumber = filterOwnPhoneNumber(ownCustomerIDFromSales);
            List<String> ownPostcode = filterOwnPostcode(ownCustomerIDFromSales);
            System.out.println("\nSearch for own customer records");
            System.out.println("1. By customer ID");
            System.out.println("2. By customer name");
            System.out.println("3. By phone number");
            System.out.println("4. By postcode");
            System.out.println("5. Skip");
            System.out.print("Enter choice: ");
            int searchCustomer = sc.nextInt();
            sc.nextLine();
            switch (searchCustomer) {
                // search by customer id
                case 1: {
                    System.out.print("Enter customer ID: ");
                    String customerID = sc.nextLine();
                    System.out.println("\nCustomer records of " + customerID);
                    System.out.printf("%-20S", "Customer ID");
                    System.out.printf("%-35S", "Customer Name");
                    System.out.printf("%-15S", "Phone Number");
                    System.out.printf("%-10S", "Postcode");
                    System.out.println();
                    int count = 0;
                    for (int i = 0; i < ownCustomerIDFromSales.size(); i++) {
                        if (customerID.equalsIgnoreCase(ownCustomerIDFromSales.get(i))) {
                            count++;
                            System.out.printf("%-20s", ownCustomerIDFromSales.get(i));
                            System.out.printf("%-35s", ownCustomerName.get(i));
                            System.out.printf("%-15s", ownPhoneNumber.get(i));
                            System.out.printf("%-10s", ownPostcode.get(i));
                            System.out.println();
                        }
                    }
                    if (count == 0) {
                        System.out.println("No record is found.");
                    }
                    System.out.println();
                }
                    break;
                // search by customer name
                case 2: {
                    System.out.print("Enter customer name: ");
                    String customerName = sc.nextLine();
                    System.out.println("\nCustomer records of " + customerName);
                    System.out.printf("%-20S", "Customer ID");
                    System.out.printf("%-35S", "Customer Name");
                    System.out.printf("%-15S", "Phone Number");
                    System.out.printf("%-10S", "Postcode");
                    System.out.println();
                    int count = 0;
                    for (int i = 0; i < ownCustomerName.size(); i++) {
                        if (customerName.equalsIgnoreCase(ownCustomerName.get(i))) {
                            count++;
                            System.out.printf("%-20s", ownCustomerIDFromSales.get(i));
                            System.out.printf("%-35s", ownCustomerName.get(i));
                            System.out.printf("%-15s", ownPhoneNumber.get(i));
                            System.out.printf("%-10s", ownPostcode.get(i));
                            System.out.println();
                        }
                    }
                    if (count == 0) {
                        System.out.println("No record is found.");
                    }
                    System.out.println();
                }
                    break;
                // search by phone number
                case 3: {
                    System.out.print("Enter phone number(without \"-\"): ");
                    String phoneNumber = sc.nextLine();
                    System.out.println("\nCustomer records of " + phoneNumber);
                    System.out.printf("%-20S", "Customer ID");
                    System.out.printf("%-35S", "Customer Name");
                    System.out.printf("%-15S", "Phone Number");
                    System.out.printf("%-10S", "Postcode");
                    System.out.println();
                    int count = 0;
                    for (int i = 0; i < ownPhoneNumber.size(); i++) {
                        if (phoneNumber.equalsIgnoreCase(ownPhoneNumber.get(i))) {
                            count++;
                            System.out.printf("%-20s", ownCustomerIDFromSales.get(i));
                            System.out.printf("%-35s", ownCustomerName.get(i));
                            System.out.printf("%-15s", ownPhoneNumber.get(i));
                            System.out.printf("%-10s", ownPostcode.get(i));
                            System.out.println();
                        }
                    }
                    if (count == 0) {
                        System.out.println("No record is found.");
                    }
                    System.out.println();
                }
                    break;
                // skip
                case 4: {
                    System.out.print("Enter postcode: ");
                    String postcode = sc.nextLine();
                    System.out.println("\nCustomer records of " + postcode);
                    System.out.printf("%-20S", "Customer ID");
                    System.out.printf("%-35S", "Customer Name");
                    System.out.printf("%-15S", "Phone Number");
                    System.out.printf("%-10S", "Postcode");
                    System.out.println();
                    int count = 0;
                    for (int i = 0; i < ownPostcode.size(); i++) {
                        if (postcode.equalsIgnoreCase(ownPostcode.get(i))) {
                            count++;
                            System.out.printf("%-20s", ownCustomerIDFromSales.get(i));
                            System.out.printf("%-35s", ownCustomerName.get(i));
                            System.out.printf("%-15s", ownPhoneNumber.get(i));
                            System.out.printf("%-10s", ownPostcode.get(i));
                            System.out.println();
                        }
                    }
                    if (count == 0) {
                        System.out.println("No record is found.");
                    }
                    System.out.println();
                }
                    break;
                case 5: {

                }
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }
            // search for sales records
            System.out.println("Search for own sales records");
            System.out.println("1. By sales ID");
            System.out.println("2. By month");
            System.out.println("3. By car plate");
            System.out.println("4. By customer ID");
            System.out.println("5. By employee ID");
            System.out.println("6. Skip");
            System.out.print("Enter choice: ");
            int searchSales = sc.nextInt();
            sc.nextLine();
            switch (searchSales) {
                // search by sales id
                case 1: {
                    System.out.print("Enter sales ID: ");
                    String salesID = sc.nextLine();
                    System.out.println("\nSales record of " + salesID);
                    System.out.printf("%-12S", "Sales ID");
                    System.out.printf("%-25S", "Date and Time");
                    System.out.printf("%-15S", "Car Plate");
                    System.out.printf("%-15S", "Customer ID");
                    System.out.printf("%-20S", "Employee ID");
                    System.out.println();
                    int count = 0;
                    for (int i = 0; i < salesIDS.size(); i++) {
                        if (salesID.equalsIgnoreCase(salesIDS.get(i)) && userIDLogin.equals(employeeIDS.get(i))) {
                            count++;
                            System.out.printf("%-12s", salesIDS.get(i));
                            System.out.printf("%-25s", dateTimeS.get(i));
                            System.out.printf("%-15s", carPlateS.get(i));
                            System.out.printf("%-15s", customerIDS.get(i));
                            System.out.printf("%-20s", employeeIDS.get(i));
                            System.out.println();
                        }
                    }
                    if (count == 0) {
                        System.out.println("No record is found.");
                    }
                    System.out.println();
                }
                    break;
                // search by month
                case 2: {
                    System.out.print("Enter month: ");
                    int month = sc.nextInt();
                    sc.nextLine();
                    System.out.println("\nSales record of month " + month);
                    System.out.printf("%-12S", "Sales ID");
                    System.out.printf("%-25S", "Date and Time");
                    System.out.printf("%-15S", "Car Plate");
                    System.out.printf("%-15S", "Customer ID");
                    System.out.printf("%-20S", "Employee ID");
                    System.out.println();
                    int count = 0;
                    List<String> filteredDateTime = filterDateTimeByMonth(dateTimeS, month);
                    for (int i = 0; i < dateTimeS.size(); i++) {
                        for (int j = 0; j < filteredDateTime.size(); j++) {
                            if (dateTimeS.get(i).equals(filteredDateTime.get(j))
                                    && userIDLogin.equals(employeeIDS.get(i))) {
                                count++;
                                System.out.printf("%-12s", salesIDS.get(i));
                                System.out.printf("%-25s", dateTimeS.get(i));
                                System.out.printf("%-15s", carPlateS.get(i));
                                System.out.printf("%-15s", customerIDS.get(i));
                                System.out.printf("%-20s", employeeIDS.get(i));
                                System.out.println();
                            }
                        }
                    }
                    if (count == 0) {
                        System.out.println("No record is found.");
                    }
                    System.out.println();
                }
                    break;
                // search by car plate
                case 3: {
                    System.out.print("Enter car plate: ");
                    String carPlate = sc.nextLine();
                    System.out.println("\nSales record of " + carPlate);
                    System.out.printf("%-12S", "Sales ID");
                    System.out.printf("%-25S", "Date and Time");
                    System.out.printf("%-15S", "Car Plate");
                    System.out.printf("%-15S", "Customer ID");
                    System.out.printf("%-20S", "Employee ID");
                    System.out.println();
                    int count = 0;
                    for (int i = 0; i < carPlateS.size(); i++) {
                        if (carPlate.equalsIgnoreCase(carPlateS.get(i)) && userIDLogin.equals(employeeIDS.get(i))) {
                            count++;
                            System.out.printf("%-12s", salesIDS.get(i));
                            System.out.printf("%-25s", dateTimeS.get(i));
                            System.out.printf("%-15s", carPlateS.get(i));
                            System.out.printf("%-15s", customerIDS.get(i));
                            System.out.printf("%-20s", employeeIDS.get(i));
                            System.out.println();
                        }
                    }
                    if (count == 0) {
                        System.out.println("No record is found.");
                    }
                    System.out.println();
                }
                    break;
                // search by customer id
                case 4: {
                    System.out.print("Enter customer ID: ");
                    String customerID = sc.nextLine();
                    System.out.println("\nSales record of " + customerID);
                    System.out.printf("%-12S", "Sales ID");
                    System.out.printf("%-25S", "Date and Time");
                    System.out.printf("%-15S", "Car Plate");
                    System.out.printf("%-15S", "Customer ID");
                    System.out.printf("%-20S", "Employee ID");
                    System.out.println();
                    int count = 0;
                    for (int i = 0; i < customerIDS.size(); i++) {
                        if (customerID.equalsIgnoreCase(customerIDS.get(i)) && userIDLogin.equals(employeeIDS.get(i))) {
                            count++;
                            System.out.printf("%-12s", salesIDS.get(i));
                            System.out.printf("%-25s", dateTimeS.get(i));
                            System.out.printf("%-15s", carPlateS.get(i));
                            System.out.printf("%-15s", customerIDS.get(i));
                            System.out.printf("%-20s", employeeIDS.get(i));
                            System.out.println();
                        }
                    }
                    if (count == 0) {
                        System.out.println("No record is found.");
                    }
                    System.out.println();
                }
                    break;
                // search by employee id
                case 5: {
                    System.out.println("\nSales record of " + userIDLogin);
                    System.out.printf("%-12S", "Sales ID");
                    System.out.printf("%-25S", "Date and Time");
                    System.out.printf("%-15S", "Car Plate");
                    System.out.printf("%-15S", "Customer ID");
                    System.out.printf("%-20S", "Employee ID");
                    System.out.println();
                    int count = 0;
                    for (int i = 0; i < employeeIDS.size(); i++) {
                        if (userIDLogin.equals(employeeIDS.get(i))) {
                            count++;
                            System.out.printf("%-12s", salesIDS.get(i));
                            System.out.printf("%-25s", dateTimeS.get(i));
                            System.out.printf("%-15s", carPlateS.get(i));
                            System.out.printf("%-15s", customerIDS.get(i));
                            System.out.printf("%-20s", employeeIDS.get(i));
                            System.out.println();
                        }
                    }
                    if (count == 0) {
                        System.out.println("No record is found.");
                    }
                    System.out.println();
                }
                    break;
                // skip
                case 6: {

                }
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }
            // search for vehicle records
            System.out.println("Search for vehicle records");
            System.out.println("1. By car plate");
            System.out.println("2. By car model");
            System.out.println("3. By acquired price");
            System.out.println("4. By car status");
            System.out.println("5. By sales price");
            System.out.println("6. Skip");
            System.out.print("Enter choice: ");
            int searchVehicle = sc.nextInt();
            sc.nextLine();
            switch (searchVehicle) {
                // search by car plate
                case 1: {
                    System.out.print("Enter car plate: ");
                    String carPlate = sc.nextLine();
                    managementSearchVehicleByCarPlate(carPlate);
                }
                    break;
                // search by car model
                case 2: {
                    System.out.print("Enter car model: ");
                    String carModel = sc.nextLine();
                    managementSearchVehicleByCarModel(carModel);
                }
                    break;
                // search by acquired price
                case 3: {
                    System.out.print("Enter the acquired price: ");
                    double acquiredPrice = sc.nextDouble();
                    sc.nextLine();
                    managementSearchVehicleByAcquiredPrice(acquiredPrice, acquiredPrice);
                }
                    break;
                // search by car status
                case 4: {
                    System.out.print("Enter car status: ");
                    String carStatus = sc.nextLine();
                    managementSearchVehicleByCarStatus(carStatus);
                }
                    break;
                // search by sales price
                case 5: {
                    System.out.print("Enter the sales price: ");
                    double salesPrice = sc.nextDouble();
                    sc.nextLine();
                    managementSearchVehicleBySalesPrice(salesPrice, salesPrice);
                }
                    break;
                // skip
                case 6: {

                }
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }
        }

        // SALES INSIGHT (only for management lvl employees)
        System.out.println("\nSALES INSIGHTS");
        // sales lvl employee cannot access to sales insight
        if (checkManagement(userIDLogin) == false) {
            System.out.println("Sorry, you do not have the access to sales insights. ");
        }
        // management lvl employee
        else {
            System.out.println("1. Annual sales"); // include the total number and the total sales price
            System.out.println("2. Monthly sales"); // include average monthly sales
            System.out.println("3. Number of cases");
            System.out.print("Enter choice: ");
            int salesInsights = sc.nextInt();
            sc.nextLine();
            switch (salesInsights) {
                // annual sales
                case 1: {
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    double annualSales = getAnnualSales(year);
                    System.out.printf("Annual sales: RM %.2f", annualSales);
                    System.out.println();
                }
                    break;
                // monthly sales
                case 2: {
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    double annualSales = getAnnualSales(year);
                    System.out.println("Monthly Sales");
                    for (int i = 0; i < month.length; i++) {
                        System.out.printf(month[i] + ": RM %.2f", getMonthlySales(year, i + 1));
                        System.out.println();
                    }
                    System.out.printf("Average monthly sales: RM %.2f", annualSales / 12);
                    System.out.println();
                }
                    break;
                // number of cases growth
                case 3: {
                    System.out.print("Number of Cases");
                    for (int i = 0; i < month.length; i++) {
                        List<String> filteredDateTimeByMonth = filterDateTimeByMonth(dateTimeS, i + 1);
                        cases[i] = filteredDateTimeByMonth.size();
                    }
                    SwingUtilities.invokeLater(() -> new LineChart("NUMBER OF CASES"));
                    System.out.println();
                }
                    break;
                default: {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
                    break;
            }
        }

    }

    // HERE ARE METHODS!!!!!

    public static double getMonthlySales(int year, int month) {
        double monthlySales = 0;
        double salesPrice;
        List<String> filteredDateTimeByYear = filterDateTimeByYear(dateTimeS, year);
        List<String> filteredDateTimeByMonth = filterDateTimeByMonth(filteredDateTimeByYear, month);
        List<String> filteredCarPlateByMonth = filterEmployeeCarPlateByMonth(filteredDateTimeByMonth);
        for (int i = 0; i < filteredCarPlateByMonth.size(); i++) {
            for (int j = 1; j < carPlateV.size(); j++) {
                if (filteredCarPlateByMonth.get(i).equals(carPlateV.get(j))) {
                    salesPrice = Double.parseDouble(salesPriceV.get(j));
                    monthlySales += salesPrice;
                }
            }
        }
        return monthlySales;
    }

    public static double getAnnualSales(int year) {
        double annualSales = 0;
        double salesPrice;
        List<String> filteredDateTimeByYear = filterDateTimeByYear(dateTimeS, year);
        List<String> filteredCarPlateByYear = filterEmployeeCarPlateByMonth(filteredDateTimeByYear);
        for (int i = 0; i < filteredCarPlateByYear.size(); i++) {
            for (int j = 1; j < carPlateV.size(); j++) {
                if (filteredCarPlateByYear.get(i).equals(carPlateV.get(j))) {
                    salesPrice = Double.parseDouble(salesPriceV.get(j));
                    annualSales += salesPrice;
                }
            }
        }
        return annualSales;
    }

    public static List<String> filterDateTimeByYear(List<String> dateTime, int year) {
        List<String> filteredDateTime = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        for (String dateString : dateTime) {
            try {
                LocalDateTime currentDateTime = LocalDateTime.parse(dateString, formatter);
                LocalDate currentDate = currentDateTime.toLocalDate();
                if (currentDateTime.getYear() == year)
                    filteredDateTime.add(dateString);
            } catch (Exception e) {
                // System.out.println("Error parsing date"+ dateString);
            }
        }
        return filteredDateTime;
    }

    public static List<String> filterOwnPostcode(List<String> ownCustomerIDFromSales) {
        List<String> ownPostcode = new ArrayList<>();
        for (int i = 0; i < ownCustomerIDFromSales.size(); i++) {
            for (int j = 0; j < customerIDC.size(); j++) {
                if (ownCustomerIDFromSales.get(i).equals(customerIDC.get(j))) {
                    ownPostcode.add(postcodeC.get(j));
                }
            }
        }
        return ownPostcode;
    }

    public static List<String> filterOwnPhoneNumber(List<String> ownCustomerIDFromSales) {
        List<String> ownPhoneNumber = new ArrayList<>();
        for (int i = 0; i < ownCustomerIDFromSales.size(); i++) {
            for (int j = 0; j < customerIDC.size(); j++) {
                if (ownCustomerIDFromSales.get(i).equals(customerIDC.get(j))) {
                    ownPhoneNumber.add(phoneNumberC.get(j));
                }
            }
        }
        return ownPhoneNumber;
    }

    public static List<String> filterOwnCustomerName(List<String> ownCustomerIDFromSales) {
        List<String> ownCustomerName = new ArrayList<>();
        for (int i = 0; i < ownCustomerIDFromSales.size(); i++) {
            for (int j = 0; j < customerIDC.size(); j++) {
                if (ownCustomerIDFromSales.get(i).equals(customerIDC.get(j))) {
                    ownCustomerName.add(customerNameC.get(j));
                }
            }
        }
        return ownCustomerName;
    }

    public static List<String> filterOwnCustomerIDFromSales() {
        List<String> filteredOwnCustomerIDFromSales = new ArrayList<>();
        for (int i = 0; i < employeeIDS.size(); i++) {
            if (userIDLogin.equals(employeeIDS.get(i))) {
                filteredOwnCustomerIDFromSales.add(customerIDS.get(i));
            }
        }
        return filteredOwnCustomerIDFromSales;
    }

    public static void managementSearchVehicleBySalesPrice(double minimumSalesPrice, double maximumSalesPrice) {
        System.out.printf("\nVehicle record of sales price between RM %.2f", minimumSalesPrice);
        System.out.printf(" to RM %.2f", maximumSalesPrice);
        System.out.println();
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-25S", "Car Model");
        System.out.printf("%-20S", "Acquired Price(RM)");
        System.out.printf("%-15S", "Car Status");
        System.out.printf("%-15S", "Sales Price(RM)");
        System.out.println();
        int count = 0;
        for (int i = 1; i < salesPriceV.size(); i++) {
            double salesPrice = 0;
            if (salesPriceV.get(i).isEmpty()) {
                String nullSalesPrice = salesPriceV.get(i).replace("", "0").trim();
                salesPrice = Double.parseDouble(nullSalesPrice);
            } else {
                salesPrice = Double.parseDouble(salesPriceV.get(i));
            }
            if (salesPrice >= minimumSalesPrice && salesPrice <= maximumSalesPrice) {
                count++;
                System.out.printf("%-15s", carPlateV.get(i));
                System.out.printf("%-25s", carModelV.get(i));
                System.out.printf("%-20s", acquiredPriceV.get(i));
                System.out.printf("%-15s", carStatusV.get(i));
                System.out.printf("%-15s", salesPriceV.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchVehicleByCarStatus(String carStatus) {
        System.out.println("\nVehicle record of status" + carStatus);
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-25S", "Car Model");
        System.out.printf("%-20S", "Acquired Price(RM)");
        System.out.printf("%-15S", "Car Status");
        System.out.printf("%-15S", "Sales Price(RM)");
        System.out.println();
        int count = 0;
        for (int i = 1; i < carStatusV.size(); i++) {
            if (carStatus.equalsIgnoreCase(carStatusV.get(i))) {
                count++;
                System.out.printf("%-15s", carPlateV.get(i));
                System.out.printf("%-25s", carModelV.get(i));
                System.out.printf("%-20s", acquiredPriceV.get(i));
                System.out.printf("%-15s", carStatusV.get(i));
                System.out.printf("%-15s", salesPriceV.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchVehicleByAcquiredPrice(double minimumAcquiredPrice,
            double maximumAcquiredPrice) {
        System.out.printf("\nVehicle record of acquired price between RM %.2f", minimumAcquiredPrice);
        System.out.printf(" to RM %.2f", maximumAcquiredPrice);
        System.out.println();
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-25S", "Car Model");
        System.out.printf("%-20S", "Acquired Price(RM)");
        System.out.printf("%-15S", "Car Status");
        System.out.printf("%-15S", "Sales Price(RM)");
        System.out.println();
        int count = 0;
        for (int i = 1; i < acquiredPriceV.size(); i++) {
            double acquiredPrice = Double.parseDouble(acquiredPriceV.get(i));
            if (acquiredPrice >= minimumAcquiredPrice && acquiredPrice <= maximumAcquiredPrice) {
                count++;
                System.out.printf("%-15s", carPlateV.get(i));
                System.out.printf("%-25s", carModelV.get(i));
                System.out.printf("%-20s", acquiredPriceV.get(i));
                System.out.printf("%-15s", carStatusV.get(i));
                System.out.printf("%-15s", salesPriceV.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchVehicleByCarModel(String carModel) {
        System.out.println("\nVehicle record of " + carModel);
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-25S", "Car Model");
        System.out.printf("%-20S", "Acquired Price(RM)");
        System.out.printf("%-15S", "Car Status");
        System.out.printf("%-15S", "Sales Price(RM)");
        System.out.println();
        int count = 0;
        for (int i = 1; i < carModelV.size(); i++) {
            if (carModel.equalsIgnoreCase(carModelV.get(i))) {
                count++;
                System.out.printf("%-15s", carPlateV.get(i));
                System.out.printf("%-25s", carModelV.get(i));
                System.out.printf("%-20s", acquiredPriceV.get(i));
                System.out.printf("%-15s", carStatusV.get(i));
                System.out.printf("%-15s", salesPriceV.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchVehicleByCarPlate(String carPlate) {
        System.out.println("\nVehicle record of " + carPlate);
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-25S", "Car Model");
        System.out.printf("%-20S", "Acquired Price(RM)");
        System.out.printf("%-15S", "Car Status");
        System.out.printf("%-15S", "Sales Price(RM)");
        System.out.println();
        int count = 0;
        for (int i = 1; i < carPlateV.size(); i++) {
            if (carPlate.equalsIgnoreCase(carPlateV.get(i))) {
                count++;
                System.out.printf("%-15s", carPlateV.get(i));
                System.out.printf("%-25s", carModelV.get(i));
                System.out.printf("%-20s", acquiredPriceV.get(i));
                System.out.printf("%-15s", carStatusV.get(i));
                System.out.printf("%-15s", salesPriceV.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchSalesByEmployeeID(String employeeID) {
        System.out.println("\nSales record of " + employeeID);
        System.out.printf("%-12S", "Sales ID");
        System.out.printf("%-25S", "Date and Time");
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-15S", "Customer ID");
        System.out.printf("%-20S", "Employee ID");
        System.out.println();
        int count = 0;
        for (int i = 1; i < employeeIDS.size(); i++) {
            if (employeeID.equalsIgnoreCase(employeeIDS.get(i))) {
                count++;
                System.out.printf("%-12s", salesIDS.get(i));
                System.out.printf("%-25s", dateTimeS.get(i));
                System.out.printf("%-15s", carPlateS.get(i));
                System.out.printf("%-15s", customerIDS.get(i));
                System.out.printf("%-20s", employeeIDS.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchSalesByCustomerID(String customerID) {
        System.out.println("\nSales record of " + customerID);
        System.out.printf("%-12S", "Sales ID");
        System.out.printf("%-25S", "Date and Time");
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-15S", "Customer ID");
        System.out.printf("%-20S", "Employee ID");
        System.out.println();
        int count = 0;
        for (int i = 1; i < customerIDS.size(); i++) {
            if (customerID.equalsIgnoreCase(customerIDS.get(i))) {
                count++;
                System.out.printf("%-12s", salesIDS.get(i));
                System.out.printf("%-25s", dateTimeS.get(i));
                System.out.printf("%-15s", carPlateS.get(i));
                System.out.printf("%-15s", customerIDS.get(i));
                System.out.printf("%-20s", employeeIDS.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchSalesByCarPlate(String carPlate) {
        System.out.println("\nSales record of " + carPlate);
        System.out.printf("%-12S", "Sales ID");
        System.out.printf("%-25S", "Date and Time");
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-15S", "Customer ID");
        System.out.printf("%-20S", "Employee ID");
        System.out.println();
        int count = 0;
        for (int i = 1; i < carPlateS.size(); i++) {
            if (carPlate.equalsIgnoreCase(carPlateS.get(i))) {
                count++;
                System.out.printf("%-12s", salesIDS.get(i));
                System.out.printf("%-25s", dateTimeS.get(i));
                System.out.printf("%-15s", carPlateS.get(i));
                System.out.printf("%-15s", customerIDS.get(i));
                System.out.printf("%-20s", employeeIDS.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchSalesByMonth(int salesMonth) {
        List<String> filteredDateTimeByMonth = filterDateTimeByMonth(dateTimeS, salesMonth);
        System.out.println("\nSales record of month " + salesMonth);
        System.out.printf("%-12S", "Sales ID");
        System.out.printf("%-25S", "Date and Time");
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-15S", "Customer ID");
        System.out.printf("%-20S", "Employee ID");
        System.out.println();
        int count = 0;
        for (int i = 1; i < dateTimeS.size(); i++) {
            for (int j = 0; j < filteredDateTimeByMonth.size(); j++) {
                if (filteredDateTimeByMonth.get(j).equals(dateTimeS.get(i))) {
                    count++;
                    System.out.printf("%-12s", salesIDS.get(i));
                    System.out.printf("%-25s", dateTimeS.get(i));
                    System.out.printf("%-15s", carPlateS.get(i));
                    System.out.printf("%-15s", customerIDS.get(i));
                    System.out.printf("%-20s", employeeIDS.get(i));
                    System.out.println();
                }
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchSalesBySalesID(String salesID) {
        System.out.println("\nSales record of " + salesID);
        System.out.printf("%-12S", "Sales ID");
        System.out.printf("%-25S", "Date and Time");
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-15S", "Customer ID");
        System.out.printf("%-20S", "Employee ID");
        System.out.println();
        int count = 0;
        for (int i = 1; i < salesIDS.size(); i++) {
            if (salesID.equalsIgnoreCase(salesIDS.get(i))) {
                count++;
                System.out.printf("%-12s", salesIDS.get(i));
                System.out.printf("%-25s", dateTimeS.get(i));
                System.out.printf("%-15s", carPlateS.get(i));
                System.out.printf("%-15s", customerIDS.get(i));
                System.out.printf("%-20s", employeeIDS.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchCustomerByPostcode(String postcode) {
        System.out.println("\nCustomer records of postcode " + postcode);
        System.out.printf("%-20S", "Customer ID");
        System.out.printf("%-35S", "Customer Name");
        System.out.printf("%-15S", "Phone Number");
        System.out.printf("%-10S", "Postcode");
        System.out.println();
        int count = 0;
        for (int i = 0; i < postcodeC.size(); i++) {
            if (postcode.equalsIgnoreCase(postcodeC.get(i))) {
                count++;
                System.out.printf("%-20s", customerIDC.get(i));
                System.out.printf("%-35s", customerNameC.get(i));
                System.out.printf("%-15s", phoneNumberC.get(i));
                System.out.printf("%-10s", postcodeC.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchCustomerByPhoneNumber(String phoneNumber) {
        System.out.println("\nCustomer records of phone number " + phoneNumber);
        System.out.printf("%-20S", "Customer ID");
        System.out.printf("%-35S", "Customer Name");
        System.out.printf("%-15S", "Phone Number");
        System.out.printf("%-10S", "Postcode");
        System.out.println();
        int count = 0;
        for (int i = 0; i < phoneNumberC.size(); i++) {
            if (phoneNumber.equalsIgnoreCase(phoneNumberC.get(i))) {
                count++;
                System.out.printf("%-20s", customerIDC.get(i));
                System.out.printf("%-35s", customerNameC.get(i));
                System.out.printf("%-15s", phoneNumberC.get(i));
                System.out.printf("%-10s", postcodeC.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
    }

    public static void managementSearchCustomerByCustomerName(String customerName) {
        System.out.println("\nCustomer records of " + customerName);
        System.out.printf("%-20S", "Customer ID");
        System.out.printf("%-35S", "Customer Name");
        System.out.printf("%-15S", "Phone Number");
        System.out.printf("%-10S", "Postcode");
        System.out.println();
        int count = 0;
        for (int i = 0; i < customerNameC.size(); i++) {
            if (customerName.equalsIgnoreCase(customerNameC.get(i))) {
                count++;
                System.out.printf("%-20s", customerIDC.get(i));
                System.out.printf("%-35s", customerNameC.get(i));
                System.out.printf("%-15s", phoneNumberC.get(i));
                System.out.printf("%-10s", postcodeC.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchCustomerByCustomerID(String customerID) {
        System.out.println("\nCustomer records of " + customerID);
        System.out.printf("%-20S", "Customer ID");
        System.out.printf("%-35S", "Customer Name");
        System.out.printf("%-15S", "Phone Number");
        System.out.printf("%-10S", "Postcode");
        System.out.println();
        int count = 0;
        for (int i = 0; i < customerIDC.size(); i++) {
            if (customerID.equalsIgnoreCase(customerIDC.get(i))) {
                count++;
                System.out.printf("%-20s", customerIDC.get(i));
                System.out.printf("%-35s", customerNameC.get(i));
                System.out.printf("%-15s", phoneNumberC.get(i));
                System.out.printf("%-10s", postcodeC.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchEmployeeByEmployeeStatus(String employeeStatus) {
        System.out.println("\nEmployee records of status " + employeeStatus);
        System.out.printf("%-20S", "Employee ID");
        System.out.printf("%-35S", "Employee Name");
        System.out.printf("%-20S", "Employee Status");
        System.out.printf("%-15S", "Password");
        System.out.println();
        int count = 0;
        for (int i = 0; i < employeeStatusE.size(); i++) {
            if (employeeStatus.equals(employeeStatusE.get(i))) {
                count++;
                System.out.printf("%-20s", employeeIDE.get(i));
                System.out.printf("%-35s", employeeNameE.get(i));
                System.out.printf("%-20s", employeeStatusE.get(i));
                System.out.printf("%-15s", passwordE.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchEmployeeByEmployeeName(String employeeName) {
        System.out.println("\nEmployee records of " + employeeName);
        System.out.printf("%-20S", "Employee ID");
        System.out.printf("%-35S", "Employee Name");
        System.out.printf("%-20S", "Employee Status");
        System.out.printf("%-15S", "Password");
        System.out.println();
        int count = 0;
        for (int i = 0; i < employeeNameE.size(); i++) {
            if (employeeName.equalsIgnoreCase(employeeNameE.get(i))) {
                count++;
                System.out.printf("%-20s", employeeIDE.get(i));
                System.out.printf("%-35s", employeeNameE.get(i));
                System.out.printf("%-20s", employeeStatusE.get(i));
                System.out.printf("%-15s", passwordE.get(i));
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static void managementSearchEmployeeByEmployeeID(String employeeID) {
        System.out.println("\nEmployee records of " + employeeID);
        System.out.printf("%-20S", "Employee ID");
        System.out.printf("%-35S", "Employee Name");
        System.out.printf("%-20S", "Employee Status");
        System.out.printf("%-15S", "Password");
        System.out.println();
        int count = 0;
        for (int i = 0; i < employeeIDE.size(); i++) {
            if (employeeID.equalsIgnoreCase(employeeIDE.get(i))) {
                System.out.printf("%-20s", employeeIDE.get(i));
                System.out.printf("%-35s", employeeNameE.get(i));
                System.out.printf("%-20s", employeeStatusE.get(i));
                System.out.printf("%-15s", passwordE.get(i));
                System.out.println();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No record is found.");
        }
        System.out.println();
    }

    public static double calSalesAmountInMonth(List<Double> employeeCarPrice) {
        double salesAmountInMonth = 0;
        for (Double price : employeeCarPrice) {
            salesAmountInMonth += price;
        }
        return salesAmountInMonth;
    }

    public static double calSalesAmount(String employeeID) {
        double salesAmount = 0;
        for (int i = 0; i < salesIDS.size(); i++) {
            if (employeeIDS.get(i).equals(employeeID)) {
                String carPlate = carPlateS.get(i);
                for (int j = 0; j < carPlateV.size(); j++) {
                    if (carPlate.equals(carPlateV.get(j))) {
                        double amount = Double.parseDouble(salesPriceV.get(j));
                        salesAmount += amount;
                    }
                }
            }
        }
        return salesAmount;
    }

    public static int calSalesRecord(String employeeID) {
        int salesCounter = 0;
        for (int i = 1; i < salesIDS.size(); i++) {
            if (employeeIDS.get(i).equals(userIDLogin))
                salesCounter++;
        }
        return salesCounter;
    }

    public static boolean checkExistingUserByID(String userID) {
        for (int i = 0; i < employeeIDE.size(); i++) {
            if (employeeIDE.get(i).equals(userID))
                return true;
        }
        return false;
    }

    public static List<String> filterEmployeeDateTimeByMonth(List<String> filteredDateTime, String employeeID) {
        List<String> filteredEmployeeDateTime = new ArrayList<>();
        for (int i = 0; i < dateTimeS.size(); i++) {
            for (int j = 0; j < filteredDateTime.size(); j++) {
                if (dateTimeS.get(i).equals(filteredDateTime.get(j))) {
                    if (employeeID.equals(employeeIDS.get(i)))
                        filteredEmployeeDateTime.add(filteredDateTime.get(j));
                }
            }
        }
        return filteredEmployeeDateTime;
    }

    public static List<String> filterEmployeeCarPlateByMonth(List<String> filteredDateTime) {
        List<String> filteredEmployeeCarPlateByMonth = new ArrayList<>();
        for (int i = 0; i < dateTimeS.size(); i++) {
            for (int j = 0; j < filteredDateTime.size(); j++) {
                if (dateTimeS.get(i).equals(filteredDateTime.get(j)))
                    filteredEmployeeCarPlateByMonth.add(carPlateS.get(i));
            }
        }
        return filteredEmployeeCarPlateByMonth;
    }

    public static List<Double> filterEmployeeCarPriceByMonth(List<String> filteredEmployeeCarPlate) {
        List<Double> filteredEmployeeCarPriceByMonth = new ArrayList<>();
        double price = 0;
        for (int i = 0; i < filteredEmployeeCarPlate.size(); i++) {
            for (int j = 0; j < carPlateV.size(); j++) {
                if (filteredEmployeeCarPlate.get(i).equals(carPlateV.get(j))) {
                    price = Double.parseDouble(salesPriceV.get(j));
                    filteredEmployeeCarPriceByMonth.add(price);
                }
            }
        }
        return filteredEmployeeCarPriceByMonth;
    }

    public static List<String> filterDateTimeByMonth(List<String> dateTime, int month) {
        List<String> filteredDateTime = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        for (String dateString : dateTime) {
            try {
                LocalDateTime currentDateTime = LocalDateTime.parse(dateString, formatter);
                LocalDate currentDate = currentDateTime.toLocalDate();
                if (currentDateTime.getMonthValue() == month)
                    filteredDateTime.add(dateString);
            } catch (Exception e) {
                // System.out.println("Error parsing date"+ dateString);
            }
        }
        return filteredDateTime;
    }

    public static List<String> filterCustomerIDByEmployeeID(String employeeID) {
        List<String> filteredCustomerIDByEmployeeID = new ArrayList<>();
        for (int i = 0; i < employeeIDS.size(); i++) {
            if (employeeID.equals(employeeIDS.get(i))) {
                filteredCustomerIDByEmployeeID.add(customerIDS.get(i));
            }
        }
        return filteredCustomerIDByEmployeeID;
    }

    public static List<String> filterCustomerNameByCustomerID(List<String> filteredCustomerIDByEmployeeID) {
        List<String> filteredCustomerNameByCustomerID = new ArrayList<>();
        for (int i = 0; i < filteredCustomerIDByEmployeeID.size(); i++) {
            for (int j = 0; j < customerIDC.size(); j++) {
                if (filteredCustomerIDByEmployeeID.get(i).equals(customerIDC.get(j))) {
                    filteredCustomerNameByCustomerID.add(customerNameC.get(j));
                }
            }
        }
        return filteredCustomerNameByCustomerID;
    }

    public static List<String> filterPhoneNumberByCustomerID(List<String> filteredCustomerIDByEmployeeID) {
        List<String> filteredPhoneNumberByCustomerID = new ArrayList<>();
        for (int i = 0; i < filteredCustomerIDByEmployeeID.size(); i++) {
            for (int j = 0; j < customerIDC.size(); j++) {
                if (filteredCustomerIDByEmployeeID.get(i).equals(customerIDC.get(j))) {
                    filteredPhoneNumberByCustomerID.add(phoneNumberC.get(j));
                }
            }
        }
        return filteredPhoneNumberByCustomerID;
    }

    public static List<String> filterCustomerPostcodeByCustomerID(List<String> filteredCustomerIDByEmployeeID) {
        List<String> filteredCustomerPostcodeByCustomerID = new ArrayList<>();
        for (int i = 0; i < filteredCustomerIDByEmployeeID.size(); i++) {
            for (int j = 0; j < customerIDC.size(); j++) {
                if (filteredCustomerIDByEmployeeID.get(i).equals(customerIDC.get(j))) {
                    filteredCustomerPostcodeByCustomerID.add(postcodeC.get(j));
                }
            }
        }
        return filteredCustomerPostcodeByCustomerID;
    }

    public static void viewAllVehicle() {
        System.out.println("\nVehicle Data");
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-25S", "Car Model");
        System.out.printf("%-20S", "Acquired Price(RM)");
        System.out.printf("%-15S", "Car Status");
        System.out.printf("%-15S", "Sales Price(RM)");
        System.out.println();
        for (int i = 1; i < carPlateV.size(); i++) {
            System.out.printf("%-15S", carPlateV.get(i));
            System.out.printf("%-25S", carModelV.get(i));
            System.out.printf("%-20S", acquiredPriceV.get(i));
            System.out.printf("%-15S", carStatusV.get(i));
            System.out.printf("%-15S", salesPriceV.get(i));
            System.out.println();
        }
    }

    public static void viewAllCustomer() {
        System.out.println("\nCustomer Data");
        System.out.printf("%-20S", "Customer ID");
        System.out.printf("%-35S", "Customer Name");
        System.out.printf("%-15S", "Phone Number");
        System.out.printf("%-10S", "Postcode");
        System.out.println();
        for (int i = 1; i < customerIDC.size(); i++) {
            System.out.printf("%-20S", customerIDC.get(i));
            System.out.printf("%-35S", customerNameC.get(i));
            System.out.printf("%-15S", phoneNumberC.get(i));
            System.out.printf("%-10S", postcodeC.get(i));
            System.out.println();
        }
    }

    public static void viewAllSales() {
        System.out.println("\nSales Record");
        System.out.printf("%-12S", "Sales ID");
        System.out.printf("%-25S", "Date and Time");
        System.out.printf("%-15S", "Car Plate");
        System.out.printf("%-15S", "Customer ID");
        System.out.printf("%-20S", "Employee ID");
        System.out.println();
        for (int i = 1; i < salesIDS.size(); i++) {
            System.out.printf("%-12S", salesIDS.get(i));
            System.out.printf("%-25S", dateTimeS.get(i));
            System.out.printf("%-15S", carPlateS.get(i));
            System.out.printf("%-15S", customerIDS.get(i));
            System.out.printf("%-20S", employeeIDS.get(i));
            System.out.println();
        }
    }

    public static void viewAllEmployee() {
        System.out.println("\nEmployee Data");
        System.out.printf("%-20S", "Employee ID");
        System.out.printf("%-35S", "Employee Name");
        System.out.printf("%-20S", "Employee Status");
        System.out.printf("%-15S", "Password");
        System.out.println();
        for (int i = 1; i < employeeIDE.size(); i++) {
            System.out.printf("%-20S", employeeIDE.get(i));
            System.out.printf("%-35S", employeeNameE.get(i));
            System.out.printf("%-20S", employeeStatusE.get(i));
            System.out.printf("%-15S", passwordE.get(i));
            System.out.println();
        }
    }

    public static void addManagement() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter employee name: ");
        String employeeName = sc.nextLine();
        String employeeStatus = "1";
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if (checkExistingUserByName(employeeName)) {
            System.out.println("Employee name already exists. Please enter another name which has not been used.");
            System.exit(0);
        } else {
            String generatedID = generateEmployeeID();
            String newData = generatedID + "," + employeeName + "," + employeeStatus + "," + password;
            writeToFile("employee.csv", newData);
            employeeIDE.add(generatedID);
            employeeNameE.add(employeeName);
            employeeStatusE.add(employeeStatus);
            passwordE.add(password);
            userDatabase.put(generatedID, password);
            System.out.println("Successfully added.");
            System.out.println("The employee ID is " + generatedID);
            System.out.println();
        }
    }

    public static String generateCustomerID() {
        String generatedID;
        int generate = customerIDC.size();
        if (customerIDC.size() < 100) {
            generatedID = "C00" + String.valueOf(generate);
        } else
            generatedID = "C0" + String.valueOf(generate);
        return generatedID;
    }

    public static boolean checkExistingCustomer(String customerNameA) {
        for (int i = 0; i < customerNameC.size(); i++) {
            if (customerNameC.get(i).equals(customerNameA))
                return true;
        }
        return false;
    }

    public static void addCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();
        System.out.print("Enter phone number(without \"-\"):");
        String phoneNumber = sc.nextLine();
        System.out.print("Enter postcode: ");
        String postcode = sc.nextLine();
        if (checkExistingCustomer(customerName)) {
            System.out.println("Customer name already exists. ");
            System.exit(0);
        } else {
            String generatedID = generateCustomerID();
            String newData = generatedID + "," + customerName + "," + phoneNumber + "," + postcode;
            writeToFile("customer.csv", newData);
            customerIDC.add(generatedID);
            customerNameC.add(customerName);
            phoneNumberC.add(phoneNumber);
            postcodeC.add(postcode);
            System.out.println("Successfully added.");
            System.out.println("The Customer ID is " + generatedID);
            System.out.println();
        }
    }

    public static String generateSalesID() {
        String generatedID;
        int generate = salesIDS.size();
        if (salesIDS.size() >= 100 && salesIDS.size() < 1000) {
            generatedID = "A0" + String.valueOf(generate);
        } else
            generatedID = "A" + String.valueOf(generate);
        return generatedID;
    }

    public static String generateDateTime() {
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }

    public static void addSales() {
        Scanner sc = new Scanner(System.in);
        String dateTime = generateDateTime();
        System.out.print("Enter car plate: ");
        String carPlate = sc.nextLine();
        System.out.print("Enter customer ID: ");
        String customerID = sc.nextLine();
        String employeeID = userIDLogin;
        String generatedID = generateSalesID();
        String newData = generatedID + "," + dateTime + "," + carPlate + "," + customerID + "," + employeeID;
        writeToFile("sales.csv", newData);
        salesIDS.add(generatedID);
        dateTimeS.add(dateTime);
        carPlateS.add(carPlate);
        customerIDS.add(customerID);
        employeeIDS.add(employeeID);
        System.out.println("Successfully added.");
        System.out.println("Sales ID is " + generatedID);
        System.out.println();
    }

    public static void addVehicle() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter car plate: ");
        String carPlate = sc.nextLine();
        System.out.print("Enter car model: ");
        String carModel = sc.nextLine();
        System.out.print("Enter acquired price: ");
        String acquiredPrice = sc.nextLine();
        System.out.print("Enter car status: ");
        String carStatus = sc.nextLine();
        System.out.print("Enter sales price: ");
        String salesPrice = sc.nextLine();
        String newData = carPlate + "," + carModel + "," + acquiredPrice + "," + carStatus + "," + salesPrice;
        writeToFile("vehicle.csv", newData);
        carPlateV.add(carPlate);
        carModelV.add(carModel);
        acquiredPriceV.add(acquiredPrice);
        carStatusV.add(carStatus);
        salesPriceV.add(salesPrice);
        System.out.println("Successfully added.");
        System.out.println();
    }

    public static boolean checkManagement(String employeeID) {
        boolean status = false;
        for (int i = 0; i < employeeStatusE.size(); i++) {
            if (employeeIDE.get(i).equals(employeeID) && (employeeStatusE.get(i).equals("1"))) {
                status = true;
                break;
            } else
                status = false;
        }
        return status;
    }

    public static boolean checkIfLoggedIn() {
        boolean loginStatus = false;
        try {
            BufferedReader in = new BufferedReader(new FileReader("user login status.txt"));
            String employeeID = in.readLine();
            if (employeeID != null) {
                userIDLogin = employeeID;
                loginStatus = true;
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: user login status.txt");
        } catch (IOException e) {
            System.out.println("Problem with file input: user login status.txt");
        }

        return loginStatus;
    }

    public static void saveLoginStatus(String employeeID) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("user login status.txt"));
            out.write(employeeID);
            userIDLogin = employeeID;
            out.close();
        } catch (IOException e) {
            System.out.println("Problem with file output: user login status.txt");
        }
    }

    public static void userLogin(String employeeID, String password) {
        if (userDatabase.containsKey(employeeID) && userDatabase.get(employeeID).equals(password)) {
            System.out.println("Login successful!");
            saveLoginStatus(employeeID);
        } else {
            System.out.println("Login failed. Please check your credentials.");
            System.exit(0);
        }
    }

    public static String generateEmployeeID() {
        String generatedID;
        int generate = employeeIDE.size();
        if (employeeIDE.size() < 100)
            generatedID = "E00" + String.valueOf(generate);
        else
            generatedID = "E0" + String.valueOf(generate);
        return generatedID;
    }

    public static boolean checkExistingUserByName(String username) {
        for (int i = 0; i < employeeNameE.size(); i++) {
            if (employeeNameE.get(i).equals(username))
                return true;
        }
        return false;
    }

    public static void userRegister() {
        Scanner sc = new Scanner(System.in);
        String secretKey = "ABCDEFG";
        System.out.println("\nUSER REGISTRATION");
        System.out.println("You need to have a secret key in order to register an account.");
        System.out.print("Enter secret key: ");
        String key = sc.nextLine();
        if (key.equals(secretKey)) {
            System.out.println("The secret key is correct! You can now register your own account!");
            System.out.print("Enter employee name: ");
            String employeeName = sc.nextLine();
            String employeeStatus = "0";
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            if (checkExistingUserByName(employeeName)) {
                System.out.println("Employee name already exists. Please enter another name which has not been used.");
                System.exit(0);
            } else {
                String generatedID = generateEmployeeID();
                String newData = generatedID + "," + employeeName + "," + employeeStatus + "," + password;
                writeToFile("employee.csv", newData);
                employeeIDE.add(generatedID);
                employeeNameE.add(employeeName);
                employeeStatusE.add(employeeStatus);
                passwordE.add(password);
                userDatabase.put(generatedID, password);
                System.out.println("Registration successful!");
                System.out.println("Your employee ID is : " + generatedID);
            }
        } else {
            System.out.println("Sorry, the secret key does not match.");
            System.exit(0);
        }
    }

    public static void loadUserData() {
        try {
            Scanner in = new Scanner(new FileInputStream("employee.csv"));
            in.useDelimiter(",|\\n"); // Set the delimiter to comma or newline
            while (in.hasNext()) {
                String employeeID = in.next().trim();
                String employeeName = in.next().trim();
                String employeeStatus = in.next().trim();
                String password = in.next().trim();
                userDatabase.put(employeeID, password);
                if (in.hasNextLine()) {
                    in.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: employee.csv");
        }
    }

    public static void writeToFile(String filepath, String newData) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filepath, true));
            out.write(newData);
            out.newLine();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filepath);
        } catch (IOException e) {
            System.out.println("Problem with file output: " + filepath);
        }
    }
}
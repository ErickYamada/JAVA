package app;

import java.util.List;
import java.util.Scanner;

// Dependencies
import dao.DAO;
import dao.UserDAO;
import model.User;

public class Application 
{
    public static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) throws Exception 
    {
        // variables
        UserDAO userDAO = new UserDAO();
        User user = new User();
 
        int option = 0;

        do
        {
            // show the menu 
            menu();
            
            // read input
            System.out.print( "Enter an option: " );
            option = sc.nextInt();
            
            switch( option )
            {
            case 1:
                listUsers(userDAO);
                break;
            case 2:
                insertUser(userDAO, user);
                break;
            case 3:
                deleteUser(userDAO, user);
                break;
            case 4:
                updateUser(userDAO, user);
                break;
            case 5:
                System.out.printf("\n%s\n", "Program ended.");
                break;
                
            default:
                System.out.printf("\n%s\n", "ERROR: Invalid option");
                break;
            } // end switch case
        } while (option != 5); // end do while
        
        sc.close();
    } // end main()
    
    // menu
    public static void menu() 
    {
        System.out.println("\n*****MENU*****");
        System.out.println(" 1 - List    ");
        System.out.println(" 2 - Insert  ");
        System.out.println(" 3 - Delete  ");
        System.out.println(" 4 - Update  ");
        System.out.println(" 5 - Exit    ");
    } // end menu()
    
    // function to list all users
    public static void listUsers(UserDAO userDAO) 
    {
        int option = 0;
        List<User> users;
        
        System.out.println("\n*****LIST*****");
        
        System.out.println("1 - List.");
        System.out.println("2 - List users ordered by code.");
        System.out.println("3 - List users ordered by login.");
        option = sc.nextInt();
        sc.nextLine();
        
        if (option == 1)
        {
            users = userDAO.get();
            for (User u : users) 
            {
                System.out.println(u.toString());
            } // end for
        }
        else if (option == 2)
        {
            users = userDAO.getOrderByCode();
            for (User u : users) 
            {
                System.out.println(u.toString());
            } // end for
        }
        else if (option == 3)
        {
            users = userDAO.getOrderByLogin();
            for (User u : users) 
            {
                System.out.println(u.toString());
            } // end for
        }
        else
        {
            System.out.println("ERROR: Invalid option");
        } // end if
    } // end listUsers()
    
    // function to create a new user
    private static void insertUser(UserDAO userDAO, User user) 
    {
        int code = 0;
        char gender = '0';
        User existingUser;
        boolean codeExists = false;
        
        System.out.println("\n*****Insert user*****");
        
        do 
        {
            System.out.print("Enter the code: ");
            code = sc.nextInt();
            sc.nextLine();
            
            existingUser = userDAO.get(code);
            if (existingUser != null)
            {
                System.out.println("ERROR: Code already exists. Enter another code");
            }
            else
            {
                codeExists = true;
            } // end if
        } while (!codeExists); // end while
        
        System.out.print("Enter the login: ");
        String login = sc.nextLine();
        
        System.out.print("Enter the password: ");
        String password = sc.nextLine();
        
        System.out.print("Enter the gender (M/F): ");
        gender = sc.next().charAt(0);
        
        user = new User(code, login, password, gender);
        
        if (userDAO.insert(user)) 
        {
            System.out.println("Successfully inserted -> " + user.toString());
        } else {
            System.out.println("Failed to insert user.");
        } // end if
    } // end insertUser()
    
 // function to update a user
    private static void updateUser(UserDAO userDAO, User user) 
    {
        int code = 0;
        
        System.out.println("\n*****Update User*****");
        
        System.out.print("Enter the code of the user to be updated: ");
        code = sc.nextInt();
        sc.nextLine();
        
        user = userDAO.get(code);
        if (user != null) 
        {
            System.out.print("Enter the new login: ");
            user.setLogin(sc.nextLine());
            
            System.out.print("Enter the new password: ");
            user.setPassword(sc.nextLine());
            
            System.out.print("Enter the new gender (M/F): ");
            user.setGender(sc.next().charAt(0));
            
            if (userDAO.update(user)) 
            {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("Failed to update user.");
            } // end if
        } else 
        {
            System.out.println("User not found.");
        } // end if
    } // end updateUser()

    // function to delete a user
    private static void deleteUser(UserDAO userDAO, User user) 
    {
        int code = 0;
        char confirm = '0';
        
        System.out.println("\n*****Delete User*****");
        
        System.out.print("Enter the code of the user to be deleted: ");
        code = sc.nextInt();
        
        System.out.println("Are you sure you want to delete the user? (Y/N): ");
        confirm = sc.next().charAt(0);
        
        if (confirm == 'Y' || confirm == 'y')
        {
            if (userDAO.delete(code)) 
            {
                System.out.println("User deleted successfully.");
            } else 
            {
                System.out.println("Failed to delete user.");
            } // end if
        }
        else if (confirm == 'N' || confirm == 'n')
        {
            System.out.println("\nDeletion canceled.");
        }
        else
        {
            System.out.println("\nERROR: Invalid option.");
        } // end if
    } // end deleteUser()
    
} // end classpackage app;

import java.util.List;
import java.util.Scanner;

// Dependencies
import dao.DAO;
import dao.UserDAO;
import model.User;

public class Application 
{
    public static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) throws Exception 
    {
        // variables
        UserDAO userDAO = new UserDAO();
        User user = new User();
 
        int option = 0;

        do
        {
            // show the menu 
            menu();
            
            // read input
            System.out.print("Enter an option: ");
            option = sc.nextInt();
            
            switch(option)
            {
            case 1:
                listUsers(userDAO);
                break;
            case 2:
                insertUser(userDAO, user);
                break;
            case 3:
                deleteUser(userDAO, user);
                break;
            case 4:
                updateUser(userDAO, user);
                break;
            case 5:
                System.out.printf("\n%s\n", "Program ended.");
                break;
                
            default:
                System.out.printf("\n%s\n", "ERROR: Invalid option");
                break;
            } // end switch case
        } while (option != 5); // end do while
        
        sc.close();
    } // end main()
    
    // menu
    public static void menu() 
    {
        System.out.println("\n*****MENU*****");
        System.out.println(" 1 - List    ");
        System.out.println(" 2 - Insert  ");
        System.out.println(" 3 - Delete  ");
        System.out.println(" 4 - Update  ");
        System.out.println(" 5 - Exit    ");
    } // end menu()
}
//function to list all users
public static void listUsers(UserDAO userDAO) 
{
 int option = 0;
 List<User> users;
 
 System.out.println("\n*****LIST*****");
 
 System.out.println("1 - List.");
 System.out.println("2 - List users ordered by code.");
 System.out.println("3 - List users ordered by login.");
 option = sc.nextInt();
 sc.nextLine();
 
 if (option == 1) 
 {
     users = userDAO.get();
     for (User u : users) 
     {
         System.out.println(u.toString());
     } // end for
 } 
 else if (option == 2) 
 {
     users = userDAO.getOrderByCode();
     for (User u : users) 
     {
         System.out.println(u.toString());
     } // end for
 } 
 else if (option == 3) 
 {
     users = userDAO.getOrderByLogin();
     for (User u : users) 
     {
         System.out.println(u.toString());
     } // end for
 } 
 else 
 {
     System.out.println("ERROR: Invalid option.");
 } // end if
} // end listUsers()

//function to create a new user
private static void insertUser(UserDAO userDAO, User user) 
{
 int code = 0;
 char gender = '0';
 User existingUser;
 boolean codeExists = false;
 
 System.out.println("\n*****Insert User*****");
 
 do 
 {
     System.out.print("Enter the code: ");
     code = sc.nextInt();
     sc.nextLine();
     
     existingUser = userDAO.get(code);
     if (existingUser != null) 
     {
         System.out.println("ERROR: Code already exists. Enter another code.");
     } 
     else 
     {
         codeExists = true;
     } // end if
 } while (!codeExists); // end while
 
 System.out.print("Enter the login: ");
 String login = sc.nextLine();
 
 System.out.print("Enter the password: ");
 String password = sc.nextLine();
 
 System.out.print("Enter the gender (M/F): ");
 gender = sc.next().charAt(0);
 
 user = new User(code, login, password, gender);
 
 if (userDAO.insert(user)) 
 {
     System.out.println("Insertion successful -> " + user.toString());
 } 
 else 
 {
     System.out.println("Failed to insert user.");
 } // end if
} // end insertUser()

//function to update a user
private static void updateUser(UserDAO userDAO, User user) 
{
 int code = 0;
 
 System.out.println("\n*****Update User*****");
 
 System.out.print("Enter the user code to be updated: ");
 code = sc.nextInt();
 sc.nextLine();
 
 user = userDAO.get(code);
 if (user != null) 
 {
     System.out.print("Enter the new login: ");
     user.setLogin(sc.nextLine());
     
     System.out.print("Enter the new password: ");
     user.setPassword(sc.nextLine());
     
     System.out.print("Enter the new gender (M/F): ");
     user.setGender(sc.next().charAt(0));
     
     if (userDAO.update(user)) 
     {
         System.out.println("User updated successfully.");
     } 
     else 
     {
         System.out.println("Failed to update user.");
     } // end if
 } 
 else 
 {
     System.out.println("User not found.");
 } // end if
} // end updateUser()

//function to delete a user
private static void deleteUser(UserDAO userDAO, User user) 
{
 int code = 0;
 char confirm = '0';
 
 System.out.println("\n*****Delete User*****");
 
 System.out.print("Enter the user code to be deleted: ");
 code = sc.nextInt();
 
 System.out.println("Are you sure you want to delete the user? (Y/N): ");
 confirm = sc.next().charAt(0);
 
 if (confirm == 'Y' || confirm == 'y') 
 {
     if (userDAO.delete(code)) 
     {
         System.out.println("User deleted successfully.");
     } 
     else 
     {
         System.out.println("Failed to delete user.");
     } // end if
 } 
 else if (confirm == 'N' || confirm == 'n') 
 {
     System.out.println("\nDeletion canceled.");
 } 
 else 
 {
     System.out.println("\nERROR: Invalid option.");
 } // end if
} // end deleteUser()

} // end classpackage app;
import java.util.List;
import java.util.Scanner;

// Dependencies
import dao.DAO;
import dao.UserDAO;
import model.User;

public class Application 
{
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception 
    {
        // variables
        UserDAO userDAO = new UserDAO();
        User user = new User();
 
        int option = 0;

        do
        {
            // show the menu 
            menu();
            
            // data input
            System.out.print("Enter an option: ");
            option = sc.nextInt();
            
            switch(option)
            {
                case 1:
                    listUsers(userDAO);
                    break;
                case 2:
                    insertUser(userDAO, user);
                    break;
                case 3:
                    deleteUser(userDAO, user);
                    break;
                case 4:
                    updateUser(userDAO, user);
                    break;
                case 5:
                    System.out.printf("\n%s\n", "Program terminated.");
                    break;
                
                default:
                    System.out.printf("\n%s\n", "ERROR: Invalid Option");
                    break;
            } // end switch case
        } while (option != 5); // end do while
        
        sc.close();
    } // end main()

    // menu
    public static void menu() 
    {
        System.out.println("\n*****MENU*****");
        System.out.println(" 1 - List");
        System.out.println(" 2 - Insert");
        System.out.println(" 3 - Delete");
        System.out.println(" 4 - Update");
        System.out.println(" 5 - Exit");
    } // end menu()

    // function to list all users
    public static void listUsers(UserDAO userDAO) 
    {
        int option = 0;
        List<User> users;
        
        System.out.println("\n*****LIST*****");
        
        System.out.println("1 - List.");
        System.out.println("2 - List users sorted by code.");
        System.out.println("3 - List users sorted by login.");
        option = sc.nextInt();
        sc.nextLine();
        
        if (option == 1)
        {
            users = userDAO.get();
            for (User u : users) 
            {
                System.out.println(u.toString());
            } // end for
        }
        else if (option == 2)
        {
            users = userDAO.getOrderByCode();
            for (User u : users) 
            {
                System.out.println(u.toString());
            } // end for
        }
        else if (option == 3)
        {
            users = userDAO.getOrderByLogin();
            for (User u : users) 
            {
                System.out.println(u.toString());
            } // end for
        }
        else
        {
            System.out.println("ERROR: Invalid Option");
        } // end if
    } // end listUsers()
}
//function to create a new user
private static void insertUser(UserDAO userDAO, User user) 
{
 int code = 0;
 char gender = '0';
 User existingUser;
 boolean codeExists = false;
 
 System.out.println("\n*****Insert User*****");
 
 do 
 {
     System.out.print("Enter the code: ");
     code = sc.nextInt();
     sc.nextLine();
     
     existingUser = userDAO.get(code);
     if(existingUser != null)
     {
         System.out.println("ERROR: Code already exists. Enter another code.");
     }
     else
     {
         codeExists = true;
     } // end if
 } while(!codeExists); // end while
 
 System.out.print("Enter the login: ");
 String login = sc.nextLine();
 
 System.out.print("Enter the password: ");
 String password = sc.nextLine();
 
 System.out.print("Enter the gender (M/F): ");
 gender = sc.next().charAt(0);
 
 user = new User(code, login, password, gender);
 
 if (userDAO.insert(user)) 
 {
     System.out.println("Successfully inserted -> " + user.toString());
 } 
 else 
 {
     System.out.println("Failed to insert user.");
 } // end if
} // end insertUser()

//function to update a user
private static void updateUser(UserDAO userDAO, User user) 
{
 int code = 0;
 
 System.out.println("\n*****Update User*****");
 
 System.out.print("Enter the code of the user to be updated: ");
 code = sc.nextInt();
 sc.nextLine();
 
 user = userDAO.get(code);
 if (user != null) 
 {
     System.out.print("Enter the new login: ");
     user.setLogin(sc.nextLine());
     
     System.out.print("Enter the new password: ");
     user.setPassword(sc.nextLine());
     
     System.out.print("Enter the new gender (M/F): ");
     user.setGender(sc.next().charAt(0));
     
     if (userDAO.update(user)) 
     {
         System.out.println("User successfully updated.");
     } 
     else 
     {
         System.out.println("Failed to update user.");
     } // end if
 } 
 else 
 {
     System.out.println("User not found.");
 } // end if
} // end updateUser()

//function to delete a user
private static void deleteUser(UserDAO userDAO, User user) 
{
 int code = 0;
 char confirm = '0';
 
 System.out.println("\n*****Delete User*****");
 
 System.out.print("Enter the code of the user to be deleted: ");
 code = sc.nextInt();
 
 System.out.println("Are you sure you want to delete the user? (Y/N): ");
 confirm = sc.next().charAt(0);
 
 if(confirm == 'Y' || confirm == 'y')
 {
     if (userDAO.delete(code)) 
     {
         System.out.println("User successfully deleted.");
     } 
     else 
     {
         System.out.println("Failed to delete user.");
     } // end if
 }
 else if(confirm == 'N' || confirm == 'n')
 {
     System.out.println("\nDeletion canceled.");
 }
 else
 {
     System.out.println("\nERROR: Invalid option.");
 } // end if
 
} // end deleteUser()

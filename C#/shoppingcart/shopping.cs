using System;
using System.Collections.Generic;
using System.IO;


class Program
{
    // Structure to represent an item
    struct Item
    {
        public string Name;
        public double Price;
    }

    static List<Item> shoppingCart = new List<Item>(); // Global shopping cart list

    static void Main(string[] args)
    {
        while (true)
        {
            Console.WriteLine("Welcome to the Shopping Program!");
            Console.WriteLine("1. Add item to cart");
            Console.WriteLine("2. View cart");
            Console.WriteLine("3. Save cart to file");
            Console.WriteLine("4. Exit");

            Console.Write("Enter your choice: ");
            string choice = Console.ReadLine();

            switch (choice)
            {
                case "1":
                    AddItemToCart();
                    break;
                case "2":
                    ViewCart();
                    break;
                case "3":
                    SaveCartToFile();
                    break;
                case "4":
                    Console.WriteLine("Exiting program.");
                    return;
                default:
                    Console.WriteLine("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Function to add item to cart
    static void AddItemToCart()
    {
        Item newItem = new Item();

        Console.Write("Enter item name: ");
        newItem.Name = Console.ReadLine();

        Console.Write("Enter item price: ");
        if (!double.TryParse(Console.ReadLine(), out newItem.Price))
        {
            Console.WriteLine("Invalid price. Please enter a valid number.");
            return;
        }

        shoppingCart.Add(newItem);
        Console.WriteLine("Item added to cart.");
    }

    // Function to view cart
    static void ViewCart()
    {
        Console.WriteLine("Shopping Cart:");
        foreach (var item in shoppingCart)
        {
            Console.WriteLine($"{item.Name}: ${item.Price}");
        }
    }

    // Function to save cart to file
    static void SaveCartToFile()
    {
        Console.Write("Enter file name to save cart: ");
        string fileName = Console.ReadLine();

        try
        {
            using (StreamWriter writer = new StreamWriter(fileName + ".txt"))
            {
                foreach (var item in shoppingCart)
                {
                    writer.WriteLine($"{item.Name}: ${item.Price}");
                }
            }
            Console.WriteLine("Shopping cart saved to file.");
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error saving cart to file: {ex.Message}");
        }
    }
}

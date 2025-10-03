import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class characterCreator
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        List<String> races = Arrays.asList("Android", "Human", "Giant", "Wizard");
        List<String> classes = Arrays.asList("Warrior", "Medic", "Assassin", "Mage", "Tank");
        List<String> weapon = Arrays.asList("Sword", "Hammer", "Bow", "Shield", "shotgun");

        System.out.println("Welcome to my character creator :)");
        System.out.println("Enter your character name: ");
        String nam1 = scanner.nextLine();

        String typ1 = getValidChoice(scanner, classes, "class");
        String rac1 = getValidChoice(scanner, races, "race");
        String weap1 = getValidChoice(scanner, weapon, "weapon");

        // Create the character with chosen attributes
        Character myChar = new Character(nam1, rac1, typ1, weap1);

        // Display the character stats
        myChar.display();
        scanner.close();
    }

    private static String getValidChoice(Scanner scanner, List<String> options, String label)
    {
        String choice;
        while (true)
        {
            System.out.println("Now we have to pick your " + label);
            System.out.println("    " + label.substring(0, 1).toUpperCase() + label.substring(1) + " list");
            System.out.println(" ---------------");
            for (String option : options)
            {
                System.out.println(option);
            }
            System.out.print("Which " + label + " will you choose: ");
            choice = scanner.nextLine();

            // Fixed: replaced lambda with simple for-loop
            boolean valid = false;
            for (String o : options)
            {
                if (o.equalsIgnoreCase(choice))
                {
                    valid = true;
                    break;
                }
            }

            if (valid)
            {
                return choice;
            }
            else
            {
                System.out.println("Invalid " + label + ", try again.\n");
            }
        }
    }
}

class Character
{
    private String name;
    private String race;
    private String type;
    private String weapon;

    public Character(String name, String race, String type, String weapon)
    {
        this.type = type;
        this.race = race;
        this.weapon = weapon;
        this.name = name;
    }

    public double calHealth()
    {
        double health = 100;
        health = switch (race)
        {
            case "Android" -> health + 20;
            case "Giant" -> health * 1.75;
            case "Human" -> health;
            case "Wizard" -> health - 10;
            default -> health;
        };
        return health;
    }

    public int calBaseDamage()
    {
        int damage = 0;
        damage = switch (type)
        {
            case "Tank" -> damage + 6;
            case "Mage" -> damage + 4;
            case "Assassin" -> damage + 13;
            case "Medic" -> damage + 5;
            case "Warrior" -> damage + 8;
            default -> damage;
        };

        return damage;
    }

    public int weaponDam()
    {
        int weapons = 0;
        weapons = switch (weapon)
        {
            case "Sword" -> weapons + 4;
            case "Hammer" -> weapons + 8;
            case "Bow" -> weapons + 3;
            case "Shield" -> weapons + 1;
            case "shotgun" -> weapons + 25;
            default -> weapons;
        };
        return weapons;
    }

    public void display()
    {
        double health = calHealth();
        int damage = weaponDam();
        int base = calBaseDamage();
        int totaldamage;

        if (weapon.equalsIgnoreCase("shotgun"))
        {
            totaldamage = damage;
        }
        else
        {
            totaldamage = damage + base;
        }

        System.out.println("\n===== Character Summary =====");
        System.out.println("Your Character name is: " + name);
        System.out.println("Your race is: " + race);
        System.out.println("Your type is: " + type);
        System.out.println("Your weapon is: " + weapon);
        System.out.println("Health: " + health);
        System.out.println("Base Damage: " + base);
        System.out.println("Weapon Damage: " + damage);
        System.out.println("Total Attack Damage: " + totaldamage);
        System.out.println("=============================");
    }
}

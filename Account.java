//Menu Driven Account using HashMap with java
//Create,Delete,access & Save
//Store and retrieve data from external file
package HashMapProject;

import java.util.*;
import java.io.*;

@SuppressWarnings("serial")
class Account implements Serializable 
{	
	String accno;
	String name;
	double balance;
	
	Account(){}		//Non Parameterize constructor
	
	Account(String a,String n, double b)	//Parameterize constructor
	{
		accno=a;
		name=n;
		balance=b;
	}
	
	public String toString() 
	{
		return "account No:"+accno+"\nName :"+name+"\nBalance :"+balance+"\n";
	}

	
	public static void main(String[] args) throws Exception 
	{
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		
		Account acc=null; //Account Reference
		
		//key is string type and the value is Account type
		HashMap<String,Account> hm = new HashMap<>();
		
		try
		{
			FileInputStream fis = new FileInputStream("E:\\ProgramData/Account.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			int count=ois.readInt();	//Reading how many Accounts the there in the file
			for(int i=0; i<count; i++) 
			{
				acc=(Account)ois.readObject();
				System.out.println(acc);
				hm.put(acc.accno,acc);	//Putting them in HashMap
			}
			fis.close();	//closing the resources
			ois.close();	//closing the resources
		}
		catch(Exception e)
		{
			System.out.println("File not found");
		}
		
		
		FileOutputStream fos=new FileOutputStream("E:\\ProgramData/Account.txt");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		
		System.out.println("Menu Driven Account");
		
		int choice;
		String accno,name;
		double balance;
		
		do
		{
			System.out.println("1. Create Account");
			System.out.println("2. Delete Account");
			System.out.println("3. View Account");
			System.out.println("4. View All Account");
			System.out.println("5. Save Account");
			System.out.println("6. Exit");
			System.out.println("Enter your choice");	
			//Reading the choice from keyboard
			choice=sc.nextInt();
			
			//Ignore any new line character and all
			sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		 	
			switch(choice)
			{
			case 1: System.out.println("Enter Details -> Accno,name, balance");
					accno=sc.nextLine();
					name=sc.nextLine();
					balance=sc.nextDouble();
					acc=new Account(accno,name,balance);
					hm.put(accno, acc);
					System.out.println("Account created for "+name);
					
					break;
			case 2: System.out.println("Enter Account Number");
					sc.nextLine();
					accno=sc.nextLine();
					hm.remove(accno);
					
					break;
			case 3: System.out.println("Enter Account Number");
					accno=sc.nextLine();
					acc=hm.get(accno);
					System.out.println(acc);
					
					break;
			case 4: 
					for(Account a:hm.values())
						System.out.println(a);
					
					break;
			case 5: 
			case 6: oos.writeInt(hm.size());
					for(Account a:hm.values())
						oos.writeObject(a);
					
			}
			
		}while(choice!=6);
		oos.flush();	//Before closing it should flush it
		oos.close();
		fos.close();
			
		}
	}


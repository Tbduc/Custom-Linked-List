import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.stream.Stream;

public class MyLinkedListTest {
	public static MyLinkedList linkededList;
 
	public static void main(String[] args) throws IOException {

		linkededList = new MyLinkedList();


		linkededList.add(new Employee("Aga", "Bed", 2000.0, 32));
		linkededList.add(new Employee("Adrian", "Fuks", 4000.0, 16));
		linkededList.add(new Employee("Panda", "Moks", 6000.0, 35));
 

		System.out.println(linkededList);
		
		//Opcje wyboru
			Scanner scanner = new Scanner(System.in);	
			int selection;
			do {
				System.out.println("Opcje wyboru:\n[1] DODAJ PRACOWNIKA\n[2] USUN PRACOWNIKA\n[3] ZAMKNIJ\n");
					
				selection = scanner.nextInt();
				switch (selection) {
						case 1:
							System.out.println("Imie:");
							scanner.nextLine();
							String name = scanner.nextLine();
							System.out.println("Nazwisko:");
							String surname = scanner.nextLine();
							System.out.println("Wynagrodzenie:");
							double salary = scanner.nextDouble();
							System.out.println("Doswiadczenie:");
							int experience = scanner.nextInt();
							linkededList.add(new Employee(name, surname, salary, experience));
							System.out.println(linkededList);
							break;
						case 2:
							System.out.println("Ktory wiersz chcesz usunac");
							int choice = scanner.nextInt();
							if (choice == 0)
								System.out.println("Nie ma takiego wiersza");
							else if (choice > linkededList.size())
								System.out.println("Nie ma takiego wiersza");
							else
								linkededList.remove(choice - 1);
							System.out.println(linkededList);
							break;	
						/*case 3:
							System.out.println("SORTUJ PRZEZ: 1.IMIE\t2.NAZWISKO\t3.WYNAGRODZENIE\t4.DOSWIADCZENIE\n");
							selection = scanner.nextInt();	
								switch(selection){
									case 1:
										//linkededList.bubbleSort(2);
										break;
									default:
										System.out.println("Posortowano");
								}
							break;	
						case 4:
							System.out.println("Zapisz pod nazwa:");
							scanner.nextLine();
							String fileName = scanner.nextLine();
							linkededList.saveToFile(new File(fileName));
							break;	
						case 5:
							System.out.println("Podaj nazwe pliku:");
							scanner.nextLine();
							fileName = scanner.nextLine();
							linkededList = MyLinkedList.readFromFile(new File(fileName));
							break;
						*/	
						case 3:
							System.out.println("Zamknieto");
							break;
						default:
							System.out.println("Zly wybor");					
					}
				} while (selection != 3);
		
	}
 
}
 
class MyLinkedList<T> {
 
	private static int counter;
	private Node head;

    public MyLinkedList() {}

    public void add(Employee data) {
 

		if (head == null) {
			head = new Node(data);
		}
 
		Node myTemp = new Node(data);
		Node myCurrent = head;
 

		if (myCurrent != null) {
 
			while (myCurrent.getNext() != null) {
				myCurrent = myCurrent.getNext();
			}
 
			myCurrent.setNext(myTemp);
		}
 
		incrementCounter();
	}
 
	private static int getCounter() {
		return counter;
	}
 
	private static void incrementCounter() {
		counter++;
	}
 
	private void decrementCounter() {
		counter--;
	}
 
	public void add(Employee data, int index) {
		Node myTemp = new Node(data);
		Node myCurrent = head;
 
		if (myCurrent != null) {
			for (int i = 0; i < index && myCurrent.getNext() != null; i++) {
				myCurrent = myCurrent.getNext();
			}
		}
 
		myTemp.setNext(myCurrent.getNext());
 
		myCurrent.setNext(myTemp);
		

		incrementCounter();
	}

    public void saveToFile(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        if (head != null) {
            Node myCurrent = head.getNext();
            while (myCurrent != null) {
                bw.write(myCurrent.getData().toString());
                myCurrent = myCurrent.getNext();
				if(myCurrent != null)
					bw.write("\n");
            }
        }
		bw.close();
    }

    public static MyLinkedList<Employee> readFromFile(File file) throws IOException {
        Stream<String> stream = Files.lines(file.toPath());
        MyLinkedList<Employee> list = new MyLinkedList<>();
        stream.map((s) -> {
            String[] parts = s.split("\\|");
			System.out.println(parts[0].substring(6)+ " | "+ parts[1].substring(10) + " | " + parts[2].substring(16) + " | " + parts[3].substring(16) );
			return new Employee(parts[0].substring(6), parts[1].substring(10), Double.parseDouble(parts[2].substring(16)), Integer.parseInt(parts[3].substring(16)));
        }).forEach(list::add);
        return list;
    }
 
	public Object get(int index){
		if (index < 0)
			return null;
		Node myCurrent = null;
		if (head != null) {
			myCurrent = head.getNext();
			for (int i = 0; i < index; i++) {
				if (myCurrent.getNext() == null)
					return null;
 
				myCurrent = myCurrent.getNext();
			}
			return myCurrent.getData();
		}
		return myCurrent;
 
	}
 
	public boolean remove(int index) {
 
		if (index < 1 || index > size())
			return false;
 
		Node myCurrent = head;
		if (head != null) {
			for (int i = 0; i < index; i++) {
				if (myCurrent.getNext() == null)
					return false;
				
				myCurrent = myCurrent.getNext();
			}
			myCurrent.setNext(myCurrent.getNext().getNext());
 
			decrementCounter();
			return true;
 
		}
		return false;
	}
	
	public int size() {
		return getCounter();
	}
 
	public String toString() {
		String output = "\n";
 
		if (head != null) {
			Node myCurrent = head.getNext();
			while (myCurrent != null) {
				output += myCurrent.getData().toString();
				myCurrent = myCurrent.getNext();
			}
 
		}
		return output;
	}
	// compare and sort
	public void compare(int index){
		Node myCurrent = head.getNext();
		if(myCurrent != myCurrent.getNext())
			myCurrent = head;
		else 
			myCurrent = myCurrent.getNext();
	}
	
	public void bubbleSort(Employee data, int index){
		Node myTemp = new Node(data);
		Node myCurrent = head;
		
		if (myCurrent != null) {
			for (int i = 0; i < index && myCurrent.getNext() != null; i++) {
				for (int j = 0; j < index && myCurrent.getNext() != null; j++){
					myTemp = myCurrent;
					myCurrent = myCurrent.getNext();
					myCurrent = myTemp;
				}
			}
		}
	}
	
	private class Node {
		Node next;
 
		Object data;
 
		public Node(Object dataValue) {
			next = null;
			data = dataValue;
		}
 
		@SuppressWarnings("unused")
		public Node(Object dataValue, Node nextValue) {
			next = nextValue;
			data = dataValue;
		}
 
		public Object getData() {
			return data;
		}
 
		@SuppressWarnings("unused")
		public void setData(Object dataValue) {
			data = dataValue;
		}
 
		public Node getNext() {
			return next;
		}
 
		public void setNext(Node nextValue) {
			next = nextValue;
		}
 
	}

}	
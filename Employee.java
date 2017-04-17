import java.io.Serializable;

public class Employee implements Serializable
{

	private String firstName;
	private String lastName;
	private double salary;
	private int experience;
	
	
	public Employee(String firstName, String lastName, double salary, int experience)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.experience = experience;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName) {
        this.lastName = lastName;
    }
	public double getSalary()
	{
		return salary;
	}
	public void setSalary(double salary) {
        this.salary = salary;
    }
	public int getExperience()
	{
		return experience;
	}
	public void setExperience(int experience) {
        this.experience = experience;
    }

	public String toString()
	{
		String ret ="Imie: "+firstName +" | Nazwisko: "+lastName +" | Wynagrodzenie: "+salary + " | Doswiadczenie: "+experience + "\n";
		return ret;
	}
}
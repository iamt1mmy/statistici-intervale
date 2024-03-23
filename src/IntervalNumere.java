import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Interval 
{
    private double stanga, dreapta;
    private int numereTotale = 0, numereInterval = 0;

    public Interval(double stanga, double dreapta) 
    {
        this.stanga = stanga;
        this.dreapta = dreapta;
    }

    public void test(double numar) 
    {
        numereTotale++;
        if (numar >= stanga && numar <= dreapta) 
        {
        	numereInterval++;
        }
    }

    public double procent() 
    {
    	if (numereTotale == 0) 
    	{
    	    return 0;
    	} 
    	else 
    	{
    	    return (double) numereInterval / numereTotale * 100;
    	}

    }

    public String toString() 
    {
        return "[" + stanga + ", " + dreapta + "]: " + procent() + "%";
    }
}

public class IntervalNumere 
{

	public static void main(String[] args) 
	{
		
		try (BufferedReader intervalReader = new BufferedReader(new FileReader("intervale.dat"));
	             BufferedWriter writer = new BufferedWriter(new FileWriter("statistica.dat"))) 
		{
	            String line;
	            while ((line = intervalReader.readLine()) != null) 
	            {
	                String[] parts = line.split(",");
	                double start = Double.parseDouble(parts[0].substring(1));
	                double end = Double.parseDouble(parts[1].substring(0, parts[1].length() - 1));
	                Interval interval = new Interval(start, end);

	                try (BufferedReader numberReader = new BufferedReader(new FileReader("numere.dat"))) 
	                {
	                    while ((line = numberReader.readLine()) != null) 
	                    {
	                        String[] numbersArray = line.trim().split("\\s+");
	                        for (String num : numbersArray) 
	                        {
	                            if (!num.isEmpty()) 
	                            {
	                                double number = Double.parseDouble(num);
	                                interval.test(number);
	                            }
	                        }
	                    }
	                }
	                writer.write(interval.toString());
	                writer.newLine();
	            }
	    } 
		catch (IOException mesajException) 
		{
	            System.out.println("EROARE: " + mesajException.getMessage());
	    }

	}

}

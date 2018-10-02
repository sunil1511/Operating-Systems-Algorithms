package Assembler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

class symbol
{
	String sym;
	int addr;
	symbol(String sym,int addr)
	{
		this.addr=addr;
		this.sym=sym;
	}
}
class literal
{
	String lit;
	int addr;
	literal(String lit,int addr)
	{
		this.addr=addr;
		this.lit=lit;
	}
}
class out
{
	int lc,opcode,operand1,operand2;
	 
}
public class pass2 {
	String ic[]=new String[10];
	symbol s[]=new symbol[10];
	literal l[]=new literal[10];
	out o[]=new out[50]; 
	int icp=0,stn=0,ltn=0,op=0;
void get() throws IOException
{ String temp,temp2[];
	BufferedReader br=new BufferedReader(new FileReader("ic.txt"));
    ic[icp]=br.readLine();
    
    System.out.println("\nIntermediate Code");
    while(ic[icp]!=null)
    {
    	System.out.println(ic[icp]);
    	icp++;
    	ic[icp]=br.readLine();
    }
    BufferedReader br2=new BufferedReader(new FileReader("sym.txt"));
    temp=br2.readLine();
    
    System.out.println("\nSymbol Table");
    while(temp!=null)
    {
    	temp2=temp.split("[, ]");
    	System.out.println(temp2[0]+" "+temp2[1]);
    	s[stn]=new symbol(temp2[0],Integer.parseInt(temp2[1]));
    	stn++;
    	temp=br2.readLine();
    }
    BufferedReader br3=new BufferedReader(new FileReader("lit.txt"));
    temp=br3.readLine();
    
    System.out.println("\nliteral Table");
    while(temp!=null)
    {
    	temp2=temp.split("[, ]");
    	System.out.println(temp2[0]+" "+temp2[1]);
    	l[ltn]=new literal(temp2[0],Integer.parseInt(temp2[1]));
    	ltn++;
    	temp=br3.readLine();
    }
    
}
int computeIS(String temp[],int lc)
{
	o[op]=new out();
	o[op].lc=lc;
	boolean f1=false;
	for(int z=0;z<temp.length;z++)
	{
		if(temp[z].equals("IS"))
		{
			z++;
			o[op].opcode=Integer.parseInt(temp[z]);
			continue;
		}
		if(Character.isDigit(temp[z].charAt(0)))
		{
			if(f1==false){
			o[op].operand1=Integer.parseInt(temp[z]);
			f1=true;
			}
			else
			{
			o[op].operand2=Integer.parseInt(temp[z]);
			}
			continue;
		}
		if(temp[z].equals("S"))
		{
			z++;
			if(f1==false){
				o[op].operand1=s[Integer.parseInt(temp[z])].addr;
				f1=true;
				}
				else
				{
				o[op].operand2=s[Integer.parseInt(temp[z])].addr;
				}
			continue;
		}
		if(temp[z].equals("L"))
		{
			z++;
			if(f1==false){
				o[op].operand1=l[Integer.parseInt(temp[z])].addr;
				f1=true;
				}
				else
				{
				o[op].operand2=l[Integer.parseInt(temp[z])].addr;
				}
			continue;
		}
	}
	op++;
	lc++;
	return lc;
	
}
void calc()
{
	int j=0,loc=0;
	String temp[];
	while(j<icp)
	{
		temp=ic[j].split("[, ]");
		if(temp[0].equals("AD"))
		{
			if(temp.length>2)
			{
				if(temp[2].equals("C")){
			loc=Integer.parseInt(temp[3]);}
			}
		}
		else if(temp[0].equals("IS"))
		{
			loc=computeIS(temp,loc);
		}
		else if(temp[0].equals("DL"))
		{
			o[op]=new out();
			o[op].lc=loc;
			loc++;
			o[op].opcode=0;
			o[op].operand1=0;
			o[op].operand2=Integer.parseInt(temp[3]);
            op++;
		}
		j++;
	}
}
void display() throws IOException
{
	File file=new File("out.txt");
	OutputStream os=new FileOutputStream(file);
	for(int z=0;z<op;z++)
	{
		os.write((o[z].lc+" "+o[z].opcode+" "+o[z].operand1+" "+o[z].operand2).getBytes());
		System.out.println(o[z].lc+" "+o[z].opcode+" "+o[z].operand1+" "+o[z].operand2);
		os.write(System.getProperty("line.separator").getBytes());
	}
	os.close();
}
	public static void main(String[] args) throws IOException {
    pass2 p=new pass2();
    p.get();
    p.calc();
    p.display();

	}

}

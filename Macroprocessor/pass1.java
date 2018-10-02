package Macroprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

class MNT
{
	int mdtp;
	String macronm;
	MNT(String nm,int mdtp)
	{
		this.macronm=nm;
		this.mdtp=mdtp;
	}
}
class ALA
{
	String arg;
	int pos;
	ALA(String arg,int pos)
	{
		this.arg=arg;
		this.pos=pos;
	}
}
public class pass1 {
String mdt[][],code[],target[];
int i=0,tp=0,mdtp=0,mntp=0,ap=0;
MNT m[];
ALA a[];
void get() throws IOException
{
	BufferedReader br=new BufferedReader(new FileReader("source.txt"));
	code=new String[100];
	code[i]=br.readLine();
	while(code[i]!=null)
	{
		System.out.println(code[i]);
		i++;
		code[i]=br.readLine();	
	}
	mdt=new String[i][5];
	m=new MNT[5];
	a=new ALA[5];
	target=new String[20];
}
int computeDef(String temp[],int z)
{
    m[mntp]=new MNT(temp[1],mdtp);
    mntp++;
    int y=1;
    for(int k=2;k<temp.length;k++)
    {
    	a[y-1]=new ALA(temp[k], y);
    	y++;
    }
    z++;
    temp=code[z].split("[, ]");
    boolean flag=false;
    while(!temp[0].equals("MEND"))
    {   
    	for(int k=1;k<temp.length;k++)
    	{
    		for(int y1=0;y1<(y-1);y1++){
    	if(a[y1].arg.equals("&"+temp[k]))
    	{
    		temp[k]="#"+a[y1].pos;
    		break;
    	}
    		}
    	}
    	ap=y-1;
    	for(int k=0;k<temp.length;k++){
    	mdt[mdtp][k]=temp[k]+" ";
    	}
    	mdtp++;
    	z++;
    	temp=code[z].split("[, ]");
    }
    if(temp[0].equals("MEND"))
    {
    	mdt[mdtp][0]=temp[0];
    	mdtp++;
    }
    return z;
}
void calc()
{
	String temp[];
	int j=0;
	while(j<i)
	{
		temp=code[j].split("[, ]");
		if(temp[0].equals("MACRO"))
		{
			j=computeDef(temp,j);
		}
		else
		{
			target[tp]=code[j];
			tp++;
		}
		j++;
	}
}

void display() throws IOException
{
	File file=new File("mnt.txt");
    OutputStream os=new FileOutputStream(file);
	System.out.println("\nMNT");
	for(int x=0;x<mntp;x++)
	{   os.write((m[x].macronm+" "+m[x].mdtp).getBytes());
		System.out.println(m[x].macronm+"\t"+m[x].mdtp);
		os.write(System.getProperty("line.separator").getBytes());
	}
	os.close();
	File file1=new File("mdt.txt");
    OutputStream os1=new FileOutputStream(file1);
	System.out.println("\nMDT");
	for(int x=0;x<mdtp;x++)
	{
		int y=0;
		while(mdt[x][y]!=null)
		{   os1.write(mdt[x][y].getBytes());
			System.out.print(mdt[x][y]);
			y++;
		}
		System.out.println();
		os1.write(System.getProperty("line.separator").getBytes());
	}
	os1.close();
	File file2=new File("ala.txt");
    OutputStream os2=new FileOutputStream(file2);
	System.out.println("\nALA");
	for(int x=0;x<ap;x++)
	{   os2.write((a[x].arg+" "+a[x].pos).getBytes());
		System.out.println(a[x].arg+" "+a[x].pos);
		os2.write(System.getProperty("line.separator").getBytes());
	}
    os2.close();
    File file3=new File("target.txt");
    OutputStream os3=new FileOutputStream(file3);
	System.out.println("\nTarget Code");
	for(int x=0;x<tp;x++)
	{   os3.write(target[x].getBytes());
		System.out.println(target[x]);
		os3.write(System.getProperty("line.separator").getBytes());
	}
	os3.close();
}
public static void main(String[] args) throws IOException {
pass1 p=new pass1();
p.get();
p.calc();
p.display();
}
}

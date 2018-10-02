package Macroprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
class ALA2
{
	String act;
	int pos;
	ALA2(int pos,String act)
	{
		this.pos=pos;
		this.act=act;
	}
}
public class pass2 {
String mdt[]=new String[20];
String mnt[]=new String[10];
String target[]=new String[40];
String out[][]=new String[40][6];
ALA2 a[]=new ALA2[6];
int mdtp=0,mntp=0,tp=0,op=0,ap=0;
    void get() throws IOException
    {
    	BufferedReader br=new BufferedReader(new FileReader("mdt.txt"));
    	mdt[mdtp]=br.readLine();
    	System.out.println("\nMDT");
    	while(mdt[mdtp]!=null)
    	{
    		System.out.println(mdt[mdtp]);
    		mdtp++;
    		mdt[mdtp]=br.readLine();
    		
    	}
    	BufferedReader br2=new BufferedReader(new FileReader("mnt.txt"));
    	mnt[mntp]=br2.readLine();
    	System.out.println("\nMNT");
    	while(mnt[mntp]!=null)
    	{
    		System.out.println(mnt[mntp]);
    		mntp++;
    		mnt[mntp]=br2.readLine();
    	}
    	BufferedReader br3=new BufferedReader(new FileReader("target.txt"));
        target[tp]=br3.readLine();
    	System.out.println("\nTarget");
    	while(target[tp]!=null)
    	{
    		System.out.println(target[tp]);
    		tp++;
    		target[tp]=br3.readLine();	
    	}
    }
    int match(String temp)
    {
    	String tmnt[];
    	for(int z=0;z<mntp;z++)
    	{
    		tmnt=mnt[z].split("[, ]");
    		if(tmnt[0].equals(temp))
    		{
    		return (Integer.parseInt(tmnt[1]));
    		}
    	}
    	return -1;
    }
	void calc()
	{String temp[],temp2[];
		int j=0;
		while(j<tp)
		{
		 temp=target[j].split("[, ]");
		 int m=match(temp[0]);
		 if(m!=-1)
		 {
			 ap=0;
			 for(int z=1;z<temp.length;z++)
			 {
				 a[ap]=new ALA2(z, temp[z]);
				 ap++;
			 }
			 while(!mdt[m].equals("MEND"))
			 {  
				 temp2=mdt[m].split("[, ]");
				 for(int z=0;z<temp2.length;z++)
				 {
					 for(int k=0;k<ap;k++)
					 {
						 if(temp2[z].equals("#"+a[k].pos))
						 {
							 temp2[z]=a[k].act;
						 }
						 
					 }
					
				 }
				 m++;
				 for(int z=0;z<temp2.length;z++)
				 {
					 out[op][z]=temp2[z];
				 }
				 op++;
				 
				 
				 
			 }
		 }
		 else
		 {
			 for(int z=0;z<temp.length;z++)
			 {
				 out[op][z]=temp[z];
			 }
			 op++;
		 }
		 j++;
		}
		
		
	}
	void display() throws IOException
	{
		File file1=new File("out.txt");
	    OutputStream os1=new FileOutputStream(file1);
		System.out.println("\nOut");
		for(int x=0;x<op;x++)
		{
			int y=0;
			while(out[x][y]!=null)
			{   os1.write((out[x][y]+" ").getBytes());
				System.out.print(out[x][y]+"\t");
				y++;
			}
			System.out.println();
			os1.write(System.getProperty("line.separator").getBytes());
		}
		os1.close();
	}
	public static void main(String[] args) throws IOException {
		pass2 p=new pass2();
		p.get();
		p.calc();
		p.display();
	}

}

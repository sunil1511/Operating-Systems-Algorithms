package Assembler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
class symbol_tab{
    String val;
    int addr;   
}
class lit_tab{
    int addr;
    String val;
}

class mot
{
	String instruct;
	int index;
	String type;
	mot(String ins,int i,String type)
	{
		this.instruct=ins;
		this.index=i;
		this.type=type;
		
	}
}
public class pass1 {
String code[];
String inter[][];
symbol_tab sym[]=new symbol_tab[20];
lit_tab lit[]=new lit_tab[20];
int pool[]=new int[10];
BufferedReader br;
mot m[]=new mot[6];
int i=0,loc=0,stn=0,ltn=0,j=0;
void get() throws IOException
{   pool[0]=0;
	m[0]=new mot("START",1,"AD");
	m[1]=new mot("END",2,"AD");
	m[2]=new mot("MOVR",1,"IS");
	m[3]=new mot("MOVEM",2,"IS");
	m[4]=new mot("ADD",3,"IS");
	m[5]=new mot("DC",1,"DL");
	br=new BufferedReader(new FileReader("inputcode.txt"));
	code=new String[100];
	code[i]=br.readLine();
	while(code[i]!=null)
	{
		System.out.println(code[i]);
		i++;
		code[i]=br.readLine();
	}
	
}
int getOpcode(String s)
{
	for(int z=0;z<6;z++)
	{
		if(m[z].instruct.equals(s))
		{
			return z;
		}
	}
	return -1;
}
char check(String s)
{
	if(s.equals("AREG")||s.equals("BREG")||s.equals("CREG")||s.equals("DREG"))
	{
		return 'r';
	}
	else if(s.charAt(0)=='=')
	{
		return 'l';
	}
	return 's';
}
int addsym(String s)
{
	boolean f=false;
	int x;
	for( x=0;x<stn;x++)
	{   if(stn!=0)
	  {
		if(sym[x].val.equals(s))
		{
			f=true;
			break;
		}
	  }
	}
	if(f==false)
	{
		sym[stn]=new symbol_tab();
		sym[stn].val=s;
		stn++;
		return (stn-1);
	}
	else
	{
		return x;
	}
}

int addlit(String s)
{
	boolean f=false;
	int x=0;
	for(x=0;x<ltn;x++)
	{   if(ltn!=0)
	  {
		if(lit[x].val.equals(s))
		{
			f=true;
		}
	  }
	}
	if(f==false)
	{
		lit[ltn]=new lit_tab();
		lit[ltn].val=s;
		ltn++;
		return (ltn-1);
	}
	else
	{
		return x;
	}
}

void computIS(String temp[],int j)
{
	loc++;
	int t=getOpcode(temp[0]);
	inter[j][0]="("+m[t].type+","+m[t].index+")";
	char c=check(temp[1]);
	if(c=='s')
	{
		int y=addsym(temp[1]);
		inter[j][1]="(S"+","+y+")";
		}
	else if(c=='l')
	{
		int y1=addlit(temp[1]);
		inter[j][1]="(L"+","+y1+")";
	}
	else{
		inter[j][1]=temp[1];
		}
	c=check(temp[2]);
	if(c=='s')
	{
		addsym(temp[2]);
		inter[j][2]="(S"+","+(stn-1)+")";
	}
	else if(c=='l')
	{
		addlit(temp[2]);
		inter[j][2]="(L"+","+(ltn-1)+")";
	}
	else{
		inter[j][2]=temp[2];
		}

}
int searchsym(String s)
{
	for(int z=0;z<stn;z++)
	{
		if(sym[z].val.equals(s))
		{
			return z;
		}
	}
	return -1;
}

void cal()
{ j=0;
	int t=0;
  inter=new String[i][3];
  while(j<i)
	{
		String temp[]=code[j].split("[, ]");
			switch(temp[0])
			{
			case "START":
				loc=Integer.parseInt(temp[1]);
				t=getOpcode(temp[0]);
				inter[j][0]="("+m[t].type+","+m[t].index+")";
				inter[j][1]="(C"+","+loc+")";
				break;
			case "END":
				loc++;
				t=getOpcode(temp[0]);
				inter[j][0]="("+m[t].type+","+m[t].index+")";
				for(int o=0;o<ltn;o++)
				{
					lit[o].addr=loc;
					loc++;
				}
				break;
			case "MOVR":
				computIS(temp, j);
				break;
			case "MOVEM":
				computIS(temp, j);
				break;
			case "ADD":
				computIS(temp, j);
				break;
			default:
				t=getOpcode(temp[0]);
				if(t==-1)
				{
					t=getOpcode(temp[1]);
					loc++;
					inter[j][0]="("+m[t].type+","+m[t].index+")";
					int s=searchsym(temp[0]);
					if(s==-1){
					addsym(temp[0]);
					}
					else
					{
						sym[s].addr=loc;
					}
					inter[j][1]="(C"+","+temp[2]+")";
					loc+=Integer.parseInt(temp[2]);					
				}
				
			}
		j++;
	}
}
void display() throws IOException
{
	 File file = new File("ic.txt");
	 OutputStream os = new FileOutputStream(file);
	 File file1 = new File("sym.txt");
	 OutputStream os1 = new FileOutputStream(file1);
	 File file2 = new File("lit.txt");
	 OutputStream os2 = new FileOutputStream(file2);
	for(int z=0;z<j;z++)
	{
		int k=0;
		while(inter[z][k]!=null)
		{
			System.out.print(inter[z][k]);
		   os.write(inter[z][k].getBytes());
			k++;
			if(k==3)
			{
				break;
			}
		}
		os.write(System.getProperty("line.separator").getBytes());
		System.out.println();
	}
	os.close();
	System.out.println("SYMBOL TABLE");
	for(int z=0;z<stn;z++)
	{   os1.write((sym[z].val+" "+sym[z].addr).getBytes());
		System.out.println(sym[z].val+"\t"+sym[z].addr);
		os1.write(System.getProperty("line.separator").getBytes());
	}
	os1.close();
	System.out.println("LITERAL TABLE");
	for(int z=0;z<ltn;z++)
	{
		os2.write((lit[z].val+" "+lit[z].addr).getBytes());
	System.out.println(lit[z].val+"\t"+lit[z].addr);
	os2.write(System.getProperty("line.separator").getBytes());
	}
	os2.close();
}
	public static void main(String[] args) throws IOException {
		pass1 p=new pass1();
		p.get();
		p.cal();
		p.display();
		
	}
}

package page;

import java.util.Scanner;

public class optimal {
	int pgno=0,fno=0,hit=0,fault=0;
	int pg_seq[],buffer[],dist[];
	final int inf=1000;
	Scanner sc=new Scanner(System.in);
void input()
{
	System.out.println("Enter no of pages:");
	pgno=sc.nextInt();
	pg_seq=new int[pgno];
	System.out.println("enter sequence of pages:");
	for(int i=0;i<pgno;i++)
	{
		pg_seq[i]=sc.nextInt();
	}
	System.out.println("enter no of frames:");
	fno=sc.nextInt();
	buffer=new int[fno];
	dist=new int[fno];
	for(int i=0;i<fno;i++)
	{
		buffer[i]=-1;
		dist[i]=0;
	}
}
int search(int a)
{
	for(int i=0;i<fno;i++)
	{
		if(buffer[i]==a)
		{
			return i;
		}
	}
	return -1;
}

int findempty()
{
	for(int i=0;i<fno;i++)
	{
		if(buffer[i]==-1)
		{
			return i;
		}
	}
	return -1;
}


int findmax()
{int j=0;
	for(int i=0;i<fno;i++)
	{
		if(dist[i]>dist[j])
		{
			j=i;
		}
	}
	return j;
}
int distance(int start,int bno)
{
	int k;
	for(k=start+1;k<pgno;k++)
	{
		if(bno==pg_seq[k])
		{
			return (k-start);
		}
	}
	return inf;
}
void calc()
{ System.out.println("Page No \t Layout \t Distance");
	for(int i=0;i<pgno;i++)
	{
		int res=search(pg_seq[i]);
		if(res==-1)
		{  int res2=findempty();
			if(res2!=-1)
			{buffer[res2]=pg_seq[i];
			}
			else
			{
				int res3=findmax();
				buffer[res3]=pg_seq[i];
			}
			fault++;
			}
		else
		{
			hit++;
		
		}
		for(int j=0;j<fno;j++)
		{
			dist[j]=distance(i,buffer[j]);
		}
		
	
	System.out.print(pg_seq[i]+"\t \t");
	for(int q=0;q<fno;q++)
	{System.out.print(" "+buffer[q]);}
	System.out.print("\t \t");
	for(int q=0;q<fno;q++)
	{System.out.print(" "+dist[q]);}
		System.out.println();
	}
	System.out.println("Hits="+hit);
	System.out.println("Fault="+fault);
}

public static void main(String[] args)
{
	optimal l=new optimal();
	l.input();
	l.calc();
}


}

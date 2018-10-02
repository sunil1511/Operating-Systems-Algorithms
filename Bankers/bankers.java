package Bankers;
import java.util.Scanner;
class process
{
	int allo[];
	int maxneed[];
	int need[];
	boolean done=false;
}
public class bankers {
	Scanner sc=new Scanner(System.in);
	int rno=0,pno=0,o=0;
	process p[];
	int avail[],order[];
	void get()
	{
		System.out.println("enter no of resources:");
		rno=sc.nextInt();
		System.out.println("enter no of processes:");
		pno=sc.nextInt();
		p=new process[pno];
		order=new int[pno];
		avail=new int[rno];
		
		for(int i=0;i<pno;i++)
		{
			p[i]=new process();
			p[i].allo=new int[rno];
			p[i].maxneed=new int[rno];
			p[i].need=new int[rno];
			for(int j=0;j<rno;j++){
			System.out.println("Enter Allocation for process"+i+" resources "+j);
			p[i].allo[j]=sc.nextInt();
			System.out.println("Enter maxneed for "+i+" resources "+j);
			p[i].maxneed[j]=sc.nextInt();
			}
		}
		for(int i=0;i<rno;i++)
		{
			System.out.println("Enter no of available resources "+i);
			avail[i]=sc.nextInt();
		}
		
	}
	void calc()
	{
		for(int i=0;i<pno;i++)
		{ 
			for(int j=0;j<rno;j++)
			{
				p[i].need[j]=p[i].maxneed[j]-p[i].allo[j];
			}
		}
		int count=0,z=0,f=0;
		boolean fl=false;
		while(count<pno)
		{
			if(z>=pno)
			{
				z=0;
			}
			if(p[z].done==false){
			for(int i=0;i<rno;i++)
			{
			if(p[z].need[i]<=avail[i])
			{
				f++;
				fl=true;
			}
			}
			if(f>=rno && fl==true)
			{
			  for(int i=0;i<rno;i++)
			  {
				  avail[i]+=p[z].allo[i];
			  }
			  count++;
			  order[o]=z;
			  o++;
			}}
			z++;
		}
	}
	void display()
	{
		System.out.println("Safe Sequence");
		for(int i=0;i<o;i++)
				{
					System.out.print("P"+order[i]+" ");
				}
		System.out.println("\navailable");
		for(int i=0;i<rno;i++)
		{
			System.out.print(avail[i]+" ");
		}
	}
	public static void main(String[] args) {
		bankers b=new bankers();
		b.get();
		b.calc();
		b.display();
	}
}
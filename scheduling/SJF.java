package scheduling;
import java.util.Scanner;
class process2
{
	int id,at=0,wt=0,tat=0,bt=0,ex=0;
	process2(int a)
	{
		this.id=a+1;
	}
}
public class SJF
{Scanner sc=new Scanner(System.in);
	process2[] p;
	int n;
	void input()
	{
	System.out.println("enter no of processes:");
	n=sc.nextInt();
	p=new process2[n];
	for(int i=0;i<n;i++)
	{
		p[i]=new process2(i);
		System.out.println("enter arrival time for process"+p[i].id+":");
		p[i].at=sc.nextInt();
		System.out.println("enter burst time for process"+p[i].id+":");
		p[i].bt=sc.nextInt();
		p[i].ex=p[i].bt;
	}
	}

	
	int select(int ip)
	{int min=9999;
	int i=0,sel=-1;
	
		for( i=0;i<n;i++)
		{
			if(p[i].at<=ip)
			{
				if(p[i].ex<min && p[i].ex!=0)
				{
					min=p[i].ex;
					sel=i;
				}
			}
		}
		return sel;
	}
	void calc()
	{
		int ip=0,count=0,i=0;
		float wtavg=0,tatavg=0;
		while(count<n)
		{
			i=select(ip);
			if(i==-1)
			{
				continue;
			}
			p[i].ex-=1;
			if(p[i].ex==0)
			{
				count++;
				System.out.println("p"+p[i].id+"->"+ip+" to "+(ip+1));
				ip++;
				p[i].tat=ip-p[i].at;
				tatavg+=p[i].tat;
				p[i].wt-=p[i].at;
				wtavg+=p[i].wt;
				System.out.println("Waiting time for p"+p[i].id+"="+p[i].wt);
				System.out.println("TAT time for p"+p[i].id+"="+p[i].tat);
			}
			else
			{
				System.out.println("p"+p[i].id+"->"+ip+" to "+(ip+1));
				ip++;
			}
			for(int k=0;k<n;k++)
			{
				if((k+1)!=p[i].id && p[k].ex!=0)
				{
					p[k].wt++;
				}
			}
			
		}
		wtavg/=n;
		tatavg/=n;
		System.out.println("Average wt="+wtavg);
		System.out.println("Average tat="+tatavg);
	}
	
			

	public static void main(String[] args)
	{
		SJF f=new SJF();
	    f.input();
	    f.calc();
	}

}


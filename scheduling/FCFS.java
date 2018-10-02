package scheduling;
import java.util.Scanner;
class process
{
	int id,at=0,wt=0,tat=0,bt=0;
	process(int a)
	{
		this.id=a+1;
	}
}
public class FCFS
{Scanner sc=new Scanner(System.in);
	process p[];
	int n;
	void input()
	{
	System.out.println("enter no of processes:");
	n=sc.nextInt();
	p=new process[n];
	for(int i=0;i<n;i++)
	{
		p[i]=new process(i);
		System.out.println("enter arrival time for process"+p[i].id+":");
		p[i].at=sc.nextInt();
		System.out.println("enter burst time for process"+p[i].id+":");
		p[i].bt=sc.nextInt();
	}
	}
	void sort()
	{
	for(int i=0;i<n-1;i++)
	{
		for(int j=0;j<n-1;j++)
		{
		  if(p[j].at>p[j+1].at)
		  {
			process temp=p[j];
			p[j]=p[j+1];
			p[j+1]=temp;
		   }
		}
	}
	}
	
	void calc()
	{
		int ip=0,count=0,i=0;
		float wtavg=0,tatavg=0;
		while(count<n)
		{
			if(p[i].at>ip)
			{
				ip=p[i].at;
			}
			else
			{
				System.out.println("Current running process p"+p[i].id+"\nfrom "+ip+ " to "+(ip+p[i].bt) );
				p[i].wt=ip-p[i].at;
				p[i].tat=p[i].wt+p[i].bt;
				wtavg+=p[i].wt;
				tatavg+=p[i].tat;
				System.out.println("Waiting time of process p"+p[i].id+"="+p[i].wt);
				System.out.println("TAT time of process p"+p[i].id+"="+p[i].tat);
				ip+=p[i].bt;
				i++;
				count++;
				
			}
		}
		wtavg/=n;
		tatavg/=n;
		System.out.println("Average wt="+wtavg);
		System.out.println("Average tat="+tatavg);
	}
	
			

	public static void main(String[] args)
	{
		FCFS f=new FCFS();
	    f.input();
	    f.sort();
	    f.calc();
	}

}


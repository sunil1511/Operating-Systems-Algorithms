package scheduling;
import java.util.Scanner;
class process1
{
	int id,at=0,wt=0,tat=0,bt=0;
	process1(int a)
	{
		this.id=a+1;
	}
}
public class FCFS2
{Scanner sc=new Scanner(System.in);
	process1 p[];
	int order[];
	int n;
	void input()
	{
	System.out.println("enter no of processes:");
	n=sc.nextInt();
	p=new process1[n];
	order=new int[n];
	for(int i=0;i<n;i++)
	{
		p[i]=new process1(i);
		System.out.println("enter arrival time for process"+p[i].id+":");
		p[i].at=sc.nextInt();
		System.out.println("enter burst time for process"+p[i].id+":");
		p[i].bt=sc.nextInt();
	}
	System.out.println("enter order in which you want to execute process:");
	for(int i=0;i<n;i++)
	{
		order[i]=sc.nextInt();
	}
	}
	
	
	void calc()
	{
		int ip=0,count=0,i=0;
		float wtavg=0,tatavg=0;
		while(count<n)
		{
		 for(int k=0;k<n;k++)
		 {
			 if(order[count]==p[k].id)
			 {
				 i=k;
				 if(p[i].at>ip)
					{
						ip=p[i].at;
					}
						System.out.println("Current running process p"+p[i].id+"\nfrom "+ip+ " to "+(ip+p[i].bt) );
						p[i].wt=ip-p[i].at;
						p[i].tat=p[i].wt+p[i].bt;
						wtavg+=p[i].wt;
						tatavg+=p[i].tat;
						System.out.println("Waiting time of process p"+p[i].id+"="+p[i].wt);
						System.out.println("TAT time of process p"+p[i].id+"="+p[i].tat);
						ip+=p[i].bt;
						count++;
						break;
						
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
		FCFS2 f=new FCFS2();
	    f.input();
	    f.calc();
	}

}


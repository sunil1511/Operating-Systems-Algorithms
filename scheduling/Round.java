package scheduling;
import java.util.Scanner;
class process3
{
	int id,at=0,wt=0,tat=0,bt=0,ex=0;
	process3(int a)
	{
		this.id=a+1;
	}
}
public class Round
{Scanner sc=new Scanner(System.in);
	process3 p[];
	int order[];
	int n,q;
	void input()
	{
	System.out.println("enter no of processes:");
	n=sc.nextInt();
	p=new process3[n];
	order=new int[n];
	for(int i=0;i<n;i++)
	{
		p[i]=new process3(i);
		System.out.println("enter arrival time for process"+p[i].id+":");
		p[i].at=sc.nextInt();
		System.out.println("enter burst time for process"+p[i].id+":");
		p[i].bt=sc.nextInt();
		p[i].ex=p[i].bt;
	}
	System.out.println("enter order in which you want to execute process:");
	for(int i=0;i<n;i++)
	{
		order[i]=sc.nextInt();
	}
	System.out.println("Enter time quantum:");
	q=sc.nextInt();
	}
	
	
	void calc()
	{
		int ip=0,count=0,i=0,z=0;
		float wtavg=0,tatavg=0;
		
		while(count<n)
		{
			boolean flag=false;
		 for(int k=0;k<n;k++)
		 {
			 if(p[k].ex==0)
			 {
				continue;
				 
			 }
			 if(z==n)
			 {
				 z=0;
			 }
			 if(order[z]==p[k].id && p[k].ex!=0)
			 {
				 flag=true;
				 i=k;
				 if(p[i].at>ip && p[i].ex!=0)
					{
						ip=p[i].at;
					}
				 if(p[i].ex<q)
				 {
					 System.out.println("Current running process p"+p[i].id+"\nfrom "+ip+ " to "+(ip+p[i].ex) );
					 ip+=p[i].ex;
					 p[i].ex=0;
					 
				 }
				 else{
					 System.out.println("Current running process p"+p[i].id+"\nfrom "+ip+ " to "+(ip+q) );
				 ip+=q;
				 p[i].ex-=q;
				 }
				
					if(p[i].ex==0)
					{
						p[i].tat=ip-p[i].at;
						tatavg+=p[i].tat;
						p[i].wt=p[i].tat-p[i].bt;
						wtavg+=p[i].wt;
						System.out.println("Waiting time for p"+p[i].id+"="+p[i].wt);
						System.out.println("TAT time for p"+p[i].id+"="+p[i].tat);
						count++;
						
					}
						
						
					z++;	
					break;	
						
			      }
			 if(k==(n-1)&& flag==false)
			 {
				 z++;
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
		Round f=new Round();
	    f.input();
	    f.calc();
	}
}
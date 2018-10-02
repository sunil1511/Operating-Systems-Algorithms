package scheduling;
import java.util.Scanner;
class process4
{
	int id,at=0,wt=0,tat=0,bt=0,prio=0;
	boolean done=false;
	process4(int a)
	{
		this.id=a+1;
	}
}
public class Priority
{Scanner sc=new Scanner(System.in);
	process4 p[];
	int n;
	void input()
	{
	System.out.println("enter no of processes:");
	n=sc.nextInt();
	p=new process4[n];
	for(int i=0;i<n;i++)
	{
		p[i]=new process4(i);
		System.out.println("enter arrival time for process"+p[i].id+":");
		p[i].at=sc.nextInt();
		System.out.println("enter burst time for process"+p[i].id+":");
		p[i].bt=sc.nextInt();
		System.out.println("enter priority for process"+p[i].id+":");
		p[i].prio=sc.nextInt();
	}
	
	}
	void sort()
	{
	for(int i=0;i<n-1;i++)
	{
		for(int j=0;j<n-1;j++)
		{
		  if(p[j].prio>p[j+1].prio)
		  {
			process4 temp=p[j];
			p[j]=p[j+1];
			p[j+1]=temp;
		   }
		}
	}
	}
	int select(int ip,int i)
	{				
		if(p[i].at<=ip && p[i].done==false)
		{
			return i;
		}
		else{
			for(int k=0;k<n;k++)
			{
				if(p[k].at<=ip && p[i].done==false)
				{
					return k;
				}
			}
			return -1;
		}
	}
	
	void calc()
	{
		int ip=0,count=0,i=0;
		float wtavg=0,tatavg=0;
		while(count<n)
		{
			int z=select(ip,i);
			if(z==-1)
			{
				ip=p[i].at;
			}
			else
			{
				i=z;
				System.out.println("p"+p[i].id+"->"+ip+" to "+(ip+p[i].bt));
				ip=ip+p[i].bt;
				p[i].done=true;
				p[i].tat=ip-p[i].at;
				tatavg+=p[i].tat;
				p[i].wt=p[i].tat-p[i].bt;
				wtavg+=p[i].wt;
				System.out.println("Waiting time for p"+p[i].id+"="+p[i].wt);
				System.out.println("TAT time for p"+p[i].id+"="+p[i].tat);
			    count++;
			    i++;
			}
          }
		wtavg/=n;
		tatavg/=n;
		System.out.println("Average wt="+wtavg);
		System.out.println("Average tat="+tatavg);
	}
	
			

	public static void main(String[] args)
	{
		Priority f=new Priority();
	    f.input();
	    f.sort();
	    f.calc();
	}

}


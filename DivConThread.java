import java.util.*;
import java.math.*;
import java.text.DecimalFormat;
class DivConThread
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int i = 0; int j = 0; int k = 0;
		for(k=0;k<1;k++)
	{

		//n = 4;
		//System.out.println(n);
		int minimum = 1;
		int maximum = 50;
		float [][]a = new float[n][n];
		float [][]b = new float[n][n];
		float [][]c = new float[n][n];
		//float [][]d = new float[n][n];
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				//a[i][j] = (float)(minimum + (int)(Math.random() * maximum));
                //b[i][j] = (float)(minimum + (int)(Math.random() * maximum));
				a[i][j] = sc.nextInt();

			}

			for(j=0;j<n;j++)
			{
				//a[i][j] = (float)(minimum + (int)(Math.random() * maximum));
                //b[i][j] = (float)(minimum + (int)(Math.random() * maximum));
				b[i][j] = sc.nextInt();
			}
		}
		double start = System.nanoTime();
		c = multiplyRecFinal(a,b,n);
		double end = System.nanoTime();

		/*for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				System.out.print(c[i][j]+" ");
			}
			System.out.println();
		} */

		System.out.println("Time taken for n = "+n+" is : "+(end-start)/1000000000 + " Seconds");
	}
	}

public static float[][] multiplyRecFinal(float[][] A, float[][] B, int n){

    return  multiplyRec(A, B, 0, 0, 0, 0, n);

}


public static float[][] multiplyRec(float[][] A, float[][] B, int rowA, int colA, int rowB, int colB, int size)
	{

    		float[][] C= new float[size][size];

    	if(size==1)
        	C[0][0]= A[rowA][colA]*B[rowB][colB];

    	else if(size<=64)
	{

        	int newSize= size/2;
         	sumMatrix(C,multiplyRec(A, B, rowA, colA, rowB, colB, newSize),multiplyRec(A, B, rowA, colA+newSize, rowB+ newSize, colB, newSize),0, 0);

         sumMatrix(C,multiplyRec(A, B, rowA, colA, rowB, colB + newSize, newSize),multiplyRec(A, B, rowA, colA+newSize, rowB+ newSize, colB+newSize, newSize),0, newSize);

         sumMatrix(C,multiplyRec(A, B, rowA+ newSize, colA, rowB, colB, newSize), multiplyRec(A, B, rowA+ newSize, colA+newSize, rowB+ newSize, colB, newSize),newSize, 0);

         sumMatrix(C,multiplyRec(A, B, rowA+ newSize, colA, rowB, colB+newSize, newSize),multiplyRec(A, B, rowA+ newSize, colA+newSize, rowB+ newSize, colB+newSize, newSize),newSize, newSize);
    	}

	else
	{	int newSize= size/2;
		class Mul implements Runnable
			{
				private Thread t;
				private String threadName;
				Mul(String name)
				{
					threadName=name;
				}
				public void run()
				{
					if(threadName.equals("thread1"))
					{
						sumMatrix(C,multiplyRec(A, B, rowA, colA, rowB, colB, newSize),multiplyRec(A, B, rowA, colA+newSize, rowB+ newSize, colB, newSize),0, 0);
					}
					else if(threadName.equals("thread2"))
					{
						sumMatrix(C,multiplyRec(A, B, rowA, colA, rowB, colB + newSize, newSize),multiplyRec(A, B, rowA, colA+newSize, rowB+ newSize, colB+newSize, newSize),0, newSize);
					}

					else if(threadName.equals("thread3"))
					{
						 sumMatrix(C,multiplyRec(A, B, rowA+ newSize, colA, rowB, colB, newSize), multiplyRec(A, B, rowA+ newSize, colA+newSize, rowB+ newSize, colB, newSize),newSize, 0);
					}

					else if(threadName.equals("thread4"))
					{
						sumMatrix(C,multiplyRec(A, B, rowA+ newSize, colA, rowB, colB+newSize, newSize),multiplyRec(A, B, rowA+ newSize, colA+newSize, rowB+ newSize, colB+newSize, newSize),newSize, newSize);
					}
				}
				public void start()
				{
					if(t==null)
					{
						t=new Thread(this,"Thread1");
						t.start();
					}
				}
			}
			Mul m1=new Mul("thread1");
			m1.start();
			Mul m2=new Mul("thread2");
			m2.start();
			Mul m3=new Mul("thread3");
			m3.start();
			Mul m4=new Mul("thread4");
			m4.start();
			for (Thread t : new Thread[] { m1.t,m2.t,m3.t,m4.t })
			{
				try
				{
					t.join();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
	}

    return C;

	}


private static void sumMatrix(float[][] C, float[][]A, float[][]B,int rowC, int colC)
	{
    		int n=A.length;
    		for(int i =0; i<n; i++)
		{
        		for(int j=0; j<n; j++)
            			C[i+rowC][j+colC]=A[i][j]+B[i][j];
    		}

	}
}

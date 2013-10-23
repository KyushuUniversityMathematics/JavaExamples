import java.util.Random;

public class Test3  {
	static int x[], a[][], s, N;

static void initialx(){
	Random rnd = new Random();
	for(int i=0; i<N; i++) x[i]=i+1;
	for(int i=0; i<N; i++) {
		int ix,iy,t;
		ix=rnd.nextInt(N);iy=rnd.nextInt(N);
		if (ix != iy) { t=x[ix];x[ix]=x[iy];x[iy]=t; }
	}
	System.out.print("長さ"+N+"の入力データ: ");
	for(int i=0; i<N-1; i++) {
		System.out.print(x[i]+", ");
	}
	System.out.println(x[N-1]+".");
}

static void assend(boolean as) {
	for(int i=0; i<N; i++){
		if (i==0) {
			a[0][0]=x[0];
		} else {
			int j=0;
			boolean exitflag = false;
			do {
				if ((as && (x[i]<a[j][j])||((!as) && (x[i]>a[j][j])))) {
					if (j==0) {
						a[j][j]=x[i];
						exitflag = true;
					} else {
					for(int k=0; k<j; k++) { a[j][k]=a[j-1][k]; }
					a[j][j]=x[i];
					exitflag = true;
					}
				} else {
					if (j==s) {
						for(int k=0; k<s+1; k++) { a[s+1][k]=a[s][k]; }
						a[s+1][s+1]=x[i];
						s = s+1;
						exitflag = true;
					} else {
						j=j+1;
					}
				}
			} while (exitflag==false);
		}
	}
}

static void printa(String text,boolean debug) {
	int i0;
	if (debug) {i0=0;} else {i0=s;}
	System.out.println(text+"(Max Length="+(s+1)+")");
	for(int i=i0; i<s+1; i++){
		System.out.print((i+1)+": ");
		for(int j=0; j<i; j++) {
			System.out.print(a[i][j]+",");
		}
		System.out.println(a[i][i]+".");
	}
}

public static void main(String args[]){	
		N=16;
		x = new int[N];
		a = new int[N][N];

		initialx();
		s=0; assend(true);
		printa("最長上昇列",false);
		s=0; assend(false);
		printa("最長下降列",false);
	
	}
}

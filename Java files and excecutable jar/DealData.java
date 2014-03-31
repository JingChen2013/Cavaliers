package Data;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class DealData {
    public static int[][] mysort(int[][] ar) {//sort the array by the first column.
        Arrays.sort(ar, new Comparator<int[]>() {
            @Override
            public int compare(int[] int1, int[] int2) {
                Integer numOfKeys1 = int1[0];
                Integer numOfKeys2 = int2[0];
                return numOfKeys1.compareTo(numOfKeys2);
            }
        });
        return ar;
    }
	public static void main(String[] args){
		ReadinData read=new ReadinData();
		read.convertTxtToJava();
		read.shopping=mysort(read.shopping);
/*        For testing use
 * 		int[][] myarr = {{0, 10}, {1, 9}, {2, 9}, {3, 9}, {4, 15}, {5, 10}, {6, 4}};
        for (int[] i : myarr) {
            System.out.println(i[0] + "," + i[1]);
        }
        myarr = mysort(myarr);
*/
		int[][] output=getOutput(read.shopping);
		
/*		Test of f1.
 * 		ReadinData test=new ReadinData();
		test.convertTxtToJava();
		double f1=result(output,test.shopping);
		System.out.println(f1);
*/
		write(output);
		

	}
	private static void write(int[][] output) {//write to file result.txt;
		// TODO Auto-generated method stub
		try{
			FileWriter fw=new FileWriter("result.txt");
			PrintWriter pw=new PrintWriter(fw);
			for(int i=0;i<output.length;i++){
				if(output[i][0]!=0){
								pw.print(output[i][0]+"\t");
							if(output[i][1]!=0){
								pw.print(output[i][1]);
							}
							int j=2;
							while(j<6&&output[i][j]!=0){
								pw.print(","+output[i][j]);
								j=j+1;
							}
							pw.println();
				}
			}
			pw.close();
		}catch(IOException e){
			System.out.print("ERROR!");
		}
		
	}
	private static int[][] getOutput(int[][] shopping) {//deal with the data and get the output;
		// TODO Auto-generated method stub
		int[][] list=shopping;
		int userID=list[0][0];
		int[][] output=new int[900][6];
		int user=0;
		for(int i=0;i<182880;i++){
			output[user][0]=userID;
			int predict=1;
			int[] brandID=new int[5];//record the ID that is already recorded for each user.
			int bflag=0;
			int brand=0;
			while(list[i][0]==userID){//the same user;
				if(list[i][2]==1){//buy
					if(bflag==1){
						if(find(brandID,list[i][1])==0){
							brandID[brand]=list[i][1];
							output[user][predict]=list[i][1];
							predict=predict+1;
							brand=brand+1;
						}

					}
					bflag=1;
				}
				if(brand==5)break;
				if(list[i][3]==2&&bflag!=1){//add to cart
					if(find(brandID,list[i][1])==0){
						brandID[brand]=list[i][1];
						output[user][predict]=list[i][1];
						predict=predict+1;
						brand=brand+1;
					}
				}
				if(brand==5)break;
				if(list[i][2]==1&&bflag!=1&&list[i][2]!=1&&list[i][3]>715){//favorite
					if(find(brandID,list[i][1])==0){
						brandID[brand]=list[i][1];
						output[user][predict]=list[i][1];
						predict=predict+1;
						brand=brand+1;
					}
				}
				if(brand==5)break;
				i=i+1;
			}
			while(userID==list[i][0])i++;//make sure it goes to the next user;
			user=user+1;
			userID=list[i][0];
			i=i-1;
		}
		return output;
	}
	private static double result(int[][] output, int[][] shopping) {
		// TODO Auto-generated method stub
		shopping=mysort(shopping);
		double f1=0;
		int i=0;
		double pbrand=0;//store pbrand.
		while(output[i][0]!=0){
			int j=0;
			while(output[i][j]!=0)j++;
			pbrand=pbrand+j;
			i=i+1;
			int p=0;
			while(shopping[p][0]==output[i][0]){
				//if()
				p++;
			}
		}
		
		return f1;
	}

	private static int find(int[] brandID, int i) {
		// TODO Auto-generated method stub
		for(int j=0;j<brandID.length;j++){
			if(i==brandID[j])return 1;
		}
		return 0;
	}
}

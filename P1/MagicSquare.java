import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.util.*;

public class MagicSquare {
    String fileName;
    public static void main(String[] args) throws IOException {
        String address="C:\\Users\\L\\Desktop\\Spring2021_HITCS_SC_Lab1\\P1\\txt";
        System.out.println("1.txt:");
        MagicSquare M=new MagicSquare(address+"\\1.txt");
        boolean Bool=M.isLegalMagicSquare(address+"\\1.txt");
        System.out.println(Bool);
        System.out.println("2.txt:");
        M=new MagicSquare(address+"\\2.txt");
        Bool=M.isLegalMagicSquare(address+"\\2.txt");
        System.out.println(Bool);
        System.out.println("3.txt:");
        M=new MagicSquare(address+"\\3.txt");
        Bool=M.isLegalMagicSquare(address+"\\3.txt");
        System.out.println(Bool);
        System.out.println("4.txt:");
        M=new MagicSquare(address+"\\4.txt");
        Bool=M.isLegalMagicSquare(address+"\\4.txt");
        System.out.println(Bool);
        System.out.println("5.txt:");
        M=new MagicSquare(address+"\\5.txt");
        Bool=M.isLegalMagicSquare(address+"\\5.txt");
        System.out.println(Bool);
        //对已有矩阵验证

        MagicSquare.generateMagicSquare(7);
        //创建一个矩阵
        System.out.println("6.txt:");
        M=new MagicSquare(address+"\\6.txt");
        Bool=M.isLegalMagicSquare(address+"\\6.txt");
        System.out.println(Bool);
    }
    public MagicSquare(String fileName){
        this.fileName=fileName;
    }
    public boolean isLegalMagicSquare(String fileName) throws IOException {
        Path path=Paths.get(fileName);
        int m=0,i=0;
        Scanner aScanner=new Scanner(path);
        int[][] Ms;
        String[][] StrMs;
        String line;
        while (aScanner.hasNext()){
            aScanner.nextLine();
            m++;
        }
        StrMs=new String[m][];
        Ms=new int[m][m];
        aScanner=new Scanner(path);
        while(aScanner.hasNext()){
            line=aScanner.nextLine();
            StrMs[i]=line.split("\t");
            if(StrMs[i].length!=m){
                System.out.println("不是矩阵或分隔符不为‘\t’！");
                /*
                System.out.println(m);
                System.out.println(i);
                System.out.println(StrMs[i].length);
                System.out.println(Arrays.toString(StrMs[i]));
                Scanner bScan=new Scanner(line);System.out.print("[");
                while(bScan.hasNext()){
                    System.out.print(bScan.nextInt()+", ");
                }System.out.print("]");
                */
                return false;
            }
            i++;
        }
        for(int j=0;j<m;j++){
            for(int k=0;k<m;k++){
                Ms[j][k]=Integer.parseInt(StrMs[j][k]);
                if(Ms[j][k]<=0){
                    System.out.println("矩阵数字错误！");
                    return false;
                }
            }
        }

        int sum=0,temp=0;
        for(int a=0;a<m;a++){
            sum += Ms[a][a];
        }//以右斜线为基准数
        for(int a=0;a<m;a++){
            temp += Ms[m-a-1][a];
        }//计算左斜线
        if(temp!=sum){
            return false;
        }
        temp=0;
        for(int[] k :Ms){
            for(int l:k){
                temp+=l;
            }
            if(temp!=sum){
                return false;
            }
            temp=0;
        }//计算所有行
        for(int k=0;k<m;k++){
            for(int l=0;l<m;l++){
                temp+=Ms[l][k];
            }
            if(temp!=sum){
                return false;
            }
            temp=0;
        }//计算所有列
        return true;
    }

 /*       for(int k=0;k<m;k++){
            for(int l=0;l<n;l++){
                System.out.print(Ms[k][l]);
            }
            System.out.println();
        }
        return true;
    }
    */
 public static boolean generateMagicSquare(int n) throws FileNotFoundException {
     int[][] magic;
     try{
         magic = new int[n][n];
     }catch(NegativeArraySizeException e){
         System.out.println("输入了负数！");
         return false;
     }
     int row = 0, col = n / 2, i, j, square = n * n;
     for (i = 1; i <= square; i++) {
         try{
             magic[row][col] = i;//从1开始放入，放到正中间第一行
         }catch(ArrayIndexOutOfBoundsException e){
             System.out.println("输入了偶数！");
             return  false;
         }
         if (i % n == 0)
             row++;//每次放入n个数
         else {
             if (row == 0)
                 row = n - 1;
             else
                 row--;
             if (col == (n - 1))
                 col = 0;
             else
                 col++;
         }//向斜右上方移动
     }

     //扩展部分
     PrintWriter out = new PrintWriter("C:\\Users\\L\\Desktop\\Spring2021_HITCS_SC_Lab1\\P1\\txt\\6.txt");
     for (i = 0; i < n; i++) {
         for (j = 0; j < n; j++)
             out.print(magic[i][j] + "\t");
         out.println();
     }
     out.close();
     return true;
 }
}

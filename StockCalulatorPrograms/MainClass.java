import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bean.StockBean;

public class MainClass {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	StockBean sb=new StockBean();
	List<StockBean> list=new ArrayList<>();
	Double totalStockValue=0.0;
	System.out.println("Enter no of stacks");
	int stackCount=sc.nextInt();
	for(int i=1;i<=stackCount;i++)
	{
		System.out.println("Enter sharename");
		sb.setShareName(sc.next());
		System.out.println("Enter no of shares");
		sb.setNoOfShares(sc.nextInt());
		System.out.println("Enter share price");
		sb.setSharePrice(sc.nextDouble());
		sb.setStockValue(sb.CalucateStockValue(sb));
		totalStockValue=totalStockValue+sb.getStockValue();
		list.add(sb);
		sb=new StockBean();
	}
	
	System.out.println("Stock Report");
	
	System.out.println("------------------------------------------------------------------------------------");
    System.out.printf("%10s %30s %20s %20s ", "SHARENAME", "NO OF SHARES", "SHAREPRICE", "STOCKVALUE");
    System.out.println();
    System.out.println("------------------------------------------------------------------------------------");
	for(StockBean stb:list)
	{
		System.out.format("%10s %30s %20s %20s ",
                stb.getShareName(), stb.getNoOfShares(), stb.getSharePrice(), stb.getStockValue());
        System.out.println();
    }
	System.out.println();
    System.out.printf("%5s", "Total");
    System.out.format("%80s",totalStockValue);
    System.out.println();
    System.out.println("------------------------------------------------------------------------------------");
	
}
}

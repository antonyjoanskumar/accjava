package accounts;
import java.sql.*;
import dd.DBConnectionManager;
public class reports
{
String report[][] = new String[5000][50];
String report1[][] = new String[5000][50];
String matchname[] = new String[5000];
String matchslno[] = new String[5000];

String reason[] = new String[5000];
String login[] = new String[5000];
String name[] = new String[5000];

String hdate[] = new String[5000];
String hname[] = new String[5000];
int hamt[] = new int[5000];
int hrollno[] = new int[5000];
int hchelan[] = new int[5000];
int hslno[]=new int[5000];
int DD[] = new int[5000];

int Fslno[] = new int[5000];
String Fname[] = new String[5000];
int Famt[] = new int[5000];


int depno[] = new int[500];
int year[] = new int[500];
String depname[] = new String[500];
int fee[] = new int[500];
int dues[] = new int[500];
int examfee[] = new int[500];
int busfee[] = new int[500];
int revalfee[] = new int[500];
int libfine[] = new int[500];
int printdue[] = new int[500];
int receipt[] = new int[500];
int payment[] = new int[500];

String academicyear="";
String fdate;
String tdate;
int amt;
int ref;

int count=0;
int hcount=0;
int roll=0;
public String getHead()
{
count=0;
String quer="select slno,head from feesstructureheads order by head";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	matchslno[count]=rs.getString(1);
   	matchname[count]=rs.getString(2);
   	count++;
   }
rs.close();

 	matchslno[count]="2004";
   	matchname[count]="Printout Due";
   	count++;
 	matchslno[count]="2005";
   	matchname[count]="Library Fine";
   	count++;
 	matchslno[count]="2006";
   	matchname[count]="Fine or Due";
   	count++;
 	matchslno[count]="3000";
   	matchname[count]="Bus Fee";
   	count++;
 	matchslno[count]="4000";
   	matchname[count]="Exam Fee";
   	count++;
 	matchslno[count]="5000";
   	matchname[count]="Revaluation Fee";
   	count++;


stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}


public String getAmt(String hslno)
{
int	islno=Integer.parseInt(hslno);
String quer="";
if(islno<2006)
quer="select sum(amount) from payment where head='"+hslno+"' and date>='"+fdate+"' and date<='"+tdate+"'";

if ((islno>=2006)&&(islno<3000))
quer="select sum(amount) from payment where head>=2006 and head<3000 and date>='"+fdate+"' and date<='"+tdate+"'";

if ((islno>=3000)&&(islno<4000))
quer="select sum(amount) from payment where head>=3000 and head<4000 and date>='"+fdate+"' and date<='"+tdate+"'";

if ((islno>=4000)&&(islno<5000))
quer="select sum(amount) from payment where head>=4000 and head<5000 and date>='"+fdate+"' and date<='"+tdate+"'";

if ((islno>=5000)&&(islno<6000))
quer="select sum(amount) from payment where head>=5000 and head<6000 and date>='"+fdate+"' and date<='"+tdate+"'";


try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	amt=rs.getInt(1);
   }
rs.close();

if(islno<2006)
quer="select sum(amount) from refund where head='"+hslno+"' and date>='"+fdate+"' and date<='"+tdate+"'";

if ((islno>=2006)&&(islno<3000))
quer="select sum(amount) from refund where head>=2006 and head<3000 and date>='"+fdate+"' and date<='"+tdate+"'";

if ((islno>=3000)&&(islno<4000))
quer="select sum(amount) from refund where head>=3000 and head<4000 and date>='"+fdate+"' and date<='"+tdate+"'";

if ((islno>=4000)&&(islno<5000))
quer="select sum(amount) from refund where head>=4000 and head<5000 and date>='"+fdate+"' and date<='"+tdate+"'";

if ((islno>=5000)&&(islno<6000))
quer="select sum(amount) from refund where head>=5000 and head<6000 and date>='"+fdate+"' and date<='"+tdate+"'";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	ref=rs.getInt(1);
   }
rs.close();


stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}



public String getHeadname(int hslno)
{

String hn="";
String quer="";
if (hslno<2004)
{
quer="SELECT  head from feesstructureheads WHERE slno ="+hslno;

/*else
{
if (hslno==2004) hn="Printout Due";
if (hslno==2005) hn="Library Fine";

if ((hslno>=2006)&&(hslno<3000))
{ 
quer="SELECT  head from feesstructureheads WHERE slno ="+hslno;
hn="Fine or Due ";
}

if ((hslno>=3000)&&(hslno<4000))
{ 
quer="SELECT  head from feesstructureheads WHERE slno ="+hslno;
hn="Bus Fee ";
}
if ((hslno>=4000)&&(hslno<5000))
{ 
quer="SELECT  head from feesstructureheads WHERE slno ="+hslno;
hn="Exam Fee ";
}

if ((hslno>=5000)&&(hslno<6000))
{ 
quer="SELECT  head from feesstructureheads WHERE slno ="+hslno;
hn="Revaluation Fee ";
}
}
*/
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	hn=rs.getString(1);
   }
rs.close();

stmt.close();

connMgr.freeConnection("accounts",con);
}catch(Exception e){}


}
if (hslno==2004) hn="Printout Due";
if (hslno==2005) hn="Library Fine";
if ((hslno>=2006)&&(hslno<3000)) hn="Fine or Due ";
if ((hslno>=3000)&&(hslno<4000)) hn="Bus Fee ";
if ((hslno>=4000)&&(hslno<5000)) hn="Exam Fee ";
if ((hslno>=5000)&&(hslno<6000)) hn="Revaluation Fee ";

return hn;
}

public String getHeadGroup(String head1)
{
hcount=0;
String quer=" ";
quer="select reason,CAST(sum(a.amount) AS INT) ,CAST(isnull(sum(b.amount),0) as int),CAST(sum(a.amount)-isnull(sum(b.amount),0) as int),max(login) from "
+"(select * from dues where date between '"+fdate+"' and '"+tdate+"') a "
+"left outer join (select * from payment where date between '"+fdate+"' and '"+tdate+"') b "
+"on a.head=b.head and a.rollno=b.rollno group by reason "+head1;


try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	for(int i=1;i<=5;i++)
	{
		report[hcount][i-1]=rs.getString(i);
	}
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}
return "suc"+head1;
}



public String getHeadwise(String head1)
{
hcount=0;
String quer=" ";
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),s.rollno,chelan,amount,name from sxcce.dbo.student s,payment where s.rollno=payment.rollno and date>='"+fdate+"' and date<='"+tdate+"' and head=(select slno from feesstructureheads where head='"+ head1+"') order by chelan";

if (head1.equals("All"))
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),s.rollno,chelan,amount,name from sxcce.dbo.student s,payment where s.rollno=payment.rollno and date>='"+fdate+"' and date<='"+tdate+"' order by chelan";

if (head1.equals("Printout Due"))
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),s.rollno,chelan,amount,name from sxcce.dbo.student s,payment where s.rollno=payment.rollno and date>='"+fdate+"' and date<='"+tdate+"' and head=2004 order by chelan";

if (head1.equals("Library Fine"))
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),s.rollno,chelan,amount,name from sxcce.dbo.student s,payment where s.rollno=payment.rollno and date>='"+fdate+"' and date<='"+tdate+"' and head=2005 order by chelan";

if (head1.equals("Fine or Due"))
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),s.rollno,chelan,amount,name from sxcce.dbo.student s,payment where s.rollno=payment.rollno and date>='"+fdate+"' and date<='"+tdate+"' and head>=2006 and head<3000 order by chelan";

if (head1.equals("Bus Fee"))
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),s.rollno,chelan,amount,name from sxcce.dbo.student s,payment where s.rollno=payment.rollno and date>='"+fdate+"' and date<='"+tdate+"' and head>=3000 and head<4000 order by chelan";

if (head1.equals("Exam Fee"))
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),s.rollno,chelan,amount,name from sxcce.dbo.student s,payment where s.rollno=payment.rollno and date>='"+fdate+"' and date<='"+tdate+"' and head>=4000 and head<5000 order by chelan";

if (head1.equals("Revaluation Fee"))
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),s.rollno,chelan,amount,name from sxcce.dbo.student s,payment where s.rollno=payment.rollno and date>='"+fdate+"' and date<='"+tdate+"' and head>=5000 and head<6000 order by chelan";

if (head1.equals("Refunded"))
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),s.rollno,chelan,amount,name from sxcce.dbo.student s,refund where s.rollno=refund.rollno and date>='"+fdate+"' and date<='"+tdate+"' order by chelan";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hchelan[hcount]=rs.getInt(3);
   	hamt[hcount]=rs.getInt(4);
   	name[hcount]=rs.getString(5);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}
return "suc"+head1;
}

public String getHeadGroup(int slno,String head1)
{
count=0;
String quer=" ";
String table="";
                count=0;
                int amount=0;

	if (slno<3000) table="dues";
	if (slno==3000) table="busfee";
	if (slno==4000) table="examfee";
	if (slno==5000) table="revalfee";
	
quer="select ltrim(rtrim(ltrim(rtrim(str(day(date))))+'/'+ltrim(rtrim(str(month(date))))+'/'+ltrim(rtrim(str(year(date)))))) date1,sxcce.dbo.student.rollno,"+
" sxcce.dbo.student.name,ltrim(rtrim(ltrim(rtrim(str(amount))))) amount,login,head from "+table+",sxcce.dbo.student where date>='"+fdate+"' and date<='"+tdate+"' and "+
table+".rollno=sxcce.dbo.student.rollno and reason='"+ head1+"' order by date1,sxcce.dbo.student.rollno";

if (head1.substring(0,3).equalsIgnoreCase("All")) 
quer="select ltrim(rtrim(ltrim(rtrim(str(day(date))))+'/'+ltrim(rtrim(str(month(date))))+'/'+ltrim(rtrim(str(year(date)))))) date1,sxcce.dbo.student.rollno,"+
" sxcce.dbo.student.name,ltrim(rtrim(ltrim(rtrim(str(amount))))) amount,login,head from "+table+",sxcce.dbo.student where date>='"+fdate+"' and date<='"+tdate+"' and "+
table+".rollno=sxcce.dbo.student.rollno order by date1,sxcce.dbo.student.rollno";
		
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
	  			report[count][0]=rs.getString(1);
	  			report[count][1]=rs.getString(2);
	  			report[count][2]=rs.getString(3);
	  			report[count][3]=rs.getString(4);
	  			report[count][4]=rs.getString(5);
	  			report[count][5]=rs.getString(6);
	  			report[count][6]="0";
	  			count++;
}
rs.close();
        for(int j=0;j<count;j++)
        {
        	amount=0;
        	quer="select isnull(sum(amount),0) from accounts.dbo.payment where head="+report[j][5]+" and rollno="+report[j][1];

		rs = stmt.executeQuery(quer);
        if(rs.next())					
		{	
			amount=rs.getInt(1);
			amount=Integer.parseInt(report[j][3])-amount;
			report[j][6]=Integer.toString(amount);
		}
			rs.close();
        	amount=0;
        	quer="select isnull(sum(amount),0) from accounts.dbo.refund where head="+report[j][5]+" and rollno="+report[j][1];

		rs = stmt.executeQuery(quer);
        if(rs.next())					
		{	
			amount=rs.getInt(1);
			amount=Integer.parseInt(report[j][6])+amount;
			report[j][6]=Integer.toString(amount);
		}
			rs.close();
        }

stmt.close();
connMgr.freeConnection("accounts",con);

}catch(Exception e){return quer+e.toString();}
return "suc";
}

public String getHeadGroupClass(int slno,String head1,int dno1, int year1, String ayear1)
{
count=0;
int count1=0;
String quer=" ";
String table="";
                count=0;
                int amount=0;

	if (slno<3000) table="dues";
	if (slno==3000) table="busfee";
	if (slno==4000) table="examfee";
	if (slno==5000) table="revalfee";
	
quer="select ltrim(rtrim(ltrim(rtrim(str(day(date))))+'/'+ltrim(rtrim(str(month(date))))+'/'+ltrim(rtrim(str(year(date)))))) date1,sxcce.dbo.stud.rollno,"+
" sxcce.dbo.stud.name,ltrim(rtrim(ltrim(rtrim(str(amount))))) amount,login,head from "+table+",sxcce.dbo.stud where date>='"+fdate+"' and date<='"+tdate+"' and "+
table+".rollno=sxcce.dbo.stud.rollno and reason='"+ head1+"' and academicyear='"+ayear1+"' and department="+dno1+" and year="+year1+" order by date1,sxcce.dbo.stud.rollno";

if (head1.substring(0,3).equalsIgnoreCase("All")) 
quer="select ltrim(rtrim(ltrim(rtrim(str(day(date))))+'/'+ltrim(rtrim(str(month(date))))+'/'+ltrim(rtrim(str(year(date)))))) date1,sxcce.dbo.stud.rollno,"+
" sxcce.dbo.stud.name,ltrim(rtrim(ltrim(rtrim(str(amount))))) amount,login,head from "+table+",sxcce.dbo.stud where date>='"+fdate+"' and date<='"+tdate+"' and "+
table+".rollno=sxcce.dbo.stud.rollno and academicyear='"+ayear1+"' and department="+dno1+" and year="+year1+" order by date1,sxcce.dbo.stud.rollno";
		
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
	  			report1[count1][0]=rs.getString(1);
	  			report1[count1][1]=rs.getString(2);
	  			report1[count1][2]=rs.getString(3);
	  			report1[count1][3]=rs.getString(4);
	  			report1[count1][4]=rs.getString(5);
	  			report1[count1][5]=rs.getString(6);
	  			report1[count1][6]="0";
	  			count1++;
}
rs.close();
count=0;
        for(int j=0;j<count1;j++)
        {
        	
        	amount=0;
        	quer="select isnull(sum(amount),0) from accounts.dbo.payment where head="+report1[j][5]+" and rollno="+report1[j][1];

		rs = stmt.executeQuery(quer);
        if(rs.next())					
		{	
			amount=rs.getInt(1);
			amount=Integer.parseInt(report1[j][3])-amount;
			report1[j][6]=Integer.toString(amount);
		}
		if(amount>0)
		{
				  			report[count][0]=report1[j][0];
				  			report[count][1]=report1[j][1];
				  			report[count][2]=report1[j][2];
				  			report[count][3]=report1[j][3];
				  			report[count][4]=report1[j][4];
				  			report[count][5]=report1[j][5];
				  			report[count][6]=report1[j][6];
				  			count++;
		}
			rs.close();
        }

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}



public String getOtherHeadwise(String head1)
{
hcount=0;
String quer=" ";
quer="select ltrim(str(day(payment.date)))+'/'+ltrim(str(month(payment.date)))+'/'"+
" +ltrim(str(year(payment.date))),payment.rollno,payment.chelan,payment.amount from payment,dues where "+
"dues.head = payment.head AND dues.rollno = payment.rollno and payment.date>='"+fdate+"' and payment.date<='"+tdate+"' "+
"and (dues.reason = '"+ head1+"') order by chelan";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hchelan[hcount]=rs.getInt(3);
   	hamt[hcount]=rs.getInt(4);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}


public String getPaymentHeadLike(String head1)
{
hcount=0;
String quer=" ";
quer="select ltrim(str(day(payment.date)))+'/'+ltrim(str(month(payment.date)))+'/'"+
" +ltrim(str(year(payment.date))),payment.rollno,payment.chelan,payment.amount,sxcce.dbo.student.name,reason from sxcce.dbo.student,payment,dues where sxcce.dbo.student.rollno=payment.rollno and "+
"dues.head = payment.head AND dues.rollno = payment.rollno and payment.date>='"+fdate+"' and payment.date<='"+tdate+"' "+
"and (dues.reason like '%"+ head1+"%') order by chelan";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hchelan[hcount]=rs.getInt(3);
   	hamt[hcount]=rs.getInt(4);
   	name[hcount]=rs.getString(5);
   	reason[hcount]=rs.getString(6);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}

public String getDuesHeadLike(String head1)
{
hcount=0;
String quer=" ";
quer="select ltrim(str(day(dues.date)))+'/'+ltrim(str(month(dues.date)))+'/'"+
" +ltrim(str(year(dues.date))),dues.rollno,dues.amount,login,sxcce.dbo.student.name,reason from sxcce.dbo.student,dues where sxcce.dbo.student.rollno=dues.rollno and " +
"dues.date>='"+fdate+"' and dues.date<='"+tdate+"' "+
"and (dues.reason like '%"+ head1+"%') order by dues.date";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hamt[hcount]=rs.getInt(3);
   	login[hcount]=rs.getString(4);
   	name[hcount]=rs.getString(5);
   	reason[hcount]=rs.getString(6);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}


public String getDuesHeadwise(String head1)
{
hcount=0;
String quer=" ";
quer="select ltrim(str(day(dues.date)))+'/'+ltrim(str(month(dues.date)))+'/'"+
" +ltrim(str(year(dues.date))),dues.rollno,dues.login,dues.amount from dues where "+
"dues.date>='"+fdate+"' and dues.date<='"+tdate+"' "+
"and (dues.reason = '"+ head1+"') order by date,rollno";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	login[hcount]=rs.getString(3);
   	hamt[hcount]=rs.getInt(4);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}

public String amtInsert()
	{
		//getCollection();
				int i=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
				PreparedStatement ps;
	String sql1="";
				for(i=0;i<count;i++)
				{
if (DD[i]==0)
	sql1="update payment set amount=?,chelan=? where rollno=? and date=? and head=?";
else
	sql1="delete from payment where amount=? and chelan=? and rollno=? and date=? and head=?";

				ps = con.prepareStatement(sql1);
				ps.setInt(1,hamt[i]);
				ps.setInt(2,hchelan[i]);
				ps.setInt(3,hrollno[i]);
				ps.setString(4,hdate[i]);
				ps.setInt(5,hslno[i]);
				ps.executeUpdate();
				ps.close();
			}
			connMgr.freeConnection("accounts",con);
				}catch(Exception e){return e.toString()+hrollno[i]+hdate[i];}
//			return count+"success"+ hamt[0]+hchelan[0]+hrollno[0]+hdate[0]+hslno[0];

			return "success";
	}

public String refundUpdate()
	{
	int i=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
				PreparedStatement ps;
	String sql2="";
				for( i=0;i<count;i++)
				{
	if (DD[i]==0)
	sql2="update refund set amount=? where rollno=? and date=? and head=?";
	else
	sql2="delete from refund where amount=? and rollno=? and date=? and head=?";

				ps = con.prepareStatement(sql2);
				ps.setInt(1,hamt[i]);
//				ps.setInt(2,hchelan[i]);
				ps.setInt(2,hrollno[i]);
				ps.setString(3,hdate[i]);
				ps.setInt(4,hslno[i]);
				ps.executeUpdate();
				ps.close();
			}
			connMgr.freeConnection("accounts",con);

				}catch(Exception e){return e.toString()+"---"+count+"---"+hrollno[i]+hdate[i];}
//			return sql1+count+"success"+ hamt[0]+hchelan[0]+hrollno[0]+hdate[0]+hslno[0]+"---"+DD[1];
//			return "success"+hamt[1]+hchelan[1]+hrollno[1]+hdate[1]+hslno[1]+"---"+DD[1];
			
return "success";
	}


public String duesUpdate()
	{
int i=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
				PreparedStatement ps;
	String sql1="";
	String tab1="dues";
for(i=0;i<count;i++)
{
if((hslno[i]>3000) && (hslno[i]<4000)) 
tab1="busfee";

if((hslno[i]>4000) && (hslno[i]<5000)) 
tab1="examfee";

if((hslno[i]>5000) && (hslno[i]<6000)) 
tab1="revalfee";
 
if (DD[i]==0)
	sql1="update "+tab1+" set amount=? where date=? and rollno=? and head=?";
else
	sql1="delete from "+tab1+" where amount=? and date=? and rollno=? and head=?";

				ps = con.prepareStatement(sql1);
				ps.setInt(1,hamt[i]);
				ps.setString(2,hdate[i]);
				ps.setInt(3,hrollno[i]);
				ps.setInt(4,hslno[i]);
				ps.executeUpdate();
				ps.close();
			}
			connMgr.freeConnection("accounts",con);

				}catch(Exception e){return e.toString()+hrollno[i]+hdate[i];}
//			return count+"success"+ hamt[0]+hchelan[0]+hrollno[0]+hdate[0]+hslno[0];
			
			return "success";
	}


public String getCollection()
{
hcount=0;
String quer=" ";
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),rollno,head,chelan,amount from payment where date>='"+fdate+"' and date<='"+tdate+"'  order by chelan";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);

while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hslno[hcount]=rs.getInt(3);
   	hchelan[hcount]=rs.getInt(4);
   	hamt[hcount]=rs.getInt(5);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}


public String getRefund()
{
hcount=0;
String quer=" ";
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),refund.rollno,head,chelan,amount,name from refund,sxcce.dbo.student where refund.rollno=sxcce.dbo.student.rollno and date>='"+fdate+"' and date<='"+tdate+"'  order by chelan";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);

while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hslno[hcount]=rs.getInt(3);
   	hchelan[hcount]=rs.getInt(4);
   	hamt[hcount]=rs.getInt(5);
   	hname[hcount]=rs.getString(6);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}

public String getstudCollection()
{
hcount=0;
String quer=" ";
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),rollno,head,chelan,amount from payment where rollno="+roll+" order by date";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);

while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hslno[hcount]=rs.getInt(3);
   	hchelan[hcount]=rs.getInt(4);
   	hamt[hcount]=rs.getInt(5);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}

public String getstudCollection1()
{
hcount=0;
String quer=" ";
quer="select ltrim(str(month(date)))+'/'+ltrim(str(day(date)))+'/'+ltrim(str(year(date))),rollno,head,chelan,amount from payment where rollno="+roll+" order by date";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);

while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hslno[hcount]=rs.getInt(3);
   	hchelan[hcount]=rs.getInt(4);
   	hamt[hcount]=rs.getInt(5);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}


public String getColhdate()
{
hcount=0;
String quer=" ";
quer="select date from payment where date>='"+fdate+"' and date<='"+tdate+"'  order by chelan";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);

while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}


public String getRefhdate()
{
hcount=0;
String quer=" ";
quer="select date from refund where date>='"+fdate+"' and date<='"+tdate+"'  order by chelan";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);

while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}



public String getGroupCollection()
{
hcount=0;
String quer=" ";
quer="select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),rollno,chelan,sum(amount) from payment where date>='"+fdate+"' and date<='"+tdate+"'  GROUP BY rollno, date, chelan order by chelan";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);

while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hchelan[hcount]=rs.getInt(3);
   	hamt[hcount]=rs.getInt(4);
   	hcount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}


public String getDues()
{
hcount=0;
String quer=" ";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;

quer="select ltrim(str(month(date)))+'/'+ltrim(str(day(date)))+'/'+ltrim(str(year(date))),dues.rollno,head,amount,reason,login,name from dues,sxcce.dbo.student where dues.rollno=sxcce.dbo.student.rollno and date>='"+fdate+"' and date<='"+tdate+"'  order by dues.rollno";
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hslno[hcount]=rs.getInt(3);
//   	hchelan[hcount]=rs.getInt(4);
   	hamt[hcount]=rs.getInt(4);
   	reason[hcount]=rs.getString(5);
   	login[hcount]=rs.getString(6);
   	name[hcount]=rs.getString(7);
   	hcount++;
}
rs.close();
quer="select ltrim(str(month(date)))+'/'+ltrim(str(day(date)))+'/'+ltrim(str(year(date))),busfee.rollno,head,amount,reason,login,name from busfee,sxcce.dbo.student where busfee.rollno=sxcce.dbo.student.rollno and  date>='"+fdate+"' and date<='"+tdate+"'  order by busfee.rollno";
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hslno[hcount]=rs.getInt(3);
//   	hchelan[hcount]=rs.getInt(4);
   	hamt[hcount]=rs.getInt(4);
   	reason[hcount]=rs.getString(5);
   	login[hcount]=rs.getString(6);
   	name[hcount]=rs.getString(7);
   	hcount++;
}
rs.close();
quer="select ltrim(str(month(date)))+'/'+ltrim(str(day(date)))+'/'+ltrim(str(year(date))),examfee.rollno,head,amount,reason,login,name from examfee,sxcce.dbo.student where examfee.rollno=sxcce.dbo.student.rollno and date>='"+fdate+"' and date<='"+tdate+"'  order by examfee.rollno";
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hslno[hcount]=rs.getInt(3);
//   	hchelan[hcount]=rs.getInt(4);
   	hamt[hcount]=rs.getInt(4);
   	reason[hcount]=rs.getString(5);
   	login[hcount]=rs.getString(6);
   	name[hcount]=rs.getString(7);
   	hcount++;
}
rs.close();
quer="select ltrim(str(month(date)))+'/'+ltrim(str(day(date)))+'/'+ltrim(str(year(date))),revalfee.rollno,head,amount,reason,login,name from revalfee,sxcce.dbo.student where revalfee.rollno=sxcce.dbo.student.rollno and  date>='"+fdate+"' and date<='"+tdate+"'  order by revalfee.rollno";
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	hdate[hcount]=rs.getString(1);
   	hrollno[hcount]=rs.getInt(2);
   	hslno[hcount]=rs.getInt(3);
//   	hchelan[hcount]=rs.getInt(4);
   	hamt[hcount]=rs.getInt(4);
   	reason[hcount]=rs.getString(5);
   	login[hcount]=rs.getString(6);
   	name[hcount]=rs.getString(7);
   	hcount++;
}
rs.close();


stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}


public String getFeeStru()
{
hcount=0;
String quer="select slno,name from feesstructureidentify order by name";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	Fslno[hcount]=rs.getInt(1);
   	Fname[hcount]=rs.getString(2);
   	hcount++;
}
rs.close();

for(int i=0;i<hcount;i++)
{
rs = stmt.executeQuery("SELECT     SUM(amount) FROM feesstructureamount where fsino ="+Fslno[i]);
if(rs.next())
   {
   	Famt[i]=rs.getInt(1);
}
rs.close();

}
stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return quer+e.toString();}

return "suc";
}


public String getTotFee(String accyear)
{
count=0;
String quer="";
String quer1="";
int t1=0;
String s1="";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select dno,sf from sxcce.dbo.department";
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	t1=rs.getInt(1);
	s1=rs.getString(2);

for(int j=1;j<=4;j++)
{
   	depno[count]=t1;
   	year[count]=j;
if(j==1)	depname[count]="I "+s1;
if(j==2)	depname[count]="II "+s1;
if(j==3)	depname[count]="III "+s1;
if(j==4)	depname[count]="IV "+s1;
	count++;
}
}
rs.close();
quer= "select academicyear FROM sxcce.dbo.academicyear";

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	academicyear=rs.getString(1);
}
rs.close();
depname[count]="Discontinued students";
count++;

for(int i=0;i<count;i++)
{

fee[i]=0;
dues[i]=0;
busfee[i]=0;
examfee[i]=0;
revalfee[i]=0;
libfine[i]=0;
printdue[i]=0;
receipt[i]=0;
payment[i]=0;
if(i==count-1)
quer1="(SELECT sxcce.dbo.stud.rollno FROM  sxcce.dbo.stud,sxcce.dbo.student "+
" WHERE sxcce.dbo.stud.rollno = sxcce.dbo.student.rollno  AND sxcce.dbo.student.active=0 and sxcce.dbo.stud.academicyear = '"+accyear+"' group by sxcce.dbo.stud.rollno)";

//quer1="(SELECT rollno FROM payment WHERE (rollno NOT IN (SELECT sxcce.dbo.stud.rollno"+
//" FROM sxcce.dbo.stud,sxcce.dbo.student WHERE sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and sxcce.dbo.student.active=1 and sxcce.dbo.stud.academicyear = '"+ academicyear + "')))";
else 
quer1="(SELECT sxcce.dbo.stud.rollno FROM  sxcce.dbo.stud,sxcce.dbo.student "+
"WHERE sxcce.dbo.stud.rollno = sxcce.dbo.student.rollno  and  sxcce.dbo.stud.department ="+ depno[i]+" and sxcce.dbo.stud.year="+ year[i] +
" AND sxcce.dbo.student.active=1 and sxcce.dbo.stud.academicyear = '"+ accyear +"' group by sxcce.dbo.stud.rollno)";

quer= "SELECT SUM(amount) FROM feesstructureamount,feeassign where feesstructureamount.fsino = feeassign.fslno and feeassign.rollno IN "+quer1;

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	fee[i]=fee[i]+rs.getInt(1);
}
rs.close();

quer= "SELECT SUM(amount) FROM feesstructureindividualamount,"+
" feesstructureindividualidentify where feesstructureindividualamount.fsiino = feesstructureindividualidentify.slno  and feesstructureindividualidentify.rollno IN "+quer1;

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	fee[i]=fee[i]+rs.getInt(1);
}
rs.close();


quer= "select sum(amount) FROM dues WHERE  rollno IN " +quer1;

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	dues[i]=rs.getInt(1);
}
rs.close();

quer= "select sum(amount) FROM busfee WHERE  rollno IN " +quer1;

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	busfee[i]=rs.getInt(1);
}
rs.close();

quer= "select sum(amount) FROM examfee WHERE  rollno IN " +quer1;

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	examfee[i]=rs.getInt(1);
}
rs.close();

quer= "select sum(amount) FROM revalfee WHERE  rollno IN " +quer1;

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	revalfee[i]=rs.getInt(1);
}
rs.close();

if(i==count-1)
quer= "select sum(fine) from library.dbo.libfine where rollno IN " + "(SELECT convert(char, rollno) FROM payment WHERE (rollno NOT IN (SELECT sxcce.dbo.stud.rollno"+ 
" FROM sxcce.dbo.stud,sxcce.dbo.student WHERE sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and sxcce.dbo.student.active=1 and sxcce.dbo.stud.academicyear = '"+ accyear + "')))";
else
quer= "select sum(fine) from library.dbo.libfine where rollno IN " + "(SELECT convert(char, sxcce.dbo.stud.rollno) FROM  sxcce.dbo.stud,sxcce.dbo.student "+
"WHERE sxcce.dbo.stud.rollno = sxcce.dbo.student.rollno  and  sxcce.dbo.stud.department ="+ depno[i]+" and sxcce.dbo.stud.year="+ year[i] +
" AND sxcce.dbo.student.active=1 and sxcce.dbo.stud.academicyear = '"+ accyear +"' group by sxcce.dbo.stud.rollno)";

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	libfine[i]=rs.getInt(1);
}
rs.close();


quer= "select sum(noofpages)*3 from sxcce.dbo.printouts where printedorregistered=1 and rollno IN " +quer1;

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	printdue[i]=rs.getInt(1);
}
rs.close();

quer= "select sum(amount) FROM payment WHERE  rollno IN " +quer1;

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	receipt[i]=rs.getInt(1);
}
rs.close();

quer= "select sum(amount) FROM REFUND WHERE  rollno IN " +quer1;

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	payment[i]=rs.getInt(1);
}
rs.close();

}
stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return count+quer+e.toString();}

return count+"suc";
}



public String getAllFee(String accyear,int act)
{
count=0;
String quer="";
String quer1="";
int t1=0;
String s1="";
int jk1=0;
int jk2=0;
int jk3=0;
int jk4=0;
int jk5=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select * from feesstructureheads where slno not in(101010) order by head";
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	report[0][count]=rs.getInt(1)+"";
   	report[1][count]=rs.getString(2);
	count++;
}
rs.close();

if(act==0)	
quer1="(SELECT sxcce.dbo.stud.rollno FROM  sxcce.dbo.stud,sxcce.dbo.student "
+" WHERE sxcce.dbo.stud.rollno = sxcce.dbo.student.rollno  and sxcce.dbo.stud.academicyear = '"+ accyear +"' group by sxcce.dbo.stud.rollno)";
else
quer1="(SELECT sxcce.dbo.stud.rollno FROM  sxcce.dbo.stud,sxcce.dbo.student "
+" WHERE sxcce.dbo.stud.rollno = sxcce.dbo.student.rollno AND sxcce.dbo.student.active=1 and sxcce.dbo.stud.academicyear = '"+ accyear +"' group by sxcce.dbo.stud.rollno)";


for(int i=0;i<count;i++)
{
jk1=0;
jk2=0;
jk3=0;
jk4=0;
jk5=0;

  for(int j=2;j<10;j++)
	report[j][i]="0";

quer="SELECT isnull(SUM(amount),0) FROM feesstructureamount,feeassign where feesstructureamount.fsino = feeassign.fslno and fshno="+report[0][i]+" and feeassign.rollno IN "+quer1;
rs = stmt.executeQuery(quer);
if(rs.next())
   {
	jk1=rs.getInt(1);
}
rs.close();

quer= "SELECT isnull(SUM(amount),0) FROM feesstructureindividualamount, "+
" feesstructureindividualidentify where feesstructureindividualamount.fsiino = feesstructureindividualidentify.slno and fshno="+report[0][i]+"   and feesstructureindividualidentify.rollno IN "+quer1;

rs = stmt.executeQuery(quer);
if(rs.next())
   {
	jk2=rs.getInt(1);
}
rs.close();
	report[2][i]=(jk1+jk2)+"";

quer= "select isnull(sum(amount),0) FROM payment WHERE head="+report[0][i]+" and  rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next()) {	jk3=rs.getInt(1);}
rs.close();
	report[3][i]=jk3+"";

quer= "select isnull(sum(amount),0) FROM REFUND WHERE head="+report[0][i]+" and rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk4=rs.getInt(1);}
rs.close();
	report[4][i]=jk4+"";
	report[5][i]=((jk1+jk2)-(jk3-jk4))+"";
	

}

jk1=0; jk2=0; jk3=0; jk4=0; jk5=0;

quer= "select isnull(sum(noofpages),0)*3 from sxcce.dbo.printouts where printedorregistered=1 and rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())    {	jk1=rs.getInt(1);}
rs.close();
report[2][count]=jk1+"";

quer= "select isnull(sum(amount),0) FROM payment WHERE head=2004 and  rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next()) {	jk3=rs.getInt(1);}
rs.close();
report[3][count]=jk3+"";

quer= "select isnull(sum(amount),0) FROM REFUND WHERE head=2004 and rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk4=rs.getInt(1);}
rs.close();
	report[4][count]=jk4+"";
	report[5][count]=(jk1-(jk3-jk4))+"";

report[1][count]="Printout Due";
count++;

jk1=0; jk2=0; jk3=0; jk4=0; jk5=0;

quer= "select isnull(sum(fine),0) from library.dbo.libfine where rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk1=rs.getInt(1);
}
rs.close();
report[2][count]=jk1+"";

quer= "select isnull(sum(amount),0) FROM payment WHERE head=2005 and  rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next()) {	jk3=rs.getInt(1);}
rs.close();
report[3][count]=jk3+"";

quer= "select isnull(sum(amount),0) FROM REFUND WHERE head=2005 and rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk4=rs.getInt(1);}
rs.close();
	report[4][count]=jk4+"";
	report[5][count]=(jk1-(jk3-jk4))+"";

report[1][count]="Library Fine";
count++;

jk1=0; jk2=0; jk3=0; jk4=0; jk5=0;
quer= "select isnull(sum(amount),0) FROM dues WHERE  rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk1=rs.getInt(1);}
rs.close();
report[2][count]=jk1+"";
quer= "select isnull(sum(amount),0) FROM payment WHERE head >2005 and head<3000 and  rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next()) {	jk3=rs.getInt(1);}
rs.close();
report[3][count]=jk3+"";

quer= "select isnull(sum(amount),0) FROM REFUND WHERE head >2005 and head<3000 and rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk4=rs.getInt(1);}
rs.close();
	report[4][count]=jk4+"";
	report[5][count]=(jk1-(jk3-jk4))+"";

report[1][count]="Fine or Dues";
count++;

jk1=0; jk2=0; jk3=0; jk4=0; jk5=0;
quer= "select isnull(sum(amount),0) FROM busfee WHERE  rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk1=rs.getInt(1);}
rs.close();
report[2][count]=jk1+"";
quer= "select isnull(sum(amount),0) FROM payment WHERE head >3000 and head<4000 and  rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next()) {	jk3=rs.getInt(1);}
rs.close();
report[3][count]=jk3+"";

quer= "select isnull(sum(amount),0) FROM REFUND WHERE head >3000 and head<4000 and rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk4=rs.getInt(1);}
rs.close();
	report[4][count]=jk4+"";
	report[5][count]=(jk1-(jk3-jk4))+"";

report[1][count]="Bus Fee";
count++;

jk1=0; jk2=0; jk3=0; jk4=0; jk5=0;
quer= "select isnull(sum(amount),0) FROM examfee WHERE  rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk1=rs.getInt(1);}
rs.close();
report[2][count]=jk1+"";
quer= "select isnull(sum(amount),0) FROM payment WHERE head >4000 and head<5000 and  rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next()) {	jk3=rs.getInt(1);}
rs.close();
report[3][count]=jk3+"";

quer= "select isnull(sum(amount),0) FROM REFUND WHERE head >4000 and head<5000 and rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk4=rs.getInt(1);}
rs.close();
	report[4][count]=jk4+"";
	report[5][count]=(jk1-(jk3-jk4))+"";

report[1][count]="Exam Fee";
count++;

jk1=0; jk2=0; jk3=0; jk4=0; jk5=0;
quer= "select isnull(sum(amount),0) FROM revalfee WHERE  rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk1=rs.getInt(1);}
rs.close();
report[2][count]=jk1+"";
quer= "select isnull(sum(amount),0) FROM payment WHERE head >5000 and head<6000 and  rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next()) {	jk3=rs.getInt(1);}
rs.close();
report[3][count]=jk3+"";

quer= "select isnull(sum(amount),0) FROM REFUND WHERE head >5000 and head<6000 and rollno IN " +quer1;
rs = stmt.executeQuery(quer);
if(rs.next())   {	jk4=rs.getInt(1);}
rs.close();
	report[4][count]=jk4+"";
	report[5][count]=(jk1-(jk3-jk4))+"";

report[1][count]="Revaluation Fee";
count++;


stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return count+quer+e.toString();}

return count+"suc";
}

public String[] getdepname()
{
	return this.depname;
}

public int[] getfee()
{
	return this.fee;
}

public int[] getdues()
{
	return this.dues;
}

public int[] getbusfee()
{
	return this.busfee;
}
public int[] getexamfee()
{
	return this.examfee;
}
public int[] getrevalfee()
{
	return this.revalfee;
}
public int[] getlibfine()
{
	return this.libfine;
}
public int[] getprintdue()
{
	return this.printdue;
}

public int[] getreceipt()
{
	return this.receipt;
}

public int[] getpayment()
{
	return this.payment;
}

public int[] getFslno()
{
	return this.Fslno;
}

public String[] getFname()
{
	return this.Fname;
}
public String[] getname()
{
	return this.name;
}

public int[] getFamt()
{
	return this.Famt;
}

public String[] getmatchslno()
{
	return this.matchslno;
}
public String[] getmatchname()
{
	return this.matchname;
}

public String[] getreason()
{
	return this.reason;
}

public String[] getlogin()
{
	return this.login;
}

public int getcount()
{
	return this.count;
}

public int gethcount()
{
	return this.hcount;
}

public int getamt()
{
	return this.amt;
}
public int getref()
{
	return this.ref;
}

public int[] gethamt()
{
	return this.hamt;
}

public int[] gethrollno()
{
	return this.hrollno;
}

public int[] gethchelan()
{
	return this.hchelan;
}

public String[] gethdate()
{
	return this.hdate;
}

public String[] gethname()
{
	return this.hname;
}

public int[] gethslno()
{
	return this.hslno;
}

public void sethdate(String[] hdate) 
{ 
this.hdate = hdate; 
} 

public void sethrollno(int[] hrollno) 
{ 
this.hrollno = hrollno; 
} 

public void sethslno(int[] hslno) 
{ 
this.hslno = hslno; 
} 
public void sethchelan(int[] hchelan) 
{ 
this.hchelan = hchelan; 
} 

public void setDD(int[] dd) 
{ 
this.DD = dd; 
} 

public void sethamt(int[] hamt) 
{ 
this.hamt = hamt; 
} 

public void setcount(int count) 
{ 
this.count = count; 
} 

public void setroll(int roll) 
{ 
this.roll = roll; 
} 

public int getroll()
{
	return this.roll;
}

public void setfdate(String fdate) 
{ 
this.fdate = fdate; 
} 

public void settdate(String tdate) 
{ 
this.tdate = tdate; 
} 
public String[][] getreport() 
{ 
return this.report; 
} 


}

/*
-------------------------------------------------------------------------------------------
<% String address1=""; 
String address2=""; 
String address3=""; 
String cell=""; 
String name=""; 
String phone=""; 
String pincode=""; 
String slno=""; 
%>
<jsp:useBean class="xavier.xavier." id="sss" >
<jsp:setProperty name="sss" property="*" />
<%
address1=sss.getaddress1(); 
address2=sss.getaddress2(); 
address3=sss.getaddress3(); 
cell=sss.getcell(); 
name=sss.getname(); 
phone=sss.getphone(); 
pincode=sss.getpincode(); 
slno=sss.getslno(); 
%>
</jsp:useBean>
-------------------------------------------------------------------------------------------
*/

package accounts;
import java.sql.*;
import dd.DBConnectionManager;
public class accrep
{
String repin[] = new String[100];
String report[][] = new String[50][5000];
String report1[] = new String[5000];

int count=0;

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


public String getTutFee(String accyear,int act)
{
count=0;
String quer="";
String quer1="";
String quer2="";
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
quer="select * from feesstructureheads where slno in(101,126,127,128) order by head";
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	report[0][count]=rs.getInt(1)+"";
   	report[1][count]=rs.getString(2);
	count++;
}
rs.close();


for(int i=0;i<count;i++)
{
jk1=0;
jk2=0;
jk3=0;
jk4=0;
jk5=0;

if(report[0][i].equals("101")) quer2=" and stud.year=1 ";
if(report[0][i].equals("126")) quer2=" and stud.year=2 ";
if(report[0][i].equals("127")) quer2=" and stud.year=3 ";
if(report[0][i].equals("128")) quer2=" and stud.year=4 ";


if(act==0)	
quer1="(SELECT sxcce.dbo.stud.rollno FROM  sxcce.dbo.stud,sxcce.dbo.student "
+" WHERE sxcce.dbo.stud.rollno = sxcce.dbo.student.rollno "+quer2+"  and departmentno not in(7,30) and sxcce.dbo.stud.academicyear = '"+ accyear +"' group by sxcce.dbo.stud.rollno)";
else
quer1="(SELECT sxcce.dbo.stud.rollno FROM  sxcce.dbo.stud,sxcce.dbo.student "
+" WHERE sxcce.dbo.stud.rollno = sxcce.dbo.student.rollno "+quer2+"  and departmentno not in(7,30) AND sxcce.dbo.student.active=1 and sxcce.dbo.stud.academicyear = '"+ accyear +"' group by sxcce.dbo.stud.rollno)";


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




stmt.close();
connMgr.freeConnection("accounts",con);
}catch(Exception e){return count+quer+e.toString();}

return count+"suc";
}


public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}


}


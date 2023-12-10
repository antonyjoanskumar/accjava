package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
public class studentselect
{
String rollno="";
String quer="";
int year=0;
String name="";
String academicyear="";
String department="";
String crollno[] = new String[200];
String cname[] = new String[200];
int semester=0;
int ccount=0;
int dno=0;
int id=0;

String quesname="";
int quesdno=0;
int quesyear=0;
int queslink=0;

public int sem()
{int found=0;
	String quer="";
quer="select * from fineviewidentify";
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	academicyear=rs.getString(1);
	semester=rs.getInt(2);
}
rs.close();
int semesterno=0;
if(semester==2)
   {
   if(year==1)semesterno=2;
   else if(year==2)semesterno=4;
   else if(year==3)semesterno=6;
   else if(year==4)semesterno=8;
   }
else if(semester==1)
   {
   if(year==1)semesterno=1;
   else if(year==2)semesterno=3;
   else if(year==3)semesterno=5;
   else if(year==4)semesterno=7;
   }   

rs = stmt.executeQuery("select id from subjectidentify where academicyear='"+academicyear+"' and dno="+dno+" and semester="+semesterno);
if(rs.next())
{
	id=rs.getInt(1);
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
return found; 
}




public int classselect()
{int found=0;
	String quer="";
quer="select rollno,name from stud where academicyear in "+
"(select academicyear from academicyear) and department="+dno+" and year="+year;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
	crollno[ccount]=rs.getString(1);
	cname[ccount]=rs.getString(2);
	ccount++;
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
return found; 
}



public int View()
{
	int found=0;
	name="";
quer="select rollno,name,year,dname,academicyear,dno from stud,department where "+
" rollno="+rollno+" and department=dno and academicyear=(select academicyear from academicyear)";
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	rollno=rs.getString(1);
	name=rs.getString(2);
	year=rs.getInt(3);
	department=rs.getString(4);
	academicyear=rs.getString(5);	
	dno=rs.getInt(6);
	found=1;
}
rs.close();

if(found==0)
{
quer="select rollno,name,year,dname,academicyear,dno from stud,department where "+
" rollno="+rollno+" and department=dno order by year desc";
rs = stmt.executeQuery(quer);
if(rs.next())
{
	rollno=rs.getString(1);
	name=rs.getString(2);
	year=rs.getInt(3);
	department=rs.getString(4);
	academicyear=rs.getString(5);	
	dno=rs.getInt(6);
	found=1;
}
rs.close();
	
}
int ff=0;

rs = stmt.executeQuery("select * from generalfeedbackquestionselect");
if(rs.next())
{
	quesname=rs.getString(1);
	quesdno=rs.getInt(2);
	quesyear=rs.getInt(3);
	ff=1;
}
rs.close();

if(ff==1)
   {sem();
quer="select * from generalfeedbackenteredornot where rollno="+rollno+" and id="+id+" and quesname='"+quesname+"'";
	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
		ff=0;
	}
	rs.close();


   }

if(quesdno==dno && quesyear==year)queslink=1;

if(quesdno==0 && quesyear==year)queslink=1;

if(quesdno==dno && quesyear==0)queslink=1;

if(quesdno==0 && quesyear==0)queslink=1;

if(ff==0)queslink=0;


stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
return found; 
}



public int View1()
{int found=0;

quer="select rollno,name,year,dname,academicyear,dno from stud,department where "+
" rollno="+rollno+" and department=dno";
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance(); 
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	rollno=rs.getString(1);
	name=rs.getString(2);
	year=rs.getInt(3);
	department=rs.getString(4);
	academicyear=rs.getString(5);	
	dno=rs.getInt(6);
	found=1;
}
rs.close();

int ff=0;

rs = stmt.executeQuery("select * from generalfeedbackquestionselect");
if(rs.next())
{
	quesname=rs.getString(1);
	quesdno=rs.getInt(2);
	quesyear=rs.getInt(3);
	ff=1;
}
rs.close();

if(ff==1)
   {sem();
quer="select * from generalfeedbackenteredornot where rollno="+rollno+" and id="+id+" and quesname='"+quesname+"'";
	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
		ff=0;
	}
	rs.close();


   }

if(quesdno==dno && quesyear==year)queslink=1;

if(quesdno==0 && quesyear==year)queslink=1;

if(quesdno==dno && quesyear==0)queslink=1;

if(quesdno==0 && quesyear==0)queslink=1;

if(ff==0)queslink=0;


stmt.close();
//con.close();
connMgr.freeConnection("xavier",con);

}catch(Exception e){}
//finally{connMgr.freeConnection("xavier",con);}
return found; 
}

  
public void setdepartment(String department) 
{ 
this.department = department; 
} 
public String getdepartment() 
{ 
return this.department; 
} 
public void setname(String name) 
{ 
this.name = name; 
} 
public String getname() 
{ 
return this.name; 
} 
public void setrollno(String rollno) 
{ 
this.rollno = rollno; 
} 
public String getrollno() 
{ 
return this.rollno; 
} 
public void setyear(int year) 
{ 
this.year = year; 
} 
public int getyear() 
{ 
return this.year; 
} 

public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 

public void setquer(String quer) 
{ 
this.quer = quer; 
} 
public String getquer() 
{ 
return this.quer; 
} 


public void setdno(int dno) 
{ 
this.dno = dno; 
} 
public int getdno() 
{ 
return this.dno; 
} 


public void setid(int id) 
{ 
this.id = id; 
} 
public int getid() 
{ 
return this.id; 
} 

public void setccount(int ccount) 
{ 
this.ccount = ccount; 
} 
public int getccount() 
{ 
return this.ccount; 
} 

public void setsemester(int semester) 
{ 
this.semester = semester; 
} 
public int getsemester() 
{ 
return this.semester; 
} 


public void setqueslink(int queslink) 
{ 
this.queslink = queslink; 
} 
public int getqueslink() 
{ 
return this.queslink; 
} 



 public void setcname(String[] cname) 
{ 
this.cname = cname; 
} 
public String[] getcname() 
{ 
return this.cname; 
} 

public void setcrollno(String[] crollno) 
{ 
this.crollno = crollno; 
} 
public String[] getcrollno() 
{ 
return this.crollno; 
} 



}


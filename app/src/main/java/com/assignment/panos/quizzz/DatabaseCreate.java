package com.assignment.panos.quizzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCreate extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Quizzz.db";
    private static final String TABLE_QUEST="Quest";
    private static final String QUESTION = "Question" ;
    private static final String OPTIONA = "optiona" ;
    private static final String OPTIONB = "optionb" ;
    private static final String OPTIONC = "optionc" ;
    private static final String OPTIOND = "optiond" ;
    private static final String ANSWER = "answer" ;

    SQLiteDatabase dbase;
    public DatabaseCreate(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        db.execSQL("create table if not exists Quest(QID int primary key, Question varchar(500) not null, optiona varchar(50) not null, optionb varchar(50) not null, optionc varchar(50) not null, optiond varchar(50) not null, answer varchar(50) not null)");
        addQuestions();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Quest");
        onCreate(db);
    }
    private void addQuestions()
    {
        /*Questions https://www.easytutorial.in/question/6765*/
        Question q1=new Question(1,"Which among the following brand is most influential brand in India?","Microsoft", "Google","Amazon", "Facebook", "Google");
        this.addQuestion(q1);
        Question q2=new Question(2,"Who among the following is the newly elected FIFA president?","Prince Ali of Jordan","Sheikh Salman","Gianni Infantino","Jerome Champagne","Gianni Infantino");
        this.addQuestion(q2);
        Question q3=new Question(3,"12th South Asian Games held at?","Bengaluru","Hyderabad","Guwahati and Shillong","New Delhi","Guwahati and Shillong" );
        this.addQuestion(q3);
        Question q4=new Question(4,"Who has won the FIFA world player award for 2015?","Cristiano Ronaldo","Lionel Messi","Neymar","Luis Suárez","Lionel Messi");
        this.addQuestion(q4);
        Question q5=new Question(5,"Who was the founder of the City of Agra?","Ala-ud-din Khalji","Muhammad Tughlaq","Firoz Tughlaq","Sikandar Lodi","Sikandar Lodi");
        this.addQuestion(q5);
        Question q6=new Question(6,"The members of the Rajya Sabha are elected by ________","the people","Lok Sabha","elected members of the legislative assembly","elected members of the legislative council","elected members of the legislative assembly");
        this.addQuestion(q6);
        Question q7=new Question(7,"The power to decide an election petition is vested in the________","Parliament","Supreme Court","High courts","Election Commission","High courts");
        this.addQuestion(q7);
        Question q8=new Question(8,"The present Lok Sabha is the________","13th Lok Sabha","14th Lok Sabha","15th Lok Sabha","16th Lok Sabha","16th Lok Sabha");
        this.addQuestion(q8);
        Question q9=new Question(9,"The members of Lok Sabha hold office for a term of _______", "4 years", "5 years", "6 years","3 years","5 years" );
        this.addQuestion(q9);

    }
    public void addQuestion(Question quest) {
        ContentValues values = new ContentValues();
        values.put(QUESTION, quest.getQUESTION());
        values.put(ANSWER, quest.getANSWER());
        values.put(OPTIONA, quest.getOPTA());
        values.put(OPTIONB, quest.getOPTB());
        values.put(OPTIONC, quest.getOPTC());
        values.put(OPTIOND, quest.getOPTD());
        dbase.insert("Quest", null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setQUESTION(cursor.getString(1));
                quest.setOPTA(cursor.getString(2));
                quest.setOPTB(cursor.getString(3));
                quest.setOPTC(cursor.getString(4));
                quest.setOPTD(cursor.getString(5));
                quest.setANSWER(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        return quesList;
    }
}
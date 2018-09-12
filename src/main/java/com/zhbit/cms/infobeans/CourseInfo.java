package com.zhbit.cms.infobeans;


import com.alibaba.fastjson.annotation.JSONField;
import com.zhbit.cms.infobeans.beaninterface.BasicInterface;

public class CourseInfo implements BasicInterface {

    private String CourseName;
    private String ClassName;
    private int Week=-1;
    private int ClassWeek=-1;
    private int ClassTime=-1;
    private String Teacher;
    private int RID=-1;
    private int StuCount=0;
    private int CurTermID=0;
    private int CourseID=-1;

    public CourseInfo(){}

    @Override
    public boolean isNotNull(){
        return (CourseName!=null && ClassName!=null && Week!=-1 && ClassWeek!=-1 && ClassTime!=-1
                && Teacher!=null && RID!=-1 && StuCount!=-1 && CurTermID!=-1 && CourseID!=-1);
    }

    @JSONField(name = "course_id")
    public int getCourseID() {
        return CourseID;
    }

    @JSONField(name = "course_id")
    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    @JSONField(name = "course_name")
    public String getCourseName() {
        return CourseName;
    }

    @JSONField(name = "course_name")
    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    @JSONField(name = "class_name")
    public String getClassName() {
        return ClassName;
    }

    @JSONField(name = "class_name")
    public void setClassName(String className) {
        ClassName = className;
    }

    @JSONField(name = "week")
    public int getWeek() {
        return Week;
    }

    @JSONField(name = "week")
    public void setWeek(int week) {
        Week = week;
    }

    @JSONField(name = "class_week")
    public int getClassWeek() {
        return ClassWeek;
    }

    @JSONField(name = "class_week")
    public void setClassWeek(int classWeek) {
        ClassWeek = classWeek;
    }

    @JSONField(name = "class_time")
    public int getClassTime() {
        return ClassTime;
    }

    @JSONField(name = "class_time")
    public void setClassTime(int classTime) {
        ClassTime = classTime;
    }

    @JSONField(name = "teacher")
    public String getTeacher() {
        return Teacher;
    }

    @JSONField(name = "teacher")
    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    @JSONField(name = "stu_count")
    public int getStuCount() {
        return StuCount;
    }

    @JSONField(name = "stu_count")
    public void setStuCount(int stuCount) {
        StuCount = stuCount;
    }

    @JSONField(name = "cur_term_id")
    public int getCurTermID() {
        return CurTermID;
    }

    @JSONField(name = "cur_term_id")
    public void setCurTermID(int curTermID) {
        CurTermID = curTermID;
    }

    @JSONField(name = "rid")
    public int getRID() {
        return RID;
    }

    @JSONField(name = "rid")
    public void setRID(int RID) {
        this.RID = RID;
    }

}

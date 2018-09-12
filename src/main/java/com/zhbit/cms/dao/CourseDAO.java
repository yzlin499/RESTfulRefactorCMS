package com.zhbit.cms.dao;

import com.zhbit.cms.infobeans.CourseInfo;
import com.zhbit.cms.infobeans.FakerMap;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseDAO {

    int createCourse(CourseInfo courseInfo);

    @Select("EXEC F_ModifyCourse @CID=#{id},@CourseName=#{CourseName},@ClassName=#{ClassName},@Week=#{Week}," +
            "@ClassWeek=#{ClassWeek},@ClassTime=#{ClassTime},@Teacher=#{Teacher},@RID=#{RID},@StuCount=#{StuCount}," +
            "@CurTermID=#{CurTermID}")
    void updateCourse(CourseInfo courseInfo,@Param("id")String id);

    int deleteCourse(String id);

    CourseInfo selectCourseByID(int id);

    List<CourseInfo> selectCourse(FakerMap fakerMap);
}

--用于筛选出符合条件的课程
CREATE PROC dbo.F_FilterCourse(
	@Week TINYINT = 128,				--默认查找所有的星期(0-6),0表示星期一,默认为查找整周
	@ClassWeek INTEGER = 1048575,		--默认查找全部周次(1-20)
	@ClassTime SMALLINT = 32767,		--默认查找全部节次(1-14)
	@RID SMALLINT=0,					--默认查找全部教室
	@TermID TINYINT=0					--默认查找全局的学期配置
) AS BEGIN
	SET NOCOUNT ON
	IF (@TermID=0)
		SELECT @TermID=CONVERT(INT,GValue) FROM dbo.GlobalInfo WHERE GName=N'CurTermID'
	ELSE BEGIN
		IF NOT EXISTS(SELECT * FROM dbo.TermInfo WHERE TID=@TermID) BEGIN
			RAISERROR(N'找不到指定的学期',16,1);
			RETURN
		END
	END
	
	SELECT CID AS CourseID,CName AS CourseName,CClass AS ClassName,CWeek AS [Week],
			CClWeek AS ClassWeek,CClTime AS ClassTime,CTeacher AS Teacher,RName AS Room,
			CStuCount AS StuCount,TID AS CurTermID,CourseInfo.RID
		 FROM dbo.CourseInfo,dbo.RoomInfo WHERE (CWeek=@Week OR @Week=128) AND (CClWeek&@ClassWeek)<>0 AND 
		(CClTime&@ClassTime)<>0 AND dbo.CourseInfo.RID=dbo.RoomInfo.RID AND (dbo.RoomInfo.RID=@RID OR @RID=0) AND TID=@TermID
END
go

